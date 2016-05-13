package com.etone.atm;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinanceActivity extends AppCompatActivity {
    @BindView(R.id.list) ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        ButterKnife.bind(this);

        setupListView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @OnClick(R.id.fab) void onClick(View view){
        startActivity(new Intent(FinanceActivity.this, AddActivity.class));
    }

    private void setupListView() {
        Cursor c = MyDBHelper.getInstance(this).getReadableDatabase().query("exp", null, null, null, null, null, null);
        String[] from = {"_id", "cdate", "info", "amount"};
        int[] to = {R.id.text_id, R.id.text_cdate, R.id.text_info, R.id.text_amount};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item_finance,
                c,
                from,
                to,
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER){
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                super.bindView(view, context, cursor);
                int amount = cursor.getInt(cursor.getColumnIndex("amount"));
                if (amount>50){
                    TextView tAmount = (TextView) view.findViewById(R.id.text_amount);
                    tAmount.setTextColor(Color.RED);
                }
            }
        };
        list.setAdapter(adapter);

    }

}
