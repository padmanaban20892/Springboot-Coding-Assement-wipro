package com.telsta.codechallenge.githubsearchapi;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GitController {
	
	private final GitService gitService;

	  public GitController(
			  GitService gitService) {
	    this.gitService = gitService;
	  }

	  @GetMapping(path = "/gitSearchRepos-lastweek")
	  public List<GitSearch> getGitSearchRepobyLastWeek() {
		  
		//Getting the current Date value
	      LocalDate currentDate = LocalDate.now();
	      System.out.println("Current date: "+currentDate);
	      //Adding one week to the current date
	      LocalDate oneweekBefore = currentDate.plus(-1, ChronoUnit.WEEKS);
	      System.out.println("oneweekBefore date: "+oneweekBefore);
	    return gitService.getGitSearchRepositoriesforLastWeek(oneweekBefore.toString(),currentDate.toString());
	  }
	  
	  @GetMapping(path = "/gitSearchReposbyzerofollowers")
	  public List<GitSearch> getGitSearchRepobyzerofollowers() {
	    return gitService.getGitSearchRepositoriesbyzeroFollowers();
	  }
}
