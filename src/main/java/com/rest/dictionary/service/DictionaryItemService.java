package com.rest.dictionary.service;

import java.util.List;
import java.util.Optional;

import com.rest.dictionary.form.DictionaryItemForm;
import com.rest.dictionary.model.Dictionary;
import com.rest.dictionary.model.DictionaryItem;

public interface DictionaryItemService {

	List<DictionaryItem> findAllDictionaryItems();

	DictionaryItem createNewDictionaryItem(DictionaryItem dictionaryItem);

	void removeDictionaryItem(Long dicItemId);

	void deactivateDictionaryItem(DictionaryItemForm dictionaryItemForm);

	void editDictionaryItem(DictionaryItemForm dictionaryItemForm);

	List<DictionaryItem> getDictionaryItemsByDictionary(Dictionary dictionary);

	Optional<DictionaryItem> findDictionaryItemById(DictionaryItemForm dictionaryItemForm);

}
