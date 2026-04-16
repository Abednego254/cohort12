package app.model;

import app.framework.Cohort12Form;
import app.framework.Cohort12FormField;
import app.framework.Cohort12Table;
import app.framework.Cohort12TableCol;
import java.io.Serializable;

@Cohort12Form(label = "Course Register", actionUrl = "./register_course")
@Cohort12Table(label = "Courses", tableUrl = "./course_lists", registerUrl = "./register_course")
public class Course implements Serializable {

    @Cohort12FormField(label = "Course Title", name = "title", placeholder = "Enter Course Title")
    @Cohort12TableCol(label = "Title")
    private String title;

    @Cohort12FormField(label = "Description", name = "description", placeholder = "Enter Course Description")
    @Cohort12TableCol(label = "Description")
    private String description;

    @Cohort12FormField(label = "Duration (Weeks)", name = "duration", placeholder = "Enter Duration")
    @Cohort12TableCol(label = "Duration")
    private int duration;

    public Course() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
