package com.example.quack;

public class Event {

    /** Title of the earthquake event */
    private String title;

    /** Time that the earthquake happened (in milliseconds) */
    private long time;

    private String url;

    public Event(String eventTitle, long eventTime, String eventUrl) {
        title = eventTitle;
        time = eventTime;
        url = eventUrl;

    }

    public String getTitle() {
        return title;
    }

    public long getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

}
