package com.ericlam.minestrike.dao;

import com.ericlam.minestrike.modals.Donation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends CrudRepository<Donation, String> {
}
