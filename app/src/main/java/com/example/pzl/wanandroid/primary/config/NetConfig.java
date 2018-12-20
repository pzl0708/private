package com.example.pzl.wanandroid.primary.config;

public class NetConfig {
    public static int apiType = 1;
    public static String BASE_URL;

    static {
        if (apiType == 1) {
            BASE_URL = "http://www.wanandroid.com/";
        }
    }
}