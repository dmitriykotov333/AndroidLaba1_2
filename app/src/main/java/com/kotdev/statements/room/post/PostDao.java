package com.kotdev.statements.room.post;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface PostDao {

    @Query("select * from post")
    Flowable<List<Post>> getAll();


    @Query("select id from profile")
    Flowable<List<Long>> getIdAccounts();

    @Insert
    Completable insert(Post post);

    @Delete
    Completable delete(Post post);

    @Query("SELECT po.title, po.theme, po.comment, pr.id, pr.first_name, pr.last_name, pr.icon " +
            "FROM post as po inner join profile as pr on pr.id == po.account_id")
    Flowable<List<PostAccount>> getPostWithAccount();
}
