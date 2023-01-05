package com.itachay.ldapauthentication.domain;

public class LDAPServerConfiguration {

    private String userDnPatterns;
    private String url;

    public LDAPServerConfiguration(String userDnPatterns, String url) {
        this.userDnPatterns = userDnPatterns;
        this.url = url;
    }

    public String getUserDnPatterns() {
        return userDnPatterns;
    }

    public void setUserDnPatterns(String userDnPatterns) {
        this.userDnPatterns = userDnPatterns;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}