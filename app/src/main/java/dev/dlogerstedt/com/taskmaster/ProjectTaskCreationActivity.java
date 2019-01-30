package dev.dlogerstedt.com.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import dev.dlogerstedt.com.taskmaster.enums.StatusEnum;
import dev.dlogerstedt.com.taskmaster.models.ProjectTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProjectTaskCreationActivity extends AppCompatActivity {

    FirebaseFirestore db;
    Intent intent;
    Bundle extras;
    String projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);
        intent = getIntent();
        extras = intent.getExtras();
        projectId = extras.getString("projectId");

        db = FirebaseFirestore.getInstance();
//        db = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks").allowMainThreadQueries().build();

    }

    public void onCreateTask(View v) {

        // Get the inputs
        TextView titleView = findViewById(R.id.task_name_input);
        String title = titleView.getText().toString();

        // Make the project
        final ProjectTask theProjectTask = new ProjectTask(title, StatusEnum.Available.toString());

        // Save the project
        db.collection("Projects").document(projectId).collection("Tasks").add(theProjectTask).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                    DocumentReference document = task.getResult();
                    String id = document.getId();
                    theProjectTask.setTaskId(id);
                    document.set(theProjectTask).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            finish();
                        }
                    });
                }

    });
    }
}
