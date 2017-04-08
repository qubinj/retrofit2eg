package com.example.qubin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(GitHubClient.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        GitHubClient client = retrofit.create(GitHubClient.class);
        Call<ArrayList<User>> call=client.userList();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ArrayList<User> users = response.body();
                //UserAdapter adapter= new UserAdapter(this,users);

               // User[] userarray=users.toArray(new User[users.size()]);
                //ArrayAdapter<User> adapter = new ArrayAdapter<User>(MainActivity.this,android.R.layout.activity_list_item,userarray);

                UserAdapter adapter = new UserAdapter(getApplicationContext(),R.layout.row,users);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }
}
