package com.example.taskminder;
import java.util.Date;

public class TaskModel {
    private int id;
    private String title;
    private String description;
    private Date dueDate;
    private boolean isCompleted;
    private int completePercentage; // New field for completion percentage

    // Constructor
    public TaskModel( String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false; // By default, task is not completed
        this.completePercentage = 0; // By default, completion percentage is 0
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getCompletePercentage() {
        return completePercentage;
    }

    public void setCompletePercentage(int completePercentage) {
        this.completePercentage = completePercentage;
    }

    // toString method to represent the task as a string
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", isCompleted=" + isCompleted +
                ", completePercentage=" + completePercentage +
                '}';
    }
}
