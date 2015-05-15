package com.github.immrgabriel.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ser88 on 18.11.2014.
 */
public class MyParseTag {
    public static void main(String[] args) {
        String str = "Info about Leela <span xml:lang=\"en\" lang=\"en\"><b><span>Turanga Leela\n" + "</span></b></span>";
//        String str = "Info about Leela <span xml:lang=\"en\" lang=\"en\"><b><span>Turanga Leela</span\n></b></span><span>AAA</span >";
        String tag = "span";
        System.out.println(str);
        System.out.println("***********************************************************************************************************");
        parseTag(str, tag);
    }

    private static void parseTag(String str, String tag) {
        Pattern patternOC = Pattern.compile("<[\\p{Space}]*?" + tag + "[\\p{Space}]*?.*?>" + "|" + "<[\\p{Space}]*?/[\\p{Space}]*?" + tag + "[\\p{Space}]*?>");
        Pattern patternO = Pattern.compile("<[\\p{Space}]*?" + tag + "[\\p{Space}]*?.*?>");
//        Pattern patternC = Pattern.compile("<[\\p{Space}]*?/[\\p{Space}]*?" + tag + "[\\p{Space}]*?>");
        Matcher matcherOC = patternOC.matcher(str);
        int count = 0;
        int iStart = 0;
        int iEnd = 0;
        boolean isInner = false;
        while(matcherOC.find()) {
            if(count == 0)
                iStart = matcherOC.start();
            count += patternO.matcher(matcherOC.group()).matches() ? 1 : -1;
            if(count > 1)
                isInner = true;
            if(count == 0) {
                iEnd = matcherOC.end();
                System.out.println(str.substring(iStart, iEnd));
                if(isInner) {
                    isInner = false;
                    parseTag(str.substring(iStart + 1, iEnd - 1), tag);
                }
            }
        }
    }
}
