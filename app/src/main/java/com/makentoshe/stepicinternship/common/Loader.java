package com.makentoshe.stepicinternship.common;


import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.common.model.SectionModel;
import com.makentoshe.stepicinternship.common.model.UnitModel;
import com.makentoshe.stepicinternship.func.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Makentoshe on 27.04.2018.
 */

public class Loader {

    public static void loadCourse(int courseID, Consumer<CourseModel.Course> consumer){
        Callback<CourseModel> callback = new Callback<CourseModel>() {
            @Override
            public void onResponse(Call<CourseModel> call, Response<CourseModel> response) {
                CourseModel.Course course = response.body().getCourses().get(0);
                consumer.accept(course);
            }

            @Override
            public void onFailure(Call<CourseModel> call, Throwable t) {
                t.printStackTrace();
                consumer.accept(null);
            }
        };
        Call<CourseModel> call = StepicInternship.getApi().getCourseData(courseID);
        call.enqueue(callback);
    }

    public static void loadSection(int sectionID, Consumer<SectionModel.Section> consumer){
        Call<SectionModel> call = StepicInternship.getApi().getSectionData(sectionID);
        call.enqueue(new Callback<SectionModel>() {
            @Override
            public void onResponse(Call<SectionModel> call, Response<SectionModel> response) {
                SectionModel.Section section = response.body().getSections().get(0);
                consumer.accept(section);
            }

            @Override
            public void onFailure(Call<SectionModel> call, Throwable t) {
                t.printStackTrace();
                consumer.accept(null);
            }
        });
    }

    public static void loadUnit(int unitID, Consumer<UnitModel.Unit> consumer){
        Call<UnitModel> ucall = StepicInternship.getApi().getUnitData(unitID);
        ucall.enqueue(new Callback<UnitModel>() {

            @Override
            public void onResponse(Call<UnitModel> call, Response<UnitModel> response) {
                UnitModel.Unit unit = response.body().getUnits().get(0);
                consumer.accept(unit);
            }

            @Override
            public void onFailure(Call<UnitModel> call, Throwable t) {
                t.printStackTrace();
                consumer.accept(null);
            }
        });
    }

    public static void loadLesson(int lessonID, Consumer<LessonModel.Lesson> consumer){
        Call<LessonModel> lcall = StepicInternship.getApi().getLessonData(lessonID);
        lcall.enqueue(new Callback<LessonModel>() {

            @Override
            public void onResponse(Call<LessonModel> call, Response<LessonModel> response) {
                LessonModel.Lesson lesson = response.body().getLessons().get(0);
                consumer.accept(lesson);
            }

            @Override
            public void onFailure(Call<LessonModel> call, Throwable t) {
                t.printStackTrace();
                consumer.accept(null);
            }
        });
    }
}
