package com.ericlam.minestrike.controllers;

import com.ericlam.minestrike.dao.AnnounceRepository;
import com.ericlam.minestrike.modals.Announce;
import com.ericlam.minestrike.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/announce")
public class AnnounceController {
    private final AnnounceRepository announceRepository;

    @Autowired
    public AnnounceController(AnnounceRepository announceRepository) {
        this.announceRepository = announceRepository;
    }

    @PutMapping("{id}")
    public Announce updateAnnounces(@RequestBody Announce edited, @PathVariable int id) {
        edited.setDate(Date.from(Instant.now()));
        return announceRepository.findById(id).map(announce -> {
            announce.setTitle(edited.getTitle());
            announce.setContent(edited.getContent());
            announce.setDate(edited.getDate());
            return announceRepository.save(announce);
        }).orElseGet(() -> {
            edited.setId(id);
            return announceRepository.save(edited);
        });
    }

    @GetMapping
    public Iterable<Announce> getAllAnnounces() {
        return announceRepository.findAll();
    }

    @PostMapping
    public Announce createAnnounces(@RequestBody Announce announce) {
        announce.setDate(Date.from(Instant.now()));
        return announceRepository.save(announce);
    }

    @DeleteMapping("{id}")
    public Map deleteAnnounce(@PathVariable int id) {
        boolean success;
        if (announceRepository.findById(id).isPresent()) {
            success = true;
            announceRepository.deleteById(id);
        } else {
            success = false;
        }
        return Utils.returnResult(success);
    }
}
