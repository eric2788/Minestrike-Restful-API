package com.ericlam.minestrike.dao;

import com.ericlam.minestrike.modals.Tutorial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends CrudRepository<Tutorial, Integer> {
}
