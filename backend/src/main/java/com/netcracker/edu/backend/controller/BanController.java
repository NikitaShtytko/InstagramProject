package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Ban;
import com.netcracker.edu.backend.service.BanService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/bans")
public class BanController {

    private BanService banService;

    @Autowired
    public BanController(BanService banService) {
        this.banService = banService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ban> getBanById(@PathVariable(name = "id") Long id) {
        Optional<Ban> bans = banService.getById(id);
        return bans.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Ban> getAllBans() {
        return banService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PostMapping
    public Ban saveBan(@RequestBody Ban ban) {
        return banService.save(ban);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBan(@PathVariable(name = "id") Long id) {
        banService.delete(id);
    }
}
