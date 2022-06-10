package com.oauth.security.controller.impl;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.GoogleConsoleController;
import com.oauth.security.entity.GoogleConsole;
import com.oauth.security.model.request.GoogleConsoleCreateRequest;
import com.oauth.security.model.request.GoogleConsoleUpdateRequest;
import com.oauth.security.service.GoogleConsoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GoogleConsoleControllerImpl implements GoogleConsoleController {

	@Autowired
	private GoogleConsoleService googleService;

	/**
	 * {@code POST  /v1.0/google-console} : Create a new GoogleConsole.
	 *
	 * @param facebookConsoleCreateRequest the googleConsoleCreateRequest to create
	 *                                     googleConsole.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new googleConsole, or with status {@code 400 (Bad Request)}
	 *         if the gooleConsole already exist.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@Override
	public ResponseEntity<GoogleConsole> registerGoogleConsoleUsingPOST(
			GoogleConsoleCreateRequest googleConsoleCreateRequest) throws Exception {
		log.debug("REST request to save GoogleConsole : {}", googleConsoleCreateRequest);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(googleService.registerGoogleConsoleUsingPOST(googleConsoleCreateRequest));
	}

	/**
	 * {@code GET  /v1.0/google-console/:productId} : get the "productId"
	 * GoogleConsole.
	 *
	 * @param id the id of the product to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the application, or with status {@code 404 (Not Found)}.
	 */
	@Override
	public ResponseEntity<GoogleConsole> retrieveRegisteredGoogleConsoleUsingGET(String productId) throws Exception {
		log.debug("REST request to get GoogleConsole : {}", productId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(googleService.retrieveRegisteredGoogleConsoleUsingGET(productId));
	}

	/**
	 * {@code DELETE  /v1.0/google-console/:productId} : delete the "productId"
	 * GoogleConsole.
	 *
	 * @param id the id of the product to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@Override
	public ResponseEntity<Void> unregisterGoogleConsoleUingDELETE(String productId) throws Exception {
		log.debug("REST request to delete GoogleConsole : {}", productId);
		googleService.unregisterGoogleConsoleUingDELETE(productId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/**
	 * {@code PUT  /v1.0/google-console/:productId} : Updates an existing
	 * GoogleConsole.
	 *
	 * @param id          the id of the Product to save.
	 * @param application the GoogleConsole to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated GoogleConsole, or with status {@code 400 (Bad Request)}
	 *         if the GoogleConsole is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the GoogleConsole couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@Override
	public ResponseEntity<GoogleConsole> updateRegisteredGoogleConsoleUsingPUT(
			GoogleConsoleUpdateRequest googleConsoleUpdateRequest, String productId) throws Exception {
		log.debug("REST request to update GoogleConsole : {}, {}", productId, googleConsoleUpdateRequest);
		return ResponseEntity.status(HttpStatus.OK)
				.body(googleService.updateRegisteredGoogleConsoleUsingPUT(googleConsoleUpdateRequest, productId));
	}

	/**
	 * {@code GET  /v1.0/google-console} : get all the GoogleConsole.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of GoogleConsole in body.
	 */
	@Override
	public ResponseEntity<List<GoogleConsole>> getAllGoogleConsole() {
		log.debug("REST request to get all FacebookConsole");
		return ResponseEntity.status(HttpStatus.OK).body(googleService.getAllGoogleConsole());
	}

}
