package com.example.admin.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPassword;

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button btnLogin;

    //直接提取pref文件的isremember值，如果无就默认为false。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rememberPassword = findViewById(R.id.remember_password);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //创建一个默认文件，否则在没有文件的时候判断是否记住密码不能运行
        editor = pref.edit();
        editor.putString("status","statusTrue");
        editor.apply();

        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        boolean isRememberPass = pref.getBoolean("isRemeberPass",false);
        if (isRememberPass) {
            accountEdit.setText(pref.getString("account",""));
            passwordEdit.setText(pref.getString("password",""));
            rememberPassword.setChecked(true);
        }

        //账号密码核对无误后再做是否记住密码业务的判断，如果是则记住刺客账号密码的状态
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    if (rememberPassword.isChecked()) {
                        editor = pref.edit();
                        editor.putString("account",account);
                        editor.putString("password",password);
                        editor.putBoolean("isRemeberPass",true);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码有误，请检查核对后重试！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
