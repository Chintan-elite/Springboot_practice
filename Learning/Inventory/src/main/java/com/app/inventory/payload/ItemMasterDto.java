package com.app.inventory.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ItemMasterDto {

	int itemId;
	String itemName;
	String itemDesc;
	int itemStock;
	
}
