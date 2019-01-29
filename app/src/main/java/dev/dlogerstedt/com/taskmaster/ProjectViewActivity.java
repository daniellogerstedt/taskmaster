package dev.dlogerstedt.com.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import dev.dlogerstedt.com.taskmaster.adapters.TaskAdapter;
import dev.dlogerstedt.com.taskmaster.database.ProjectDatabase;
import dev.dlogerstedt.com.taskmaster.database.TaskDatabase;
import dev.dlogerstedt.com.taskmaster.models.Project;
import dev.dlogerstedt.com.taskmaster.models.Task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ProjectViewActivity extends AppCompatActivity {

    private RecyclerView taskList;
    private TaskAdapter taskAdapter;
    private RecyclerView.LayoutManager taskLayoutManager;
    private List<Task> tasks;
    TaskDatabase taskDB;
    ProjectDatabase projectDB;
    Intent intent;
    Bundle extras;
    long projectId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        intent = getIntent();
        extras = intent.getExtras();
        projectId = extras.getLong("projectId");

        projectDB = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "projects").allowMainThreadQueries().build();
        taskDB = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks").allowMainThreadQueries().build();

        Project theProject = projectDB.daoAccess().fetchOneProjectById(projectId);

        TextView projectTitle = findViewById(R.id.project_view_name_text);

        projectTitle.setText(theProject.getTitle());

        tasks = taskDB.daoAccess().fetchTasksByProjectId(projectId);

        taskList = findViewById(R.id.task_recycler_view);

        // recycler view won't resize for content
        taskList.setHasFixedSize(true);

        // linear layout manager
        taskLayoutManager = new LinearLayoutManager(this);
        taskList.setLayoutManager(taskLayoutManager);

        // the adapter for the recycler view
        taskAdapter = new TaskAdapter(tasks);
        taskList.setAdapter(taskAdapter);

    }

    public void onAddTaskClick (View v) {

        Intent taskAddIntent = new Intent(this, TaskCreationActivity.class);
        taskAddIntent.putExtra("projectId", projectId);
        startActivityForResult(taskAddIntent, 2341);

    }
}
