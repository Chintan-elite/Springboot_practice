package com.app.inventory.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="stock_master")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int stockId;
	
	@ManyToOne
	ItemMaster itemMasters;
	
	long stock;
	
	String flag;
	
	
	
}
