package com.restrosectives.scrumceremony.controller;

import com.restrosectives.scrumceremony.entity.Feedback;
import com.restrosectives.scrumceremony.entity.Retrospective;
import com.restrosectives.scrumceremony.repository.ScrumRepository;
import com.restrosectives.scrumceremony.service.ScrumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/retrospective")
public class ScrumController
{

    @Autowired
    ScrumService scrumservice;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PostMapping("/addRetrospective")
    public ResponseEntity<String> saveRetrospective(@RequestBody Retrospective retrospective) {

        try {
            if(retrospective.getDate().isEmpty() || retrospective.getParticipants().isEmpty()){
                return new ResponseEntity<>("Date or Participants list is missing.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                Retrospective retrospectivesaved = scrumservice.saveRestrospective(retrospective);
                LOG.info("Retrospective saved to the database" + retrospectivesaved);
                return new ResponseEntity<String>("Retrospective saved with id" + retrospectivesaved.getId(), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save Retrospective", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/saveFeedBacks")
    public ResponseEntity<String> saveFeedbacks(@RequestBody Feedback feedback) {

        try {
            Feedback feedbacksaved=scrumservice.saveFeedbacks(feedback);
            LOG.info("Feedback saved to the database");
            return new ResponseEntity<String>("Feedback saved with id" +feedbacksaved.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save Feedbback", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateFeedBack/{id}")
    public ResponseEntity<String> updateFeedback(@PathVariable("id") String id,@RequestBody Feedback feedback) {

            Feedback feedbackupdated=scrumservice.updateFeedback(id,feedback);
            LOG.info("Feedback updated in the database"+feedbackupdated);
            return new ResponseEntity<String>("Feedback updated with id" +feedbackupdated.getId(), HttpStatus.CREATED);


    }
    @GetMapping("/getAllRetrospectives")
    public ResponseEntity<List<Retrospective>> getRetrospectives() {
   try{
        List<Retrospective> allRetrospectives=scrumservice.getAllRetrospectives();
        LOG.info("Got the retrospectiveList from the database");
        return new ResponseEntity<>(allRetrospectives, HttpStatus.OK);
    } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }


    @GetMapping("/pagination/{pageNumber}/{pageSize}")
    private ResponseEntity<Page<Retrospective>> getRetrospectiveWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
        try {
            Page<Retrospective> allRetrospectives = scrumservice.findRetrospectiveWithPagination(pageNumber, pageSize);
            return new ResponseEntity<>(allRetrospectives, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchWithDate/{date}")
    private ResponseEntity<List<Retrospective>> getProductsWithPagination(@PathVariable String date) {
        try {
           List<Retrospective> allRetrospectives = scrumservice.findRetrospectiveWithDateSearch(date);
            return new ResponseEntity<>(allRetrospectives, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
