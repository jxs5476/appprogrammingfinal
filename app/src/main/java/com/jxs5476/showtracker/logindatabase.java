package com.jxs5476.showtracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

    @Database(entities = {User.class}, version = 1)
    public abstract class logindatabase extends RoomDatabase {

        private static final String DATABASE_NAME = "user.db";

        private static com.jxs5476.showtracker.logindatabase db;

        // Singleton
        public static logindatabase getInstance(Context context) {
            if (db == null) {
                db = Room.databaseBuilder(context, com.jxs5476.showtracker.logindatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
                db.addStarterData();
            }

            return db;
        }

        public abstract UserDao userDao();

        private void addStarterData() {

            if (userDao().getUsers().size() == 0) {

                // Execute code on a background thread
                runInTransaction(() -> {
                    User user = new User("jxs5476", "1234");
                    userDao().insertUser(user);

                });
            }
        }

    }
