package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.Ban;
import com.netcracker.edu.fapi.service.BanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bans")
public class BanController {

    @Autowired
    private BanService banService;

    @RequestMapping
    public ResponseEntity<Iterable<Ban>> getAllBans() {
        return ResponseEntity.ok(banService.getAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Ban> getBan(@PathVariable String id) throws InterruptedException {
        Long banId = Long.valueOf(id);
        return ResponseEntity.ok(banService.getById(banId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Ban> saveBan(@RequestBody Ban ban) {
        if (ban != null) {
            return ResponseEntity.ok(banService.save(ban));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBan(@PathVariable String id) {
        banService.delete(Long.valueOf(id));
    }
}
