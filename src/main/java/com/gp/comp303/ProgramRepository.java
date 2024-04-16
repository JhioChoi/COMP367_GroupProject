package com.gp.comp303;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProgramRepository extends CrudRepository<Program, Long> {
	
	    Optional<Program> findByProgramName(String programName);

}
