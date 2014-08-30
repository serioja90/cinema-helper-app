package cinema.helper.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
  } 
  
  @Override
  public void onStart(){
    super.onStart();
    adapter.clear();
    for(Film film : Parser.getFilms()){
      adapter.add(film);
    }
  }
}
