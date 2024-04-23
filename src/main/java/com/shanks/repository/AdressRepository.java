package com.shanks.repository;

import com.shanks.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Address,Long> {
}
