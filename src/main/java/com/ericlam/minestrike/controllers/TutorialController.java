package com.ericlam.minestrike.controllers;

import com.ericlam.minestrike.dao.TutorialRepository;
import com.ericlam.minestrike.modals.Tutorial;
import com.ericlam.minestrike.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

    private final TutorialRepository tutorialRepository;

    @Autowired
    public TutorialController(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @GetMapping
    public Iterable<Tutorial> getTurorials() {
        return tutorialRepository.findAll();
    }

    @DeleteMapping("{id}")
    public Map deleteTurorial(@PathVariable int id) {
        boolean success;
        if (tutorialRepository.findById(id).isPresent()) {
            success = true;
            tutorialRepository.deleteById(id);
        } else {
            success = false;
        }
        return Utils.returnResult(success);
    }

    @PostMapping
    public Tutorial createTutorials(@RequestBody Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @PutMapping("{id}")
    public Tutorial updateTutorials(@RequestBody Tutorial edited, @PathVariable int id) {
        return tutorialRepository.findById(id).map(tutorial -> {
            tutorial.setTitle(edited.getTitle());
            tutorial.setType(edited.getType());
            tutorial.setContent(edited.getContent());
            return tutorialRepository.save(tutorial);
        }).orElseGet(() -> {
            edited.setId(id);
            return tutorialRepository.save(edited);
        });
    }
}
