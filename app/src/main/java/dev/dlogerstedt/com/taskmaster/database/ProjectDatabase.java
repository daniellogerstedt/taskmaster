package dev.dlogerstedt.com.taskmaster.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import dev.dlogerstedt.com.taskmaster.daoaccess.ProjectDaoAccess;
import dev.dlogerstedt.com.taskmaster.models.Project;

@Database(entities={Project.class}, version = 1, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {
    public abstract ProjectDaoAccess daoAccess();

}
