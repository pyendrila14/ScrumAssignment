package com.restrosectives.scrumceremony.service;

import com.restrosectives.scrumceremony.entity.Feedback;
import com.restrosectives.scrumceremony.entity.Retrospective;
import com.restrosectives.scrumceremony.repository.ScrumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public interface ScrumService {

    public Retrospective saveRestrospective(Retrospective retrospective);

    public Feedback saveFeedbacks(Feedback feedback);

    public Feedback updateFeedback(String id, Feedback feedback);

    public List<Retrospective> getAllRetrospectives();

    public Page<Retrospective> findRetrospectiveWithPagination(int pageNumber, int pageSize);

    public List<Retrospective> findRetrospectiveWithDateSearch(String date);
}
