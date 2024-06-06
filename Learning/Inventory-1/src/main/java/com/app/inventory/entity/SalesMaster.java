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
@Table(name="Sales_master")
@NoArgsConstructor
@Getter
@Setter
public class SalesMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int salesId;
	
	@ManyToOne
	ItemMaster itemMasters;
	
	int stock;
	
	Date date;
}
