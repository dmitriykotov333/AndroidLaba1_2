package com.kotdev.statements.room.profile;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kotdev.statements.room.post.PostAccount;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface AccountDao {

    @Query("select * from profile")
    Flowable<List<Account>> getAll();

    @Query("select * from profile where id = :idAccount")
    Flowable<Account> getAccount(long idAccount);

    @Query("select count(account_id) from post where account_id = :idAcc")
    Integer getSizePosts(long idAcc);

    @Query("select * from profile")
    Flowable<List<AccountView>> getAccountView();

    @Query("SELECT po.title, po.theme, po.comment, pr.id, pr.first_name, pr.last_name, pr.icon " +
            "FROM post as po inner join profile as pr on pr.id == po.account_id where pr.id = :id")
    Flowable<List<PostAccount>> getPosts(long id);

    @Insert
    Completable insert(Account post);

    @Update
    Completable update(Account post);

    @Delete
    Completable delete(Account post);

}
