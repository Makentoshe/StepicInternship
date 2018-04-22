package com.makentoshe.stepicinternship.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.model.SearchModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        setBitmap(result.getCourseCover(), previewImage);
        return rowView;
    }

    private void setBitmap(String url, ImageView imageView) {
        Call<ResponseBody> call = StepicInternship.getApi().getCover(url);
        call.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                imageView.setImageBitmap(BitmapFactory.decodeStream(response.body().byteStream()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                }
        );
    }

}
