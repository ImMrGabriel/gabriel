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
public class HTMLParserHH {
    public static void main(String args[]) {



        // JSoup Example 3 - Parsing an HTML file in Java
        Document htmlFile = null;
        try {
            htmlFile = Jsoup.connect("http://hh.ua/search/vacancy?text=java+Kiev&page=1").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(htmlFile.body());
//        System.out.println(htmlFile.select("vacancy-list-item__name"));

        if(htmlFile != null) {
            HtmlToPlainText formatter = new HtmlToPlainText();

            Elements titles = htmlFile.select("div.vacancy-list-item__name");
            Iterator<Element> iteratorTitles = titles.iterator();

            Elements companies = htmlFile.select("div.vacancy-list-item__company");
            Iterator<Element> iteratorCompanies = companies.iterator();

            while (iteratorTitles.hasNext() && iteratorCompanies.hasNext()) {
                System.out.print(formatter.getPlainText(iteratorTitles.next()));
                System.out.println(" / "+ formatter.getPlainText(iteratorCompanies.next()));
            }
        }
//        for (Element element : htmlFile.select("div.vacancy-list-item__name")) {
//            System.out.println(formatter.getPlainText(element));
//        }

    }
}
