package com.kotdev.statements.ui.fragments.posts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.kotdev.statements.R;
import com.kotdev.statements.app.presenters.PresenterCreatePost;
import com.kotdev.statements.app.views.ContractPosts;
import com.kotdev.statements.room.post.Post;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.rxjava3.disposables.Disposable;

public class SecondFragment extends DaggerFragment implements ContractPosts.ViewContractPosts {

    @BindView(R.id.name)
    EditText id_account;
    @BindView(R.id.desc)
    EditText theme;
    @BindView(R.id.comments)
    EditText comments;
    @BindView(R.id.editTitle)
    EditText title;

    @Inject
    PresenterCreatePost presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("CreateAccountFragment", "onViewCreated: CreateAccountFragment was created");
        ButterKnife.bind(this, view);
        presenter.attachView(this);

        SecondFragmentArgs args = SecondFragmentArgs.fromBundle(getArguments());
        title.setText(args.getName());
        theme.setText(args.getTheme());
        comments.setText(args.getComment());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_add).setIcon(R.drawable.ic_done_black_24dp);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {

            if (!TextUtils.isEmpty(id_account.getText().toString())) {

                Disposable disposable = presenter.getIdAccounts()
                        .subscribe(longs -> {
                            for (Long id : longs) {
                                if (Long.parseLong(id_account.getText().toString()) == id) {
                                    Post post = new Post();
                                    post.title = title.getText().toString();
                                    post.accountId = Long.parseLong(id_account.getText().toString());
                                    post.theme = theme.getText().toString();
                                    post.comment = comments.getText().toString();
                                    presenter.addPosts(post);
                                    Navigation.findNavController(requireView())
                                            .navigate(SecondFragmentDirections.actionSecondFragmentToNavPosts());
                                    break;
                                } else {
                                    Snackbar.make(requireView(), "Id incorrect", Snackbar.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
            } else {
                Snackbar.make(requireView(), "Write ID", Snackbar.LENGTH_LONG)
                        .show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.onDestroy();
    }
}