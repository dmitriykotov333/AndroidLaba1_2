package com.kotdev.statements.ui.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kotdev.statements.R;
import com.kotdev.statements.interfaces.CallbackDelete;
import com.kotdev.statements.interfaces.ClickListener;
import com.kotdev.statements.interfaces.GlobalActionInterface;
import com.kotdev.statements.room.profile.Account;
import com.kotdev.statements.room.profile.AccountView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 21.09.2018.
 */

public class AdapterAccounts extends RecyclerView.Adapter<AdapterAccounts.AccountViewHolder> {

    private List<AccountView> accountView = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

    private CallbackDelete<Account> callbackDelete;
    private ClickListener<Account> clickListener;
    private GlobalActionInterface<AccountView> globalActionInterface;

    public interface CallbackSize {
        int size(long id);
    }
    public CallbackSize callbackSize;

    public void setGlobalActionInterface(GlobalActionInterface<AccountView> globalActionInterface) {
        this.globalActionInterface = globalActionInterface;
    }

    public void setCallbackSize(CallbackSize callbackSize) {
        this.callbackSize = callbackSize;
    }

    public void setCallbackDelete(CallbackDelete<Account> callbackDelete) {
        this.callbackDelete = callbackDelete;
    }

    public void setClickListener(ClickListener<Account> clickListener) {
        this.clickListener = clickListener;
    }


    public void detachCallbackDelete() {
        this.callbackDelete = null;
    }

    public void detachClickListener() {
        this.clickListener = null;
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.account_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
            holder.bind(position, accountView.get(position), accounts.get(position));
    }

    @Override
    public int getItemCount() {
        return accountView.size();
    }

    public void setAccountsView(List<AccountView> account) {
        accountView.clear();
        accountView.addAll(account);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView id, first_name, last_name, size_posts;
        ImageView imageView;
        ImageView delete;
        ImageView update;
        androidx.constraintlayout.widget.ConstraintLayout constraintLayout;

        public AccountViewHolder(@NonNull View view) {
            super(view);
            id = view.findViewById(R.id.id);
            first_name = view.findViewById(R.id.first_name);
            last_name = view.findViewById(R.id.last_name);
            size_posts = view.findViewById(R.id.size_posts);
            imageView = view.findViewById(R.id.icon_account_view);
            delete = view.findViewById(R.id.remove);
            update = view.findViewById(R.id.update);
            constraintLayout = view.findViewById(R.id.constraint);
        }

        public void bind(int position, AccountView account, Account accounts) {
            id.setText(String.valueOf(account.id));
            first_name.setText(account.first_name);
            last_name.setText(account.last_name);
            Log.d("AdapterAccounts", "bind: " + account.count);
            size_posts.setText(String.format("%s: %s", itemView.getContext().getString(R.string.published_publications), callbackSize.size(account.id)));
            Glide.with(imageView)
                    .load(account.icon)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView);
            delete.setOnClickListener(v -> callbackDelete.delete(position, accounts));
            update.setOnClickListener(v -> clickListener.setOnClickListener(position, accounts));
            constraintLayout.setOnClickListener((v) -> globalActionInterface.openAccount(position, account));
        }
    }

    public void remove(int position) {
        accountView.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, accountView.size());
    }
}