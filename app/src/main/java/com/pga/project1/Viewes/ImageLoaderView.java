package com.pga.project1.Viewes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.pga.project1.Intefaces.ProgressCallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.AsynLoadImage;
import com.pga.project1.Utilities.ErrorMessage;

/**
 * Created by ashkan on 8/27/2014.
 */
public class ImageLoaderView extends RelativeLayout {

    private String url;
    private ImageView fakeImageView;
    private ImageView mainImageView;
    private ProgressBar progressBar;

    public ImageLoaderView(Context context, String url) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_loader_view, this, true);

        this.url = url;

        configure();
    }

    public ImageLoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageLoaderView);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_loader_view, this, true);

        this.url = a.getString(R.styleable.ImageLoaderView_URL);

        configure();

        a.recycle();
    }

    private void configure() {

        this.fakeImageView = (ImageView) findViewById(R.id.imv_fake);
        this.mainImageView = (ImageView) findViewById(R.id.imv_main);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);


        if(!isInEditMode())
            startLoading();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {

        progressBar.setProgress(0);
        mainImageView.setImageResource(0);

        this.url = url;
    }

    public void startLoading() {

        if(url != null && url.length() > 0) {
            AsynLoadImage async = new AsynLoadImage(this.url, new ProgressCallBack<String>() {
                @Override
                public void onSuccess(String result) {

                    mainImageView.setImageBitmap(BitmapFactory.decodeFile(result));
                }

                @Override
                public void onError(ErrorMessage err) {

                }

                @Override
                public void onProgress(int done, int total, String result) {

                    progressBar.setProgress(done);
                }
            });

            //async.execute();
            async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }


}
