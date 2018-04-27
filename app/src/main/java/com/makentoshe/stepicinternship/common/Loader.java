package com.makentoshe.stepicinternship.common;


import android.widget.ProgressBar;

import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.common.model.SectionModel;
import com.makentoshe.stepicinternship.common.model.UnitModel;
import com.makentoshe.stepicinternship.func.Consumer;
import com.makentoshe.stepicinternship.func.TriConsumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Makentoshe on 27.04.2018.
 */

public class Loader {

    public static void loadCourse(int courseID, Consumer<CourseModel.Course> consumer) {
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

    public static void loadSection(int sectionID, Consumer<SectionModel.Section> consumer) {
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

    public static void loadUnit(int unitID, Consumer<UnitModel.Unit> consumer) {
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

    public static void loadLesson(int lessonID, Consumer<LessonModel.Lesson> consumer) {
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

    public static void loadCourseMainData(CourseModel.Course course,
                                          TriConsumer<
                                                  List<SectionModel.Section>,
                                                  List<List<UnitModel.Unit>>,
                                                  List<List<LessonModel.Lesson>>
                                                  > consumer) {
        loadCourseMainDataWithProgress(course, null, consumer);
    }

    public static void loadCourseMainDataWithProgress(CourseModel.Course course,
                                                      ProgressBar progressBar,
                                                      TriConsumer<
                                                              List<SectionModel.Section>,
                                                              List<List<UnitModel.Unit>>,
                                                              List<List<LessonModel.Lesson>>
                                                              > consumer) {
        List<SectionModel.Section> sectionList = new ArrayList<>(); //все сегменты
        List<List<LessonModel.Lesson>> lessonsList = new ArrayList<>(); //все уроки;
        List<List<UnitModel.Unit>> unitsList = new ArrayList<>();//все юниты

        //Заполняем пустыми данными, что бы занять место под все секции
        for (int i = 0; i < course.getSections().size(); i++) {
            lessonsList.add(null);
            unitsList.add(null);
        }
        //текущее количество секций
        final int[] sectionCount = {0};
        //для каждой секции
        for (Integer sectionID : course.getSections()) {

            //скачиваем её
            Loader.loadSection(sectionID, section -> {
                //лист со всеми уроками в текущей секции
                List<LessonModel.Lesson> lessons = new ArrayList<>();
                List<UnitModel.Unit> units = new ArrayList<>();
                //Заполняем пустыми данными, что бы занять место
                for (int i = 0; i < section.getUnits().size(); i++) {
                    lessons.add(null);
                    units.add(null);
                }
                //добавляем секцию в список секций
                sectionList.add(section);
                //количество уроков в текущей секции
                final int[] lessonCount = {0};
                //для каждого юнита в текущей секции
                for (Integer unitID : section.getUnits()) {
                    //скачиваем
                    Loader.loadUnit(unitID, unit -> {
                        //вставляем юнит на свою позицию в текущий список уроков
                        units.set(unit.getPosition() - 1, unit);
                        //скачиваем из юнита урок.
                        Loader.loadLesson(unit.getLesson(), lesson -> {
                            //уеличиваем счетчик уроков
                            lessonCount[0]++;
                            //вставляем урок на свою позицию в текущий список уроков
                            lessons.set(unit.getPosition() - 1, lesson);
                            //если все уроки в секции скачаны
                            if (lessonCount[0] == section.getUnits().size()) {
                                //увеличиваем счетчик секций
                                sectionCount[0]++;
                                //кладем список уроков в список со списками уроков.
                                lessonsList.set(section.getPosition() - 1, lessons);
                                unitsList.set(section.getPosition() - 1, units);
                                //обновляем прогресс бар
                                if (progressBar != null){
                                    progressBar.setIndeterminate(false);
                                    progressBar.setProgress(sectionCount[0]);
                                }
                                //если все уроки и секции скачаны
                                if (sectionCount[0] == course.getSections().size()) {
                                    Collections.sort(sectionList, (o1, o2) -> {
                                        if (o1.getPosition() > o2.getPosition()) {
                                            return 1;
                                        }
                                        if (o1.getPosition() < o2.getPosition()) {
                                            return -1;
                                        }
                                        return 0;
                                    });
                                    consumer.accept(sectionList, unitsList, lessonsList);
                                }
                            }
                        });
                    });
                }
            });
        }
    }
}
