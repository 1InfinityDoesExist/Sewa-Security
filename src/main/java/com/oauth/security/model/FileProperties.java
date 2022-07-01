package com.oauth.security.model;

import java.awt.Dimension;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class FileProperties implements Serializable {

	private String name;
	private String extension;
	private long size;
	private String type;
	private Dimension dimension;

}