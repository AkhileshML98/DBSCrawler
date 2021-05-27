package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrawlerPageInfo {

	private static final int MAX_PAGES = 10;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	private int cont = 0;
	public List<String> urlList = new ArrayList<String>();
 	@Autowired
	SpiderLeg leg;

	public CrawlerPageInfo(SpiderLeg leg) {
		super();
		this.leg = leg;
	}

	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextUrl));
		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}

	public List<String> search(String url, String searchWord) {
		while (this.pagesVisited.size() < MAX_PAGES) {
			String currentUrl;
			
			if (this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
			} else {
				currentUrl = this.nextUrl();
			}
			leg.crawl(currentUrl);
			boolean success = leg.searchForWord(searchWord);

			if (success) {				
				urlList.add(currentUrl);
				cont++;  //total 10 out put it will return
				if (cont > 10) {
					break;
				}

			}
			this.pagesToVisit.addAll(leg.getLinks());
		}		
		urlList.stream().forEach(s -> System.out.println(s));
		if (!urlList.isEmpty())
			return urlList;
		else {
			urlList.add("NO Match Found");
			return urlList;
		}
					

	}
}
