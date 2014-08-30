package cinema.helper.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class CinemaHelper extends Activity{
  ListView list;
  ArrayList<String> listItems;
  ArrayAdapter<String> adapter;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    list = (ListView)findViewById(R.id.list);
    listItems = new ArrayList<String>();
    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
    list.setAdapter(adapter);
    adapter.add("Ciao");
    adapter.add("Hello");
  } 
}
