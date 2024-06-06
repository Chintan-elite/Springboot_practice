package com.app.inventory.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.inventory.entity.PurchaseMaster;
import com.app.inventory.payload.PurchaseMAsterDto;
import com.app.inventory.repositories.PurchaseMasterRepo;
import com.app.inventory.service.PurchaseMasterService;

@Service
public class PurchaseMasterServiceImpl implements PurchaseMasterService
{

	@Autowired
	PurchaseMasterRepo purchaseMasterRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public PurchaseMAsterDto createPurchase(PurchaseMAsterDto dto) {
		
		PurchaseMaster purchase =  this.purchaseMasterRepo.save(this.modelMapper.map(dto, PurchaseMaster.class));
		return modelMapper.map(purchase, PurchaseMAsterDto.class);
	}

}
