package com.market.carmarketservice.service.news;

import com.market.carmarketservice.exception.LinkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetNewsServiceImpl implements GetNewsService {
    public Object getPost(String link) {
        try {
            Document document = Jsoup.connect(link).timeout(5000).get();
            Element page = document.select("div[class=detail__main]").first();
            return page.html();
        } catch (Exception e) {
            return new LinkNotFoundException();
        }
    }

    public Object getRss(String link) {
        try {
            Document document = Jsoup.connect(link).timeout(5000).get();
            JSONObject jsonObject = XML.toJSONObject(document.html());
            return jsonObject.toString();
        } catch (Exception e) {
            return new LinkNotFoundException();
        }
    }
}
