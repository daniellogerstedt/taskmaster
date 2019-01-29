package dev.dlogerstedt.com.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import dev.dlogerstedt.com.taskmaster.database.TaskDatabase;
import dev.dlogerstedt.com.taskmaster.enums.StatusEnum;
import dev.dlogerstedt.com.taskmaster.models.Task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TaskCreationActivity extends AppCompatActivity {

    TaskDatabase db;
    Intent intent;
    Bundle extras;
    long projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);
        intent = getIntent();
        extras = intent.getExtras();
        projectId = extras.getLong("projectId");

        db = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks").allowMainThreadQueries().build();

    }

    public void onCreateTask(View v) {

        // Get the inputs
        TextView titleView = findViewById(R.id.task_name_input);
        String title = titleView.getText().toString();

        // Make the project
        Task theTask = new Task(title, projectId, StatusEnum.Available.toString());

        // Save the project
        db.daoAccess().insertTask(theTask);

        // End activity
        finish();
    }
}
