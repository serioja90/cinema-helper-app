/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sergiu
 */
public class Film {
  private String id, title, image, genre, tecnology, duration, year, 
                 director, cast, description, nation, origin;
  public Film(JSONObject film){
    id = getValue(film, "id");
    title = getValue(film, "title");
    image = getValue(film, "image");
    genre = getValue(film, "genre");
    tecnology = getValue(film, "tecnology");
    duration = getValue(film, "duration");
    year = getValue(film, "release_year");
    director = getValue(film, "director");
    cast = getValue(film, "film_cast");
    description = getValue(film, "description");
    nation = getValue(film, "nation");
    origin = getValue(film, "origin");
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
  public String getNation(){ return nation; }
  public String getOrigin(){ return origin; }
  
  private static String getValue(JSONObject item, String field){
    String result = null;
    try{
      result = item.getString(field);
    }catch(JSONException ex){
      Log.e("Film", ex.toString());
    }
    return result;
  }
}
