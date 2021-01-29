package com.rest.dictionary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.dictionary.model.Dictionary;

@Repository
public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

}
