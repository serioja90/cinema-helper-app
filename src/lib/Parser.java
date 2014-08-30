/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;

/**
 *
 * @author sergiu
 */
public final class Parser {
  public static ArrayList<Film> getFilms(){
    Log.d("Parser", "===============================================================");
    InputStream stream = null;
    String result = null;
    ArrayList<Film> films = new ArrayList<Film>();
    DefaultHttpClient client = new DefaultHttpClient(new BasicHttpParams());
    HttpGet request = new HttpGet("http://code-panic.com:9999/cinema-helper/films/index");
    request.setHeader("Content-type", "application/json");
    try{
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      stream = entity.getContent();
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"),8);
      StringBuffer buffer = new StringBuffer();
      String line = null;
      while((line = reader.readLine()) != null){
        buffer.append(line + '\n');
      }
      result = buffer.toString();
      Log.d("Parser", result);
      JSONArray filmsJson = new JSONArray(result);
      for(int i = 0; i < filmsJson.length(); i++){
        films.add(new Film(filmsJson.getJSONObject(i)));
      }
    }catch(Exception e){
      Log.e("Parser", e.toString());
    }finally{
      if(stream != null) try {
        stream.close();
      } catch (IOException ex) {
        Log.e("Parser", ex.toString());
      }
    }
    return films;
  }
}
