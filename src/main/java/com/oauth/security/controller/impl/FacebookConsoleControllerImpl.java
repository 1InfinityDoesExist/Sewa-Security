package com.oauth.security.controller.impl;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.FacebookConsoleController;
import com.oauth.security.entity.FacebookConsole;
import com.oauth.security.model.request.FacebookConsoleCreateRequest;
import com.oauth.security.model.request.FacebookConsoleUpdateRequest;
import com.oauth.security.service.FacebookConsoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FacebookConsoleControllerImpl implements FacebookConsoleController {

	@Autowired
	private FacebookConsoleService facebookService;

	/**
	 * {@code POST  /v1.0/facebook-console} : Create a new FacebookConsole.
	 *
	 * @param facebookConsoleCreateRequest the facebookConsoleCreateRequest to
	 *                                     create facebookConsole.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new facebookConsole, or with status
	 *         {@code 400 (Bad Request)} if the facebookConsole already exist.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@Override
	public ResponseEntity<FacebookConsole> registerFacebookConsoleUsingPOST(
			FacebookConsoleCreateRequest facebookConsoleCreateRequest) throws Exception {
		log.info("REST request to save FacebookConsole : {}", facebookConsoleCreateRequest);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(facebookService.registerFacebookConsoleUsingPOST(facebookConsoleCreateRequest));
	}

	/**
	 * {@code GET  /v1.0/facebook-console/:productId} : get the "productId"
	 * FacebookConsole.
	 *
	 * @param id the id of the product to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the application, or with status {@code 404 (Not Found)}.
	 */
	@Override
	public ResponseEntity<FacebookConsole> retrieveRegisteredFacebookConsoleUsingGET(String productId)
			throws Exception {
		log.debug("REST request to get FacebookConsole : {}", productId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(facebookService.retrieveRegisteredFacebookConsoleUsingGET(productId));
	}

	/**
	 * {@code DELETE  /v1.0/facebook-console/:productId} : delete the "productId"
	 * FacebookConsole.
	 *
	 * @param id the id of the product to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@Override
	public ResponseEntity<Void> unregisterFacebookConsoleUingDELETE(String productId) throws Exception {
		log.debug("REST request to delete FacebookConsole : {}", productId);
		facebookService.unregisterFacebookConsoleUingDELETE(productId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/**
	 * {@code PUT  /v1.0/facebook-console/:productId} : Updates an existing
	 * FacebookConsole.
	 *
	 * @param id          the id of the Product to save.
	 * @param application the FacebookConsole to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated FacebookConsole, or with status {@code 400 (Bad Request)}
	 *         if the FacebookConsole is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the FacebookConsole couldn't
	 *         be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@Override
	public ResponseEntity<FacebookConsole> updateRegisteredFacebookConsoleUsingPUT(
			FacebookConsoleUpdateRequest facebookConsoleUpdateRequest, String productId) throws Exception {
		log.debug("REST request to update FacebookConsole : {}, {}", productId, facebookConsoleUpdateRequest);
		return ResponseEntity.status(HttpStatus.OK)
				.body(facebookService.updateRegisteredFacebookConsoleUsingPUT(facebookConsoleUpdateRequest, productId));
	}

	/**
	 * {@code GET  /v1.0/facebook-console} : get all the FacebookConsole.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of FacebookConsole in body.
	 */
	@Override
	public ResponseEntity<List<FacebookConsole>> getAllFacebookConsole() {
		log.debug("REST request to get all FacebookConsole");
		return ResponseEntity.status(HttpStatus.OK).body(facebookService.getAllFacebookConsole());
	}

}
