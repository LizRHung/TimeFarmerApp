package project.tthree;

import com.google.gson.annotations.SerializedName;

public class UserInformation {

    @SerializedName("id")
    public String id;

    @SerializedName("username")
    public String username;

    @SerializedName("password")
    public String password;

    @SerializedName("birthday")
    public String birthday;

    @SerializedName("gender")
    public String gender;
}
