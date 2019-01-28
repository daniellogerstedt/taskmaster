package dev.dlogerstedt.com.taskmaster;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities={Project.class}, version = 1, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {
    public abstract ProjectDaoAccess daoAccess();

}
