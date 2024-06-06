package com.app.inventory.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.inventory.payload.CurrentStockDto;
import com.app.inventory.payload.StockMasterDto;
import com.app.inventory.service.ItemMasterService;
import com.app.inventory.service.StockmasterService;

@RestController
@RequestMapping("/api/stock")
public class StockController {
	
	@Autowired
	ItemMasterService itemMasterService;
	
	@Autowired
	StockmasterService stockmasterService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping("/")
	public ResponseEntity<List<CurrentStockDto>> allItemwithcurrentStock()
	{
		
		return ResponseEntity.ok(this.stockmasterService.currentStock());
	}
}
