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

    public List<SearchResult> getData(String query) {
        query = query.replace(" ", "+");
        List<SearchResult> results = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://rabota.by/search/vacancy?ored_clusters=true&text=" + query + "&search_period=3").get();

            Elements repositories = doc.getElementsByClass("serp-item");

            for (Element repository : repositories) {
                SearchResult sr = new SearchResult();
                sr.setLink(repository.getElementsByClass("serp-item__title").attr("href"));
                sr.setTitle(repository.getElementsByClass("serp-item__title").text());
                sr.setSalary(repository.getElementsByClass("bloko-header-section-2").text());
                sr.setCompany(repository.getElementsByClass("bloko-link bloko-link_kind-tertiary").text());
                String loc = repository.getElementsByClass("vacancy-serp-item__info").text();
                sr.setLocation(loc.replace(sr.getCompany(), ""));
                sr.setExperience(repository.getElementsByClass("bloko-h-spacing-container bloko-h-spacing-container_base-0").text());
                results.add(sr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}