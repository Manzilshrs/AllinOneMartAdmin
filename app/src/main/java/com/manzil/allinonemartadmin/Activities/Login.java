package com.manzil.allinonemartadmin.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.manzil.allinonemartadmin.R;

public class Login extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {

    EditText name, password;
    Button confirm;
    CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        name = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        rem_userpass = findViewById(R.id.checkbox);

        if (sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);
        name.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        password.setText(sharedPreferences.getString(KEY_PASS, ""));

        name.addTextChangedListener(this);
        password.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done();
            }

        });

//        if (!isTaskRoot()
//                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
//                && getIntent().getAction() != null
//                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {
//
//            finish();
//            return;
//        }

    }

    public void done() {
        String ename = name.getText().toString().trim();
        String epassword = password.getText().toString().trim();

        if (ename.equals("admin") && epassword.equals("admin")) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Login Failed ...Username or Password Error!!", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();

    }

    private void managePrefs() {
        if (rem_userpass.isChecked()) {
            editor.putString(KEY_USERNAME, name.getText().toString().trim());
            editor.putString(KEY_PASS, password.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        } else {
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
            editor.apply();
        }
    }
}


