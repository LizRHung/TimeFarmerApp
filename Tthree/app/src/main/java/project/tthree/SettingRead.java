package project.tthree;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SettingRead implements Serializable{

    @SerializedName("id")
    public String id;

    @SerializedName("workcycle")
    public int workcycle;

    @SerializedName("shortbreak")
    public int shortbreak;

    @SerializedName("longbreak")
    public int longbreak;

    @SerializedName("breakcycle")
    public int breakcycle;

    @SerializedName("remind")
    public int remind;
}
