package dev.dlogerstedt.com.taskmaster.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.dlogerstedt.com.taskmaster.daoaccess.TaskDaoAccess;
import dev.dlogerstedt.com.taskmaster.models.Project;
import dev.dlogerstedt.com.taskmaster.models.Task;

@Database(entities={Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDaoAccess daoAccess();

}
