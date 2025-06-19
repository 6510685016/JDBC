package jabc.kasidit.model;

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

        public Builder SemesterId(int semesterId) {
            this.semesterId = semesterId;
            return this;
        }

        public Builder Year(int year) {
            this.year = year;
            return this;
        }

        public Builder Term(String term) {
            this.term = term;
            return this;
        }

        public Semester build() {
            return new Semester(this);
        }
    }
}