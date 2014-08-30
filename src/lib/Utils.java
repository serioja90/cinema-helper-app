/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 *
 * @author sergiu
 */
public final class Utils {
  
  public static synchronized void loadImageAsync(ImageView image, String url){
   new DownloadImageTask(image).execute(url);
  }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
  private final WeakReference imageReference;
  String url;
  
  static class DownloadedDrawable extends ColorDrawable {
    private final WeakReference<DownloadImageTask> downloaderReference;

    public DownloadedDrawable(DownloadImageTask bitmapDownloaderTask) {
        super(Color.BLACK);
        downloaderReference = new WeakReference<DownloadImageTask>(bitmapDownloaderTask);
    }

    public DownloadImageTask getBitmapDownloaderTask() {
        return downloaderReference.get();
    }
  }
  
  public DownloadImageTask(ImageView image){
    this.imageReference = new WeakReference(image);
  }

  @Override
  protected Bitmap doInBackground(String... urls) {
    Bitmap bmp = null;
    url = urls[0];
    try {
      InputStream input = new URL(url).openStream();
      bmp = BitmapFactory.decodeStream(input);
    } catch (Exception ex) {
      Log.e("DownloadImageTask", ex.toString());
    }
    return bmp;
  }
  
  @Override
  protected void onPostExecute(Bitmap bitmap){
    if(isCancelled()) bitmap = null;
    if(imageReference != null){
      ImageView image = (ImageView)imageReference.get();
      if(image != null) image.setImageBitmap(bitmap);
    }
  }  
}