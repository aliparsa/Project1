package com.pga.project1.Viewes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
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
    private AsynLoadImage async;

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
        mainImageView.setImageBitmap(null);

        this.url = url;
    }

    public void startLoading() {

        if(async != null && !async.isCancelled()) {
            async.cancel(true);
            progressBar.setProgress(0);
            mainImageView.setImageResource(0);
            mainImageView.setImageBitmap(null);
        }

        if(url != null && url.length() > 0) {
            async = new AsynLoadImage(getContext(), this.url, new ProgressCallBack<Bitmap>() {
                @Override
                public void onSuccess(Bitmap result) {

                    mainImageView.setImageBitmap(result);
                }

                @Override
                public void onError(String err) {

                }

                @Override
                public void onProgress(int done, int total, Bitmap result) {

                    progressBar.setProgress(done);
                }
            });

            //async.execute();
            async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if(async != null && !async.isCancelled())
            async.cancel(true);
    }
}
