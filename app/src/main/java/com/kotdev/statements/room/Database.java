package com.kotdev.statements.room;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Database {

    private final AppDatabase database;

    @Inject
    public Database(Context context) {
        database = Room
                .databaseBuilder(context, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDatabase() {
        return database;
    }

}
