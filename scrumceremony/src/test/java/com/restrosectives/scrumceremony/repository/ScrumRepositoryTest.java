package com.restrosectives.scrumceremony.repository;


import com.restrosectives.scrumceremony.entity.Retrospective;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataMongoTest
@ActiveProfiles("test")
public class ScrumRepositoryTest {

    @MockBean
    private ScrumRepository mockscrumRepository;

    @Test
    public void findByIdTest() {
        Retrospective dummydata = new Retrospective("123","data10", "data", "12/12/2025",new ArrayList<String>(Arrays.asList("Viktor", "Gareth", "Mike")),
                List.of());

        when(mockscrumRepository.findById(any())).thenReturn(Optional.of(dummydata));
        Optional<Retrospective> retrospective = mockscrumRepository.findById("123");

        assertEquals("data10",retrospective.get().getName());
    }
}
