package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Bans;
import com.netcracker.edu.backend.service.BansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ban")
public class BanController {

    private BansService bansService;

    @Autowired
    public BanController(BansService bansService) {
        this.bansService = bansService;
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bans> getBansById(@PathVariable(name = "id") Long id) {
        Optional<Bans> bans = bansService.getBansById(id);
        if (bans.isPresent()) {
            return ResponseEntity.ok(bans.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Bans> getAllBans() {
        System.out.printf("GOVNO DETECTED");
        return bansService.getAllBans();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Bans saveBans(@RequestBody Bans bans) {
        return bansService.saveBans(bans);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBans(@PathVariable(name = "id") Long id) {
        bansService.deleteBans(id);
    }
}