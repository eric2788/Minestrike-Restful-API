package com.ericlam.minestrike.dao;

import com.ericlam.minestrike.modals.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<Auth, String> {
}
