package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    //Json link

    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=4e44d9029b1270a757cddc766a1bcb63";

    ImageView header;
    List<MoiveModelClass> movielist;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movielist = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        header= findViewById(R.id.header);

        Glide.with(this)
                .load(R.drawable.to)
                .into(header);


        GetData getData = new GetData();
        getData.execute();


    }

    public class GetData extends AsyncTask<String, String,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try {
                URL url;
               HttpURLConnection httpURLConnection = null;

                try {
                    url = new URL(JSON_URL);
                   httpURLConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream=httpURLConnection.getInputStream();
                    InputStreamReader reader= new InputStreamReader(inputStream);

                    int data = reader.read();
                    while (data != -1)
                    {
                        current += (char) data;
                        data = reader.read();

                    }
                   return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(httpURLConnection != null)
                    {
                        httpURLConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try
            {
                JSONObject object = new JSONObject(s);
                JSONArray jsonArray = object.getJSONArray("results");

                for(int i = 0 ; i< jsonArray.length() ; i++)
                {
                    JSONObject jsonObject =jsonArray.getJSONObject(i);

                    MoiveModelClass modelClass = new MoiveModelClass();

                    modelClass.setName(jsonObject.getString("title"));
                    modelClass.setId(jsonObject.getString("vote_average"));
                    modelClass.setImage(jsonObject.getString("poster_path"));
//                    modelClass.setOverview(jsonObject.getString("overview"));

                    movielist.add(modelClass);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView( movielist);

        }
    }

    private void PutDataIntoRecyclerView(List<MoiveModelClass> movielist)
    {

        MoiveAdapter adapter = new MoiveAdapter(this,movielist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}