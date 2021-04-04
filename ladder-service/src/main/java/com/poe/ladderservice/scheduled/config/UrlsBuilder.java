package com.poe.ladderservice.scheduled.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poe.ladderservice.service.LeagueService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UrlsBuilder {

	@Getter
	List<Map<String, String>> urls = new ArrayList<>();

	@Autowired
	private UrlsConfig urlsConfig;

	@Autowired
	private LeagueService leagueService;

	Map<String, String> delveUrls = new HashMap<>();
	Map<String, String> uberLabUrls = new HashMap<>();
	Map<String, String> raceTo100Urls = new HashMap<>();

	@PostConstruct
	public void init() {
		buildUrls();
	}

	private void buildUrls() {
		List<String> leagueNames = leagueService.getLeagueNames();
		for (String leagueName : leagueNames) {
			buildDelveUrls(leagueName);
			buildUberLabUrls(leagueName);
			buildRaceTo100Urls(leagueName);
		}
		urls.add(delveUrls);
		urls.add(uberLabUrls);
		urls.add(raceTo100Urls);
	}
	
	public String buildDelveUrls(String leagueName) {
		String url = String.format(urlsConfig.delve, leagueName);
		delveUrls.put(leagueName, url);
		return url;
	}

	public String buildUberLabUrls(String leagueName) {
		String url = String.format(urlsConfig.uberLab, leagueName);
		uberLabUrls.put(leagueName, url);
		return url;
	}

	public String buildRaceTo100Urls(String leagueName) {
		String url = String.format(urlsConfig.raceTo100, leagueName);
		raceTo100Urls.put(leagueName, url);
		return url;
	}

}
		