package dev.dlogerstedt.com.taskmaster.models;


public class ProjectTask {
    private String taskId;

    //this is the Id associated with the parent project
    private String title;
    private String status;



    public ProjectTask() {}

    public ProjectTask(String title, String status) {
        this.title = title;
        this.status = status;
    }

    public String getTaskId () {return this.taskId;}
    public String getTitle () {return this.title;}
    public String getStatus () {return this.status;}

    public void setTaskId (String id) {this.taskId = id;}
    public void setTitle (String title) {this.title = title;}
    public void setStatus (String status) {this.status = status;}

    public String toString() {
        return "";
    }

}
