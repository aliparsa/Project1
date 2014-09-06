package com.pga.project1.Viewes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.pga.project1.R;


/**
 * Created by aliparsa on 8/21/2014.
 */
public class Graphview extends View {

    public boolean showAnimation;
    RectF rectf;
    //            = new RectF (10, 10, 100, 100);
    Rect rect;
    int temp = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] value_degree;
    private int[] COLORS = {Color.BLUE, Color.GREEN, Color.GRAY, Color.CYAN, Color.RED};
    private int background;
    private int color1;
    private int color2;
    private int color3;
    private int color4;
    private int textColor;
    private int strokeColor;
    private float endAngle;
    private int animationSpeed;
    private int strokeWidth;
    private float percent;
    int red = 255;
    int blue = 0;
    int green = 0;


    public Graphview(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Graphview);
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflater.inflate(R.layout.view_name_value, this, true);


        percent = a.getFloat(R.styleable.Graphview_percentValue, 0);

        background = a.getColor(R.styleable.Graphview_background, Color.DKGRAY);
        COLORS[0] = a.getColor(R.styleable.Graphview_color1, COLORS[0]);
        COLORS[1] = a.getColor(R.styleable.Graphview_color2, COLORS[1]);
        COLORS[2] = a.getColor(R.styleable.Graphview_color3, COLORS[2]);
        COLORS[3] = a.getColor(R.styleable.Graphview_color4, COLORS[3]);

        strokeWidth = a.getInt(R.styleable.Graphview_strokeWidth, 35);
        animationSpeed = a.getInt(R.styleable.Graphview_animationSpeed, 5);

        textColor = a.getColor(R.styleable.Graphview_textColor, Color.BLACK);

        strokeColor = a.getColor(R.styleable.Graphview_strokeColor, Color.BLACK);
        value_degree = new float[]{(percent * 360 / 100)};
        endAngle = value_degree[0];
        value_degree[0] = 0;

        rectf = new RectF(0, 0, 0, 0);


    }

    public Graphview(Context context, float[] values) {

        super(context);
        value_degree = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            value_degree[i] = values[i];
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);

        float min = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        rectf = new RectF(5, 5, min - 5, min - 5);
        // rectf = new RectF(20, 20, min - 20, min - 20);


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        temp = -90;

        // draw background
        paint.setColor(background);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawArc(rectf, 0, 360, true, paint);
        // end draw background

        for (int i = 0; i < value_degree.length; i++) {//values2.length; i++) {
            if (i == 0) {


                // Draw Main
                paint.setColor(COLORS[i]);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawArc(rectf, -90, value_degree[i], true, paint);

                // Draw Inner Circle
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                float start_Point = this.getMeasuredWidth() * 1 / 4;
                float end_Point = this.getMeasuredWidth() * 3 / 4;
                RectF innerCircle = new RectF(rectf);
                innerCircle.set(start_Point, start_Point, end_Point, end_Point);
                canvas.drawArc(innerCircle, 0, 360, true, paint);

                // Draw Text
                paint.setColor(textColor);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(Math.min(this.getMeasuredWidth(), this.getMeasuredHeight()) / 5);

                paint.measureText((Math.floor(value_degree[i] * 100 / 360)) + "%");
                String percent_text = ((int) value_degree[i] * 100 / 360) + "%";
                Rect rect = new Rect();
                paint.getTextBounds(percent_text, 0, percent_text.length(), rect);
                canvas.drawText(percent_text, this.getMeasuredWidth() / 2 - (rect.width() / 2), this.getMeasuredHeight() / 2 + (rect.height() / 2), paint);


            } else {
                temp += (int) value_degree[i - 1];
                paint.setColor(COLORS[i]);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawArc(rectf, temp, value_degree[i], true, paint);
            }
        }

        if (value_degree[0] < endAngle) {
            value_degree[0] += animationSpeed;
            invalidate();
        }
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;

        value_degree = new float[]{(percent * 360 / 100)};

        if (showAnimation) {
            endAngle = value_degree[0];
            value_degree[0] = 0;
            invalidate();
        } else {
            endAngle = 0;
            invalidate();
        }
    }
}
