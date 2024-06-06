package com.app.inventory.service;

import java.util.List;

import com.app.inventory.payload.ItemMasterDto;

public interface ItemMasterService {
	
	ItemMasterDto addItem(ItemMasterDto itemMasterDto);
	ItemMasterDto updateItem(ItemMasterDto itemMasterDto,Integer itemId);
	ItemMasterDto getItemById(Integer itemId);
	List<ItemMasterDto> getAllItem();
	void deleteItem(Integer itemId);
	
}
