package com.project.SearchSpider.service;

import com.project.SearchSpider.model.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchService {

    public List<SearchResult> getData(String query) {
        log.info("start execution of the getData() method");
        query = query.replace(" ", "+");
        List<SearchResult> results = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("http://rabota.by/search/vacancy?ored_clusters=true&text=" + query + "&search_period=3").get();
            log.debug("get data page");

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
            log.error("Error occurred: {}", e.getMessage());
        }
        log.info("finish execution of the getData() method");
        return results;
    }
}