package com.oauth.security.repository.mysql;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.ContentInfo;

@Repository
public interface ContentRepository extends JpaRepository<ContentInfo, UUID>, ContentRepositoryCustom {

	@Transactional
	@Modifying
	@Query("delete from ContentInfo where user_id = :user_id and file_path = :file_path and file_name = :file_name")
	void deleteContent(@Param("user_id") String userId, @Param("file_path") String filePath,
			@Param("file_name") String fileName);

	@Query("select count(file_name)>0 from ContentInfo where user_id = :user_id and file_path = :file_path and file_name = :file_name")
	boolean isAllowedToDelete(@Param("user_id") String userId, @Param("file_path") String filePath,
			@Param("file_name") String fileName);

	List<ContentInfo> findByTagsIn(List<String> tags, Pageable pageable);

	@Query("from ContentInfo where tenant_id =:tenant_id and (length(trim(:tag)) = 0  or :tag member of tags)")
	List<ContentInfo> getContentByTenantWithRelavantTags(@Param("tenant_id") String tenantId, @Param("tag") String tag,
			Sort sortCriteria);

	@Query("from ContentInfo where tenant_id =:tenant_id")
	List<ContentInfo> getContentByTenantWithoutRelavantTags(@Param("tenant_id") String tenantId, Sort sortCriteria);

	@Query(value = "SELECT * FROM content_info u WHERE u.file_name = ?1", nativeQuery = true)
	Page<ContentInfo> findContentInfoByFileName(String fileName, Pageable pageable);

	ContentInfo findByUserIdAndFilePathAndFileName(String userId, String actualPath, String fileName);

	@Query(value = "SELECT * FROM content_info u WHERE u.tenant_id = ?1 and u.file_name = ?2 and u.file_path = ?3", nativeQuery = true)
	List<ContentInfo> findByTenantIdAndFileNameAndFilePath(@Param("tenant_id") String tenantId,
			@Param("file_name") String fileName, @Param("file_path") String filePath);

	@Query(value = "SELECT * FROM content_info u WHERE u.file_name = ?1 and u.file_path = ?2", nativeQuery = true)
	List<ContentInfo> findByFileNameAndFilePath(String fileName, String fileBasePath);

	Page<ContentInfo> findByTenantIdAndFilePathStartsWith(String tenantId, String substring, Pageable pageable);

	Page<ContentInfo> findByFilePathStartsWith(String substring, Pageable pageable);

	Page<ContentInfo> findByFilePathStartsWithOrFilePathStartsWith(String publicPath, String privatePath,
			Pageable pageable);

	Page<ContentInfo> findByFileNameAndFilePathStartsWith(String fileName, String path, Pageable pageable);

	Page<ContentInfo> findByTagsInAndFilePathStartsWith(Set<String> tags, String path, Pageable pageable);

	List<ContentInfo> findByFilePathStartsWith(String string);

	List<ContentInfo> findByFilePath(String dir);

	ContentInfo findByFilePathAndFileName(String fileName, String fileBasePath);
}
