package com.rest.dictionary.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DictionaryItem {

	@Id
	@GeneratedValue
	@Column(name = "dicItem_Id")
	private Long dicItemId;
	@Column(name = "dicItem_name")
	private String dicItemName;
	@Column(name = "dicItem_definition")
	private String dicItemDefinition;
	@Column(name = "dicItem_active")
	private Boolean dicItemActive;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "dictionary_Id")
	private Dictionary dictionary;

}
