package com.app.inventory.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.inventory.entity.ItemMaster;
import com.app.inventory.exception.ResourcesNotFountException;
import com.app.inventory.payload.ItemMasterDto;
import com.app.inventory.repositories.ItemMasterRepo;
import com.app.inventory.service.ItemMasterService;

@Service
public class ItemMasterServiceImpl implements ItemMasterService {

	@Autowired
	ItemMasterRepo itemMasterRepo;
	
	@Override
	public ItemMasterDto addItem(ItemMasterDto itemMasterDto) {

		ItemMaster item =  this.dtotoItemMaster(itemMasterDto);
		ItemMaster savedItem =  this.itemMasterRepo.save(item);
		return this.itemMasterTodto(savedItem);
	}

	@Override
	public ItemMasterDto updateItem(ItemMasterDto itemMasterDto, Integer itemId) {
		
		ItemMaster item =  this.itemMasterRepo.findById(itemId).orElseThrow(()->new ResourcesNotFountException("Item","id",itemId));
		item.setItemName(itemMasterDto.getItemName());
		item.setItemDesc(itemMasterDto.getItemDesc());
		item.setItemStock(itemMasterDto.getItemStock());
		
		ItemMaster updatedItem = this.itemMasterRepo.save(item);
		return this.itemMasterTodto(updatedItem);
	}

	@Override
	public ItemMasterDto getItemById(Integer itemId) {
		ItemMaster item =  this.itemMasterRepo.findById(itemId).orElseThrow(()->new ResourcesNotFountException("Item","id",itemId));
		return this.itemMasterTodto(item);
	}

	@Override
	public List<ItemMasterDto> getAllItem() {
		
		List<ItemMaster> items =  this.itemMasterRepo.findAll();
		List<ItemMasterDto> dtos = items.stream().map(item->this.itemMasterTodto(item)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public void deleteItem(Integer itemId) {
		
		ItemMaster item =  this.itemMasterRepo.findById(itemId).orElseThrow(()->new ResourcesNotFountException("Item","id",itemId));
		this.itemMasterRepo.delete(item);

	}

	public ItemMaster dtotoItemMaster(ItemMasterDto dto)
	{
		ItemMaster item = new ItemMaster();
		item.setItemId(dto.getItemId());
		item.setItemName(dto.getItemName());
		item.setItemDesc(dto.getItemDesc());
		item.setItemStock(dto.getItemStock());
		return item;
	}
	
	public ItemMasterDto itemMasterTodto(ItemMaster item)
	{
		ItemMasterDto dto = new ItemMasterDto();
		dto.setItemId(item.getItemId());
		dto.setItemName(item.getItemName());
		dto.setItemDesc(item.getItemDesc());
		dto.setItemStock(item.getItemStock());
		return dto;
	}
	
	
	
	
	
	
	
}
