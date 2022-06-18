package com.oauth.security.repository.mysql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ContentRepositoryImpl implements ContentRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getFileNames(String userId, String filePath) {
		return this.entityManager.createNativeQuery(
				"select file_name from content_info where user_id='" + userId + "' and file_path='" + filePath + "'")
				.getResultList();
	}
}
