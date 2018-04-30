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

import com.google.gson.Gson;
import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.Favorites;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.UserModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public class FavCourseArrayAdapter extends ArrayAdapter<File> {

    private final List<CourseModel.Course> courses = new ArrayList<>();
    private final List<File> values;
    private List<Bitmap> previews = new ArrayList<>();

    public FavCourseArrayAdapter(Context context, List<File> data) {
        super(context, R.layout.listview_item_course, data);
        this.values = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //try to inflate view
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_item_course, parent, false);
        //define data
        TextView title = rowView.findViewById(R.id.ListView_Item_Course_Title);
        TextView author = rowView.findViewById(R.id.ListView_Item_Course_Author);
        ImageView previewImage = rowView.findViewById(R.id.ListView_Item_Course_PreviewImage);

        File courseFolder = values.get(position);
        //for each file in course folder
        for (final File courseFile : courseFolder.listFiles()){
            if (courseFile.getName().equals(Favorites.coverName)){
                try{
                    Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(courseFile));
                    previewImage.setImageBitmap(bitmap);
                    previews.add(bitmap);
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
            if (courseFile.getName().equals(Favorites.courseFileName)){
                try{
                    CourseModel.Course course = new Gson().fromJson(
                            new FileReader(courseFile), CourseModel.Course.class
                    );
                    courses.add(course);
                    title.setText(course.getTitle());
                    author.setText(course.getAuthor());
                } catch (FileNotFoundException fnfe){
                    fnfe.printStackTrace();
                }
            }
        }

        return rowView;
    }

    public List<CourseModel.Course> getCourses(){
        return courses;
    }

    public void onDestroy() {
        for (Bitmap value : previews) {
                value.recycle();
        }
        previews.clear();
    }
}
