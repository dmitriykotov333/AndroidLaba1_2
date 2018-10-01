package com.onpu.statements;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Admin on 21.09.2018.
 */

public class ActivityAdd extends AppCompatActivity {
    EditText name, desc, comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        name = (EditText) findViewById(R.id.name);
        desc = (EditText) findViewById(R.id.desc);
        comments = (EditText) findViewById(R.id.comments);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item1) {
        int id = item1.getItemId();
        if (id == R.id.action_addd) {
            if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(desc.getText().toString())
                    || TextUtils.isEmpty(comments.getText().toString())) {
                Toast.makeText(this, "Введите даные", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("desc", desc.getText().toString());
                intent.putExtra("comments", comments.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item1);
    }
}
