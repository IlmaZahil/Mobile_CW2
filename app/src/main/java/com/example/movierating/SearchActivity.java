//Ilma Zahil
//w1790405

package com.example.movierating;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText searchBar ;
    Button searchBtn;
    ListView movieList;
    ArrayList<Movies> movieArray;
    Database db;
    Movies movieObj;
    ArrayAdapter<String> dataAdepterq;
    Context context;
    ArrayList<String> diplaySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.search_action_bar);


        context = this;
        searchBar = findViewById(R.id.movieSearch_bar);
        searchBtn =  findViewById(R.id.searchButton);
        movieArray = new ArrayList<>();
        movieList = findViewById(R.id.searchMovieList);
        db = new Database(context);


        //read all data in the database as an object and add it to the arrayList
        Cursor cursor = db.getAllData();
        while (cursor.moveToNext()){
            movieObj = new Movies(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getInt(7)==1);
            movieArray.add(movieObj);


        }

        diplaySearch = new ArrayList<>();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diplaySearch.removeAll(diplaySearch);
                String getSearch = searchBar.getText().toString().toLowerCase();
                //search data from the array
                for (Movies movies: movieArray){
                    if (movies.getMovieTitle().toLowerCase().contains(getSearch)|| movies.getMovieDirector().contains(getSearch) || movies.getMovieActors().contains(getSearch)){
                        System.out.println(movies);
                        String tit = movies.getMovieTitle().toLowerCase();
                        int year = movies.getMovieYear();
                        diplaySearch.add(tit);


                    }
                }
                if (diplaySearch.isEmpty()){
                    System.out.println(diplaySearch);
                    Toast.makeText(getApplicationContext(),"Incorrect Not matching",Toast.LENGTH_SHORT).show();
                }

                dataAdepterq = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,diplaySearch);
                movieList.setAdapter(dataAdepterq);

            }
        });
    }
}