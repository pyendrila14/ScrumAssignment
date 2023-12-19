package com.restrosectives.scrumceremony.service;

import com.restrosectives.scrumceremony.entity.Feedback;
import com.restrosectives.scrumceremony.entity.Retrospective;
import com.restrosectives.scrumceremony.exception.FeedBackNotFoundException;
import com.restrosectives.scrumceremony.repository.FeedBackRepository;
import com.restrosectives.scrumceremony.repository.ScrumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScrumServiceImpl implements ScrumService {
    @Autowired
    private ScrumRepository scrumRepository;

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Override
    public Retrospective saveRestrospective(Retrospective retrospective) {
        return scrumRepository.save(retrospective);
    }

    @Override
    public Feedback saveFeedbacks(Feedback feedback) {
        return feedBackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(String id, Feedback feedback) {
        Optional<Feedback> feedbackData = feedBackRepository.findById(id);
        if (feedbackData.isPresent()) {
            Feedback feedbackUpdate = feedbackData.get();
            feedbackUpdate.setId(id);
            feedbackUpdate.setName(feedback.getName());
            feedbackUpdate.setFeedbackBody(feedback.getFeedbackBody());
            feedbackUpdate.setFeedbackType(feedback.getFeedbackType());
            feedbackUpdate.setRetrospectiveId(feedback.getRetrospectiveId());
            feedBackRepository.save(feedbackUpdate);
            return feedbackUpdate;
        } else {
            throw new FeedBackNotFoundException("Feedback not found with id : " + id);
        }
    }

    @Override
    public List<Retrospective> getAllRetrospectives() {
        List<Retrospective> getAllData = scrumRepository.findAll();
        getAllData.forEach(retrospective -> {
            List<Feedback> feedbackData = feedBackRepository.findAll().stream().filter(feedback ->
                    feedback.getRetrospectiveId().equals(retrospective.getId())).toList();
            retrospective.setFeedback(feedbackData);
        });
        return getAllData;
    }


    @Override
    public Page<Retrospective> findRetrospectiveWithPagination(int pageNumber, int pageSize) {
        return scrumRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public List<Retrospective> findRetrospectiveWithDateSearch(String date) {
       List<Retrospective>listdata=scrumRepository.findAll();
        return listdata.stream().filter(retrospective ->
                retrospective.getDate().equals(date.replace('-','/'))).toList();
    }




}
