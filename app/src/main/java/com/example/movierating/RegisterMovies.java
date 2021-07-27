//Ilma Zahil
//w1790405

package com.example.movierating;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovies extends AppCompatActivity {

    EditText title,director,year,actors,rating,review;
    Button save;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movies);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.reg_action_bar);


        title =findViewById(R.id.titleText);
        year =findViewById(R.id.yearText);
        director =findViewById(R.id.directorText);
        actors =findViewById(R.id.actorText);
        rating =findViewById(R.id.ratingText);
        review =findViewById(R.id.reviewText);
        save =findViewById(R.id.btn_save);
        database = new Database(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title_mov="";
                int year_mov=0;
                String director_mov="";
                String actor_mov="";
                int rating_mov=0;
                String review_mov="";
                Boolean fav = false;

                try {
                    //getting all the values from the edit text
                    title_mov = title.getText().toString();
                    year_mov = Integer.parseInt(year.getText().toString());
                    director_mov = director.getText().toString();
                    actor_mov = actors.getText().toString();
                    rating_mov = Integer.parseInt(rating.getText().toString());
                    review_mov = review.getText().toString();


                    Boolean checkInsertData =false;
                    //value pass to the database after checking the year and rating are validate  
                    if ((year_mov<2022 && year_mov>1895) && (rating_mov<=10 && rating_mov>0)){
                        Movies movi  = new Movies(title_mov,year_mov,director_mov,actor_mov,rating_mov,review_mov, fav);
                        checkInsertData = database.insertMovieData(movi);
                    }else {
                        yearAndRatingMsg();
                    }

                    if(checkInsertData==true)
                        Toast.makeText(RegisterMovies.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(RegisterMovies.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    correctEndMessage();
                }






            }
        });




    }

    //if some edit text are isEmpty sending Alert box
    public void correctEndMessage(){
        AlertDialog.Builder builder_alertBox = new AlertDialog.Builder(this);
        builder_alertBox.setTitle("Empty");
        builder_alertBox.setMessage("Try again");

        builder_alertBox.setCancelable(true);

        builder_alertBox.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertBox = builder_alertBox.create();

        alertBox.show();


    }

    //year and rating are incorrect send this massage
    public void yearAndRatingMsg(){
        AlertDialog.Builder builder_alertBox = new AlertDialog.Builder(this);
        builder_alertBox.setTitle("Your year or rating are Incorrect");
        builder_alertBox.setMessage("2022 > year > 1895 ||||| 1 < Rating < 10");

        builder_alertBox.setCancelable(true);

        builder_alertBox.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertBox = builder_alertBox.create();

        alertBox.show();


    }
}