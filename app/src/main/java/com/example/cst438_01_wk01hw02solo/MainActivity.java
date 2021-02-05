package com.example.cst438_01_wk01hw02solo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText userBox;
    private EditText passBox;
    private Button LoginButton;
    private String inputUn;
    private String inputPw;
    private User tUser;

    private ArrayList<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList.add(new User("test","pass", 1));
        userList.add(new User("test2","pass2", 2));
        userList.add(new User("test3","pass3", 3));
        userList.add(new User("test4","pass4", 4));
        userList.add(new User("test5","pass5", 5));
        wireUpDisplay();
    }

    private void wireUpDisplay() {
        userBox = findViewById(R.id.LoginU);
        passBox = findViewById(R.id.LoginP);
        LoginButton = findViewById(R.id.buttonLoginAct);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromDisplay();
                boolean uFound = false;
                for(User u : userList){
                    if(inputUn.equals(u.getUsername())){
                        tUser = u;
                        uFound = true;
                    }
                }
                if(uFound && tUser.getPassword().equals(inputPw)){
                    //do login stuff here
                    Intent intent = LandingActivity.intentFactory(getApplicationContext(),tUser.getuId());
                    startActivity(intent);
                }
                else{
                    toastMaker("Invalid Credentials");
                }
            }
        });

    }

    private void getValuesFromDisplay() {
        inputUn = userBox.getText().toString();
        inputPw = passBox.getText().toString();
    }

    private void toastMaker(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}

