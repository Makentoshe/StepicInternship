package com.makentoshe.stepicinternship.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.common.model.SearchModel;

import java.util.List;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public class CourseArrayAdapter extends ArrayAdapter<SearchModel.SearchResult> {

    private final Context context;
    private final List<SearchModel.SearchResult> values;


    public CourseArrayAdapter(Context context, List<SearchModel.SearchResult> values) {
        super(context, R.layout.listview_item_course, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //try to inflate view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_item_course, parent, false);
        //define data
        TextView title = rowView.findViewById(R.id.ListView_Item_Course_Title);
        TextView author = rowView.findViewById(R.id.ListView_Item_Course_Author);
        ImageView previewImage = rowView.findViewById(R.id.ListView_Item_Course_PreviewImage);
        SearchModel.SearchResult result = values.get(position);
        //fill view
        title.setText(result.getCourseTitle());

        return rowView;
    }
}
