package com.onpu.statements;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class sort array conversely
 *
 * @author Kotov Dmitriy
 * @version 1
 * @since 21.09.2018
 */
public class MainActivity extends AppCompatActivity {
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter adapter;
    Item item;
    final Random VALUE = new Random();
    Integer[] users = {
            R.drawable.user1,
            R.drawable.user2,
            R.drawable.user3,
            R.drawable.user4,
            R.drawable.user5,
            R.drawable.user6,
            R.drawable.user7
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item1) {
        int id = item1.getItemId();

        if (id == R.id.action_add) {
            Intent intent = new Intent(this, ActivityAdd.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String name = data.getStringExtra("name");
        String desc = data.getStringExtra("desc");
        String comments = data.getStringExtra("comments");
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        adapter = new Adapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        for (int i = 0; i < 1; i++) {
            item = new Item("ID зявления\n" + generateId(), name, desc, comments, generateIcon(users));
            itemList.add(item);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * @return generaredId
     */
    public String generateId() {
        return String.valueOf(System.currentTimeMillis() + VALUE.nextInt());
    }

    /**
     * Данный метод выбирает из массива рандомно фото и добавляет к заявлению пользователю
     * @param array
     * @return random Icon for User
     */
    public Integer generateIcon(Integer[] array) {
        Integer rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}

