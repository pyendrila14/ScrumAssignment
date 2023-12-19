package com.restrosectives.scrumceremony.repository;

import com.restrosectives.scrumceremony.entity.Feedback;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataMongoTest
@ActiveProfiles("test")
public class FeedBackRepositoryTest {

    @MockBean
    private FeedBackRepository mockfeedBackRepository;

    @Test
    public void findByIdTest() {
       when(mockfeedBackRepository.findById(any())).thenReturn(Optional.of(new Feedback("1234", "data10", "data", "positive", "123")));
       Optional<Feedback> feedback = mockfeedBackRepository.findById("1234");

       assertEquals("data10",feedback.get().getName());
    }
}
