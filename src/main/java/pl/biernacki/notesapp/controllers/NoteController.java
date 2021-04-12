package pl.biernacki.notesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.biernacki.notesapp.entity.Note;
import pl.biernacki.notesapp.entity.User;
import pl.biernacki.notesapp.repository.NoteRepository;
import pl.biernacki.notesapp.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/add-note")
    public String addNoteView(Model model) {
        model.addAttribute("note",new Note());

        return "addNote";

    }

    @PostMapping("/add-note")
    public String addNote( Principal principal,@ModelAttribute("note") @Valid Note note, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) return "addNote";
        else {
            User user = userRepository.findByUsername(principal.getName());
            note.setUsers(user);
            Date currentUtilDate = new Date();
            note.setDate(currentUtilDate);
            noteRepository.save(note);
            return "redirect:/home";
        }
    }

    @GetMapping("/note/{id}")
    public String viewOneNote(@PathVariable Long id, Model model) {
        Note note = noteRepository.getOne(id);
        model.addAttribute("note",note);
        return "note";

    }
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        Note note = noteRepository.getOne(id);
        noteRepository.delete(note);
        return "redirect:/home";

    }
    @GetMapping("/edit-note/{id}")
    public String editNoteProces(@PathVariable Long id,Model model) {
        Note note = noteRepository.getOne(id);
        model.addAttribute("note",note);
        return "editNote";

    }
    @PostMapping("/edit-note")
    public String editNoteProcess(@RequestParam(value = "editDateCheckbox", required = false) String checkboxValue,Principal principal,@ModelAttribute("note") @Valid Note note, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "editNote";
        else {
            User user = userRepository.findByUsername(principal.getName());
            note.setUsers(user);
            if (checkboxValue != null) {
                Date currentUtilDate = new Date();
                note.setDate(currentUtilDate);
            } else {
                note.setDate(noteRepository.findById(note.getId()).get().getDate());
            }
            noteRepository.save(note);
            return "redirect:/home";
        }
    }
}
