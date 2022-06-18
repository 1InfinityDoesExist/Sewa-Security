package com.oauth.security.repository.mysql;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepositoryCustom {

	List<String> getFileNames(String userId, String filePath);

}
