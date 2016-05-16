package com.etone.atm;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.etone.atm.DAO.ExpendDAO;
import com.etone.atm.entity.Expend;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private final String TAG = getClass().getName();
    @BindView(R.id.ed_date) EditText edDate;
    @BindView(R.id.ed_info) EditText edInfo;
    @BindView(R.id.ed_amount) EditText edAmount;
    private Calendar now;
    //    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        now = Calendar.getInstance();
        Log.d(TAG, now.toString());
        String s_now =  now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH)+1) + "-" + now.get(Calendar.DAY_OF_MONTH);
        edDate.setText(s_now);

//        dbHelper = MyDBHelper.getInstance(this);
    }

    @OnClick({R.id.btn_add, R.id.ed_date}) void onClick(View view){
        int view_id = view.getId();
        switch (view_id) {
            case R.id.text_cdate:
                new DatePickerDialog(this, this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show();

                break;

            case R.id.btn_add:
                String cdate = edDate.getText().toString();
                String info = edInfo.getText().toString();
                int amount = Integer.parseInt(edAmount.getText().toString());

                Expend expend = new Expend(cdate, info, amount);
                int id = ExpendDAO.insert(expend);
        /*
                ContentValues values = new ContentValues();
                values.put("cdate", cdate);
                values.put("info", info);
                values.put("amount", amount);

                long id = dbHelper.getWritableDatabase().insert("exp",null, values);
                Log.i("add", id+"");
        */
                if (id != -1) {
                    Toast.makeText(this, "新增成功", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        edDate.setText(year + "-" + (monthOfYear+1)+ "-" + dayOfMonth);
    }
}
