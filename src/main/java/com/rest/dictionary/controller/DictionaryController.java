package com.rest.dictionary.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import com.rest.dictionary.form.DictionaryForm;
import com.rest.dictionary.model.Dictionary;
import com.rest.dictionary.service.DictionaryService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/dictionary")
@Log4j2
public class DictionaryController {

	private DictionaryService dictionaryService;

	@Autowired
	public DictionaryController(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@GetMapping
	public ResponseEntity<List<Dictionary>> getAllDictionaries() {
		return ok(dictionaryService.findAllDictionaries());
	}

	@RequestMapping(method = RequestMethod.POST, path = "/add", produces = "application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<Dictionary> addDictonary(@RequestBody Dictionary dictionary) {
		return ok(dictionaryService.createNewDictionary(dictionary));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusResponse editDictonary(@RequestBody DictionaryForm dictionaryForm) {
		if (dictionaryForm.getDictionaryId() != null) {
			dictionaryService.editDictionary(dictionaryForm);
			return new StatusResponse("Edit process was successful", Status.SAVED);
		} else {
			log.error("Error durring edit dictionary");
		}

		return new StatusResponse("Error durring edit", Status.ERROR);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/getDictionary")
	@Consumes(MediaType.APPLICATION_JSON)
	public Dictionary getDicitonaryById(@RequestBody DictionaryForm dictionaryForm) {

		Optional<Dictionary> optionalDicitonary = dictionaryService.findDictionaryById(dictionaryForm);
		if (optionalDicitonary.isPresent()) {
			Dictionary dictionary = optionalDicitonary.get();
			return dictionary;
		} else {
			log.error("Error durring found Dictionary ");
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "/deactivate")
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusResponse deactivateDictonary(@RequestBody DictionaryForm dictionaryForm) {
		if (dictionaryForm.getDictionaryId() != null) {
			dictionaryService.deactivateDictionary(dictionaryForm);
			return new StatusResponse("Deactivation process was successful ", Status.EDITED);
		} else {
			log.error("Error durring deactivate Dictionary");
			return new StatusResponse("Error durring deactivation process", Status.ERROR);
		}
	}

}
