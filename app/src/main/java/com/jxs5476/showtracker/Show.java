package com.jxs5476.showtracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Show {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "progress")
    private String progress;
    @ColumnInfo(name = "review")
    private String review;
    @ColumnInfo(name = "rating")
    private int rating;
    @ColumnInfo(name = "isFinished")
    private boolean isFinished;
    @ColumnInfo(name = "isWatching")
    private boolean isWatching;

    public Show(String name, String progress, String review, int rating, boolean isFinished, boolean isWatching) {
        this.name = name;
        this.progress = progress;
        this.review = review;
        this.rating = rating;
        this.isFinished = isFinished;
        this.isWatching = isWatching;
    }

    public Show() {
        this.name = null;
        this.progress = null;
        this.review = null;
        this.rating = -1;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String subject) {
        name = subject;
    }

    public boolean isWatching() {
        return isWatching;
    }

    public void setWatching(boolean watching) {
        this.isWatching = watching;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        this.isFinished = finished;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}