package com.project.SearchSpider.controller;

import com.project.SearchSpider.model.SearchResult;
import com.project.SearchSpider.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping()
    public String showSearchPage(Model model) {
        List<SearchResult> searchResults = new ArrayList<>();
        model.addAttribute("searchResults", searchResults);
        return "search";
    }

    @PostMapping()
    public String searchData(@RequestParam String query, Model model) {
        List<SearchResult> searchResults = searchService.getData(query);
        model.addAttribute("searchResults", searchResults);
        return "search";
    }
}