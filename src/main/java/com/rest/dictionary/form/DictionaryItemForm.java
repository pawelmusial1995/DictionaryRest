package com.rest.dictionary.form;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.rest.dictionary.model.Dictionary;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DictionaryItemForm {

	private Long dicItemId;
	private String dicItemName;
	private String dicItemDefinition;
	private Boolean dicItemActive;
	@ManyToOne
	@JoinColumn(name="dictionary_Id")
	private Dictionary dictionary;
}
