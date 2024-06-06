package com.app.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.inventory.entity.SalesMaster;

public interface SalesMasterRepo extends JpaRepository<SalesMaster, Integer> {

}
