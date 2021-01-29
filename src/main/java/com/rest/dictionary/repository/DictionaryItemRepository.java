package com.rest.dictionary.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.dictionary.model.Dictionary;
import com.rest.dictionary.model.DictionaryItem;

@Repository
public interface DictionaryItemRepository extends CrudRepository<DictionaryItem, Long> {

	List<DictionaryItem> findByDictionary(Dictionary dictionary);
}
