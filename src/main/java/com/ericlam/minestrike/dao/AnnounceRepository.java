package com.ericlam.minestrike.dao;

import com.ericlam.minestrike.modals.Announce;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnounceRepository extends CrudRepository<Announce, Integer> {

}
