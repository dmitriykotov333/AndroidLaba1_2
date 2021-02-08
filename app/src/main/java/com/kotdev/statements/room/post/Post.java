package com.kotdev.statements.room.post;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.kotdev.statements.room.profile.Account;

import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "post", foreignKeys = @ForeignKey(entity = Account.class, parentColumns = "id", childColumns = "account_id", onDelete = CASCADE))
public class Post {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "theme")
    public String theme;

    @ColumnInfo(typeAffinity = TEXT)
    public String comment;

    @ColumnInfo(name = "account_id")
    public long accountId;
}
