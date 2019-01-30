package dev.dlogerstedt.com.taskmaster.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dev.dlogerstedt.com.taskmaster.R;
import dev.dlogerstedt.com.taskmaster.models.ProjectTask;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>  {
    private List<ProjectTask> projectTaskDataset;

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout taskTextView;
        public TaskViewHolder(LinearLayout v) {
            super(v);
            taskTextView = v;
        }
    }

    public TaskAdapter(List<ProjectTask> eData){
        projectTaskDataset = eData;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout exerciseTextView = (LinearLayout)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_text_view, viewGroup, false);

        TaskViewHolder taskViewHolder = new TaskViewHolder(exerciseTextView);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder exerciseViewHolder, int i) {
        ProjectTask projectTask = projectTaskDataset.get(i);
        ((TextView)exerciseViewHolder.taskTextView.findViewById(R.id.task_text_view)).setText(projectTask.getTitle());
    }

    public void updateAdapterData(List<ProjectTask> tasks) {
        projectTaskDataset = tasks;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return projectTaskDataset.size();
    }
}
