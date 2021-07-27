//Ilma Zahil
//w1790405

package com.example.movierating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movies> {

    private Context context;
    private int resource;
    ArrayList<Movies> movies;

    MovieAdapter(Context context , int resource , ArrayList<Movies> movies){
        super(context,resource,movies);
        this.context = context;
        this.resource = resource;
        this.movies = movies;
    }

    private class ViewItem{
        TextView movieTitle;
        CheckBox checkBox;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View vc = convertView;
        ViewItem viewItem = new ViewItem();

        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vc = layoutInflater.inflate(R.layout.movie_image,null);
            viewItem.movieTitle =(TextView) vc.findViewById(R.id.move_tit);
            viewItem.checkBox = (CheckBox) vc.findViewById(R.id.checkFav);


            //set favourite after checking the check box
            viewItem.checkBox.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Movies movies = (Movies) cb.getTag();
                    Toast.makeText(getContext(), "favourite " , Toast.LENGTH_LONG).show();
                    movies.setFavourite(cb.isChecked());

                }
            });

        }else {
            viewItem = (ViewItem)vc.getTag();
        }

        try {
            //Display image, title and check box in a list
            Movies movie_box = movies.get(position);
            viewItem.movieTitle.setText(movie_box.getMovieTitle());
            viewItem.checkBox.setChecked(movie_box.getFavourite());
            viewItem.checkBox.setTag(movie_box);

        }catch (NullPointerException e){
            System.out.println("error");
        }



        return vc;
    }
}
