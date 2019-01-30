package dev.dlogerstedt.com.taskmaster;

import androidx.annotation.NonNull;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import dev.dlogerstedt.com.taskmaster.adapters.ProjectAdapter;
import dev.dlogerstedt.com.taskmaster.models.Project;

public class MainActivity extends AppCompatActivity {

    private RecyclerView projectList;
    private ProjectAdapter projectAdapter;
    private RecyclerView.LayoutManager projectLayoutManager;
    private List<Project> projects;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    final static int RC_SIGN_IN = 2222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        projects = new ArrayList<>();



        projectList = findViewById(R.id.project_recycler_view);

        // recycler view won't resize for content
        projectList.setHasFixedSize(true);

        // linear layout manager
        projectLayoutManager = new LinearLayoutManager(this);
        projectList.setLayoutManager(projectLayoutManager);

        // the adapter for the recycler view
        projectAdapter = new ProjectAdapter(projects);
        projectList.setAdapter(projectAdapter);

        // retrieve the data from the database
        db.collection("Projects").get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isSuccessful()) {
                   projects = new ArrayList<>();
                   if (task.getResult() != null)
                       for(QueryDocumentSnapshot document : task.getResult()) {
                           Map<String, Object> projectMap = document.getData();
                           String id = (String)projectMap.get("id");
                           String title = (String)projectMap.get("title");
                           String description = (String)projectMap.get("description");

                           Project current = new Project(title, description);
                           current.setId(id);
                           projects.add(current);
                       }
                   projectAdapter.updateAdapterData(projects);
               }
           }
        });

    }

    public void onSigninClick (View v) {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }



    public void onAddProjectClick (View v) {

        Intent projectAddIntent = new Intent(this, ProjectCreationActivity.class);
        startActivityForResult(projectAddIntent, 1234);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                TextView usernameText = findViewById(R.id.username_text);
                TextView signinText = findViewById(R.id.please_signin_text);
                Button signinButton = findViewById(R.id.signin_button);
                Button createProjectButton = findViewById(R.id.add_project);
                String namePossessive = user.getDisplayName().charAt(user.getDisplayName().length() - 1) == 's'? user.getDisplayName() + "' " : user.getDisplayName() + "'s ";
                usernameText.setText(namePossessive + "Projects:");
                signinText.setVisibility(View.GONE);
                signinButton.setVisibility(View.GONE);
                usernameText.setVisibility(View.VISIBLE);
                createProjectButton.setVisibility(View.VISIBLE);
                projectList.setVisibility(View.VISIBLE);
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }

        if (requestCode == 1234) {
            db.collection("Projects").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            projects = new ArrayList<>();
                            if (task.getResult() != null)
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> projectMap = document.getData();
                                    String id = (String) projectMap.get("id");
                                    String title = (String) projectMap.get("title");
                                    String description = (String) projectMap.get("description");

                                    Project current = new Project(title, description);
                                    current.setId(id);
                                    projects.add(current);
                                }
                            projectAdapter.updateAdapterData(projects);
                        }
                    }
                });
        }
    }
}
