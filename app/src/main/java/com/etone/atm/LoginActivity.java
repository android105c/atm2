package com.etone.atm;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String PREF_REMEMBER_USERID = "pref_remember_userid";
    private static final String PREF_REMEMBER_PASSWD = "pref_remember_passwd";
    private static final String PREF_USERID = "pref_userid";
    private static final String PREF_PASSWD = "pref_passwd";

    @BindView(R.id.txtUserid) TextView tvUserid;
    @BindView(R.id.txtPasswd) TextView tvPasswd;
    @BindView(R.id.cbRememberUserid) CheckBox cbUsersid;
    @BindView(R.id.cbRememberPasswd) CheckBox cbPasswd;
    @BindView(R.id.progressBar) ProgressBar pb;

    private boolean remember_userid;
    private boolean remember_passwd;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void init() {
        ButterKnife.bind(this);
        prefs = getSharedPreferences("atm", MODE_PRIVATE);
        remember_userid = prefs.getBoolean(PREF_REMEMBER_USERID, false);
        remember_passwd = prefs.getBoolean(PREF_REMEMBER_PASSWD, false);
        cbUsersid.setChecked(remember_userid);
        cbPasswd.setChecked(remember_passwd);

        if (remember_userid) tvUserid.setText(prefs.getString(PREF_USERID, ""));
        if (remember_passwd) tvPasswd.setText(prefs.getString(PREF_PASSWD,""));

    }

    @OnClick(R.id.btnLogin)
    void login(){
        String userid = tvUserid.getText().toString();
        String passwd = tvPasswd.getText().toString();
        new LoginTask().execute(userid, passwd);

    }

    @OnCheckedChanged({R.id.cbRememberUserid, R.id.cbRememberPasswd})
    void rememberChange(CompoundButton buttonView, boolean isChecked){
        int cbId = buttonView.getId();
        String pref = "";

        switch (cbId){
            case R.id.cbRememberUserid:
                remember_userid = isChecked;
                pref = PREF_REMEMBER_USERID;
                break;
            case R.id.cbRememberPasswd:
                remember_passwd = isChecked;
                pref = PREF_REMEMBER_PASSWD;
                break;
        }
        prefs.edit().putBoolean(pref, isChecked).commit();
    }


    class LoginTask extends AsyncTask<String, Void, Integer>{
        String userid;
        String passwd;
        @Override
        protected Integer doInBackground(String... params) {
            userid = params[0];
            passwd = params[1];
            String str_url = "http://atm201605.appspot.com/login?uid=" + userid + "&pw=" + passwd;
            int data = 0;
            try {
                URL url = new URL(str_url);
                InputStream is = url.openStream();
                data = is.read();
                Thread.sleep(2000);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            pb.setVisibility(View.GONE);
            if (integer==49){

                //  Remember userid / password start
                SharedPreferences.Editor editor = prefs.edit();
                boolean isChange = false;
                if (remember_userid) {
                    editor.putString(PREF_USERID, userid);
                    isChange = true;
                }else{
                    editor.remove(PREF_USERID);
                }

                if (remember_passwd) {
                    editor.putString(PREF_PASSWD, passwd);
                    isChange = true;
                }else{
                    editor.remove(PREF_PASSWD);
                }

                if(isChange) editor.commit();
                // Remember userid / password end

                Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_LONG)
                        .show();

                setResult(RESULT_OK);
                finish();

            }else{
                new AlertDialog.Builder(LoginActivity.this)
                        .setMessage("登入失敗")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
    }

}
