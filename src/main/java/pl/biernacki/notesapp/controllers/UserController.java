package pl.biernacki.notesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.biernacki.notesapp.entity.Note;
import pl.biernacki.notesapp.entity.User;
import pl.biernacki.notesapp.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "register";
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
            return "redirect:/login";
        }


    }
    @GetMapping("/home")
    public String getMainPage( Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        model.addAttribute("notes",  user.getNotesList());


        return "afterLogin";
    }
    @GetMapping("/profile")
    public String getProfile( Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        model.addAttribute("user",  user);

        return "profile";
    }
    @GetMapping("/delete-account")
    public String deleteAccount(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        userRepository.delete(user);

        return "redirect:/logout";
    }

}
