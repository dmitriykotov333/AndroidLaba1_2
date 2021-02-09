package com.kotdev.statements.ui.fragments.profile;

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

import com.kotdev.statements.R;
import com.kotdev.statements.app.presenters.PresenterAccounts;
import com.kotdev.statements.app.views.ContractAccounts;
import com.kotdev.statements.databinding.FragmentAccountBinding;
import com.kotdev.statements.databinding.FragmentFirstBinding;
import com.kotdev.statements.interfaces.CallbackDelete;
import com.kotdev.statements.interfaces.ClickListener;
import com.kotdev.statements.interfaces.GlobalActionInterface;
import com.kotdev.statements.room.profile.Account;
import com.kotdev.statements.room.profile.AccountView;
import com.kotdev.statements.ui.adapters.AdapterAccounts;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


public class AccountFragment extends DaggerFragment implements ContractAccounts.ViewContractAccounts,
        CallbackDelete<Account>, ClickListener<Account>, GlobalActionInterface<AccountView> {


    @Inject
    PresenterAccounts presenter;

    private final CompositeDisposable disposable = new CompositeDisposable();
    private FragmentAccountBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Navigation.findNavController(requireView()).navigate(AccountFragmentDirections.actionNavProfileToCreateAccountFragment());
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
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void initAdapter() {
        binding.rvAccount.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvAccount.setHasFixedSize(true);
        binding.rvAccount.setItemViewCacheSize(20);
        binding.rvAccount.setDrawingCacheEnabled(true);
        binding.rvAccount.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        presenter.setAdapter(new AdapterAccounts());
        disposable.add(presenter.getAccounts().subscribe(accounts -> presenter.getAdapter().setAccounts(accounts)));
        disposable.add(presenter.getAccountsView().subscribe(accounts -> {
            presenter.getAdapter().setAccountsView(accounts);
            binding.rvAccount.setAdapter(presenter.getAdapter());
            presenter.getAdapter().setCallbackSize(id -> presenter.getSize(id));
        }));
        presenter.getAdapter().setCallbackDelete(this);
        presenter.getAdapter().setClickListener(this);
        presenter.getAdapter().setGlobalActionInterface(this);
    }

    @Override
    public void delete(int position, Account account) {
        presenter.getAdapter().remove(position);
        presenter.deleteAccount(account);
    }

    @Override
    public void setOnClickListener(int position, Account model) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", model.id);
        Navigation.findNavController(requireView()).navigate(AccountFragmentDirections.actionNavProfileToCreateAccountFragment().getActionId(), bundle);
    }

    @Override
    public void openAccount(int position, AccountView model) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", model.id);
        Navigation.findNavController(requireView()).navigate(R.id.action_global_globalAccountFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}