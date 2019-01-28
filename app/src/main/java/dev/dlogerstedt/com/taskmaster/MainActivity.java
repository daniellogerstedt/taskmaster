package dev.dlogerstedt.com.taskmaster;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dev.dlogerstedt.com.taskmaster.adapters.ProjectAdapter;
import dev.dlogerstedt.com.taskmaster.database.ProjectDatabase;
import dev.dlogerstedt.com.taskmaster.models.Project;

public class MainActivity extends AppCompatActivity {

    private RecyclerView projectList;
    private RecyclerView.Adapter projectAdapter;
    private RecyclerView.LayoutManager projectLayoutManager;
    private List<String> projectStrings;
    ProjectDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "projects").allowMainThreadQueries().build();

        List<Project> projects = db.daoAccess().fetchProjects();
        projectStrings = new ArrayList<>();
        for (Project project : projects) {
            projectStrings.add(project.toString());
        }

        projectList = findViewById(R.id.project_recycler_view);

        // recycler view won't resize for content
        projectList.setHasFixedSize(true);

        // linear layout manager
        projectLayoutManager = new LinearLayoutManager(this);
        projectList.setLayoutManager(projectLayoutManager);

        // the adapter for the recycler view
        projectAdapter = new ProjectAdapter(projectStrings);
        projectList.setAdapter(projectAdapter);

    }

    public void onAddProjectClick (View v) {

//        TODO: Add a new activity to create projects.

//        TextView titleView = findViewById(R.id.title_input_field);
//        TextView descriptionView = findViewById(R.id.description_input_field);
//        TextView quantityView = findViewById(R.id.quantity_input_field);
//        String title = titleView.getText().toString();
//        String description = descriptionView.getText().toString();
//        int quantity = Integer.parseInt(quantityView.getText().toString());
//        Project theProject = new Project(title, quantity, description);
//        db.daoAccess().insertProject(theProject);
//        projectStrings.add(theProject.toString());
//        projectAdapter.notifyItemInserted(projectStrings.size() - 1);
//        titleView.setText("");
//        descriptionView.setText("");
//        quantityView.setText("");
    }


}
