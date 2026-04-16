package app.model;

import app.framework.Cohort12Form;
import app.framework.Cohort12FormField;
import app.framework.Cohort12Table;
import app.framework.Cohort12TableCol;
import java.io.Serializable;

@Cohort12Form(label = "Student Register", actionUrl = "./register_student")
@Cohort12Table(label = "Students", tableUrl = "./student_lists", registerUrl = "./register_student")
public class Student implements Serializable {

    @Cohort12FormField(label = "Full Name", placeholder = "Enter Student Name")
    @Cohort12TableCol(label = "Student Name")
    private String name;

    @Cohort12FormField(label = "Course Title", placeholder = "Enter Course Name")
    @Cohort12TableCol(label = "Enrolled Course")
    private String course;

    @Cohort12FormField(label = "Campus Location", placeholder = "Enter Campus")
    @Cohort12TableCol(label = "Campus")
    private String campus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
