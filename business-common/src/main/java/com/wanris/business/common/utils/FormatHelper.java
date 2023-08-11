package com.wanris.business.common.utils;

public class FormatHelper {
    public static String formatHtmlTag(String htmlStr) {
        return htmlStr.replaceAll("<img", "<img style=\"display:block;width:100%;height:auto;padding:0;margin:0\"")
                .replaceAll("<table", "<table style=\"display:;width:100%;height:auto\"")
                .replaceAll("<p", "<p style=\"padding:0;margin:0\"")
                .replaceAll("&nbsp;", "")
                .replaceAll("<br/>", "")
                .replaceAll("src=\"//", "src=\"http://")
                .replaceAll("src='//", "src='http://")
                .replaceAll("href=\"//", "href=\"http://")
                .replaceAll("href='//", "href='http://")
                .replaceAll("cssurl=\"//", "cssurl=\"http://")
                .replaceAll("cssurl='//", "cssurl='http://");
    }
}
