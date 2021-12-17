package com.jxs5476.showtracker;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ShowDao {

    @Query("SELECT * FROM Show WHERE id = :id")
    public  Show getShowbyId(String id);

    @Query("SELECT * FROM Show WHERE name = :name")
    public Show getShow(String name);

    @Query("SELECT * FROM Show WHERE name = :showName")
    public Show getShowByText(String showName);

    @Query("SELECT * FROM Show ORDER BY name COLLATE NOCASE")
    public List<Show> getShows();

    @Query("SELECT * FROM Show WHERE isFinished = :fin")
    public  List<Show> getFinishedShows(boolean fin);

    @Query("SELECT * FROM Show WHERE isWatching = :watch")
    public  List<Show> getWatchingShows(boolean watch);

    @Query("SELECT * FROM Show WHERE isFinished = :fin AND isWatching = :watch")
    public  List<Show> getNewShows(boolean fin, boolean watch);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertShow(Show Show);

    @Update
    public void updateShow(Show Show);

    @Delete
    public void deleteShow(Show Show);
}