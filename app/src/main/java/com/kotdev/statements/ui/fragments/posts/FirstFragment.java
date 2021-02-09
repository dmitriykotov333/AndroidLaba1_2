package com.kotdev.statements.ui.fragments.posts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kotdev.statements.app.presenters.PresenterPosts;
import com.kotdev.statements.app.views.ContractPosts;
import com.kotdev.statements.databinding.FragmentFirstBinding;
import com.kotdev.statements.databinding.FragmentSecondBinding;
import com.kotdev.statements.interfaces.CallbackDelete;
import com.kotdev.statements.interfaces.GlobalActionInterface;
import com.kotdev.statements.room.post.Post;
import com.kotdev.statements.room.post.PostAccount;
import com.kotdev.statements.ui.adapters.Adapter;
import com.kotdev.statements.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


public class FirstFragment extends DaggerFragment implements ContractPosts.ViewContractPosts, CallbackDelete<Post>, GlobalActionInterface<PostAccount> {

    @Inject
    PresenterPosts presenter;

    private final CompositeDisposable disposable = new CompositeDisposable();
    private FragmentFirstBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Navigation.findNavController(requireView())
                    .navigate(FirstFragmentDirections.actionNavPostsToSecondFragment("Title", "Theme", "Comment"));
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        initAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void initAdapter() {
        binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rv.setHasFixedSize(true);
        binding.rv.setItemViewCacheSize(20);
        binding.rv.setDrawingCacheEnabled(true);
        binding.rv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        presenter.setAdapter(new Adapter());
        disposable.add(presenter.getPostAccount().subscribe(posts -> {
            presenter.getAdapter().setPostAccount(posts);
            binding.rv.setAdapter(presenter.getAdapter());
        }));
        disposable.add(presenter.getPosts().subscribe(posts -> presenter.getAdapter().setPosts(posts)));
        presenter.getAdapter().setCallbackDelete(this);
        presenter.getAdapter().setGlobalActionInterface(this);
    }

    @Override
    public void delete(int position, Post post) {
        presenter.getAdapter().remove(position);
        presenter.deletePosts(post);
    }

    @Override
    public void openAccount(int position, PostAccount model) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", model.id);
        Navigation.findNavController(requireView()).navigate(R.id.action_global_globalAccountFragment, bundle);
    }
}