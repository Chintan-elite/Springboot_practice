package com.app.inventory.payload;

import java.util.Date;

import com.app.inventory.entity.ItemMaster;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SalesMasterDto {

	int salesId;
	
	ItemMaster itemMasters;
	
	int stock;
	
	Date date;
}
