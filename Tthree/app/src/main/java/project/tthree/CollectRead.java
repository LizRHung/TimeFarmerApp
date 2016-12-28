package project.tthree;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CollectRead implements Serializable {

    @SerializedName("id")
    public String id;

    @SerializedName("tomatoamount")
    public int tomatoamount;

    @SerializedName("stage")
    public int stage;

}
