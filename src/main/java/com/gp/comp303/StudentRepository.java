package com.gp.comp303;

import org.springframework.data.repository.CrudRepository;
import com.gp.comp303.Student;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

	Optional<Student> findByUserName(String userName);



}

