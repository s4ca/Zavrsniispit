package com.ftninformatika.zavrsniispit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ftninformatika.zavrsniispit.Actvty.DetaljiPrikaz;
import com.ftninformatika.zavrsniispit.Actvty.Settings;
import com.ftninformatika.zavrsniispit.adapter.MyAdapter;
import com.ftninformatika.zavrsniispit.model.OMDBApiService;
import com.ftninformatika.zavrsniispit.model.Search;
import com.ftninformatika.zavrsniispit.model.SearchResult;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    Toolbar toolbar;

    EditText etPretraga;
    Button btnScr;
    RecyclerView rvLista;
    List<SearchResult>searchResults;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLista= findViewById(R.id.rv_lista);
        etPretraga = findViewById(R.id.et_pretragaMain);
        btnScr = findViewById(R.id.btnPretragaMain);
        btnScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSrcRez(etPretraga.getText().toString().trim());
            }
        });


        setupToolbar();

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_manu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
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
            case (R.id.action_settings):
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                break;

            case (R.id.action_pogledati):
                //pogledati
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void getSrcRez(String string) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("apikey", "836e924d");
        queryParams.put("s", string);

        Call<SearchResult> call = OMDBApiService.apiInterface().searchOMDB(queryParams);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.code() == 200) {
                    SearchResult resp = response.body();
                    rvLista.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    if (myAdapter == null){
                        myAdapter= new MyAdapter(resp.getSearch(), MainActivity.this);
                        rvLista.setAdapter(myAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
            }
        });
    }


    @Override
    public void onItemClick(Search search) {
        Intent intent = new Intent(this, DetaljiPrikaz.class);
        intent.putExtra("search", search);
        startActivity(intent);


    }
}
