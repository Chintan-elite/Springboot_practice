package com.app.inventory.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.inventory.entity.StockMaster;
import com.app.inventory.payload.CurrentStockDto;
import com.app.inventory.payload.StockMasterDto;
import com.app.inventory.repositories.StockmasterRepo;
import com.app.inventory.service.StockmasterService;

@Service
public class StockMasterServiceImpl implements StockmasterService
{

	@Autowired
	StockmasterRepo stockmasterRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public StockMasterDto createStock(StockMasterDto dto) {
		
		StockMaster stock =  this.stockmasterRepo.save(this.modelMapper.map(dto, StockMaster.class));
		return this.modelMapper.map(stock, StockMasterDto.class);
	}

	@Override
	public List<CurrentStockDto> currentStock() {
		
		List<StockMaster> stock = this.stockmasterRepo.getAllStock();
		//List<StockMasterDto> dto = stock.stream().map(st->this.modelMapper.map(st, StockMasterDto.class)).collect(Collectors.toList());
		List<CurrentStockDto> dto = stock.stream().map(st->this.stockMasterTocurrentstocDto(st)).collect(Collectors.toList());
		
		return dto;
	}

	
	CurrentStockDto stockMasterTocurrentstocDto(StockMaster stock)
	{
		CurrentStockDto dto = new CurrentStockDto();
		//dto.setItemId(stock.getItemMasters().getItemId());
		dto.setItemName(stock.getItemMasters().getItemName());
		dto.setStock(stock.getStock());
		return dto;
	}
	
	
	
	
	
	
	
}
