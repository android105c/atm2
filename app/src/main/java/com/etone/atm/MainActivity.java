package com.etone.atm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getName();
    private static final int FUNC_LOGIN = 6;

    @BindView(R.id.list) ListView list;
    @BindView(R.id.spinner) Spinner spinner;

    private static boolean logon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (!logon){
            startActivityForResult(new Intent(this, LoginActivity.class), FUNC_LOGIN);

        }
        String[] func = {"餘額查詢", "交易明細", "最新消息", "投資理財", "離開"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, func);
        list.setAdapter(adapter);

/*
        String[] data = getResources().getStringArray(R.array.notify_array);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
*/
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.notify_array, android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter2);
    }

    @OnItemSelected(R.id.spinner) void selected(){

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case FUNC_LOGIN:
                if (resultCode == RESULT_OK){
                    logon=true;
                }else{
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create:
                Log.d(TAG, "careate");
                break;

            case R.id.action_settings:
                Log.d(TAG, "setting");
                break;

            case R.id.action_exit:
                finish();
                break;
        }
        // default --> return false;
        return super.onOptionsItemSelected(item);
    }


}
