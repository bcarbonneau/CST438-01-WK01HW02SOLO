package com.example.cst438_01_wk01hw02solo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingActivity extends AppCompatActivity {

    public static String ACTIVITY_LABEL = "LANDING_ACTIVITY_COM_EXAMPLE";
    private TextView textViewResult;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        textViewResult = (TextView) findViewById(R.id.text_view_result);

        uid = getIntent().getIntExtra(ACTIVITY_LABEL, -1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post p : posts) {
                    if (uid == p.getUserId()) {
                        String content = "";
                        content += "ID: " + p.getId() + "\n";
                        content += "User ID: " + p.getUserId() + "\n";
                        content += "Title: " + p.getTitle() + "\n";
                        content += "Body: " + p.getBody() + "\n\n";
                        textViewResult.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    public static Intent intentFactory(Context context, int uid) {
        Intent intent = new Intent(context, LandingActivity.class);
        intent.putExtra(LandingActivity.ACTIVITY_LABEL, uid);
        return intent;
    }
}