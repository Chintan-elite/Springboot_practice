package com.app.inventory.payload;

import com.app.inventory.entity.ItemMaster;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StockMasterDto {
	
	int stockId;
	
	ItemMaster itemMasters;
	
	long stock;
	
	String flag;
}
