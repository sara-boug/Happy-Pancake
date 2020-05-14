package com.example.App.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.App.domain.RegistrationForm;
import com.example.App.repository.JdbcUser;

@Controller
@RequestMapping("/signup")
public class UserRegistration {
	private JdbcUser jdbcUser; 
	  private PasswordEncoder passwordEncoder; 
	
	@Autowired
	public UserRegistration(JdbcUser jdbcUser ,PasswordEncoder passwordEncoder  ) { 
		this.jdbcUser= jdbcUser;
		this.passwordEncoder=passwordEncoder;
		
	}
	
	@GetMapping
	public String register(Model model) { 
		model.addAttribute("form" , new RegistrationForm());
		return "signUp"; 
	}
	
	@PostMapping
   public String processRegistration(@ModelAttribute("form") RegistrationForm form)   {
		if(this.jdbcUser.existEmail(form.getemail())) { 
			return "redirect:/signup?error= email already exist s!"; 
 		}else { 
	 		this.jdbcUser.save(form.toUser( passwordEncoder)); 
	     	   return "redirect:/login"; 

		}
   }
}
