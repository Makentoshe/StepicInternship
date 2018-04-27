package com.makentoshe.stepicinternship.common;

import android.content.Context;
import android.widget.Toast;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.common.model.SearchModel;

/**
 * Created by Makentoshe on 27.04.2018.
 */

public class Favorites {

    public static void add(Context context, SearchModel.SearchResult rawCourse) {
        Toast.makeText(context, context.getResources().getString(R.string.loading) + " " + rawCourse.getCourseTitle(),
                Toast.LENGTH_SHORT).show();
    }

}
