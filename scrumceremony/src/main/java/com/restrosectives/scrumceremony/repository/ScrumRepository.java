package com.restrosectives.scrumceremony.repository;

import com.restrosectives.scrumceremony.entity.Feedback;
import com.restrosectives.scrumceremony.entity.Retrospective;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrumRepository extends MongoRepository<Retrospective, String> { }
