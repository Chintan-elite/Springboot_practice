package com.app.inventory.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.inventory.entity.ItemMaster;
import com.app.inventory.entity.StockMaster;
import com.app.inventory.payload.APIResponse;
import com.app.inventory.payload.ItemMasterDto;
import com.app.inventory.payload.StockMasterDto;
import com.app.inventory.service.ItemMasterService;
import com.app.inventory.service.StockmasterService;

import jakarta.validation.Valid;
import lombok.Delegate;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	@Autowired
	ItemMasterService itemMasterService;
	
	@Autowired
	StockmasterService stockmasterService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("/")
	public ResponseEntity<ItemMasterDto> addItem(@Valid @RequestBody ItemMasterDto dto)
	{
		ItemMasterDto createdItem =  itemMasterService.addItem(dto);
		
		StockMasterDto sdto = new StockMasterDto();
		sdto.setItemMasters(this.modelMapper.map(createdItem, ItemMaster.class));
		sdto.setStock(createdItem.getItemStock());
		sdto.setFlag("open");
		
		stockmasterService.createStock(sdto);
		
		return new ResponseEntity<>(createdItem,HttpStatus.CREATED);
	}
	
	@PutMapping("/{itemid}")
	public ResponseEntity<ItemMasterDto> updateItem(@Valid @RequestBody ItemMasterDto dto,@PathVariable("itemid") int id)
	{
		ItemMasterDto updateItem =  itemMasterService.updateItem(dto,id);
		return new ResponseEntity<>(updateItem,HttpStatus.OK);
	}
	
	@DeleteMapping("/{itemid}")
	public ResponseEntity<APIResponse> deleteItem(@PathVariable("itemid") int id)
	{
		itemMasterService.deleteItem(id);
		return new ResponseEntity<APIResponse>(new APIResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ItemMasterDto>> allItem()
	{
		return ResponseEntity.ok(this.itemMasterService.getAllItem());
	}
	
	@GetMapping("/{itemid}")
	public ResponseEntity<ItemMasterDto> getoneitem(@PathVariable("itemid") int id)
	{
		return ResponseEntity.ok(this.itemMasterService.getItemById(id));
	}
	

	
	
	
	
}
