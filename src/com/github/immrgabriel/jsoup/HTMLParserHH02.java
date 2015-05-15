package com.github.immrgabriel.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

/**
 * Java Program to parse/read HTML documents from File using Jsoup library.
 * Jsoup is an open source library which allows Java developer to parse HTML
 * files and extract elements, manipulate data, change style using DOM, CSS and
 * JQuery like method.
 */
public class HTMLParserHH02 {
    public static void main(String args[]) {



        // JSoup Example 3 - Parsing an HTML file in Java
        Document htmlFile = null;
        try {
            htmlFile = Jsoup.connect("http://hh.ua/search/vacancy?text=java+Kiev&page=0").
            userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36").
                    referrer("http://google.com").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(htmlFile != null) {
            HtmlToPlainText formatter = new HtmlToPlainText();

            Elements elements = htmlFile.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            for(Element element : elements) {
                System.out.println("***************************************");
                System.out.println("title:" + element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                System.out.println("employer:" + element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                System.out.println("address:" + element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                System.out.println("htmlFile.title() = " + htmlFile.title());
                System.out.println("url:" + element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                System.out.println("compensation:" + element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                System.out.println("***************************************");
            }
        }

    }
}
