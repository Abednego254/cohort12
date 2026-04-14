package app;

import java.io.Serializable;

@Cohort12Form(label = "Register School", actionUrl = "./register_school")
@Cohort12Table(label = "Schools", tableUrl = "./list_registered")
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
