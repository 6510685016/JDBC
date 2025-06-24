package basic.jdbc.model;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private int semesterId;
    private double score;
    private String grade;

    private Enrollment(Builder builder) {
        this.enrollmentId = builder.enrollmentId;
        this.studentId = builder.studentId;
        this.courseId = builder.courseId;
        this.semesterId = builder.semesterId;
        this.score = builder.score;
        this.grade = builder.grade;
    }

    public void setScore(double score) { this.score = score; }
    public void setGrade(String grade) { this.grade = grade;}

    public int getEnrollmentId() { return enrollmentId; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public int getSemesterId() { return semesterId; }
    public double getScore() { return score; }
    public String getGrade() { return grade; }


    public static class Builder {
        private int enrollmentId;
        private int studentId;
        private int courseId;
        private int semesterId;
        private double score;
        private String grade;

        public Builder enrollmentId(int enrollmentId) {
            this.enrollmentId = enrollmentId;
            return this;
        }

        public Builder studentId(int studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder courseId(int courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder semesterId(int semesterId) {
            this.semesterId = semesterId;
            return this;
        }

        public Builder score(double score) {
            this.score = score;
            return this;
        }

        public Builder grade(String grade) {
            this.grade = grade;
            return this;
        }

        public Enrollment build() {
            return new Enrollment(this);
        }
    }
}
