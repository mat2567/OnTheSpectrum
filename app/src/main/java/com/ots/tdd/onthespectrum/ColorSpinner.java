package com.ots.tdd.onthespectrum;

/**
 * Created by francestsenn on 11/5/17.
 */
import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorSpinner extends ArrayAdapter<Integer> {

    LayoutInflater inflater;
    Integer[] objects;
    ViewHolder holder = null;

    public ColorSpinner(Context context, int textViewResourceId, Integer[] objects) {
        super(context, textViewResourceId, objects);
        inflater = ((Activity) context).getLayoutInflater();
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        Integer listItem = objects[position];
        View row = convertView;

        if (null == row) {
            holder = new ViewHolder();
            row = inflater.inflate(R.layout.spinner_row, parent, false);
            holder.colorSwatch = (ImageView) row.findViewById(R.id.colorSwatch);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.colorSwatch.setBackgroundResource(listItem);

        return row;
    }

    static class ViewHolder {
        ImageView colorSwatch;
    }
}