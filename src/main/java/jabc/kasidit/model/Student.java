package jabc.kasidit.model;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String surname;
    private Date birthday;
    private int yearJoin;
    private String faculty;
    private String major;
    private double gpa;
    private STATUS studentStatus;

    public Student(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.birthday = builder.birthday;
        this.yearJoin = builder.yearJoin;
        this.faculty = builder.faculty;
        this.major = builder.major;
        this.gpa = builder.gpa;
        this.studentStatus = builder.studentStatus;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public Date getBirthday() { return birthday; }
    public int getYearJoin() { return yearJoin; }
    public String getFaculty() { return faculty; }
    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
    public String getStudentStatus() { return studentStatus.toString(); }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public void setYearJoin(int yearJoin) { this.yearJoin = yearJoin; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public void setMajor(String major) { this.major = major; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public void setStudentStatus(STATUS studentStatus) { this.studentStatus = studentStatus; }

    public static class Builder {
        private int id;
        private String name;
        private String surname;
        private Date birthday;
        private int yearJoin;
        private String faculty;
        private String major;
        private double gpa;
        private STATUS studentStatus;

        public Builder Id(int id) {
            this.id = id;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder Surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder Birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder YearJoin(int yearJoin) {
            this.yearJoin = yearJoin;
            return this;
        }

        public Builder Faculty(String faculty) {
            this.faculty = faculty;
            return this;
        }

        public Builder Major(String major) {
            this.major = major;
            return this;
        }

        public Builder Gpa(double gpa) {
            this.gpa = gpa;
            return this;
        }

        public Builder StudentStatus(STATUS studentStatus) {
            this.studentStatus = studentStatus;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public static enum STATUS {
        inactive,
        active,
        graduated,
        suspended
    }
}

