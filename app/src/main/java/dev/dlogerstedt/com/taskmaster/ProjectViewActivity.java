package dev.dlogerstedt.com.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.dlogerstedt.com.taskmaster.adapters.TaskAdapter;
import dev.dlogerstedt.com.taskmaster.models.ProjectTask;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectViewActivity extends AppCompatActivity {

    private RecyclerView taskList;
    private TaskAdapter taskAdapter;
    private RecyclerView.LayoutManager taskLayoutManager;
    private List<ProjectTask> tasks;
    FirebaseFirestore db;
    Intent intent;
    Bundle extras;
    String projectId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        intent = getIntent();
        extras = intent.getExtras();
        projectId = extras.getString("projectId");
        db = FirebaseFirestore.getInstance();
        tasks = new ArrayList<>();

        db.collection("Projects").document(projectId).collection("Tasks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    tasks = new ArrayList<>();
                    if (task.getResult() != null)
                        for(QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> taskMap = document.getData();
                            String id = (String)taskMap.get("id");
                            String title = (String)taskMap.get("title");
                            String status = (String)taskMap.get("status");
                            ProjectTask current = new ProjectTask(title, status);
                            current.setTaskId(id);
                            tasks.add(current);
                        }
                    taskAdapter.updateAdapterData(tasks);
                }
            }
        });

        final TextView projectTitle = findViewById(R.id.project_view_name_text);

        db.collection("Projects").document(projectId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String title = (String)documentSnapshot.getData().get("title");
                    projectTitle.setText(title);
                }
            }
        });


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

        Intent taskAddIntent = new Intent(this, ProjectTaskCreationActivity.class);
        taskAddIntent.putExtra("projectId", projectId);
        startActivityForResult(taskAddIntent, 2341);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        db.collection("Projects").document(projectId).collection("Tasks").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            tasks = new ArrayList<>();
                            if (task.getResult() != null)
                                for(QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> taskMap = document.getData();
                                    String id = (String)taskMap.get("id");
                                    String title = (String)taskMap.get("title");
                                    String status = (String)taskMap.get("status");

                                    ProjectTask current = new ProjectTask(title, status);
                                    current.setTaskId(id);
                                    tasks.add(current);
                                }
                            taskAdapter.updateAdapterData(tasks);
                        }
                    }
                });
    }
}
