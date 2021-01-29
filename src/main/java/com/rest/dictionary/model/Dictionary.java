package com.rest.dictionary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dictionary {

	@Id
	@GeneratedValue
	@Column(name = "dictionary_Id")
	private Long dictionaryId;
	@Column(name = "dictionary_name")
	private String dictionaryName;
	@Column(name = "dictionary_active")
	private Boolean dictionaryActive;

	public Dictionary(String dictionaryName, Boolean dictionaryActive) {
		this.dictionaryName = dictionaryName;
		this.dictionaryActive = dictionaryActive;
	}

}
