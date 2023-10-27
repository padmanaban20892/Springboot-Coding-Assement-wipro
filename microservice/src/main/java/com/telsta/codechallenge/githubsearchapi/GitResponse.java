package com.telsta.codechallenge.githubsearchapi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class GitResponse {
	private List<GitSearch> data;
}
