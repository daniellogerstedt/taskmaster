package dev.dlogerstedt.com.taskmaster.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class Project {
//https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long projectId;
    private String title;
    private String description;



    public Project () {}

    public Project (String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getProjectId () {return this.projectId;}
    public String getTitle () {return this.title;}
    public String getDescription () {return this.description;}

    public void setProjectId (long id) {this.projectId = id;}
    public void setTitle (String title) {this.title = title;}
    public void setDescription (String description) {this.description = description;}

    public String toString() {
        return "";
    }

}
