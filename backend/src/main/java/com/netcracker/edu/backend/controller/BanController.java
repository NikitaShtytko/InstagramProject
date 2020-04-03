package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Ban;
import com.netcracker.edu.backend.service.BanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ban")
public class BanController {

    private BanService banService;

    @Autowired
    public BanController(BanService banService) {
        this.banService = banService;
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ban> getBansById(@PathVariable(name = "id") Long id) {
        Optional<Ban> bans = banService.getBansById(id);
        return bans.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Ban> getAllBans() {
        System.out.println("GET DETECTED");
        return banService.getAllBans();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Ban saveBans(@RequestBody Ban ban) {
        return banService.saveBans(ban);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBans(@PathVariable(name = "id") Long id) {
        banService.deleteBans(id);
    }
}
