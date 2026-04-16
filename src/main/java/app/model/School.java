package app.model;

import app.framework.Cohort12Form;
import app.framework.Cohort12FormField;
import app.framework.Cohort12Table;
import app.framework.Cohort12TableCol;
import java.io.Serializable;

@Cohort12Form(label = "School Register", actionUrl = "./register_school")
@Cohort12Table(label = "Schools", tableUrl = "./school_lists", registerUrl = "./register_school")
public class School implements Serializable {
    @Cohort12FormField(label = "School Name",
            name = "schoolName",
            placeholder = "Please enter School Name")
    @Cohort12TableCol(label = "School Names")
    private String schoolName;

    @Cohort12FormField(label = "School Location",
            placeholder = "Please enter School Location")
    @Cohort12TableCol(label = "School Location")
    private String schoolLocation;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }
}
