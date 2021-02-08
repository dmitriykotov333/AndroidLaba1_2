package com.kotdev.statements.room.profile;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static androidx.room.ColumnInfo.TEXT;


@Entity(tableName = "profile")
public class Account {

    @PrimaryKey
    public long id;

    @ColumnInfo(name = "first_name")
    public String name;

    @ColumnInfo(name = "last_name")
    public String surname;

    @ColumnInfo(typeAffinity = TEXT, name = "icon")
    public String icon;

}
