package com.jxs5476.showtracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Show.class}, version = 1)
public abstract class ShowDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Show.db";

    private static ShowDatabase db;

    // Singleton
    public static ShowDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, ShowDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
            db.addStarterData();
        }

        return db;
    }

    public abstract ShowDao showDao();

    private void addStarterData() {

        if (showDao().getShows().size() == 0) {

            // Execute code on a background thread
            runInTransaction(() -> {
                Show show = new Show("My Hero Academia", "Finished", "My hero is a pretty good anime, but like it kinda sucks sometimes. Season 6 will probably be great, but season five was honestly very lacking in the first half. The endeavor hero agency arc drags on much longer than in the manga", 8, true, false);
               showDao().insertShow(show);

                show = new Show("Fate/Apocrypha", "Episode 5", "Great", 9, false, true);
                showDao().insertShow(show);

                show = new Show("Neon Genesis Evangeleon", " ", " ", -1, false, false);
                showDao().insertShow(show);

                show = new Show("Attack on Titan", "Finished", "Excellent", 10, true, false);
                showDao().insertShow(show);

                show = new Show("Death Note", "Finished", "Great", 9, true, false);
                showDao().insertShow(show);

                show = new Show("Fate/Zero", "Finished", "Excellent", 10, true, false);
                showDao().insertShow(show);

                show = new Show("High Rise Invasion", "Finished", "Good", 7, true, false);
                showDao().insertShow(show);

                show = new Show("Sword Art online", "Episode 41", "Good", 7, false, true);
                showDao().insertShow(show);

                show = new Show("Kill La Kill", "Finished", "Pretty Good", 8, true, false);
                showDao().insertShow(show);

                show = new Show("A Silent Voice", "Finished", "Amazing", 10, true, false);
                showDao().insertShow(show);

                show = new Show("I Want to Eat Your Pancreas", "Finished", "Perfect", 10, true, false);
                showDao().insertShow(show);

                show = new Show("Fate/Unlimited Blade Works", "Finished", "Excellent", 10, true, false);
                showDao().insertShow(show);

                show = new Show("That Time I Got Reincarnated as a Slime", " ", " ", -1, false, false);
                showDao().insertShow(show);

            });
        }
    }
}