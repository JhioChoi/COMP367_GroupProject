package com.gp.comp303;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProgramController {
    
    @Autowired
    private ProgramRepository programRepository;

    @GetMapping("/programs")
    public String listPrograms(Model model) {
        model.addAttribute("programs", programRepository.findAll());
        return "programs";
    }

    @PostMapping("/registerProgram")
    public String registerProgram(@RequestParam("selectedProgramId") Long programCode, HttpSession session, RedirectAttributes redirectAttributes) {
        // Save the selected program ID to the session
        session.setAttribute("selectedProgramId", programCode);

        // The ID of the currently logged-in user is obtained from the session, 
        // and if not, error handling must be performed
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Unable to verify login status.");
            return "redirect:/login";
        }

        return "redirect:/payment/paymentForm"; // Redirect to Payment Page
    }

    
    
    
    

}
