package com.example.cst438_01_wk01hw02solo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText userBox;
    private EditText passBox;
    private String inputUn;
    private String inputPw;
    private User tUser;
    private final ArrayList<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add some predefined users
        userList.add(new User("test","pass", 1));
        userList.add(new User("test2","pass2", 2));
        userList.add(new User("test3","pass3", 3));
        userList.add(new User("test4","pass4", 4));
        userList.add(new User("test5","pass5", 5));
        userList.add(new User("test6","pass6", 6));
        userList.add(new User("test7","pass7", 7));
        userList.add(new User("test8","pass8", 8));
        userList.add(new User("test9","pass9", 9));
        userList.add(new User("test10","pass10", 10));
        wireUpDisplay();
    }

    private void wireUpDisplay() { //connects code to ui
        userBox = findViewById(R.id.LoginU);
        passBox = findViewById(R.id.LoginP);
        Button loginButton = findViewById(R.id.buttonLoginAct);

        loginButton.setOnClickListener(v -> { //does user verification on button click
            getValuesFromDisplay();
            tUser = verifyUN(userList, inputUn);
            if(tUser != null){
                if(verifyPW(tUser, inputPw)){ //if both username and password matches, launch landing page
                    Intent intent = LandingActivity.intentFactory(getApplicationContext(),tUser.getuId(),tUser.getUsername());
                    startActivity(intent);
                }
                else{
                    toastMaker("Invalid Password"); //display if password doesn't match
                }
            }
            else{
                toastMaker("Invalid Credentials"); //display if username not found
            }
        });
    }

    private void getValuesFromDisplay() { //sets variables from user text box input
        inputUn = userBox.getText().toString();
        inputPw = passBox.getText().toString();
    }

    private void toastMaker(String message) { //for message display
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    static User verifyUN(List<User> list, String input){ //returns user when found, null if not found
        for(User u : list){
            if(input.equals(u.getUsername())){
                return u;
            }
        }
        return null;
    }

    static boolean verifyPW(User u, String input){ //returns true/false if input password matches user password
        return u.getPassword().equals(input);
    }

}

