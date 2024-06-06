package com.app.inventory.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="item_master")
@NoArgsConstructor
@Getter
@Setter
public class ItemMaster {

	
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_seq")
	@GenericGenerator(
			name="item_seq",
			strategy = "com.app.inventory.entity.SequenceIdGenrerator",
			parameters = {
					@Parameter(name=SequenceIdGenrerator.INCREMENT_PARAM, value = "50"),
					@Parameter(name = SequenceIdGenrerator.VALUE_PREFIX_PARAMETER, value = "Item_"),
			        @Parameter(name = SequenceIdGenrerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	 String itemId;
	
	String itemName;
	String itemDesc;
	int itemStock;
	

	
}
