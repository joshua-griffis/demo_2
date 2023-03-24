package com.example.demo.level;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LevelService {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public void deleteLevel(Long levelId) {
        boolean exists = levelRepository.existsById(levelId);
        if (!exists) {
            throw new IllegalStateException(
                    "access with id " + levelId + "does not exists");
        }
        levelRepository.deleteById(levelId);
    }

    @Transactional
    public void updateLevel(Long levelId,
                             String levelName
    ) {
        Level level = levelRepository.findById(levelId)
                .orElseThrow(() -> new IllegalStateException(
                        "level with id " + levelId + "does not exist"));

        if (levelName != null &&
                levelName.length() > 0 &&
                !Objects.equals(level.getLevel(), levelName)) {
            level.setLevel(levelName);
        }
    }
}
