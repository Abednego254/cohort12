package app.model;

import app.framework.Cohort12Form;
import app.framework.Cohort12FormField;
import app.framework.Cohort12Table;
import app.framework.Cohort12TableCol;
import java.io.Serializable;

@Cohort12Form(label = "Person Register", actionUrl = "./register_person")
@Cohort12Table(label = "Persons", tableUrl = "./person_lists", registerUrl = "./register_person")
public class Person implements Serializable {

    @Cohort12FormField(label = "Name", name = "name", placeholder = "Enter Full Name")
    @Cohort12TableCol(label = "Name")
    private String name;

    @Cohort12FormField(label = "National ID", name = "nationalId", placeholder = "Enter National ID")
    @Cohort12TableCol(label = "National ID")
    private String nationalId;

    @Cohort12FormField(label = "Address", name = "address", placeholder = "Enter Address")
    @Cohort12TableCol(label = "Address")
    private String address;

    @Cohort12FormField(label = "Age", name = "age", placeholder = "Enter Age")
    @Cohort12TableCol(label = "Age")
    private int age;

    public Person(String name,  String nationalId){
        this.name = name.toUpperCase();
        this.nationalId = nationalId.toUpperCase();
    }

    public Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
