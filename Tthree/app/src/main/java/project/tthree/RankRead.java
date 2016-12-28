package project.tthree;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RankRead implements Serializable {

    @SerializedName("no")
    public int no;

    @SerializedName("id")
    public String id;

    @SerializedName("score")
    public int score;

    @SerializedName("collect")
    public String collect;

    @SerializedName("time")
    public String time;
}
