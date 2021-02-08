package com.kotdev.statements.ui.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kotdev.statements.R;
import com.kotdev.statements.interfaces.CallbackDelete;
import com.kotdev.statements.interfaces.GlobalActionInterface;
import com.kotdev.statements.room.post.Post;
import com.kotdev.statements.room.post.PostAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 21.09.2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.PostViewHolder> {

    private List<PostAccount> postAccount = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

    private CallbackDelete<Post> callbackDelete;

    private GlobalActionInterface<PostAccount> globalActionInterface;

    public void setGlobalActionInterface(GlobalActionInterface<PostAccount> globalActionInterface) {
        this.globalActionInterface = globalActionInterface;
    }

    public void setCallbackDelete(CallbackDelete<Post> callbackDelete) {
        this.callbackDelete = callbackDelete;
    }

    public void detachCallbackDelete() {
        this.callbackDelete = null;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(position, postAccount.get(position), posts.get(position));
    }

    @Override
    public int getItemCount() {
        return postAccount.size();
    }

    public void setPostAccount(List<PostAccount> postAccount) {
        this.postAccount = postAccount;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, title, theme, comment;
        ImageView icon;
        ImageButton remove;

        public PostViewHolder(@NonNull View view) {
            super(view);
            id = view.findViewById(R.id.id);
            name = view.findViewById(R.id.id_account_name);
            title = view.findViewById(R.id.title);
            theme = view.findViewById(R.id.theme);
            comment = view.findViewById(R.id.comment);
            icon = view.findViewById(R.id.users);
            remove = view.findViewById(R.id.remove);
        }


        public void bind(int position, PostAccount post, Post posts) {
            id.setText(String.valueOf(post.id));
            name.setText(String.format("%s %s", post.first_name, post.last_name));
            title.setText(post.title);
            theme.setText(post.theme);
            comment.setText(post.comment);
            Glide.with(icon)
                    .load(post.icon)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(icon);
            remove.setOnClickListener(v -> callbackDelete.delete(position, posts));
            name.setOnClickListener(v -> globalActionInterface.openAccount(position, post));
        }
    }

    public void remove(int position) {
        postAccount.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, postAccount.size());
    }
}