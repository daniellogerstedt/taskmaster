package dev.dlogerstedt.com.taskmaster.models;

public class Project {
//https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab
    private String id;
    private String title;
    private String description;



    public Project () {}

    public Project (String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getId () {return this.id;}
    public String getTitle () {return this.title;}
    public String getDescription () {return this.description;}

    public void setId (String id) {this.id = id;}
    public void setTitle (String title) {this.title = title;}
    public void setDescription (String description) {this.description = description;}

    public String toString() {
        return "";
    }

}
