package com.pga.project1.Utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable.Callback;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AsynLoadImage extends AsyncTask<String, String, Bitmap> {
	
	ImageView imv;
	String path;
	Bitmap bmp;
	
	Callback finishFunc;
	
	public AsynLoadImage(ImageView imv, String path){
		this.imv=imv;
		this.path=path;
	}


	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		Log.i("tag", "Asy load image called");
	}
	
	@Override
	protected Bitmap doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		
		try {

			
			URL url = new URL(path);
			bmp = BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
					

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("Image Loader",e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("Image Loader",e.getMessage());
		}
		return bmp;
		 
		
		
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
	//	Log.i("tag", "Asy load image ended");
		
		
		if(imv != null)
			imv.setImageBitmap(result);

		LoadManager.loadFinished(path, result);
		
		
		
	}
}