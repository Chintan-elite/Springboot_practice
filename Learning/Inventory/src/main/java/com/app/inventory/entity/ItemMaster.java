package com.app.inventory.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="item_master")
@NoArgsConstructor
@Getter
@Setter
public class ItemMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int itemId;
	String itemName;
	String itemDesc;
	int itemStock;
	
}
