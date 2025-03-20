package com.example.flatfeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText edtId, edtPassword;
    Button btnLogin, btnSignUp;
    DBHelper dbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        edtId = findViewById(R.id.id);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        // 로그인
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                String password = edtPassword.getText().toString();
                if(id.equals("") || password.equals("")){
                    Toast.makeText(Login.this, "모든 항목을 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else if(!dbHelper.checkUser(id)){
                    Toast.makeText(Login.this, "존재하지 않는 아이디 입니다." , Toast.LENGTH_LONG).show();
                }
                else if (!dbHelper.checkPassword(id, password)) {
                    Toast.makeText(Login.this, "아이디와 비밀번호가 일치하지 않습니다", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), Products.class);
                    startActivity(intent);
                }

            }
        });

        //회원가입 버튼 클릭 시 회원가입 창으로 이동
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    }
}