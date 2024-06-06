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
	@GenericGenerator(
			name="item_seq",
			strategy = "SequenceIdGenrerator",
			parameters = {
					@Parameter(name=SequenceIdGenrerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = SequenceIdGenrerator.VALUE_PREFIX_PARAMETER, value = "Item_"),
			        @Parameter(name = SequenceIdGenrerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_seq")
	String itemId;
	
	String itemName;
	String itemDesc;
	int itemStock;
	

	
}
