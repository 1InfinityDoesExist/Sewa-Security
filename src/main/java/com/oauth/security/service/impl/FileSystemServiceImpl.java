package com.oauth.security.service.impl;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.oauth.security.entity.ContentInfo;
import com.oauth.security.model.FileProperties;
import com.oauth.security.model.NginxProperties;
import com.oauth.security.model.request.ContentRequest;
import com.oauth.security.model.response.ContentResponse;
import com.oauth.security.repository.mysql.ContentRepository;
import com.oauth.security.service.FileSystemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileSystemServiceImpl implements FileSystemService {

	private String basePath = "/home/gaian/Videos/Learnings/sewa-security-data";
	private String mountPath = "/home/gaian/Videos/content-data";

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private NginxProperties nginxProperties;

	@Override
	public ContentResponse uploadFile(ContentRequest request) throws IOException {
		log.info("-----FileSystemServiceImpl class, uploadFile method.-----");

		String fileName = getFileName(request);
		Path path = getFilePath(request).toAbsolutePath().normalize();

		log.info("-----FleName and path : {} and {}", fileName, path);

		try {
			Files.createDirectories(path);
			Files.copy(request.getFile().getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error("-----Storeage error : {}", e.getMessage());
			e.printStackTrace();
		}

		final FileProperties fileProperties = getFileProperties(request.getFile(), path, fileName);
		log.info("-----FileProperties : {}", fileProperties);

		ContentInfo contentInfo = new ContentInfo();

		contentInfo.setLastModifiedBy(UUID.randomUUID().toString());
		contentInfo.setCreatedBy(UUID.randomUUID().toString());
		contentInfo.setContentLength(fileProperties.getSize());
		contentInfo.setContentType(fileProperties.getType());
		contentInfo.setFileExtension(fileProperties.getExtension());
		contentInfo.setFileName(fileProperties.getName());
		contentInfo.setMd5CheckSum(DigestUtils.md5Hex(request.getFile().getInputStream()));

		Path p = Paths
				.get(new StringBuilder().append(FileSystems.getDefault().getSeparator())
						.append(!ObjectUtils.isEmpty(request.getFilePath()) ? request.getFilePath() : "").toString())
				.toAbsolutePath().normalize();
		contentInfo.setFilePath(p.toString());
		contentInfo.setDescription(request.getDescription());
		contentInfo.setServiceId(UUID.randomUUID().toString());
		contentInfo.setTenantId(UUID.randomUUID().toString());
		contentInfo.setUserId(UUID.randomUUID().toString());
		contentInfo.setCreatedDate(new Date());
		contentInfo.setLastModifiedDate(new Date());

		if (!CollectionUtils.isEmpty(request.getTags()))
			contentInfo.setTags(request.getTags());

		contentInfo.getMetaData().put("dimensions", fileProperties.getDimension());

		contentInfo = contentRepository.save(contentInfo);

		String uriComponents = "http://localhost:8580/v1.0/file-system/view/" + contentInfo.getId();
		String downloadUrl = "http://localhost:8580/v1.0/file-system/download/" + contentInfo.getId();

		return new ContentResponse(contentInfo.getId(), HttpStatus.OK, "Success", uriComponents, downloadUrl,
				request.getDescription());

	}

	/**
	 * 
	 * @param file
	 * @param path
	 * @param fileName
	 * @return
	 */
	private FileProperties getFileProperties(MultipartFile file, Path path, String fileName) {

		FileProperties properties = new FileProperties();
		properties.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
		properties.setName(fileName);
		properties.setSize(file.getSize());
		properties.setType(file.getContentType());
		if (file.getContentType().startsWith("image")) {
			properties.setDimension(getImageDimension(path.resolve(fileName).toString()));
		}
		return properties;
	}

	/**
	 * 
	 * @param imagePath
	 * @return
	 */
	private Dimension getImageDimension(String imagePath) {
		try {
			log.info("-----ImagePath : {}", imagePath);
			final BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
			return new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
		} catch (IOException ex) {
			log.error("{}", ex);
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Path getFilePath(ContentRequest request) {
		if (!ObjectUtils.isEmpty(request.getFilePath())) {
			return Paths.get(basePath, request.getFilePath());
		}
		return Paths.get(basePath);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private String getFileName(ContentRequest request) {
		log.info("----FileSystemServiceImpl Class, getFileName method : original FileName  : {} and madeUpName : {}",
				request.getFile().getOriginalFilename(), request.getFileName());

		return new StringBuilder()
				.append(ObjectUtils.isEmpty(request.getFileName())
						? FilenameUtils.getBaseName(request.getFile().getOriginalFilename())
						: request.getFileName())
				.append(".").append(FilenameUtils.getExtension(request.getFile().getOriginalFilename())).toString();
	}

	/**
	 * 
	 */
	@Override
	public String getContentUri(UUID id) {

		Optional<ContentInfo> info = contentRepository.findById(id);
		if (info.isPresent()) {
			log.info("-----ContentInfo : {}", info);
			UriComponents uriComponent = UriComponentsBuilder.newInstance().scheme(nginxProperties.getScheme())
					.host(nginxProperties.getHost()).port(nginxProperties.getPort()).path(nginxProperties.getPath())
					.path(info.get().getFilePath()).path(FileSystems.getDefault().getSeparator())
					.path(info.get().getFileName()).encode().build();

			log.info("-----URI Component  : {}", uriComponent.toString());
			return uriComponent.toString();
		}
		throw new RuntimeException("ContentInfo not found.....!!!!!");
	}

	@Override
	public Resource getContent(UUID id) {
		Optional<ContentInfo> info = contentRepository.findById(id);
		if (info.isPresent()) {
			log.info("-----ContentInfo : {}", info);
			Path path = Paths.get(mountPath, info.get().getFilePath(), info.get().getFileName()).toAbsolutePath()
					.normalize();
			try {
				Resource resource = new UrlResource(path.toUri());
				return resource;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		throw new RuntimeException("ContentInfo not found.....!!!!!");
	}

}
