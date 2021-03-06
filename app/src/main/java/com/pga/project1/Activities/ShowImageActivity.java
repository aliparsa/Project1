package com.pga.project1.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.pga.project1.R;
import com.pga.project1.Viewes.ImageLoaderView;

import java.io.File;

public class ShowImageActivity extends Activity {
    String imagePath;
    ImageView imv;

    Bitmap[] images;
    private ImageLoaderView imlv;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_show_image);


//        String[] imagePaths = getIntent().getStringArrayExtra("images");
//        images = new Bitmap[imagePaths.length];
//        for (int i = 0; i < imagePaths.length; i++) {
//
//            File imgFile = new File(imagePaths[i]);
//            if (imgFile.exists()) {
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                images[i] = myBitmap;
//            }
//        }

//        imagePath = getIntent().getStringExtra("image");

        String imagePath = getIntent().getStringExtra("image");
        String imageurl = getIntent().getStringExtra("image_url");

        if (imagePath != null) {
            imv = (ImageView) findViewById(R.id.ImageView_show_Image);
            imv.setVisibility(View.VISIBLE);
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//               Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imagePath),300,300 );
                imv.setImageBitmap(myBitmap);
            }
        } else if (imageurl != null) {
            imlv = (ImageLoaderView) findViewById(R.id.ImageLoaderView_show_Image);
            imlv.setVisibility(View.VISIBLE);
            imlv.setUrlAndStartLoading(imageurl);

            imlv.setZoomAllowed(true);

        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        //Gallery gallery = (Gallery) findViewById(R.id.gallery_show);

//        gallery.setSpacing(1);
//        gallery.setAdapter(new GalleryImageAdapter(this, imv, images));

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
