package com.app.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.inventory.entity.ItemMaster;

public interface ItemMasterRepo extends JpaRepository<ItemMaster, String>
{

}
