package com.github.immrgabriel.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ser88 on 18.11.2014.
 */
public class TestParseTag {
    public static void main(String[] args) {
//        String str = "Info about Leela <span xml:lang=\"en\" lang=\"en\"><b><span>Turanga Leela\n" + "</span></b></span>";
        String str = "Info about Leela <span xml:lang=\"en\" lang=\"en\"><b><span>Turanga Leela</span\n></b></span><span>AAA</span >";
        String tag = "span";
        System.out.println(parseTag(str, tag));
    }

    private static String parseTag(String str, String tag) {
        System.out.println(str);
        System.out.println("***********************************************************************************************************");
        Matcher matcherOpen = Pattern.compile("<[\\p{Space}]*?" + tag + "[\\p{Space}]*?.*?>").matcher(str);
        Matcher matcherClose = Pattern.compile("<[\\p{Space}]*?/[\\p{Space}]*?" + tag + "[\\p{Space}]*?>").matcher(str);
        Matcher matcher = Pattern.compile("<[\\p{Space}]*?" + tag + "[\\p{Space}]*?.*?>" + "|" + "<[\\p{Space}]*?/[\\p{Space}]*?" + tag + "[\\p{Space}]*?>").matcher(str);
        System.out.println("open: ");
        while(matcherOpen.find()) {
            System.out.println(String.format("\"%s\" starting at index %d and ending at index %d.",
                    matcherOpen.group(),
                    matcherOpen.start(),
                    matcherOpen.end()));
        }
        System.out.println("close: ");
        while(matcherClose.find()) {
            System.out.println(String.format("\"%s\" starting at index %d and ending at index %d.",
                    matcherClose.group(),
                    matcherClose.start(),
                    matcherClose.end()));
        }
        System.out.println("All: ");
        while(matcher.find()) {
            System.out.print(String.format("\"%s\" starting at index %d and ending at index %d.",
                    matcher.group(),
                    matcher.start(),
                    matcher.end()));
            System.out.println("                                " + Pattern.compile("<[\\p{Space}]*?/[\\p{Space}]*?" + tag + "[\\p{Space}]*?>").matcher(matcher.group()).matches());
        }

        return null;
    }
}
