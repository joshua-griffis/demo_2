package com.example.demo.personal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    private final PersonalService personalService;

    @Autowired
    PersonalRepository personalRepository;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping
    List<Personal> getPersonal() {
        return personalRepository.findAll();
    }

    @PostMapping
    Personal createPersonal(@RequestBody Personal personal) {
        return personalRepository.save(personal);
    }

    @DeleteMapping(path = "{personalId}")
    public void deletePersonal(
            @PathVariable("personalId") Long personalId) {
        personalService.deletePersonal(personalId);
    }

    @PutMapping(path = "{personalId}")
    public void updatePersonal(
            @PathVariable("personalId") Long personalId,
            @RequestParam(required = false) String personal) {
        personalService.updatePersonal(personalId, personal);
    }
}
