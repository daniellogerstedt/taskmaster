package dev.dlogerstedt.com.taskmaster.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dev.dlogerstedt.com.taskmaster.ProjectViewActivity;
import dev.dlogerstedt.com.taskmaster.R;
import dev.dlogerstedt.com.taskmaster.models.Project;


public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>  {
    private List<Project> projectDataset;
    private String TAG = "ADAPTER";

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout projectTextView;
        public ProjectViewHolder(LinearLayout v) {
            super(v);
            projectTextView = v;
        }
    }

    public ProjectAdapter(List<Project> eData){
        projectDataset = eData;
    }

    @NonNull
    @Override
    public ProjectAdapter.ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout exerciseTextView = (LinearLayout)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.project_text_view, viewGroup, false);

        ProjectViewHolder projectViewHolder = new ProjectViewHolder(exerciseTextView);
        return projectViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder exerciseViewHolder, int i) {
        Project current = projectDataset.get(i);
        ((TextView)exerciseViewHolder.projectTextView.findViewById(R.id.project_title_view)).setText(current.getTitle());
        ((TextView)exerciseViewHolder.projectTextView.findViewById(R.id.project_description_view)).setText(current.getDescription());
        final Button projectButton = exerciseViewHolder.projectTextView.findViewById(R.id.project_button);
        projectButton.setContentDescription("View Project " + current.getTitle() + " : " + current.getId());
        projectButton.setOnClickListener(


            new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // Get some context adapter!
                    Context context = v.getContext();

                    // Make an intent and do the thing with it.
                    Intent projectIntent = new Intent(context, ProjectViewActivity.class);
                    projectIntent.putExtra("projectId", v.findViewById(R.id.project_button).getContentDescription().toString().split("\\s:\\s")[1]);
                    Log.d(TAG, "End Of On Click. Content Description: " + projectButton.getContentDescription());
                    context.startActivity(projectIntent);
                }
            });
    }

    public void updateAdapterData(List<Project> projects) {
        projectDataset = projects;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return projectDataset.size();
    }
}
