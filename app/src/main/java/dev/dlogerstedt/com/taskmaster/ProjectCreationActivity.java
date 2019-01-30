package dev.dlogerstedt.com.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;
import dev.dlogerstedt.com.taskmaster.models.Project;
import dev.dlogerstedt.com.taskmaster.models.ProjectTask;

public class ProjectCreationActivity extends AppCompatActivity {
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_creation);
        db = FirebaseFirestore.getInstance();
//        db = Room.databaseBuilder(getApplicationContext(), ProjectDatabase.class, "projects").allowMainThreadQueries().build();


    }


    public void onCreateProject(View v) {

        // Get the inputs
        TextView titleView = findViewById(R.id.project_name_input);
        TextView descriptionView = findViewById(R.id.description_input);
        String title = titleView.getText().toString();
        String description = descriptionView.getText().toString();

        // Make the project
        final Project theProject = new Project(title, description);

        // Save the project
        db.collection("Projects").add(theProject).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                DocumentReference document = task.getResult();
                String id = document.getId();
                theProject.setId(id);
                document.set(theProject).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                    }
                });
            }
        });
    }
}
