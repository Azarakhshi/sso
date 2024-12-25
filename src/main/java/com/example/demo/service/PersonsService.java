package com.example.demo.service;

import com.example.demo.entity.Persons;
import com.example.demo.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonsService {

    private final PersonsRepository repository;

    public Optional<Persons> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Persons save(Persons persons) {
        return repository.save(persons);
    }


}
