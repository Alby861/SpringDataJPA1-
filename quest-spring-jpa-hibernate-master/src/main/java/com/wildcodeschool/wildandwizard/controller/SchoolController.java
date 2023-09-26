package com.wildcodeschool.wildandwizard.controller;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Controller
public class SchoolController {
    @Autowired
    private SchoolRepository schoolRepository;


    @GetMapping("/schools")
    public String getAll(Model model) {
    model.addAttribute("schools", schoolRepository.findAll());

        return "schools";
    }

    @GetMapping("/school")
    public String getSchool(Model model,  @RequestParam(required = false) Long id) {
        School school = new School();

        // TODO : find one school by id
        if (id != null) {
            Optional<School> schoolOptional = schoolRepository.findById(id);
            if (schoolOptional.isPresent()) {
              school = schoolOptional.get();
            } else {
                model.addAttribute("errorMessage", "School not found");
            }
        }
        model.addAttribute("school", school);
        return "school";
    }

    @PostMapping("/school")
    public String postSchool(@ModelAttribute School school) {

        schoolRepository.save(school);

        return "redirect:/schools";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {

        // TODO : delete a school
        schoolRepository.deleteById(id);

        return "redirect:/schools";
    }
}
