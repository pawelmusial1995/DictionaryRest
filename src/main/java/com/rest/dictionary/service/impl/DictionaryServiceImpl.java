package com.rest.dictionary.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rest.dictionary.form.DictionaryForm;
import com.rest.dictionary.model.Dictionary;
import com.rest.dictionary.repository.DictionaryRepository;
import com.rest.dictionary.service.DictionaryService;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DictionaryServiceImpl implements DictionaryService {

	private final DictionaryRepository dictionaryRepository;

	public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
		this.dictionaryRepository = dictionaryRepository;
	}

	@Override
	public List<Dictionary> findAllDictionaries() {
		return (List<Dictionary>) dictionaryRepository.findAll();
	}

	@Override
	public Dictionary createNewDictionary(Dictionary dictionary) {
		return dictionaryRepository.save(dictionary);
	}

	@Override
	public void editDictionary(DictionaryForm dictionaryForm) {

		Optional<Dictionary> optionalDictionary = dictionaryRepository.findById(dictionaryForm.getDictionaryId());

		if (optionalDictionary.isPresent()) {
			Dictionary dictionary = optionalDictionary.get();
			dictionary.setDictionaryActive(dictionaryForm.getDictionaryActive());
			dictionary.setDictionaryName(dictionaryForm.getDictionaryName());
			dictionaryRepository.save(dictionary);
		} else {
			log.error("Dictionary not found");
		}

	}

	@Override
	public void deactivateDictionary(DictionaryForm dictionaryForm) {

		Optional<Dictionary> optionalDictionary = dictionaryRepository.findById(dictionaryForm.getDictionaryId());

		if (optionalDictionary.isPresent()) {
			Dictionary dictionaryDB = optionalDictionary.get();
			dictionaryDB.setDictionaryActive(dictionaryForm.getDictionaryActive());
			dictionaryRepository.save(dictionaryDB);
		} else {
			log.error("Error durring deactivate dictionary");
		}

	}

	@Override
	public Optional<Dictionary> findDictionaryById(DictionaryForm dictionaryForm) {
		return dictionaryRepository.findById(dictionaryForm.getDictionaryId());

	}

}
