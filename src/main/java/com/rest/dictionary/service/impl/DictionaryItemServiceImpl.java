package com.rest.dictionary.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rest.dictionary.form.DictionaryItemForm;
import com.rest.dictionary.model.Dictionary;
import com.rest.dictionary.model.DictionaryItem;
import com.rest.dictionary.repository.DictionaryItemRepository;
import com.rest.dictionary.service.DictionaryItemService;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DictionaryItemServiceImpl implements DictionaryItemService {

	private final DictionaryItemRepository dictionaryItemRepository;

	public DictionaryItemServiceImpl(DictionaryItemRepository dictionaryItemRepository) {
		this.dictionaryItemRepository = dictionaryItemRepository;
	}

	@Override
	public List<DictionaryItem> findAllDictionaryItems() {
		return (List<DictionaryItem>) dictionaryItemRepository.findAll();
	}

	@Override
	public DictionaryItem createNewDictionaryItem(DictionaryItem dictionaryItem) {
		return dictionaryItemRepository.save(dictionaryItem);
	}

	@Override
	public void removeDictionaryItem(Long dicItemId) {

		Optional<DictionaryItem> optionalDictionaryItem = dictionaryItemRepository.findById(dicItemId);

		if (optionalDictionaryItem.isPresent()) {
			DictionaryItem dictionaryItem = optionalDictionaryItem.get();
			dictionaryItemRepository.delete(dictionaryItem);
		} else {
			log.error("Dictionary Item not found in database");
		}

	}

	@Override
	public void deactivateDictionaryItem(DictionaryItemForm dictionaryItemForm) {

		Optional<DictionaryItem> optionalDictionaryItem = dictionaryItemRepository
				.findById(dictionaryItemForm.getDicItemId());

		if (optionalDictionaryItem.isPresent()) {
			DictionaryItem dictionaryItemDB = optionalDictionaryItem.get();
			dictionaryItemDB.setDicItemActive(dictionaryItemForm.getDicItemActive());
			dictionaryItemRepository.save(dictionaryItemDB);
		} else {
			log.error("Rrror durring deactivate Dictionary Item");
		}

	}

	@Override
	public void editDictionaryItem(DictionaryItemForm dictionaryItemForm) {

		Optional<DictionaryItem> optionalDictionaryItem = dictionaryItemRepository
				.findById(dictionaryItemForm.getDicItemId());

		if (optionalDictionaryItem.isPresent()) {
			DictionaryItem dicitonaryItem = optionalDictionaryItem.get();
			dicitonaryItem.setDicItemActive(dictionaryItemForm.getDicItemActive());
			dicitonaryItem.setDicItemDefinition(dictionaryItemForm.getDicItemDefinition());
			dicitonaryItem.setDicItemName(dictionaryItemForm.getDicItemName());
			dicitonaryItem.setDictionary(dictionaryItemForm.getDictionary());
			dictionaryItemRepository.save(dicitonaryItem);
		} else {
			log.error("Dictionary Item not found durring edited ");
		}
	}

	@Override
	public List<DictionaryItem> getDictionaryItemsByDictionary(Dictionary dictionary) {

		List<DictionaryItem> dictionaryItemList = dictionaryItemRepository.findByDictionary(dictionary);
		return dictionaryItemList;
	}

	@Override
	public Optional<DictionaryItem> findDictionaryItemById(DictionaryItemForm dictionaryItemForm) {
		return dictionaryItemRepository.findById(dictionaryItemForm.getDicItemId());
	}

}
