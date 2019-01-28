package dev.dlogerstedt.com.taskmaster.daoaccess;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import dev.dlogerstedt.com.taskmaster.models.Project;

@Dao
public interface ProjectDaoAccess {

    @Insert
    void insertProject (Project project);

    @Insert
    void insertMultipleProjects (List<Project> projectList);

    @Query("SELECT * FROM Project WHERE projectId = :projectId")
    Project fetchOneProjectById (long projectId);

    @Query("SELECT * FROM Project")
    List<Project> fetchProjects ();

    @Update
    void updateProject (Project project);

    @Delete
    void deleteProject (Project project);


}
