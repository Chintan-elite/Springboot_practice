package com.app.inventory.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ItemMasterDto {

	String itemId;
	
	@NotEmpty(message = "Itemname can not be blank")
	String itemName;
	@NotEmpty(message = "Desc can not be blank")
	String itemDesc;
	int itemStock;
	
}
