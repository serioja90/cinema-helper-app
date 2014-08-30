/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sergiu
 */
public class Film {
  private String id, title, image, genre, tecnology, duration, year, 
                 director, cast, description, nation;
  public Film(JSONObject film) throws JSONException{
    id = film.getString("id");
    title = film.getString("title");
    image = film.getString("image");
    genre = film.getString("genre");
    tecnology = film.getString("tecnology");
    duration = film.getString("duration");
    year = film.getString("release_year");
    //director = film.getString("director");
    //cast = film.getString("film_cast");
    description = film.getString("description");
  }
  
  public String getId(){ return id; }
  public String getTitle(){ return title; }
  public String getImageUrl(){ return image; }
  public String getGenre(){ return genre; }
  public String getTecnology(){ return tecnology; }
  public String getDuration(){ return duration; }
  public String getYear(){ return year; }
  public String getDirector(){ return director; }
  public String getCast(){ return cast; }
  public String getDescription(){ return description; }
  
  public String getBriefDescription(){
    String result = description.substring(0, 200);
    if(description.length() > result.length()){
      result += "...";
    }
    return result;
  }
}
