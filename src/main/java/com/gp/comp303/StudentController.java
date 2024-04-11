package com.gp.comp303;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;
import com.gp.comp303.Student;
import com.gp.comp303.StudentRepository;

@Controller
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "index"; // index.html
    }
    
    @GetMapping("/edit")
    public String showEditProfileForm(@RequestParam("studentId") Long studentId, Model model) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            model.addAttribute("student", optionalStudent.get());
            return "editProfile";
        } else {
            return "redirect:/studentIdInput";
        }
    }
    
    @GetMapping("/studentIdInput")
    public String showStudentIdInputForm() {
        return "studentIdInput";
    }


    
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Error message");
            return "editProfile";
        }
        studentRepository.save(student);
        return "redirect:/";
    }


    
    @PostMapping("/register")
    public String registerStudent(Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            
            return "index";
        }
        studentRepository.save(student); 
        model.addAttribute("successMessage", "Your registeration has been successfully completed.");
        model.addAttribute("student", new Student()); 
        return "index"; 
    }
    
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session, Model model) {
        Optional<Student> studentOptional = studentRepository.findByUserName(userName);

        if (studentOptional.isEmpty()) {
            model.addAttribute("loginError", "ID does not exist.");
            return "index";
        }

        Student student = studentOptional.get();
        if (!student.getPassword().equals(password)) {
            model.addAttribute("loginError", "The password does not match.");
            return "index";
        }

        session.setAttribute("studentId", student.getStudentId());
        return "redirect:/program";
    }

}

