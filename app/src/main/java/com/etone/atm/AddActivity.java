package com.etone.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends AppCompatActivity {
    @BindView(R.id.ed_date) EditText edDate;
    @BindView(R.id.ed_info) EditText edInfo;
    @BindView(R.id.ed_amount) EditText edAmount;
    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        dbHelper = new MyDBHelper(this);
    }

    @OnClick(R.id.btn_add) void add(){
        String cdate = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());

        ContentValues values = new ContentValues();
        values.put("cdate", cdate);
        values.put("info", info);
        values.put("amount", amount);

        dbHelper.getWritableDatabase().insert("exp",null, values);

    }
}
