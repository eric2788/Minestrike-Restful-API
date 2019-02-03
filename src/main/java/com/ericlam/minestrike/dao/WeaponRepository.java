package com.ericlam.minestrike.dao;

import com.ericlam.minestrike.modals.Weapons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends CrudRepository<Weapons, Integer> {
}
