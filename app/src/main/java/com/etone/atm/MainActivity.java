package com.etone.atm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private static final int FUNC_BALANCE = 0;
    private static final int FUNC_HISTORY = 1;
    private static final int FUNC_NEWS = 2;
    private static final int FUNC_FINANCE = 3;
    private static final int FUNC_EXIT = 4;

    private final String TAG = this.getClass().getName();
    private static final int FUNC_LOGIN = 6;

    @BindView(R.id.list) ListView list;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.gridView) GridView grid;

    private static boolean logon = false;
    String[] func = {"餘額查詢", "交易明細", "最新消息", "投資理財", "離開"};
    int icons[] = {R.drawable.func_balance,
                    R.drawable.func_history,
                    R.drawable.func_news,
                    R.drawable.func_finance,
                    R.drawable.func_exit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (!logon){
            startActivityForResult(new Intent(this, LoginActivity.class), FUNC_LOGIN);
        }

        // ListView
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, func);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        // spinner
        /*
                    String[] data = getResources().getStringArray(R.array.notify_array);
                    ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
                */
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.notify_array, android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(this);

        //gridview
        IconAdapter iconAdapter = new IconAdapter();
        grid.setAdapter(iconAdapter);
        grid.setOnItemClickListener(this);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] data = getResources().getStringArray(R.array.notify_array);
        Log.d("item selected", position + " / " + data[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.list:

                break;

            case R.id.gridView:
                Log.d("grid view", position + "/");
                switch (position){
                    case FUNC_BALANCE:
                        break;
                    case FUNC_HISTORY:
                        break;
                    case FUNC_NEWS:
                        break;
                    case FUNC_FINANCE:
                        break;
                    case FUNC_EXIT:
                        finish();
                        break;
                }



                break;
        }
    }

    class IconAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return func.length;
        }

        @Override
        public Object getItem(int position) {
            return func[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null){
                view = getLayoutInflater().inflate(R.layout.icon,null);
                TextView tv = (TextView) view.findViewById(R.id.icon_text);
                ImageView iv = (ImageView) view.findViewById(R.id.icon_image);

                tv.setText(func[position]);
                iv.setImageResource(icons[position]);

                convertView = view;
            }

            return convertView;
        }
    }


}
