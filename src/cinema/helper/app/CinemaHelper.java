package cinema.helper.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import java.util.ArrayList;
import lib.Film;
import lib.FilmAdapter;
import lib.Parser;

public class CinemaHelper extends Activity{
  ListView list;
  ArrayList<Film> listItems;
  FilmAdapter adapter;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    list = (ListView)findViewById(R.id.list);
    listItems = new ArrayList<Film>();
    adapter = new FilmAdapter(this, listItems);
    list.setAdapter(adapter);
    for(Film film : Parser.getFilms()){
      adapter.add(film);
    }
    
    list.setOnItemClickListener(new OnItemClickListener(){
      @Override
      public void onItemClick(AdapterView<?> av, View view, int i, long l) {
        Film film = (Film)av.getAdapter().getItem(i);
        Intent intent = new Intent(getApplicationContext(), FilmDetails.class);
        intent.putExtra("title", film.getTitle());
        intent.putExtra("image", film.getImageUrl());
        intent.putExtra("tecnology", film.getTecnology());
        intent.putExtra("genre", film.getGenre());
        intent.putExtra("duration", film.getDuration());
        intent.putExtra("director", film.getDirector());
        intent.putExtra("cast", film.getCast());
        intent.putExtra("year", film.getYear());
        intent.putExtra("nation", film.getNation());
        intent.putExtra("description", film.getDescription());
        startActivity(intent);
      }
    });
  } 
}
