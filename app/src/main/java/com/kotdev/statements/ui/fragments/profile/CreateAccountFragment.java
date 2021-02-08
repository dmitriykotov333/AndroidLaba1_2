package com.kotdev.statements.ui.fragments.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;
import com.kotdev.statements.app.presenters.PresenterAccountActions;
import com.kotdev.statements.app.views.ContractAccounts;
import com.kotdev.statements.room.profile.Account;
import com.kotdev.statements.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.rxjava3.disposables.Disposable;

import static com.kotdev.statements.helpers.Generate.generateId;

public class CreateAccountFragment extends DaggerFragment implements ContractAccounts.ViewContractAccounts {

    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.first_name)
    EditText first_name;
    @BindView(R.id.last_name)
    EditText last_name;
    @BindView(R.id.icon_account)
    ImageView icon;

    @Inject
    PresenterAccountActions presenter;

    private Disposable compositeDisposable;
    private Uri uri;

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
        if (requireArguments().getLong("id") != 0) {
            long idAccount = requireArguments().getLong("id");
            compositeDisposable = presenter.getAccount(idAccount).subscribe(account -> {

                        id.setText(String.format("ID %s", idAccount));
                        first_name.setText(account.name);
                        last_name.setText(account.surname);
                        Glide.with(requireContext())
                                .load(account.icon)
                                .circleCrop()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(icon);
                        uri = Uri.parse(account.icon);

            });
        } else {
            id.setText(String.format("ID %s", generateId()));
        }
        icon.setOnClickListener(v -> cropActivityResultLauncher.launch(actionPick()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_account, container, false);
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
            if (!TextUtils.isEmpty(first_name.getText().toString()) && !TextUtils.isEmpty(last_name.getText().toString())) {
                Account account = new Account();
                account.id = Long.parseLong(id.getText().toString().replace("ID ", ""));
                account.name = first_name.getText().toString();
                account.surname = last_name.getText().toString();
                account.icon = String.valueOf(uri);

                if (requireArguments().getLong("id") != 0) {
                    presenter.updateAccount(account);
                } else {
                    presenter.addAccount(account);
                }
                Navigation.findNavController(requireView())
                        .navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToNavProfile());
            } else {
                Snackbar.make(requireView(), "Entered data incorrect", Snackbar.LENGTH_LONG)
                        .show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("IntentReset")
    public Intent actionPick() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        return i;
    }

   /* public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }*/

    ActivityResultLauncher<Intent> cropActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            data -> {
                Intent intent = data.getData();
                if (intent != null) {
                    uri = intent.getData();
                    Glide.with(requireContext())
                            .load(uri)
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(icon);
                }
            });

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}