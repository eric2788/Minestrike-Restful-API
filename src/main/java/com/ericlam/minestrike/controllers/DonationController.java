package com.ericlam.minestrike.controllers;

import com.ericlam.minestrike.dao.DonationRepository;
import com.ericlam.minestrike.modals.Donation;
import com.ericlam.minestrike.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/donation")
public class DonationController {

    private final DonationRepository donationRepository;

    @Autowired
    public DonationController(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @GetMapping
    public Iterable<Donation> getAllDonors() {
        return donationRepository.findAll();
    }

    @PostMapping
    public Donation createNewDonor(@RequestBody Donation donation) {
        return donationRepository.save(donation);
    }

    @DeleteMapping("{uuid}")
    public Map deleteDonor(@PathVariable String uuid) {
        boolean success;
        if (donationRepository.findById(uuid).isPresent()) {
            success = true;
            donationRepository.deleteById(uuid);
        } else {
            success = false;
        }
        return Utils.returnResult(success);
    }

    @PutMapping("{uuid}")
    public Donation updateDonor(@RequestBody Donation edited, @PathVariable String uuid) {
        return donationRepository.findById(uuid).map(donation -> {
            donation.setColor(edited.getColor());
            donation.setRank(edited.getRank());
            donation.setUsername(edited.getUsername());
            return donationRepository.save(donation);
        }).orElseGet(() -> {
            edited.setUuid(uuid);
            return donationRepository.save(edited);
        });
    }
}
