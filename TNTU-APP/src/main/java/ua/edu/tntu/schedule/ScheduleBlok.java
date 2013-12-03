package ua.edu.tntu.schedule;

public class ScheduleBlok {
    private String timeBegin;
    private String timeEnd;
    private String para;
    private String nameOfDay;

    public ScheduleBlok() {
        this.nameOfDay = null;
        this.para = null;
        this.timeBegin = null;
        this.timeEnd = null;
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

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }


}

