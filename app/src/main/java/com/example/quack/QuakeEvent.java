package com.example.quack;

public class QuakeEvent {

    /** Title of the earthquake event */
    private String title;

    /** Time that the earthquake happened (in milliseconds) */
    private long time;

    private String magnitude;

    private String url;
    /**
     * Constructs a new {@link Event}.
     *
     * @param eventTitle is the title of the earthquake event
     * @param eventTime is the time the earhtquake happened
     */
    public QuakeEvent(String eventMagnitude, String eventTitle, long eventTime, String eventUrl) {
        title = eventTitle;
        time = eventTime;
        magnitude = eventMagnitude;
        url = eventUrl;
    }

    public String getTitle() {
        return title;
    }

    public long getTime() {
        return time;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getUrl() { return url; }


}
