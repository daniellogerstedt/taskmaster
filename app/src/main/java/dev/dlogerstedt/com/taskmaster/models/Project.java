package dev.dlogerstedt.com.taskmaster.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Project {
//https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long projectId;
    private String title;



    public Project () {}

    public Project (String title) {
        this.title = title;
    }

    public long getProjectId () {return this.projectId;}
    public String getTitle () {return this.title;}

    public void setProjectId (long id) {this.projectId = id;}
    public void setTitle (String title) {this.title = title;}

    public String toString() {
        return "";
    }

}
