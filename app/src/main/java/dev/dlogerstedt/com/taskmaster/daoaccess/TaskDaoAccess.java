package dev.dlogerstedt.com.taskmaster.daoaccess;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import dev.dlogerstedt.com.taskmaster.models.Task;

@Dao
public interface TaskDaoAccess {

    @Insert
    void insertTask (Task task);

    @Insert
    void insertMultipleTasks (List<Task> taskList);

    @Query("SELECT * FROM Task WHERE taskId = :taskId")
    Task fetchOneTaskById (long taskId);

    @Query("SELECT * FROM Task")
    List<Task> fetchTasks ();

    @Update
    void updateTask (Task task);

    @Delete
    void deleteTask (Task task);


}
