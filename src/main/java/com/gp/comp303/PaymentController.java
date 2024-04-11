package com.gp.comp303;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	@GetMapping("/paymentForm")
    public String showPaymentForm() {
        return "paymentForm"; // Return the Payment Form page
    }
	
	@Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProgramRepository programRepository;

    @PostMapping("/processPayment")
    public String processPayment(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        Long programId = (Long) session.getAttribute("selectedProgramId");

        if (studentId == null || programId == null) {
            // Error handling: Student ID or program ID is not in the session
            model.addAttribute("errorMessage", "An error occurred while processing the payment, please try again.");
            return "payment/processPayment";
        }

        // Inquire Student and Program Entities
        Student student = studentRepository.findById(studentId)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));
        Program program = programRepository.findById(programId)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid program ID"));

        
        Double programFee = program.getFee();
        
        // Create dummy payment information
        String confirmationNumber = "ABC123456"; // Example Payment Verification Number
        double amountPaid = programFee; // Payment amount
        
        // Creating and Setting Enrollment Objects
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setProgram(program);
        enrollment.setStartDate(LocalDate.now());
        enrollment.setAmountPaid(amountPaid);
        enrollment.setStatus("SUCCESS");

        // Save Enrollment Objects
        enrollmentRepository.save(enrollment);

        // Add payment and registration information to the model
        model.addAttribute("confirmationNumber", confirmationNumber);
        model.addAttribute("amountPaid", amountPaid);
        model.addAttribute("message", "Your payment has been processed successfully.");

        return "confirmation";
    }


	

	


    
    

    

}
