package com.heraizen.springcorebrand.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heraizen.springcorebrand.domain.Team;

public interface IplStatRepo extends MongoRepository<Team, String> {

}
