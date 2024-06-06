package com.app.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.inventory.entity.PurchaseMaster;

public interface PurchaseMasterRepo extends JpaRepository<PurchaseMaster, Integer> {

}
