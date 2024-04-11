package com.gp.comp303;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.jc.assignment2.Enrollment;
import com.jc.assignment2.EnrollmentRepository;

@Controller
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("/enroll")
    public ModelAndView showEnrollmentForm() {
        ModelAndView mav = new ModelAndView("enrollment");
        mav.addObject("enrollment", new Enrollment());
        return mav;
    }

    @PostMapping("/enroll")
    public String processEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
        return "confirmation"; 
    }
}

