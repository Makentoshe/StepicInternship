package com.makentoshe.stepicinternship.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makentoshe on 05.03.2018.
 */

public class BaseAutoCompleteAdapter extends BaseAdapter implements Filterable {

    protected final static int MAX_COUNT = 10;
    private final Context mContext;
    protected List<String> mAutocompleteList;

    public BaseAutoCompleteAdapter(Context context){
        this.mContext = context;
        mAutocompleteList = new ArrayList<>(MAX_COUNT);
    }

    @Override
    public int getCount() {
        return this.mAutocompleteList.size();
    }

    @Override
    public String getItem(int i) {
        return this.mAutocompleteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //проверка на наличие
        if (convertView == null) {
            //создаем
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            convertView = inflater.inflate(
                    android.R.layout.simple_dropdown_item_1line,
                    parent,
                    false
            );
        }
        //используем уже готовый
        String string = getItem(position);
        ((TextView) convertView).setText(string);
        return convertView;
    }

    /** {@inheritDoc} */
    @Override
    public Filter getFilter(){
        throw new RuntimeException("Write me");
    }

}