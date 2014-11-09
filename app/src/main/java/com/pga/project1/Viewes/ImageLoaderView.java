package com.pga.project1.Viewes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.pga.project1.Intefaces.ProgressCallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.AsynLoadImage;
import com.pga.project1.Utilities.OnPinchListener;

/**
 * Created by ashkan on 8/27/2014.
 */
public class ImageLoaderView extends RelativeLayout {

    private int defaultImageID;
    private boolean showProgressBar;
    private String url;
    private ImageView fakeImageView;
    private ImageView mainImageView;
    private ProgressBar progressBar;
    private AsynLoadImage async;
    private boolean circleImage;


    private boolean isZoomAllowed;
    private int zoomControler = 20;

    public ImageLoaderView(final Context context, String url) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_loader_view, this, true);

        this.url = url;
        this.showProgressBar = true;
        this.isZoomAllowed = false;

        configure();

        if (isZoomAllowed) {
            this.setOnTouchListener(new OnPinchListener(mainImageView));
        }


    }


    public ImageLoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageLoaderView);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_loader_view, this, true);

        this.url = a.getString(R.styleable.ImageLoaderView_URL);
        this.showProgressBar = a.getBoolean(R.styleable.ImageLoaderView_show_progressbar, true);
        this.isZoomAllowed = a.getBoolean(R.styleable.ImageLoaderView_zoomAllowed, false);

        this.defaultImageID = a.getResourceId(R.styleable.ImageLoaderView_default_src, 0);
        this.circleImage = a.getBoolean(R.styleable.ImageLoaderView_circleImage, false);


        configure();

        if (isZoomAllowed) {
            // mainImageView.setClickable(false);
            this.setOnTouchListener(new OnPinchListener(mainImageView));
        }

        a.recycle();
    }

    private void configure() {

        this.fakeImageView = (ImageView) findViewById(R.id.imv_fake);
        this.mainImageView = (ImageView) findViewById(R.id.imv_main);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        if (this.defaultImageID != 0) {
            fakeImageView.setImageResource(this.defaultImageID);
            progressBar.setVisibility(GONE);
        }

        if (!showProgressBar)
            progressBar.setVisibility(GONE);

        if (!isInEditMode())
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

        /*if(async != null && !async.isCancelled()) {
            async.cancel(true);
            progressBar.setProgress(0);
            mainImageView.setImageResource(0);
            mainImageView.setImageBitmap(null);
        }*/

        progressBar.setProgress(0);
        mainImageView.setImageResource(0);
        mainImageView.setImageBitmap(null);

        if (defaultImageID != 0)
            fakeImageView.setImageResource(defaultImageID);

        if (showProgressBar && this.defaultImageID == 0)
            progressBar.setVisibility(VISIBLE);
        else
            progressBar.setVisibility(GONE);

        if (url != null && url.length() > 0) {
            async = new AsynLoadImage(getContext(), this.url, new ProgressCallBack<Bitmap>() {
                @Override
                public void onSuccess(Bitmap result) {

                    // getclip(result);
                    fakeImageView.setImageResource(0);
                    fakeImageView.setImageBitmap(null);

                    mainImageView.setImageBitmap(result);
                    invalidate();

                    progressBar.setVisibility(GONE);
                }

                @Override
                public void onError(String err) {

                    if (defaultImageID == 0)
                        mainImageView.setImageResource(R.drawable.image_error);
                    else
                        fakeImageView.setImageResource(defaultImageID);

                    progressBar.setVisibility(GONE);
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


    public void setUrlAndStartLoading(String url) {

        progressBar.setProgress(0);
        mainImageView.setImageResource(0);
        mainImageView.setImageBitmap(null);

        this.url = url;

        startLoading();
    }

    public void setBitmap(Bitmap bitmap) {
        progressBar.setVisibility(GONE);
        this.mainImageView.setImageBitmap(bitmap);
    }

    public void setBitmapResource(int resource) {
        progressBar.setVisibility(GONE);
        this.mainImageView.setImageResource(resource);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (async != null && !async.isCancelled())
            async.cancel(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //here u can control the width and height of the images........ this line is very important
//        mainImageView.getDrawable().setBounds((getWidth() / 2) - zoomControler, (getHeight() / 2) - zoomControler, (getWidth() / 2) + zoomControler, (getHeight() / 2) + zoomControler);
//        mainImageView.getDrawable().draw(canvas);

    }


    public void setZoomAllowed(boolean flag) {
        isZoomAllowed = flag;
    }


}