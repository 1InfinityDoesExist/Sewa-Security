package com.oauth.security.controller.impl;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.TwitterConsoleController;
import com.oauth.security.entity.TwitterConsole;
import com.oauth.security.model.request.TwitterConsoleCreateRequest;
import com.oauth.security.model.request.TwitterConsoleUpdateRequest;
import com.oauth.security.service.TwitterConsoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TwitterConsoleControllerImpl implements TwitterConsoleController {

	@Autowired
	private TwitterConsoleService twitterService;

	/**
	 * {@code POST  /v1.0/twitter-console} : Create a new TwitterConsole.
	 *
	 * @param twitterConsoleCreateRequest the twitterConsoleCreateRequest to create
	 *                                    TwitterConsole.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new twitterConsole, or with status {@code 400 (Bad Request)}
	 *         if the twitterConsole already exist.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@Override
	public ResponseEntity<TwitterConsole> registerTwitterConsoleUsingPOST(
			TwitterConsoleCreateRequest twitterConsoleCreateRequest) throws Exception {
		log.debug("REST request to save TwitterConsole : {}", twitterConsoleCreateRequest);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(twitterService.registerTwitterConsoleUsingPOST(twitterConsoleCreateRequest));
	}

	/**
	 * {@code GET  /v1.0/twitter-console/:productId} : get the "productId"
	 * TwitterConsole.
	 *
	 * @param id the id of the product to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the application, or with status {@code 404 (Not Found)}.
	 */
	@Override
	public ResponseEntity<TwitterConsole> retrieveRegisteredTwitterConsoleUsingGET(String productId) throws Exception {
		log.debug("REST request to get TwitterConsole : {}", productId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(twitterService.retrieveRegisteredTwitterConsoleUsingGET(productId));
	}

	/**
	 * {@code DELETE  /v1.0/twitter-console/:productId} : delete the "productId"
	 * TwitterConsole.
	 *
	 * @param id the id of the product to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@Override
	public ResponseEntity<Void> unregisterTwitterConsoleUingDELETE(String productId) throws Exception {
		log.debug("REST request to delete TwitterConsole : {}", productId);
		twitterService.unregisterTwitterConsoleUingDELETE(productId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/**
	 * {@code PUT  /v1.0/twitter-console/:productId} : Updates an existing
	 * TwitterConsole.
	 *
	 * @param id          the id of the Product to save.
	 * @param application the TwitterConsole to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated TwitterConsole, or with status {@code 400 (Bad Request)}
	 *         if the TwitterConsole is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the TwitterConsole couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@Override
	public ResponseEntity<TwitterConsole> updateRegisteredTwitterConsoleUsingPUT(
			TwitterConsoleUpdateRequest twitterConsoleUpdateRequest, String productId) throws Exception {
		log.debug("REST request to update TwitterConsole : {}, {}", productId, twitterConsoleUpdateRequest);
		return ResponseEntity.status(HttpStatus.OK)
				.body(twitterService.updateRegisteredTwitterConsoleUsingPUT(twitterConsoleUpdateRequest, productId));
	}

	/**
	 * {@code GET  /v1.0/twitter-console} : get all the TwitterConsole.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of TwitterConsole in body.
	 */
	@Override
	public ResponseEntity<List<TwitterConsole>> getAllTwitterConsole() {
		log.debug("REST request to get all TwitterConsole");
		return ResponseEntity.status(HttpStatus.OK).body(twitterService.getAllTwitterConsole());
	}

}
