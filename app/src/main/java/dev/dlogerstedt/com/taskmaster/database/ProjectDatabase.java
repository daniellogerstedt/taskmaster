package dev.dlogerstedt.com.taskmaster.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.dlogerstedt.com.taskmaster.daoaccess.ProjectDaoAccess;
import dev.dlogerstedt.com.taskmaster.models.Project;

@Database(entities={Project.class}, version = 1, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {
    public abstract ProjectDaoAccess daoAccess();

}
