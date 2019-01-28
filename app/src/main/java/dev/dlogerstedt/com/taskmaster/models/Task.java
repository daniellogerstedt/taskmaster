package dev.dlogerstedt.com.taskmaster.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


// https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a - Room Database Relationships
@Entity(foreignKeys = @ForeignKey(entity = Project.class,
        parentColumns = "projectId",
        childColumns = "projectId",
        onDelete = CASCADE))
public class Task {
    //https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long taskId;

    //this is the Id associated with the parent project
    private long projectId;
    private String title;
    private String status;



    public Task() {}

    public Task(String title, long projectId, String status) {
        this.projectId = projectId;
        this.title = title;
        this.status = status;
    }

    public long getTaskId () {return this.taskId;}
    public long getProjectId () {return this.projectId;}
    public String getTitle () {return this.title;}
    public String getStatus () {return this.status;}

    public void setTaskId (long id) {this.taskId = id;}
    public void setProjectId (long id) {this.projectId = id;}
    public void setTitle (String title) {this.title = title;}
    public void setStatus (String status) {this.status = status;}

    public String toString() {
        return "";
    }

}
