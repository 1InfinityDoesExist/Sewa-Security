package com.oauth.security.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import com.oauth.security.model.request.SearchRequest;
import com.oauth.security.model.response.SearchResponse;
import com.oauth.security.service.SearchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SearchServiceImpl implements SearchService {

	@Autowired
	private RestHighLevelClient esClient;

	@Value("${search.regex:*%s*}")
	private String searchRegex;

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public SearchResponse searchUsingPOST(SearchRequest searchRequest, Pageable pageable) throws IOException {

		String keyword = searchRequest.getKeyword();
		if (ObjectUtils.isEmpty(keyword)) {
			keyword.replaceAll(" ", "*"); // check
		}

		log.info("-----Keyword : {} ", keyword);

		HashSet<String> indices = new HashSet<String>();

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		searchRequest.getIndices().entrySet().stream().forEach(entry -> {
			indices.add(entry.getKey());
			buildQuery(queryBuilder, keyword, entry);
		});

		log.info("-----Query : {}", queryBuilder);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(queryBuilder)
				.from(pageable.getPageNumber() * pageable.getPageSize()).size(pageable.getPageSize()).explain(true);

		Optional.ofNullable(pageable.getSort()).map(Sort::iterator)
				.ifPresent(iterator -> iterator.forEachRemaining(
						order -> sourceBuilder.sort(order.getProperty(), Optional.ofNullable(order.getDirection())
								.map(Direction::toString).map(SortOrder::fromString).orElse(SortOrder.ASC))));

		org.elasticsearch.action.search.SearchRequest request = new org.elasticsearch.action.search.SearchRequest()
				.indices(Iterables.toArray(indices, String.class)).source(sourceBuilder);

		org.elasticsearch.action.search.SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

		SearchHits searchHits = response.getHits();

		SearchHit[] searchHit = searchHits.getHits();

		List<JSONObject> searchResponse = Arrays.stream(searchHit).map(document -> {
			JSONObject obj = null;
			try {
				obj = (JSONObject) new JSONParser().parse(document.getSourceAsString());
				obj.put("id", document.getId());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return obj;
		}).filter(Objects::nonNull).collect(Collectors.toList());

		return SearchResponse.builder().pageNumber(pageable.getPageNumber()).pageSize(pageable.getPageSize())
				.totalRecords(searchHits.getTotalHits().value).response(searchResponse).build();
	}

	private void buildQuery(BoolQueryBuilder queryBuilder, String keyword, Entry<String, List<String>> entry) {

		String str = "DBRef('tenant', 'tenantId')";
		str = str.replace("tenantId", "627b9c072c50de0001987e1f");

		System.out.println(str);

//		queryBuilder.must(QueryBuilders.matchQuery("providedByTenant", str));

		log.info("----Creating search query-----");
		BoolQueryBuilder indexQuery = QueryBuilders.boolQuery().must(QueryBuilders.typeQuery("product")).
		// .must(QueryBuilders.queryStringQuery(str).field("providedByTenant"))
				must(QueryBuilders.matchQuery("providedByTenant", str));

		;

		if (CollectionUtils.isEmpty(entry.getValue())) {
			indexQuery.must(QueryBuilders.queryStringQuery(String.format(keyword, searchRegex)));
		} else {
			BoolQueryBuilder fieldQuery = QueryBuilders.boolQuery();
			entry.getValue().stream().forEach(field -> {

				fieldQuery.should(QueryBuilders.queryStringQuery(String.format(searchRegex, keyword)).field(field));

			});

			indexQuery.must(fieldQuery);
		}
		queryBuilder.should(indexQuery);
	}
}
