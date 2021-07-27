//Ilma Zahil
//w1790405

package com.example.movierating;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText edTitle,edYear,edDirector,edActor,edRating,edReview;
    Button update;
    Database database;
    RatingBar starRate;
    CheckBox favChecker;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_one_movie);

        Context context = this;
        database = new Database(context);

        Intent intent = getIntent();
        int titleid = intent.getIntExtra("id",0);

        Movies updateMovie = database.getSingleToDo(titleid);


        edTitle = findViewById(R.id.editTitleText);
        edYear = findViewById(R.id.editYearText);
        edDirector = findViewById(R.id.editDirectorText);
        edActor = findViewById(R.id.editActorText);
        edRating = findViewById(R.id.editRatingText);
        edReview = findViewById(R.id.editReviewText);
        update = findViewById(R.id.btn_update);
        starRate = findViewById(R.id.star_rate);
        favChecker = findViewById(R.id.check_fav);



        //setting all values to the text boxes
        edTitle.setText(updateMovie.getMovieTitle());
        edYear.setText(updateMovie.getMovieYear()+"");
        edDirector.setText(updateMovie.getMovieDirector());
        edActor.setText(updateMovie.getMovieActors());
        edRating.setText(updateMovie.getMovieRating()+"");
        edReview.setText(updateMovie.getMovieReview());
        System.out.println(updateMovie.getFavourite());
        if (updateMovie.getFavourite()){
            favChecker.setChecked(true);
        }


        starRate.setRating(updateMovie.getMovieRating());


        // update changes and save all changes to the database
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_update = edTitle.getText().toString();
                int year_update = Integer.parseInt(edYear.getText().toString());
                String dir_update = edDirector.getText().toString();
                String actor_update = edActor.getText().toString();
                int rating_update =(int) (starRate.getRating());
                String review_update = edReview.getText().toString();
                Boolean fav_update= favChecker.isChecked();

                Movies myUpdate = new Movies(titleid,title_update,year_update,dir_update,actor_update,rating_update,review_update,fav_update);
                Boolean checkInsertData = database.updateSingleToDo(myUpdate);
                if(checkInsertData==true){
                    Toast.makeText(UpdateActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context,MainActivity.class));
                }else{
                    Toast.makeText(UpdateActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
