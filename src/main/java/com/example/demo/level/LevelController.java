package com.example.demo.level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/level")
public class LevelController {

    private final LevelService levelService;

    @Autowired
    LevelRepository levelRepository;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    List<Level> getLevel() {return levelRepository.findAll();}

    @PostMapping
    Level createLevel(@RequestBody Level level) {
        return levelRepository.save(level);
    }

    @DeleteMapping(path = "{levelId}")
    public void deleteLevel(
            @PathVariable("levelId") Long levelId) {
        levelService.deleteLevel(levelId);
    }

    @PutMapping(path = "{levelId}")
    public void updateLevel(
            @PathVariable("levelId") Long levelId,
            @RequestParam(required = false) String level) {
        levelService.updateLevel(levelId, level);
    }

}
