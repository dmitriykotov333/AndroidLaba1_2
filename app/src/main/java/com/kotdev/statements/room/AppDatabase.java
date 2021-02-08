package com.kotdev.statements.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kotdev.statements.room.post.Post;
import com.kotdev.statements.room.post.PostDao;
import com.kotdev.statements.room.profile.Account;
import com.kotdev.statements.room.profile.AccountDao;

@Database(entities = {Post.class, Account.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PostDao postDao();
    public abstract AccountDao accountDao();
}