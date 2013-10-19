package ua.edu.tntu.schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleGroup {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public ScheduleGroup(String string) {
        this.string = string;
    }
}
