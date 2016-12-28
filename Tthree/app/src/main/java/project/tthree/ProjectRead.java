package project.tthree;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProjectRead implements Serializable {

    @SerializedName("no")
    public int no;

    @SerializedName("id")
    public String id;

    @SerializedName("projectname")
    public String projectname;

    @SerializedName("projecttime")
    public String projecttime;

    @SerializedName("timetype")
    public String timetype;

    @SerializedName("projecttype")
    public String projecttype;
}
