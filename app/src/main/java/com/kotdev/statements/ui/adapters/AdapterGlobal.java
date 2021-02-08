package com.kotdev.statements.ui.adapters;

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
import com.kotdev.statements.room.post.PostAccount;

import java.util.ArrayList;
import java.util.List;

public class AdapterGlobal  extends RecyclerView.Adapter<AdapterGlobal.AccountViewHolder> {

    private List<PostAccount> accountView = new ArrayList<>();

    @Override
    public AdapterGlobal.AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdapterGlobal.AccountViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGlobal.AccountViewHolder holder, int position) {
        holder.bind(accountView.get(position));
    }

    @Override
    public int getItemCount() {
        return accountView.size();
    }

    public void setAccountsView(List<PostAccount> account) {
        accountView.clear();
        accountView.addAll(account);
    }


    public class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView name, title_theme, comment;
        ImageView imageView;

        public AccountViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            title_theme = view.findViewById(R.id.title_theme);
            comment = view.findViewById(R.id.comment);
            imageView = view.findViewById(R.id.icon);

        }

        public void bind(PostAccount account) {
            name.setText(String.format("%s %s", account.first_name, account.last_name));
            title_theme.setText(String.format("%s\n%s", account.title, account.theme));
            comment.setText(account.comment);
            Glide.with(imageView)
                    .load(account.icon)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView);
           ;
        }
    }
}