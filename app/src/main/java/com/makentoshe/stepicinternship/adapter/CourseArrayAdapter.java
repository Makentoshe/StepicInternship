package com.makentoshe.stepicinternship.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.UserModel;

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
    private final Runnable runnable;

    public CourseArrayAdapter(Context context, List<SearchModel.SearchResult> values, Runnable runnable) {
        super(context, R.layout.listview_item_course, values);
        this.context = context;
        this.values = values;
        this.runnable = runnable;
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
        setBitmap(result, previewImage);
        setAuthor(result, author);
        if (position == values.size() - 1) {
            System.out.println("REACH BOTTOM! LOAD THE FOOKIN' DATA");
            runnable.run();
        }
        return rowView;
    }

    private void setBitmap(SearchModel.SearchResult course, ImageView imageView) {
        //if not save data - skip, else try to get it.
        if (!StepicInternship.save_memory_mode) {
            if (course.getPreview() != null) {
                imageView.setImageBitmap(course.getPreview());
                return;
            }
        }
        //no data - download it
        Call<ResponseBody> call = StepicInternship.getApi().getCover(course.getCourseCover());
        call.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                                imageView.setImageBitmap(bitmap);
                                //if not save memory - skip
                                if (!StepicInternship.save_memory_mode){
                                    course.setPreview(bitmap);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), "Failed to load preview", Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                }
        );

    }

    private void setAuthor(SearchModel.SearchResult course, TextView view) {
        //if not save data - skip, else try to get it.
        if (!StepicInternship.save_memory_mode) {
            if (course.getPreview() != null) {
                view.setText(course.getAuthor());
                return;
            }
        }
        //no data - download it
        StringBuilder sb = new StringBuilder();
        for (Integer id : course.getCourseAuthors()) {
            Call<UserModel> call = StepicInternship.getApi().getUser(id);
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    sb.append(", ").append(response.body().getUsers().get(0).getFullName());
                    String text = sb.toString().substring(2);
                    view.setText(text);
                    //if not save memory - skip
                    if (!StepicInternship.save_memory_mode) {
                        course.setAuthor(text);
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    Toast.makeText(getContext(), "Failed to load authors", Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        }
    }

    public void onDestroy() {
        for (SearchModel.SearchResult value : values) {
            if (value.getPreview() != null){
                value.getPreview().recycle();
                value.setPreview(null);
            }
        }
    }
}
