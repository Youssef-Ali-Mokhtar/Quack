package com.example.quack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.core.content.ContextCompat;

public class QuakeAdapter extends ArrayAdapter<QuakeEvent> {

    private static final String LOCATION_SEPARATOR = " of ";
    String primaryLocation;
    String locationOffset;

    public QuakeAdapter(Context context, ArrayList<QuakeEvent> quakeEvents) {
        super(context,0, quakeEvents);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item2, parent, false);
        }

        QuakeEvent currentQuakeEvent = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_2);
        String formattedMagnitude = formatMagnitude(Double.parseDouble(currentQuakeEvent.getMagnitude()));
        magnitudeTextView.setText(formattedMagnitude);
        int magnitudeColor = ContextCompat.getColor(getContext(), getMagnitudeColor(Double.parseDouble(currentQuakeEvent.getMagnitude())));

        magnitudeTextView.getBackground().setColorFilter(magnitudeColor, PorterDuff.Mode.MULTIPLY);
        //ColorDrawable magnitudeCircle = (ColorDrawable) listItemView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
       // int magnitudeColor = getMagnitudeColor(Double.parseDouble(currentQuakeEvent.getMagnitude()));
        // Set the color on the magnitude circle
       // magnitudeTextView.setTextColor(magnitudeColor);


        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_2);
        titleTextView.setText(getTitleString(currentQuakeEvent.getTitle()));

        TextView titleTextView2 = (TextView) listItemView.findViewById(R.id.title2_2);
        titleTextView2.setText(getTitleString2(currentQuakeEvent.getTitle()));

        // Display the earthquake date in the UI
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_2);
        dateTextView.setText(getDateString(currentQuakeEvent.getTime()));



        // Get the appropriate background color based on the current earthquake magnitude



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

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return magnitudeColorResourceId;
    }


}
