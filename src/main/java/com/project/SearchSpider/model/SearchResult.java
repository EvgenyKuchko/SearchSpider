package com.project.SearchSpider.model;

import lombok.*;

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