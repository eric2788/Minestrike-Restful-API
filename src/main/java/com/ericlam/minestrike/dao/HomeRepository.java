package com.ericlam.minestrike.dao;

import com.ericlam.minestrike.modals.Home;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends CrudRepository<Home, Integer> {
}
