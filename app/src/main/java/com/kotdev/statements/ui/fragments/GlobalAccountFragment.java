package com.kotdev.statements.ui.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kotdev.statements.R;
import com.kotdev.statements.app.presenters.PresenterGlobal;
import com.kotdev.statements.app.views.GlobalContract;
import com.kotdev.statements.ui.adapters.AdapterGlobal;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class GlobalAccountFragment extends DaggerFragment implements GlobalContract.ViewContractGlobal {

    private final CompositeDisposable disposable = new CompositeDisposable();


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.circleImageView)
    ImageView circleImageView;
    @BindView(R.id.size_posts)
    TextView size_posts;
    @BindView(R.id.background)
    ImageView background;

    @Inject
    PresenterGlobal presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_layout, container, false);
        ButterKnife.bind(this, rootView);
        presenter.attachView(this);
        disposable.add(presenter.getAccount(requireArguments().getLong("id")).subscribe(account -> {
                textName.setText(String.format("%s %s", account.name, account.surname));
                size_posts.setText(String.valueOf(presenter.getSize(requireArguments().getLong("id"))));
            Glide.with(background)
                    .load(account.icon)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(background);
                Glide.with(circleImageView)
                        .load(account.icon)
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(circleImageView);
        }));
        initAdapter();
        return rootView;
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        presenter.setAdapter(new AdapterGlobal());
        disposable.add(presenter.getPosts(requireArguments().getLong("id")).subscribe(accounts -> {
            presenter.getAdapter().setAccountsView(accounts);
            recyclerView.setAdapter(presenter.getAdapter());
        }));

    }
}