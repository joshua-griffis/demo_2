package com.example.demo.access;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccessService {

    private final AccessRepository accessRepository;

    @Autowired
    public AccessService(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    public void deleteAccess(Long accessId) {
        boolean exists = accessRepository.existsById(accessId);
        if (!exists) {
            throw new IllegalStateException(
                    "access with id " + accessId + "does not exists");
        }
        accessRepository.deleteById(accessId);
    }

    @Transactional
    public void updateAccess(Long accessId,
                              String accessName
                             ) {
        Access access = accessRepository.findById(accessId)
                .orElseThrow(() -> new IllegalStateException(
                        "access with id " + accessId + "does not exist"));

        if (accessName != null &&
                accessName.length() > 0 &&
                !Objects.equals(access.getAccess(), accessName)) {
            access.setAccess(accessName);
        }
    }
}
