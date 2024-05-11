package com.example.taskminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    Context mcontext;
    List<TaskModel> tasklist = new ArrayList<>();

    public TaskAdapter(Context mcontext, List<TaskModel> tasklist) {
        this.mcontext = mcontext;
        this.tasklist = tasklist;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(mcontext).inflate(R.layout.singletaskview, parent,false);


        return new  TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskModel singletask = tasklist.get(position);
        holder.title.setText(singletask.getTitle());
        holder.description.setText(singletask.getDescription());

    }



    @Override
    public int getItemCount() {
        return tasklist.size();
    }
    public class TaskViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView title, description;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.singletasktitle);
            description = itemView.findViewById(R.id.singletaskdescription);
        }


    }

}
