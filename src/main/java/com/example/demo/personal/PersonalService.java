package com.example.demo.personal;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    @Autowired
    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public void deletePersonal(Long personalId) {
        boolean exists = personalRepository.existsById(personalId);
        if (!exists) {
            throw new IllegalStateException(
                    "personal with id " + personalId + "does not exists");
        }
        personalRepository.deleteById(personalId);
    }

    @Transactional
    public void updatePersonal(Long personalId,
                             String personalName
    ) {
        Personal personal = personalRepository.findById(personalId)
                .orElseThrow(() -> new IllegalStateException(
                        "personal with id " + personalId + "does not exist"));

        if (personalName != null &&
                personalName.length() > 0 &&
                !Objects.equals(personal.getPersonalName(), personalName)) {
            personal.setPersonalName(personalName);
        }
    }
}
