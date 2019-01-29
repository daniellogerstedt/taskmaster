package dev.dlogerstedt.com.taskmaster;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import dev.dlogerstedt.com.taskmaster.adapters.ProjectAdapter;
import dev.dlogerstedt.com.taskmaster.database.ProjectDatabase;
import dev.dlogerstedt.com.taskmaster.models.Project;

public class MainActivity extends AppCompatActivity {

    private RecyclerView projectList;
    private ProjectAdapter projectAdapter;
    private RecyclerView.LayoutManager projectLayoutManager;
    private List<Project> projects;
    ProjectDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "projects").allowMainThreadQueries().build();

        projects = db.daoAccess().fetchProjects();

        projectList = findViewById(R.id.project_recycler_view);

        // recycler view won't resize for content
        projectList.setHasFixedSize(true);

        // linear layout manager
        projectLayoutManager = new LinearLayoutManager(this);
        projectList.setLayoutManager(projectLayoutManager);

        // the adapter for the recycler view
        projectAdapter = new ProjectAdapter(projects);
        projectList.setAdapter(projectAdapter);

    }

    public void onAddProjectClick (View v) {

        Intent projectAddIntent = new Intent(this, ProjectCreationActivity.class);
        startActivityForResult(projectAddIntent, 1234);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        projects = db.daoAccess().fetchProjects();
        projectAdapter.updateAdapterData(projects);

    }
}
