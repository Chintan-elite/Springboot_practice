package com.app.inventory.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.inventory.entity.SalesMaster;
import com.app.inventory.payload.SalesMasterDto;
import com.app.inventory.repositories.SalesMasterRepo;
import com.app.inventory.service.SalesMasterService;

@Service
public class SalesMasterServiceImpl implements SalesMasterService
{

	@Autowired
	SalesMasterRepo salesMasterRepo;
	
	@Autowired
	ModelMapper modelMapper;
	

	@Override
	public SalesMasterDto createSale(SalesMasterDto dto) {
		SalesMaster master =  salesMasterRepo.save(modelMapper.map(dto, SalesMaster.class));
		return modelMapper.map(master, SalesMasterDto.class);
	}

}
