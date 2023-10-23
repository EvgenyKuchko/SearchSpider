package com.project.SearchSpider.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchResult {
    private String link;
    private String title;
    private String salary;
    private String company;
    private String location;
    private String experience;
}