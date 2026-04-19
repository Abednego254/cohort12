package app.model;

import app.framework.Cohort12Form;
import app.framework.Cohort12FormField;
import app.framework.Cohort12Table;
import app.framework.Cohort12TableCol;

import java.io.Serializable;

@Cohort12Table(label = "Trainer", tableUrl = "./trainer_lists", registerUrl = "./register_trainer")
@Cohort12Form(label = "Trainer Register", actionUrl = "./register_trainer", method = "POST")
public class Trainer implements Serializable {

    @Cohort12TableCol(label = "ID")
    private String id;

    @Cohort12TableCol(label = "Name")
    @Cohort12FormField(label = "Trainer Name", placeholder = "Trainer Name")
    private String name;

    @Cohort12TableCol(label = "Email")
    @Cohort12FormField(label = "Email Address", placeholder = "Email Address")
    private String email;

    @Cohort12TableCol(label = "Specialty")
    @Cohort12FormField(label = "Specialty Area", placeholder = "Specialty (e.g. Java, QA)")
    private String specialty;

    public Trainer() {
    }

    public Trainer(String id, String name, String email, String specialty) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.specialty = specialty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
