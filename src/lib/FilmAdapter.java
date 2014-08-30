/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cinema.helper.app.R;
import java.util.ArrayList;

/**
 *
 * @author sergiu
 */
public class FilmAdapter extends BaseAdapter{
  Context context;
  ArrayList<Film> films;
  private static LayoutInflater inflater = null;
  private final ImageDownloader imageDownloader;
  
  static class ViewHolder {
    ImageView image;
		TextView title;
    TextView tecnology;
    TextView duration;
		TextView description;
	}
  
  public FilmAdapter(Context context, ArrayList<Film> films){
    this.context = context;
    this.films = films;
    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    imageDownloader = new ImageDownloader();
  }

  public int getCount() { return films.size(); }
  public Object getItem(int i) { return films.get(i); }
  public long getItemId(int i) { return i; }

  public View getView(int i, View view, ViewGroup vg) {
    ViewHolder holder;
    Film film = films.get(i);
    if(view == null){
      holder = new ViewHolder();
      view = inflater.inflate(R.layout.listrow, null);
      holder.title = (TextView)view.findViewById(R.id.title);
      holder.tecnology = (TextView)view.findViewById(R.id.tecnology);
      holder.duration = (TextView)view.findViewById(R.id.duration);
      holder.description = (TextView)view.findViewById(R.id.description);
      holder.image = (ImageView)view.findViewById(R.id.image);
      view.setTag(holder);
    }else{
      holder = (ViewHolder)view.getTag();
    }
    holder.title.setText(film.getTitle());
    holder.tecnology.setText("Tecnologia: " + film.getTecnology());
    holder.duration.setText("Durata: " + film.getDuration());
    holder.description.setText(film.getDescription());
    imageDownloader.download(film.getImageUrl(), holder.image);
    return view;
  }
  
  public void clear(){ films.clear(); }
  public void add(Film film){ 
    films.add(film);
  }
  
}
