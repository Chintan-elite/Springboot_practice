package com.app.inventory.service;

import java.util.List;

import com.app.inventory.payload.CurrentStockDto;
import com.app.inventory.payload.StockMasterDto;

public interface StockmasterService {
	
	StockMasterDto createStock(StockMasterDto dto);
	List<CurrentStockDto> currentStock();
}
