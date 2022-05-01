package com.oauth.security.model.response;

import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {
	private long pageNumber;
	private long pageSize;
	private long totalRecords;
	private List<JSONObject> response;

}
