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
import com.kotdev.statements.databinding.FragmentCreateAccountBinding;
import com.kotdev.statements.room.post.Post;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.rxjava3.disposables.Disposable;

import com.kotdev.statements.databinding.FragmentSecondBinding;

public class SecondFragment extends DaggerFragment implements ContractPosts.ViewContractPosts {

    @Inject
    PresenterCreatePost presenter;

    private FragmentSecondBinding binding;
    private Disposable disposable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.attachView(this);
        SecondFragmentArgs args = SecondFragmentArgs.fromBundle(getArguments());
        binding.editTitle.setText(args.getName());
        binding.desc.setText(args.getTheme());
        binding.comments.setText(args.getComment());
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

            if (!TextUtils.isEmpty(binding.name.getText().toString())) {

                disposable = presenter.getIdAccounts()
                        .subscribe(longs -> {
                            for (Long id : longs) {
                                if (Long.parseLong(binding.name.getText().toString()) == id) {
                                    Post post = new Post();
                                    post.title = binding.editTitle.getText().toString();
                                    post.accountId = Long.parseLong(binding.name.getText().toString());
                                    post.theme = binding.desc.getText().toString();
                                    post.comment = binding.comments.getText().toString();
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
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        disposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.onDestroy();
    }
}