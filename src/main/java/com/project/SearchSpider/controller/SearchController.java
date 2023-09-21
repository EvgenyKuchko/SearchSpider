package com.project.SearchSpider.controller;

import com.project.SearchSpider.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping()
    public String showSearchPage() {
        return "search";
    }

    @PostMapping()
    public String searchData(@RequestParam String query) {
        searchService.getPage(query);
        return "redirect/result";
    }
}