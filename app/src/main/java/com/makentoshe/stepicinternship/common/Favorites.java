package com.makentoshe.stepicinternship.common;

import android.content.Context;
import android.widget.Toast;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.SectionModel;
import com.makentoshe.stepicinternship.common.model.UnitModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Makentoshe on 27.04.2018.
 */

public class Favorites {

    public static void add(Context context, SearchModel.SearchResult rawCourse) {
        Toast.makeText(context, context.getResources().getString(R.string.loading) + " " + rawCourse.getCourseTitle(),
                Toast.LENGTH_SHORT).show();


    }

    private static void loadCourse(Memento memento, int courseID) {
        Loader.loadCourse(courseID, course -> {
            if (course == null) {
                return;
            }
            memento.setCourse(course);
            loadSections(memento, course.getSections());
        });
    }

    private static void loadSections(Memento memento, List<Integer> sectionIDs) {
        //список со всеми сегментами, в конце будет отсортирован
        List<SectionModel.Section> sectionList = new ArrayList<>();
        for (Integer sectionID : sectionIDs) {
            Loader.loadSection(sectionID, section -> {
                sectionList.add(section);
                if (sectionList.size() == sectionIDs.size()) {
                    Collections.sort(sectionList, (o1, o2) -> {
                        if (o1.getPosition() > o2.getPosition()) {
                            return 1;
                        }
                        if (o1.getPosition() < o2.getPosition()) {
                            return -1;
                        }
                        return 0;
                    });
                    memento.setSections(sectionList);
                }
            });
        }
    }

    private static void loadUnits(Memento memento, List<SectionModel.Section> sections) {
        List<List<UnitModel.Unit>> unitsList = new ArrayList<>();
        for (int i = 0; i < sections.size(); i++) {
            SectionModel.Section section = sections.get(i);
            List<UnitModel.Unit> units = new ArrayList<>();
            for (int j = 0; j < section.getUnits().size(); j++) {

            }
        }
    }



    public class Memento {

        private CourseModel.Course course;

        private List<SectionModel.Section> sections;

        public CourseModel.Course getCourse() {
            return course;
        }

        void setCourse(CourseModel.Course course) {
            this.course = course;
        }

        public List<SectionModel.Section> getSections() {
            return sections;
        }

        void setSections(List<SectionModel.Section> sections) {
            this.sections = sections;
        }
    }

}
