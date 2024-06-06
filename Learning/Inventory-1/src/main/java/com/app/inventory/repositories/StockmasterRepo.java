package com.app.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.inventory.entity.StockMaster;
import com.app.inventory.payload.StockMasterDto;

public interface StockmasterRepo extends JpaRepository<StockMaster, Integer> {
	
	@Query("SELECT new StockMaster(s.stockId,s.itemMasters,SUM(case when s.flag='open' then s.stock else 0 end)+SUM(case when s.flag='purchase' then s.stock else 0 end)-SUM(case when s.flag='sale' then s.stock else 0 end),s.flag) FROM StockMaster s group by s.itemMasters")
	List<StockMaster> getAllStock();
}
