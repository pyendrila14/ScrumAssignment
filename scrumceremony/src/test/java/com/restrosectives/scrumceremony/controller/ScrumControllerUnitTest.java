package com.restrosectives.scrumceremony.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restrosectives.scrumceremony.entity.Feedback;
import com.restrosectives.scrumceremony.entity.Retrospective;
import com.restrosectives.scrumceremony.service.ScrumService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ScrumController.class)
@AutoConfigureWebTestClient
public class ScrumControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
     private ScrumService mockscrumService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateNewRetrospective() throws Exception {


        Retrospective dummydata = new Retrospective("123","chiku10", "data", "12/12/2025",new ArrayList<String>(Arrays.asList("Viktor", "Gareth", "Mike")),
                List.of());

        String convertedList = objectMapper.writeValueAsString(dummydata);
        when(mockscrumService.saveRestrospective(any())).thenReturn(dummydata);


        ResultActions response =mockMvc.perform(post("/api/retrospective/addRetrospective").
                        contentType(MediaType.APPLICATION_JSON).content(convertedList).accept(MediaType.APPLICATION_JSON));



        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(content().string("Retrospective saved with id123"));

    }


    @Test
    void shouldCreateNewFeedback() throws Exception {


        Feedback dummyfeedbackdata = new Feedback("1234", "chiku10", "data", "positive", "123");

        String convertedList = objectMapper.writeValueAsString(dummyfeedbackdata);
        when(mockscrumService.saveFeedbacks(any())).thenReturn(dummyfeedbackdata);


        ResultActions response =mockMvc.perform(post("/api/retrospective/saveFeedBacks").
                contentType(MediaType.APPLICATION_JSON).content(convertedList).accept(MediaType.APPLICATION_JSON));



        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(content().string("Feedback saved with id1234"));

    }

    @Test
    void shouldUpdateNewFeedback() throws Exception {


        Feedback dummyfeedbackdata = new Feedback("1234", "chiku10", "data", "positive", "123");

        String id = "1234";
        String convertedList = objectMapper.writeValueAsString(dummyfeedbackdata);
        when(mockscrumService.updateFeedback(any(),any())).thenReturn(dummyfeedbackdata);


        ResultActions response =mockMvc.perform(put("/api/retrospective/updateFeedBack/{id}",id).
                contentType(MediaType.APPLICATION_JSON).content(convertedList).accept(MediaType.APPLICATION_JSON));



        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(content().string("Feedback updated with id1234"));

    }

    @Test
    void shouldGetAllRetrospective() throws Exception {


        Retrospective dummydata = new Retrospective("123","chiku10", "data", "12/12/2025",new ArrayList<String>(Arrays.asList("Viktor", "Gareth", "Mike")),
                List.of());

        when(mockscrumService.getAllRetrospectives()).thenReturn(List.of(dummydata));


        ResultActions response =mockMvc.perform(get("/api/retrospective/getAllRetrospectives"));
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(dummydata.getName()));

    }
    @Test
    void shouldGetPagination() throws Exception {

        var data = mock(Page.class);
        when(mockscrumService.findRetrospectiveWithPagination(1,1)).thenReturn(data);

        ResultActions response =mockMvc.perform(get("/api/retrospective/pagination/{pageNumber}/{pageSize}",1,1));
        verify(mockscrumService).findRetrospectiveWithPagination(anyInt(),anyInt());


    }

    @Test
    void shouldGetPagination_error() throws Exception {

        Page<Retrospective> pagedResponse = new PageImpl<>(List.of());

        when(mockscrumService.findRetrospectiveWithPagination(anyInt(),anyInt())).thenReturn(pagedResponse);


        ResultActions response =mockMvc.perform(get("/api/retrospective/pagination/{pageNumber}/{pageSize}",1,1));
        response.andDo(print()).
                andExpect(status().is5xxServerError());

    }

    @Test
    void shouldGetAllRetrospectiveFilterByDate() throws Exception {

        String date = "12-12-2025";
        Retrospective dummydata = new Retrospective("123","chiku10", "data", "12/12/2025",new ArrayList<String>(Arrays.asList("Viktor", "Gareth", "Mike")),
                List.of());

        when(mockscrumService.findRetrospectiveWithDateSearch(any())).thenReturn(List.of(dummydata));


        ResultActions response =mockMvc.perform(get("/api/retrospective/searchWithDate/{date}",date));
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(dummydata.getName()));

    }



}
