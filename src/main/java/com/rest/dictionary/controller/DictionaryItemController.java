package com.rest.dictionary.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dictionary.form.DictionaryItemForm;
import com.rest.dictionary.model.Dictionary;
import com.rest.dictionary.model.DictionaryItem;
import com.rest.dictionary.service.DictionaryItemService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/dictionaryItem")
@Log4j2
public class DictionaryItemController {

	private DictionaryItemService dictionaryItemService;

	@Autowired
	public DictionaryItemController(DictionaryItemService dictionaryItemService) {
		this.dictionaryItemService = dictionaryItemService;
	}

	@GetMapping
	public ResponseEntity<List<DictionaryItem>> getAllDictionaryItems() {
		return ok(dictionaryItemService.findAllDictionaryItems());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getDictionaryList")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DictionaryItem>> getDictionaryItemList(@RequestBody Dictionary dictionary) {
		return ok(dictionaryItemService.getDictionaryItemsByDictionary(dictionary));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<DictionaryItem> addDictionaryItem(@RequestBody DictionaryItem dictionaryItem) {
		return ok(dictionaryItemService.createNewDictionaryItem(dictionaryItem));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusResponse editDictionaryItem(@RequestBody DictionaryItemForm dictionaryItemForm) {
		if (dictionaryItemForm.getDicItemId() != null) {
			dictionaryItemService.editDictionaryItem(dictionaryItemForm);
			return new StatusResponse("Editing process was successful", Status.EDITED);
		} else {
			log.error("Error durring edit dictionary Item");
			return new StatusResponse("Error durring edit ", Status.ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.POST, path = "/deactivate")
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusResponse deactivateDictionaryItem(@RequestBody DictionaryItemForm dictionaryItemForm) {

		if (dictionaryItemForm.getDicItemId() != null) {
			dictionaryItemService.deactivateDictionaryItem(dictionaryItemForm);
			return new StatusResponse("Deactivate process was successful", Status.EDITED);
		} else {
			log.error("Error durring deactivate Dictionary Item");
			return new StatusResponse("Error durring deactivate process", Status.ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.POST, path = "/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusResponse removeDictionaryItem(@RequestBody Long dicItemId) {
		if (dicItemId != null) {
			dictionaryItemService.removeDictionaryItem(dicItemId);
			return new StatusResponse("Remove process was successful", Status.DELETED);
		} else {
			log.error("Error dictionary item Id is null");
			return new StatusResponse("ERROR durring remove object", Status.ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.POST, path = "/getDictionaryItem")
	@Consumes(MediaType.APPLICATION_JSON)
	public DictionaryItem getDicitonaryItemById(@RequestBody DictionaryItemForm dictionaryItemForm) {

		Optional<DictionaryItem> optionalDicitonaryItem = dictionaryItemService
				.findDictionaryItemById(dictionaryItemForm);
		if (optionalDicitonaryItem.isPresent()) {
			DictionaryItem dictionaryItem = optionalDicitonaryItem.get();
			return dictionaryItem;
		} else {
			log.error("Error durring found Dictionary ");
			return null;
		}
	}

}
