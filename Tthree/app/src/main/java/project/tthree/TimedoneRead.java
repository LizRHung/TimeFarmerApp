package project.tthree;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TimedoneRead implements Serializable{

    @SerializedName("id")
    public String id;

    @SerializedName("worktime")
    public Integer worktime;

    @SerializedName("project")
    public String project;

    @SerializedName("task")
    public String task;

    @SerializedName("year")
    public String year;

    @SerializedName("month")
    public String month;

    @SerializedName("day")
    public String day;

    @SerializedName("week")
    public String week;

    @SerializedName("hour")
    public String hour;
}
