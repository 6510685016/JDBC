package basic.jdbc.model;

public class Semester {
    private int semesterId;
    private int year;
    private String term;

    public int getSemesterId() { return semesterId; }
    public int getYear() { return year; }
    public String getTerm() { return term; }

    public Semester(Builder builder) {
        this.semesterId = builder.semesterId;
        this.year = builder.year;
        this.term = builder.term;
    }

    public static class Builder {
        private int semesterId;
        private int year;
        private String term;

        public Builder semesterId(int semesterId) {
            this.semesterId = semesterId;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder term(String term) {
            this.term = term;
            return this;
        }

        public Semester build() {
            return new Semester(this);
        }
    }
}