package com.ericlam.minestrike.controllers;

import com.ericlam.minestrike.dao.WeaponRepository;
import com.ericlam.minestrike.modals.Weapons;
import com.ericlam.minestrike.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/weapon")
public class WeaponController {

    private final WeaponRepository weaponRepository;

    @Autowired
    public WeaponController(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @GetMapping
    public Iterable<Weapons> getAllWeapons() {
        return weaponRepository.findAll();
    }

    @GetMapping("category")
    public Map getCategories() {
        Map<String, Set> category = new HashMap<>();
        HashSet<String> guns = new HashSet<>();
        HashSet<String> types = new HashSet<>();
        weaponRepository.findAll().forEach(weapons -> {
            guns.add(weapons.getGun());
            types.add(weapons.getType());
        });
        category.put("guns", guns);
        category.put("types", types);
        return category;
    }

    @PostMapping
    public Weapons createWeapon(@RequestBody Weapons weapons) {
        return weaponRepository.save(weapons);
    }

    @DeleteMapping("{id}")
    public Map deleteWeapon(@PathVariable int id) {
        boolean success;
        if (weaponRepository.findById(id).isPresent()) {
            success = true;
            weaponRepository.deleteById(id);
        } else {
            success = false;
        }
        return Utils.returnResult(success);
    }

    @PutMapping("{id}")
    public Weapons updateWeapons(@RequestBody Weapons edited, @PathVariable int id) {
        return weaponRepository.findById(id).map(weapons -> {
            weapons.setColor(edited.getColor());
            weapons.setGun(edited.getGun());
            weapons.setHot(edited.isHot());
            weapons.setName(edited.getName());
            weapons.setNew(edited.isNew());
            weapons.setPrice(edited.getPrice());
            weapons.setSrc(edited.getSrc());
            weapons.setText(edited.getText());
            weapons.setType(edited.getType());
            return weaponRepository.save(weapons);
        }).orElseGet(() -> {
            edited.setId(id);
            return weaponRepository.save(edited);
        });
    }
}
