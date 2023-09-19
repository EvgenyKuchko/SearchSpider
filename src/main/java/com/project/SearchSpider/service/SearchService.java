package com.project.SearchSpider.service;

import com.project.SearchSpider.model.SearchResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    public void getPage(String query) {
        query = query.replace(" ", "+");
        List<SearchResult> results = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://rabota.by/search/vacancy?ored_clusters=true&text=" + query + "&search_period=3").get();

            Elements repositories = doc.getElementsByClass("vacancy-serp-item__layout");

            for (Element repository : repositories) {
                SearchResult sr = new SearchResult();
                // Extract the link
                sr.setLink(repository.getElementsByClass("serp-item-title").attr("href"));
                // Extract the title
                sr.setTitle(repository.getElementsByClass("serp-item-title").text());
                // Extract the salary
                sr.setSalary(repository.getElementsByClass("bloko-header-section-2").text());
                // Extract the company
                sr.setCompany(repository.getElementsByClass("bloko-link bloko-link_kind-tertiary").text());
                // Extract the location
                sr.setLocation(repository.getElementsByClass("bloko-text").text());
                // Extract the exp
                sr.setExperience(repository.getElementsByClass("bloko-h-spacing-container bloko-h-spacing_container_base-0").text());
                results.add(sr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(SearchResult sr : results) {
            System.out.println(sr);
        }
    }
}