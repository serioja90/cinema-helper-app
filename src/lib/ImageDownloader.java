package lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashMap;

public class ImageDownloader {
  private static final HashMap<String,Bitmap> cache;
  static{
    cache = new HashMap<String,Bitmap>();
  }
  
  public ImageDownloader(){}
  
  public void download(String url, ImageView image){
    Bitmap bitmap = getFromCache(url);
    if(bitmap != null){
      image.setImageBitmap(bitmap);
      return;
    }
    if(cancelPotentialDownload(url,image)){
      BitmapDownloaderTask task = new BitmapDownloaderTask(image);
      DownloadedDrawable downloadedDrawable = new DownloadedDrawable(task);
      image.setImageDrawable(downloadedDrawable);
      task.execute(url);
    }
  }

  private void forceDownload(String url, ImageView image) {
    if(cancelPotentialDownload(url,image)){
      BitmapDownloaderTask task = new BitmapDownloaderTask(image);
      DownloadedDrawable downloadedDrawable = new DownloadedDrawable(task);
      image.setImageDrawable(downloadedDrawable);
      task.execute(url);
    }
  }

  private static boolean cancelPotentialDownload(String url, ImageView image) {
    BitmapDownloaderTask task = getBitmapDownloaderTask(image);
    if(task != null){
      String bitmapUrl = task.url;
      if((bitmapUrl == null) || !bitmapUrl.equals(url)){
        task.cancel(true);
      }else{
        return false; // ritorna false solamente se si sta scaricando la stessa immagine
      }
    }
    return true;
  }
  
  private static BitmapDownloaderTask getBitmapDownloaderTask(ImageView image) {
    if(image != null){
      Drawable drawable = image.getDrawable();
      if(drawable instanceof DownloadedDrawable){
        return ((DownloadedDrawable)drawable).getBitmapDownloaderTask();
      }
    }
    return null;
  }
  
  public static void addToCache(String url, Bitmap bitmap){
    cache.put(url, bitmap);
  }
  
  public static Bitmap getFromCache(String url){
    return cache.get(url);
  }
  
  
  static class DownloadedDrawable extends ColorDrawable {
    private final WeakReference<BitmapDownloaderTask> bitmapDownloaderTaskReference;

    public DownloadedDrawable(BitmapDownloaderTask bitmapDownloaderTask) {
        super(Color.BLACK);
        bitmapDownloaderTaskReference = new WeakReference<BitmapDownloaderTask>(bitmapDownloaderTask);
    }

    public BitmapDownloaderTask getBitmapDownloaderTask() {
        return bitmapDownloaderTaskReference.get();
    }
  }
  
  static class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap>{
    private final WeakReference imageReference;
    public String url;
    
    public BitmapDownloaderTask(ImageView image){
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
      addToCache(url, bitmap);
      Log.d(this.getClass().getSimpleName(), "isCancelled: " + isCancelled());
      if(imageReference != null){
        Log.d(this.getClass().getSimpleName(), "imageReference is not null");
        ImageView image = (ImageView)imageReference.get();
        BitmapDownloaderTask task = getBitmapDownloaderTask(image);
        Log.d(this.getClass().getSimpleName(), "Image task is: " + task);
        if(this == task) image.setImageBitmap(bitmap);
        Log.d(this.getClass().getSimpleName(), "Image task == this? " + (this == task));
      }
    }  
    
  }
}
