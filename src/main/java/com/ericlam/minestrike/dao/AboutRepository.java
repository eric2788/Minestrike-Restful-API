package com.ericlam.minestrike.dao;

import com.ericlam.minestrike.modals.About;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends CrudRepository<About, String> {
}

