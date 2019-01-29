package dev.dlogerstedt.com.taskmaster.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dev.dlogerstedt.com.taskmaster.R;
import dev.dlogerstedt.com.taskmaster.models.Project;


public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>  {
    private List<Project> projectDataset;

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
