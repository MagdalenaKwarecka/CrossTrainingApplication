package com.kvarek.registration;

import com.kvarek.registration.validation.PersonValidator;
import com.kvarek.workout.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationFormController {

    private final PersonValidator personValidator;

    public RegistrationFormController(PersonValidator personValidator) {
        this.personValidator = personValidator;
    }


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") Person userForm) {

        personValidator.coachMessage(userForm);


      /*  userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());*/

        return "result";

    }
}
