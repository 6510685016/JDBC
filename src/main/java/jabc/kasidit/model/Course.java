package jabc.kasidit.model;

public class Course {
    private int courseId;
    private String courseCode;
    private String courseName;
    private int credit;

    public int getCourseId() { return courseId; }
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getCredit() { return credit; }

    public Course(Course.Builder builder) {
        this.courseId = builder.courseId;
        this.courseCode = builder.courseCode;
        this.courseName = builder.courseName;
        this.credit = builder.credit;
    }

    public static class Builder {
        private int courseId;
        private String courseCode;
        private String courseName;
        private int credit;

        public Builder courseId(int courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder courseCode(String courseCode) {
            this.courseCode = courseCode;
            return this;
        }

        public Builder courseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public Builder credit(int credit) {
            this.credit = credit;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}