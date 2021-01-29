package com.rest.dictionary.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
final class StatusResponse {

	private String word;

	private Status status;

}

enum Status {
	SAVED, ERROR, EDITED, DELETED
}
