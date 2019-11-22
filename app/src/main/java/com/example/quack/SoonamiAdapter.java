package com.example.quack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SoonamiAdapter extends ArrayAdapter<Event> {

    private static final String LOCATION_SEPARATOR = " of ";
    String primaryLocation;
    String locationOffset;

    public SoonamiAdapter(Context context, ArrayList<Event> events) {
        super(context,0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Event currentEvent = getItem(position);


        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        titleTextView.setText(getTitleString(currentEvent.getTitle()));

        TextView titleTextView2 = (TextView) listItemView.findViewById(R.id.title2);
        titleTextView2.setText(getTitleString2(currentEvent.getTitle()));

        // Display the earthquake date in the UI
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(getDateString(currentEvent.getTime()));


        return listItemView;
    }


    /**
     * Returns a formatted date and time string for when the earthquake happened.
     */
    private String getDateString(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy "+"\nHH:mm:ss"+"\nz");
        return formatter.format(timeInMilliseconds);
    }

    private String getTitleString(String originalLocation) {

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        String formattedTitle = locationOffset;
        return formattedTitle;
    }

    private String getTitleString2(String originalLocation) {

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        String formattedTitle = primaryLocation;
        return formattedTitle;
    }


}
