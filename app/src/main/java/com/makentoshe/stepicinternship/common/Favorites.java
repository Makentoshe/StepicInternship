package com.makentoshe.stepicinternship.common;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;
import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.SectionModel;
import com.makentoshe.stepicinternship.common.model.StepModel;
import com.makentoshe.stepicinternship.common.model.UnitModel;
import com.makentoshe.stepicinternship.service.DownloadService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Makentoshe on 27.04.2018.
 */

public class Favorites {

    private Context context;
    private String courseName;
    private int id;

    public void add(Context context, SearchModel.SearchResult rawCourse, DownloadService service) {
        Toast.makeText(context, context.getResources().getString(R.string.loading) + " " + rawCourse.getCourseTitle(),
                Toast.LENGTH_SHORT).show();

        this.context = context;
        courseName = rawCourse.getCourseTitle();
        id = rawCourse.getId();

        File mainDir = new File(Environment.getExternalStorageDirectory() + File.separator + "StepicInternship");
        if (!mainDir.exists() && !mainDir.isDirectory()) {
            //create empty directory
            mainDir.mkdir();
            System.out.println("Create dir: " + mainDir.getPath());
        }

        Thread thread = new Thread(() -> {

            Call<CourseModel> call = StepicInternship.getApi().getCourseData(rawCourse.getCourse());
            try {
                CourseModel.Course course = call.execute().body().getCourses().get(0);
                File courseDir = saveCourseData(mainDir, course);
                loadSections(courseDir, course);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Looper.prepare(); //?????? debug
            notification(context, courseName, context.getResources().getString(R.string.loading_finished), id);
            this.context = null;
            service.stopSelf();
        });
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();

    }

    private void loadSections(File parentDir, CourseModel.Course course) throws IOException {
        for (int i = 0; i < course.getSections().size(); i++) {
            Call<SectionModel> section_call =
                    StepicInternship.getApi().getSectionData(course.getSections().get(i));

            SectionModel.Section section = section_call.execute().body().getSections().get(0);
            File section_dir = saveSectionData(parentDir, section);
            loadLessons(section_dir, section);
        }
    }

    private void loadLessons(File parentDir, SectionModel.Section section) throws IOException {
        for (Integer unitID : section.getUnits()) {
            Call<UnitModel> unit_call = StepicInternship.getApi().getUnitData(unitID);
            UnitModel.Unit unit = unit_call.execute().body().getUnits().get(0);
            Call<LessonModel> lesson_call = StepicInternship.getApi().getLessonData(unit.getLesson());
            LessonModel.Lesson lesson = lesson_call.execute().body().getLessons().get(0);
            File lesson_dir = saveLesson(parentDir, unit, lesson);
            loadSteps(lesson_dir, lesson);
        }
    }

    private void loadSteps(File parentDir, LessonModel.Lesson lesson) throws IOException {
        for (Integer stepID : lesson.getSteps()) {
            Call<StepModel> step_call = StepicInternship.getApi().getStepData(stepID);
            StepModel.Step step = step_call.execute().body().getSteps().get(0);
            saveStep(parentDir, step);
            if (step.getBlock().getVideo() != null) {
                loadVideo(parentDir, step);
            }
        }
    }

    private void loadVideo(File parentDir, StepModel.Step step) throws IOException {
        File filePath = new File(parentDir + File.separator + step.getPosition() + "_step.mp4");
        StepModel.Url url = step.getBlock().getVideo().getUrls().get(0);
        int q = 360;
        for (StepModel.Url _url : step.getBlock().getVideo().getUrls()) {
            if (Integer.parseInt(_url.getQuality()) < Integer.parseInt(url.getQuality())) {
                if (Integer.parseInt(_url.getQuality()) >= q) {
                    url = _url;
                }
            }
        }
        Call<ResponseBody> call = StepicInternship.getApi().getVideo(url.getUrl());

        InputStream is = null;
        OutputStream os = null;
        try {
            is = call.execute().body().byteStream();
            os = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024 * 4];
            int count;
            while ((count = is.read(buffer)) > -1) {
                os.write(buffer, 0, count);   // Don't allow any extra bytes to creep in, final write
            }
            os.flush();
            System.out.println("Create file: " + filePath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (os != null) os.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private File saveCourseData(File parentDir, CourseModel.Course course) {
        File courseDir = new File(parentDir + File.separator + course.getTitle());
        if (!courseDir.exists() && !courseDir.isDirectory()) {
            //create empty directory
            courseDir.mkdir();
            System.out.println("Create dir: " + courseDir.getPath());
        }
        Gson gson = new Gson();
        File filePath = new File(courseDir + File.separator + "course.txt");
        String str = gson.toJson(course);
        saveFile(filePath, str);
        return courseDir;
    }

    private File saveSectionData(File parentDir, SectionModel.Section section) {
        File sectionDir = new File(parentDir + File.separator + section.getTitle());
        if (!sectionDir.exists() && !sectionDir.isDirectory()) {
            //create empty directory
            sectionDir.mkdir();
            System.out.println("Create dir: " + sectionDir.getPath());
        }
        Gson gson = new Gson();
        File filePath = new File(sectionDir + File.separator + "section.txt");
        String str = gson.toJson(section);
        saveFile(filePath, str);
        return sectionDir;
    }

    private File saveLesson(File parentDir, UnitModel.Unit unit, LessonModel.Lesson lesson) {
        File lessonDir = new File(parentDir + File.separator + lesson.getTitle().replace("/", "\\"));
        if (!lessonDir.exists() && !lessonDir.isDirectory()) {
            //create empty directory
            lessonDir.mkdir();
            System.out.println("Create dir: " + lessonDir.getPath());
        }
        Gson gson = new Gson();
        File filePathLesson = new File(lessonDir + File.separator + "lesson.txt");
        File filePathUnit = new File(lessonDir + File.separator + "unit.txt");
        String lessonJson = gson.toJson(lesson);
        String unitJson = gson.toJson(unit);
        saveFile(filePathLesson, lessonJson);
        saveFile(filePathUnit, unitJson);
        progress(id, context, courseName, "Loading lesson: " + lesson.getTitle());
        return lessonDir;
    }

    private void saveStep(File parentDir, StepModel.Step step) {
        File filePath = new File(parentDir + File.separator + step.getPosition() + "_step.txt");
        Gson gson = new Gson();
        String str = gson.toJson(step);
        saveFile(filePath, str);
    }

    private void saveFile(File path, String data) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write(data);
            writer.flush();
            System.out.println("Create file: " + path);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void progress(int id, Context context, String course, String lesson){
        Notifications.downloadingProgress(id, context, 0, 0, true, course, lesson);
    }

    private void notification(Context context, String course, String message, int id){
        if (!Notifications.downloadingNotification(id, context, course, message)){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

}
