package com.rest.dictionary.service;

import java.util.List;
import java.util.Optional;

import com.rest.dictionary.form.DictionaryForm;
import com.rest.dictionary.model.Dictionary;

public interface DictionaryService {

	List<Dictionary> findAllDictionaries();

	Dictionary createNewDictionary(Dictionary dictionary);

	void editDictionary(DictionaryForm dictionaryForm);

	void deactivateDictionary(DictionaryForm dictionaryForm);

	Optional<Dictionary> findDictionaryById(DictionaryForm dictionaryForm);

}
