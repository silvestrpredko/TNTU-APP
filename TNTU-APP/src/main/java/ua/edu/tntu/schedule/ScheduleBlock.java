package ua.edu.tntu.schedule;

public class ScheduleBlock {
    private String timeBegin;
    private String timeEnd;
    private String lecture;
    private String nameOfDay;
    private String location;

    public ScheduleBlock() {
        this.nameOfDay = null;
        this.lecture = null;
        this.timeBegin = null;
        this.timeEnd = null;
        this.location = null;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNameOfDay() {
        return nameOfDay;
    }

    public void setNameOfDay(String nameOfDay) {
        this.nameOfDay = nameOfDay;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }
}

