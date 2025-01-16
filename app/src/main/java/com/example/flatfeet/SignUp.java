package com.example.flatfeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {

    EditText registerID, registerPWD, registerPWDCH;
    Button registerBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerID = findViewById(R.id.registerID);
        registerPWD = findViewById(R.id.registerPWD);
        registerPWDCH = findViewById(R.id.registerPWDCH);
        registerBTN = findViewById(R.id.registerBTN);

        DBHelper dbHelper = new DBHelper(this);

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = registerID.getText().toString();
                String PWD = registerPWD.getText().toString();
                String PWDCH = registerPWDCH.getText().toString();

                check_valid(ID, PWD, PWDCH, dbHelper);
            }
        });
    }

    // 유효성 검사
    public void check_valid(String ID, String PWD, String PWDCH, DBHelper dbHelper) {
        if (ID.isEmpty() || PWD.isEmpty() || PWDCH.isEmpty()) {
            // 필드가 비어 있으면 사용자에게 알림
            Toast.makeText(this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if (ID.length() < 8) {
            Toast.makeText(this, "아이디를 8자리 이상 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else if(PWD.length() < 8) {
            Toast.makeText(this, "비밀번호를 8자리 이상 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else if(!PWD.equals(PWDCH)) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
        else {
            dbHelper.addUser(ID, PWD);

            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    } // check_valid
}
