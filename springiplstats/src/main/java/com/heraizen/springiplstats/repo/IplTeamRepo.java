package com.heraizen.springiplstats.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heraizen.springiplstats.domain.Team;

public interface IplTeamRepo extends MongoRepository<Team, String> {

	Team findByLabel(String label);

}
