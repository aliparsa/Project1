package com.pga.project1.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.pga.project1.R;

import java.io.File;

public class ActivityShowImage extends Activity {
    String imagePath;
    ImageView imv;

    Bitmap[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_show_image);


        String imagePath = getIntent().getStringExtra("image");
        imv = (ImageView) findViewById(R.id.ImageView_show_Image);
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//               Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imagePath),300,300 );
            imv.setImageBitmap(myBitmap);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_show_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private class GalleryImageAdapter extends BaseAdapter {
//
//        private final ImageView imv;
//        private final Bitmap[] images;
//
//
//
//        public GalleryImageAdapter(Context context, ImageView imv, Bitmap[] images)
//        {
//            Context mContext = context;
//            this.imv = imv;
//            this.images = images;
//        }
//
//        public int getCount() {
//            return images.length;
//        }
//
//        public Object getItem(int position) {
//            return position;
//        }
//
//        public long getItemId(int position) {
//            return position;
//        }
//
//
//        // Override this method according to your need
//        public View getView(int index, View view, ViewGroup viewGroup)
//        {
//            // TODO Auto-generated method stub
//            imv.setImageBitmap(images[index]);
//
//            return imv;
//        }
//    }
}
