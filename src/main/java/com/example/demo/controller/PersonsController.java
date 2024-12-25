package com.example.demo.controller;

import com.example.demo.entity.Persons;
import com.example.demo.service.PersonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonsController {

    private final PersonsService service;

    @GetMapping("{id}")
    public Persons getPersonsId(@PathVariable Long id) {
        return service.getEmployeeById(id).orElseThrow();
    }

    @GetMapping("/hello")
    public ResponseEntity<String> demo() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<Persons> save(@RequestBody Persons persons) {
        return new ResponseEntity<>(service.save(persons), HttpStatus.OK);
    }


}
