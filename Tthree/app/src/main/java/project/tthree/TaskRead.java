package project.tthree;

        import com.google.gson.annotations.SerializedName;

        import java.io.Serializable;

public class TaskRead implements Serializable {

    @SerializedName("no")
    public int no;

    @SerializedName("id")
    public String id;

    @SerializedName("projectname")
    public String projectname;

    @SerializedName("taskname")
    public String taskname;

    @SerializedName("tasktime")
    public String tasktime;

    @SerializedName("timetype")
    public String timetype;

    @SerializedName("taskholder")
    public String taskholder;
}
