package com.restrosectives.scrumceremony.service;

import com.restrosectives.scrumceremony.entity.Feedback;
import com.restrosectives.scrumceremony.entity.Retrospective;
import com.restrosectives.scrumceremony.exception.FeedBackNotFoundException;
import com.restrosectives.scrumceremony.repository.FeedBackRepository;
import com.restrosectives.scrumceremony.repository.ScrumRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ScrumServiceImplUnitTest {

    @Autowired
    private ScrumService scrumService;

    @MockBean
    private ScrumRepository scrumRepository;
    @MockBean
    private FeedBackRepository feedBackRepository;


    @Test
    public void addRetrospectiveUnitTest() {
        Retrospective dummydata = new Retrospective("123","data10", "data", "12/12/2025",new ArrayList<String>(Arrays.asList("Viktor", "Gareth", "Mike")),
                List.of());

        when(scrumRepository.save(any())).thenReturn(dummydata);

       Retrospective dummyresponse = scrumService.saveRestrospective(dummydata);
        assertEquals("123", dummyresponse.getId());
    }
    @Test
    public void addFeedbackUnitTest() {
        Feedback dummyfeedbackdata = new Feedback("1234", "data10", "data", "positive", "123");
        when(feedBackRepository.save(any())).thenReturn(dummyfeedbackdata);
        Feedback dummyresponse = scrumService.saveFeedbacks(dummyfeedbackdata);
        assertEquals("1234", dummyresponse.getId());
    }


    @Test
    public void updateFeedbackUnitTest() {
        Feedback dummyfeedbackdata = new Feedback("1234", "data10", "data", "positive", "123");

        when(feedBackRepository.save(any())).thenReturn(dummyfeedbackdata);
        when(feedBackRepository.findById(any())).thenReturn(Optional.of(dummyfeedbackdata));
        Feedback dummyResponse = scrumService.updateFeedback("1234",new Feedback("1234", "data11", "data", "positive", "123"));

        assertNotNull(dummyResponse);
        assertNotNull(dummyResponse.getId());
        assertEquals("data11", dummyResponse.getName());

    }

    @Test
    public void updateFeedbackUnitTest_erroe() {
        Feedback dummyfeedbackdata = new Feedback("1234", "data10", "data", "positive", "123");

        when(feedBackRepository.save(any())).thenReturn(dummyfeedbackdata);
        when(feedBackRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> scrumService.updateFeedback("1234",new Feedback("1234", "data11", "data", "positive", "123")));

        assertEquals("Feedback not found with id : 1234", exception.getMessage());


    }

    @Test
    public void getAllRetrospectiveFromDB() {
        Feedback dummyfeedbackdata = new Feedback("1234", "data10", "data", "positive", "123");
        Retrospective dummydata = new Retrospective("123","data10", "data", "12/12/2025",new ArrayList<String>(Arrays.asList("Viktor", "Gareth", "Mike")),
                List.of(dummyfeedbackdata));
        when(scrumRepository.findAll()).thenReturn(List.of(dummydata));

       List<Retrospective> listdata=scrumService.getAllRetrospectives();
       listdata.get(0).setFeedback(List.of(dummyfeedbackdata));
        verify(scrumRepository, times(1)).findAll();
        assertNotNull(listdata);
        assertEquals("data10", listdata.get(0).getName());
        assertEquals(1, listdata.get(0).getFeedback().size());

    }

    @Test
    public void getAllRetrospectiveWithDAteFilter() {

        Retrospective dummydata = new Retrospective("123","data10", "data", "12/12/2025",new ArrayList<String>(Arrays.asList("Viktor", "Gareth", "Mike")),
                List.of());
        when(scrumRepository.findAll()).thenReturn(List.of(dummydata));

        List<Retrospective> listdata=scrumService.findRetrospectiveWithDateSearch("12-12-2025");
        assertNotNull(listdata);
        assertEquals("data10", listdata.get(0).getName());

    }

    @Test
    public void getAllRetrospectiveWithPagination() {

        var mockdata = mock(Page.class);
        when(scrumRepository.findAll(any(PageRequest.class))).thenReturn(mockdata);

        scrumService.findRetrospectiveWithPagination(1, 1);


        verify(scrumRepository).findAll(any(PageRequest.class));
        verifyNoMoreInteractions(scrumRepository);
    }



}

