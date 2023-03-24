package com.example.demo.access;

import com.example.demo.level.Level;
import com.example.demo.level.LevelRepository;
import com.example.demo.personal.Personal;
import com.example.demo.personal.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access")
public class AccessController {

    private final AccessService accessService;

    @Autowired
    AccessRepository accessRepository;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping
    List<Access> getAccess() {
        return accessRepository.findAll();
    }

    @PostMapping
    Access createAccess(@RequestBody Access access) {
        return accessRepository.save(access);
    }

    @DeleteMapping(path = "{accessId}")
    public void deleteAccess(
            @PathVariable("accessId") Long accessId) {
        accessService.deleteAccess(accessId);
    }

    @PutMapping(path = "{accessId}")
    public void updateAccess(
            @PathVariable("accessId") Long accessId,
            @RequestParam(required = false) String access) {
        accessService.updateAccess(accessId, access);
    }

    @PutMapping("/{accessId}/personal/{personalId}")
    Access registerPersonalToAccess(
            @PathVariable Long accessId,
            @PathVariable Long personalId
    ) {
        Access access = accessRepository.getOne(accessId);
        Personal personal = personalRepository.getOne(personalId);
        access.registerPersonal(personal);
        return accessRepository.save(access);
    }

    @PutMapping("/{accessId}/level/{levelId}")
    Access connectLevelToAccess(
            @PathVariable Long accessId,
            @PathVariable Long levelId
    ) {
        Access access = accessRepository.getOne(accessId);
        Level level = levelRepository.getOne(levelId);
        access.connectLevel(level);
        return accessRepository.save(access);
    }
}
