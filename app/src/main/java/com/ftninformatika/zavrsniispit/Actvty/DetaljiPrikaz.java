package com.ftninformatika.zavrsniispit.Actvty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.ftninformatika.zavrsniispit.MainActivity;
import com.ftninformatika.zavrsniispit.R;
import com.ftninformatika.zavrsniispit.model.Movie;
import com.ftninformatika.zavrsniispit.model.OMDBApiService;
import com.ftninformatika.zavrsniispit.model.Search;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetaljiPrikaz extends AppCompatActivity {

    Toolbar toolbar;

    TextView title;
    TextView year;
    TextView rated;
    TextView released;
    TextView runtime;
    TextView genre;
    TextView director;
    TextView writer;
    TextView actors;
    TextView plot;
    TextView language;
    TextView country;
    String mID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_prikaz);

        title = findViewById(R.id.title);
        year = findViewById(R.id.year);
        rated = findViewById(R.id.rated);
        released = findViewById(R.id.released);
        runtime = findViewById(R.id.runtime);
        genre = findViewById(R.id.genre);
        director = findViewById(R.id.director);
        writer = findViewById(R.id.writer);
        actors = findViewById(R.id.actors);
        plot = findViewById(R.id.plot);
        language = findViewById(R.id.language);
        country = findViewById(R.id.country);


        getServire(mID);




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_home);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_pogledati):
               //dodati film u listu za gledanje
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getServire(String string){
        Search search = (Search) getIntent().getSerializableExtra("search");
        mID = search.getImdbID();

        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("apikey", "836e924d");
        queryParams.put("i", string); //vraca film za ID koji je uzet iz intenta koji je prosledjen u MainActivity

        Call<Movie> callMovie = OMDBApiService.apiInterface().searchMovieOMDB(queryParams);
        callMovie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();

                }else{
                    Movie movie = response.body();
                    title.setText(movie.getTitle());
                    year.setText(movie.getYear());
                    rated.setText(movie.getRated());
                    released.setText(movie.getReleased());
                    runtime.setText(movie.getRuntime());
                    genre.setText(movie.getGenre());
                    director.setText(movie.getDirector());
                    writer.setText(movie.getWriter());
                    actors.setText(movie.getActors());
                    plot.setText(movie.getPlot());
                    language.setText(movie.getLanguage());
                    country.setText(movie.getCountry());

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}


