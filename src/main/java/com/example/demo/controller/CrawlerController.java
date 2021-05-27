package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UrlAndWord;
import com.example.demo.service.CrawlerPageInfo;
import com.example.demo.service.SpiderLeg;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CrawlerController {
	
	@Autowired
	CrawlerPageInfo crawlerPageInfo;
	
	
	/*Sample Json to test in postman
	{
	    "url":"http://arstechnica.com/", 
	    "searchString":"cars"
	}*/
	
	 @PostMapping("/crowler")
	    public  List<String> searchWord(@RequestBody UrlAndWord UrlAndWord ) {
		 
		 List<String> urlList=crawlerPageInfo.search(UrlAndWord.getUrl(), UrlAndWord.getSearchString());	      
	     return  urlList;
	    }
	
	

}
