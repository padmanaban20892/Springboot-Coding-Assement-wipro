package com.telsta.codechallenge.githubsearchapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GitService {

	@Value("gitSearch.base.url")
	private String gitSearchUrl;

	private final RestTemplate restTemplate;

	public GitService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<GitSearch> getGitSearchRepositoriesforLastWeek(String fromDate, String toDate) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity requestEntity = new HttpEntity<>(headers);

		Map<String, String> uriVariables = new HashMap<>();

		uriVariables.put("date", "created:" + fromDate + ".." + toDate);
		uriVariables.put("sortValue", "stars");
		uriVariables.put("orderValue", "desc");

		String mainUrl = gitSearchUrl + "/search/repositories?q={date}&sort={sortValue}&order={orderValue}";

		/*String gitSearchRespoResult = restTemplate.getForObject(mainUrl, String.class, uriVariables);
		System.out.println("userJson===>" + gitSearchRespoResult.toString());
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(mainUrl, String.class, uriVariables);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			root = mapper.readTree(responseEntity.getBody());
			System.out.println("===>" + root.toString());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

		GitResponse gitResponse = restTemplate.getForObject(mainUrl, GitResponse.class, uriVariables);
		List<GitSearch> gitSearchRepo = null;
		if (gitResponse != null) {
			gitSearchRepo = gitResponse.getData();
		}

		return gitSearchRepo;
	}

	public List<GitSearch> getGitSearchRepositoriesbyzeroFollowers() {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity requestEntity = new HttpEntity<>(headers);

		Map<String, String> uriVariables = new HashMap<>();

		uriVariables.put("date", "followers:0");
		uriVariables.put("sortValue", "joined");
		uriVariables.put("orderValue", "asc");

		String mainUrl = gitSearchUrl + "/search/users?q={date}&sort={sortValue}&order={orderValue}";
		String gitSearchRespoResult = restTemplate.getForObject(mainUrl, String.class, uriVariables);
		System.out.println("gitSearchRespoResult ===>" + gitSearchRespoResult.toString());
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(mainUrl, String.class, uriVariables);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			root = mapper.readTree(responseEntity.getBody());
			System.out.println("=Output==>" + root.toString());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		GitResponse gitResponse = restTemplate.getForObject(mainUrl, GitResponse.class, uriVariables);
		List<GitSearch> gitSearchRepo = null;
		if (gitResponse != null) {
			gitSearchRepo = gitResponse.getData();
		}

		return gitSearchRepo;
	}
}
