/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cinema.helper.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import lib.ImageDownloader;

/**
 *
 * @author sergiu
 */
public class FilmDetails extends Activity {
  ImageDownloader imageDownloader;
  ImageView image;
  TextView title, genre, duration, tecnology, director, cast, year, nation, description;
  LinearLayout directorLayout, castLayout, nationLayout;
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.film_details);
    imageDownloader = new ImageDownloader();
    Bundle params = getIntent().getExtras();
    if(params != null){
      image = (ImageView)findViewById(R.id.film_image);
      title = (TextView)findViewById(R.id.film_title);
      genre = (TextView)findViewById(R.id.film_genre);
      duration = (TextView)findViewById(R.id.film_duration);
      tecnology = (TextView)findViewById(R.id.film_tecnology);
      director = (TextView)findViewById(R.id.film_director);
      cast = (TextView)findViewById(R.id.film_cast);
      year = (TextView)findViewById(R.id.film_year);
      nation = (TextView)findViewById(R.id.film_nation);
      description = (TextView)findViewById(R.id.film_description);
      imageDownloader.download(params.getString("image"), image);
      
      title.setText(params.getString("title"));
      genre.setText(params.getString("genre"));
      duration.setText(params.getString("duration"));
      tecnology.setText(params.getString("tecnology"));
      director.setText(params.getString("director"));
      cast.setText(params.getString("cast"));
      year.setText(params.getString("year"));
      nation.setText(params.getString("nation"));
      description.setText(params.getString("description"));
      
      directorLayout = (LinearLayout)findViewById(R.id.director_layout);
      castLayout = (LinearLayout)findViewById(R.id.cast_layout);
      nationLayout = (LinearLayout)findViewById(R.id.nation_layout);
      
      directorLayout.setVisibility(params.getString("director") == null ? View.GONE : View.VISIBLE);
      castLayout.setVisibility(params.getString("cast") == null ? View.GONE : View.VISIBLE);
      nationLayout.setVisibility(params.getString("nation") == null ? View.GONE : View.VISIBLE);
    }
  }
}
