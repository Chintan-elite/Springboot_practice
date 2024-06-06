package com.app.inventory.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="purchase_master")
@NoArgsConstructor
@Getter
@Setter
public class PurchaseMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int purchaseId;
	
	@ManyToOne
	ItemMaster itemMasters;
	
	int stock;
	
	Date date;
}
