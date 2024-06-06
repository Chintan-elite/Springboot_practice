package com.app.inventory.controller;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.inventory.entity.ItemMaster;
import com.app.inventory.payload.ItemMasterDto;
import com.app.inventory.payload.PurchaseMAsterDto;
import com.app.inventory.payload.SalesMasterDto;
import com.app.inventory.payload.StockMasterDto;
import com.app.inventory.service.ItemMasterService;
import com.app.inventory.service.PurchaseMasterService;
import com.app.inventory.service.SalesMasterService;
import com.app.inventory.service.StockmasterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

	@Autowired
	ItemMasterService itemMasterService;
	
	@Autowired
	SalesMasterService salesMasterService;
	
	@Autowired
	StockmasterService stockmasterService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("/{itemid}")
	public ResponseEntity<SalesMasterDto> addSalesItem(@RequestBody SalesMasterDto dto,@PathVariable("itemid") int id)
	{
		ItemMaster currentIeam = modelMapper.map(itemMasterService.getItemById(id),ItemMaster.class);
		dto.setItemMasters(currentIeam);
		dto.setDate(new Date());
		
		SalesMasterDto createdSalesItem =  this.salesMasterService.createSale(dto);
		
		StockMasterDto sdto = new StockMasterDto();
		sdto.setItemMasters(this.modelMapper.map(currentIeam, ItemMaster.class));
		sdto.setStock(dto.getStock());
		sdto.setFlag("sale");
		
		stockmasterService.createStock(sdto);
		
		return new ResponseEntity<>(createdSalesItem,HttpStatus.CREATED);
	}
	
}
