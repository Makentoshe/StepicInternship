package com.makentoshe.stepicinternship.common;

import android.content.Context;
import android.content.Intent;
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
import com.makentoshe.stepicinternship.common.model.UserModel;
import com.makentoshe.stepicinternship.func.TriConsumer;
import com.makentoshe.stepicinternship.service.DownloadService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Makentoshe on 27.04.2018.
 */
//todo добавить проверку на повреждение/удаление файлов
public class Favorites {

    private Context context;
    private String courseName;
    private int id;
    private static File mainDir = new File(Environment.getExternalStorageDirectory() +
            File.separator + "StepicInternship");
    public static final String courseFileName = "course";
    public static final String sectionFileName = "section";
    public static final String unitFileName = "unit";
    public static final String lessonFileName = "lesson";
    public static final String coverName = "cover";

    public void add(Context context, SearchModel.SearchResult rawCourse, DownloadService service) {
        Toast.makeText(
                context,
                context.getResources().getString(R.string.loading) + " " + rawCourse.getCourseTitle(),
                Toast.LENGTH_SHORT
        ).show();

        this.context = context;
        courseName = rawCourse.getCourseTitle();
        id = rawCourse.getId();

        if (!mainDir.exists() && !mainDir.isDirectory()) {
            //create empty directory
            mainDir.mkdir();
            System.out.println("Create dir: " + mainDir.getPath());
        }

        Thread thread = new Thread(() -> {

            Call<CourseModel> call = StepicInternship.getApi().getCourseData(rawCourse.getCourse());
            try {
                CourseModel.Course course = call.execute().body().getCourses().get(0);
                if (rawCourse.getAuthor() != null){
                    course.setAuthor(rawCourse.getAuthor());
                } else {
                    //no data - download it
                    StringBuilder sb = new StringBuilder();
                    for (Integer id : course.getAuthors()) {
                        Call<UserModel> user_call = StepicInternship.getApi().getUser(id);
                        user_call.execute().body().getUsers().get(0).getFullName();
                        sb.append(", ").append(user_call.execute().body().getUsers().get(0).getFullName());
                        course.setAuthor(sb.toString().substring(2));
                    }
                }
                File courseDir = saveCourseData(mainDir, course);
                Call<ResponseBody> cover = StepicInternship.getApi().getCover(course.getCover());
                saveFile(new File(courseDir + File.separator + coverName), cover.execute().body().byteStream());
                loadSections(courseDir, course);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Looper.prepare();
            notification(context, courseName, context.getResources().getString(R.string.loading_finished), id);
            context.sendBroadcast(new Intent(DownloadService.UPDATE_FAVORITES_MESSAGE));
            this.context = null;
            service.stopSelf();
        });
        thread.setPriority(3);
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
        File filePath = new File(parentDir + File.separator + step.getPosition() + "_stepV");
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
        saveFile(filePath, call.execute().body().byteStream());
    }

    private static void saveFile(File filePath, InputStream inputStream){
        OutputStream os = null;
        try {
            os = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024 * 4];
            int count;
            while ((count = inputStream.read(buffer)) > -1) {
                os.write(buffer, 0, count);   // Don't allow any extra bytes to creep in, final write
            }
            os.flush();
            System.out.println("Create file: " + filePath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
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
        File filePath = new File(courseDir + File.separator + courseFileName);
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
        File filePath = new File(sectionDir + File.separator + sectionFileName);
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
        File filePathLesson = new File(lessonDir + File.separator + lessonFileName);
        File filePathUnit = new File(lessonDir + File.separator + unitFileName);
        String lessonJson = gson.toJson(lesson);
        String unitJson = gson.toJson(unit);
        saveFile(filePathLesson, lessonJson);
        saveFile(filePathUnit, unitJson);
        progress(id, context, courseName, "Loading lesson: " + lesson.getTitle());
        return lessonDir;
    }

    private void saveStep(File parentDir, StepModel.Step step) {
        File filePath = new File(parentDir + File.separator + step.getPosition() + "_step");
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

    private void progress(int id, Context context, String course, String lesson) {
        Notifications.downloadingProgress(id, context, 0, 0, true, course, lesson);
    }

    private void notification(Context context, String course, String message, int id) {
        if (!Notifications.downloadingNotification(id, context, course, message)) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    public static List<Integer> getSavedCourses() {
        List<Integer> savedCourseIDs = new ArrayList<>();
            //for each course
            for (final File fileEntry : getSavedCourseFolders()) {
                if (fileEntry.isDirectory()) {
                    //files in courses folder
                    for (final File innerFiles : fileEntry.listFiles()) {
                        //define, that the "file" is really file
                        if (innerFiles.isFile() && innerFiles.getPath().contains(courseFileName)) {
                            try {
                                Gson gson = new Gson();
                                CourseModel.Course course = gson.fromJson(
                                        new FileReader(innerFiles), CourseModel.Course.class
                                );
                                savedCourseIDs.add(course.getId());
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }
                    }
                }
            }
            return savedCourseIDs;
    }

    public static List<File> getSavedCourseFolders(){
        if (mainDir.exists()){
            return Arrays.asList(mainDir.listFiles());
        }
        return Collections.emptyList();
    }

    public static void getCourse(CourseModel.Course course,
                                 TriConsumer<List<SectionModel.Section>, List<List<UnitModel.Unit>>,
                                         List<List<LessonModel.Lesson>>> consumer) {
        try {
            //находим путь до курса
            File courseDir = findCourseFolder(course.getId());
            //если нашли
            if (courseDir != null) {
                loadData(courseDir, consumer);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static File findCourseFolder(int courseID) throws IOException {
        //папки со всеми курсами
        for (final File courseFolder : mainDir.listFiles()) {
            //внутри папки курса - секции и файл, описывающий курс
            for (final File fileInCourseFolder : courseFolder.listFiles()) {
                //ищем файл
                if (fileInCourseFolder.isFile() && fileInCourseFolder.getPath().contains(courseFileName)) {
                    CourseModel.Course course = new Gson().fromJson(
                            new FileReader(fileInCourseFolder), CourseModel.Course.class
                    );
                    //проверяем id
                    if (course.getId() == courseID) {
                        return courseFolder; //нашли нужный
                    }
                }
            }
        }
        return null;
    }

    private static void loadData(File courseDir, TriConsumer<List<SectionModel.Section>,
            List<List<UnitModel.Unit>>, List<List<LessonModel.Lesson>>> consumer) throws IOException {
        List<SectionModel.Section> sections = new ArrayList<>();
        List<List<UnitModel.Unit>> unitsList = new ArrayList<>();
        List<List<LessonModel.Lesson>> lessonsList = new ArrayList<>();
        //внутри папки курса - файл описывающий курс и секции
        for (File courseFile : courseDir.listFiles()) {
            if (courseFile.isDirectory()) {
                ArrayList<UnitModel.Unit> units = new ArrayList<>();
                ArrayList<LessonModel.Lesson> lessons = new ArrayList<>();
                //внутри папки секции
                File[] listFiles = courseFile.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    File sectionFile = listFiles[i];
                    //загружаем данные секции
                    if (sectionFile.isFile() && sectionFile.getPath().contains(sectionFileName)) {
                        SectionModel.Section section =
                                new Gson().fromJson(new FileReader(sectionFile), SectionModel.Section.class);
                        sections.add(section);
                    }
                    if (sectionFile.isDirectory()) {
                        for (int j = 0; j < sectionFile.listFiles().length; j++) {
                            File file = sectionFile.listFiles()[j];
                            //Юниты
                            if (file.getPath().contains(unitFileName)) {
                                UnitModel.Unit unit =
                                        new Gson().fromJson(new FileReader(file), UnitModel.Unit.class);
                                units.add(unit);
                            }
                            //уроки
                            if (file.getPath().contains(lessonFileName)) {
                                LessonModel.Lesson lesson =
                                        new Gson().fromJson(new FileReader(file), LessonModel.Lesson.class);
                                lessons.add(lesson);
                            }
//                            //степы
//                            if (file.getPath().contains("_step." + jsonFileExt)){
//                                if (Character.isDigit(file.getName().charAt(0))){
//                                    try{
//                                        StepModel.Step step =
//                                                new Gson().fromJson(new FileReader(file), StepModel.Step.class);
//
//                                    } catch (IOException ioe){
//                                        ioe.printStackTrace();
//                                    }
//                                }
//                            }
                        }
                    }
                }
                unitsList.add(units);
                lessonsList.add(lessons);
            }
        }
        consumer.accept(sections, unitsList, lessonsList);
    }
}
