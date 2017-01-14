package com.brianroper.androidweekly.model;

/**
 * Created by brianroper on 1/9/17.
 */

public class Volume {
    private String headline;
    private String source;
    private String summary;
    private String issue;
    private int id;

    public String getTitle() {
        return headline;
    }

    public void setTitle(String headline) {
        this.headline = headline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
