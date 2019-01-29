package dev.dlogerstedt.com.taskmaster;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dev.dlogerstedt.com.taskmaster.database.ProjectDatabase;
import dev.dlogerstedt.com.taskmaster.models.Project;

public class ProjectCreationActivity extends AppCompatActivity {

    ProjectDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_creation);

        db = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "projects").allowMainThreadQueries().build();


    }


    public void onCreateProject(View v) {

        // Get the inputs
        TextView titleView = findViewById(R.id.project_name_input);
        TextView descriptionView = findViewById(R.id.description_input);
        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();

        // Make the project
        Project theProject = new Project(title, description);

        // Save the project
        db.daoAccess().insertProject(theProject);

        // End activity
        finish();
    }
}
