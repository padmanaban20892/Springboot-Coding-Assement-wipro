package com.telsta.codechallenge.githubsearchapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GitSearch {
	private String name;
	private String html_url;
	private String watchers_count;
	private String language;
	private String description;
	
	
	

}
