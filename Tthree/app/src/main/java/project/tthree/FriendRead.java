package project.tthree;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FriendRead implements Serializable{

    @SerializedName("no")
    public int no;

    @SerializedName("id")
    public String id;

    @SerializedName("fid")
    public String fid;
}
