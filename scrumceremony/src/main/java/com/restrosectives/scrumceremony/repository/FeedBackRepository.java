package com.restrosectives.scrumceremony.repository;

import com.restrosectives.scrumceremony.entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedBackRepository extends MongoRepository<Feedback, String> {

}
