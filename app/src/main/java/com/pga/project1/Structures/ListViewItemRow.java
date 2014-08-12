package com.pga.project1.Structures;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pga.project1.R;

/**
 * Created by ashkan on 8/10/2014.
 */
public class ListViewItemRow {


    TextView txt1;
    TextView txt2;
    TextView txt3;
    ImageView img1;
    //
    ImageView img2;
    ImageView img3;
    //
    private String text1;
    private boolean text1_flag = false;
    //
    private int text1_resId = R.id.txt_listViewRowItem_txt1;
    //
    private String text2;
    private boolean text2_flag = false;
    private int text2_resId = R.id.txt_listViewRowItem_txt2;
    //
    //
    private String text3;
    private boolean text3_flag = false;
    private int text3_resId = R.id.txt_listViewRowItem_txt3;
    //
    private Bitmap image1;
    //
    private boolean image1_flag = false;
    private int image1_resId = R.id.imgv_listViewRowItem_img1;
    //
    private Bitmap image2;
    private boolean image2_flag = false;
    //
    private int image2_resId = R.id.imgv_listViewRowItem_img2;
    //
    private Bitmap image3;
    private boolean image3_flag = false;
    private int image3_resId = R.id.imgv_listViewRowItem_img3;
    //
    private int resId;
    private int type;

    public ListViewItemRow(String text1) {
        this.resId = resId;

        this.setText1(text1);

        this.type = 0;
    }

    public ListViewItemRow(String text1, int text1_resId, int resId) {
        this.resId = resId;

        this.setText1(text1);
        this.text1_resId = text1_resId;

        this.type = 0;
    }

    public ListViewItemRow(String text1, Bitmap image1) {
        this.resId = resId;

        this.setText1(text1);
        this.setImage1(image1);

        this.type = 1;
    }


    public ListViewItemRow(String text1, int text1_resId, Bitmap image1, int image1_resId, int resId) {
        this.resId = resId;

        this.setText1(text1);
        this.text1_resId = text1_resId;

        this.setImage1(image1);
        this.image1_resId = image1_resId;

        this.type = 1;
    }

    public ListViewItemRow(String text1, String text2, Bitmap image1) {
        this.resId = resId;

        this.setText1(text1);
        this.setText2(text2);
        this.setImage1(image1);

        this.type = 3;
    }

    public ListViewItemRow(String text1, int text1_resId, String text2, int text2_resId, Bitmap image1, int image1_resId, int resId) {
        this.resId = resId;

        this.setText1(text1);
        this.setText2(text2);
        this.setImage1(image1);

        this.type = 3;
    }

    public View getView(Context context, View convertView) {

        View view = convertView;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.resId, null);

            setContents(view);
            view.setTag(this);

        } else {

            ListViewItemRow convertHolder = (ListViewItemRow) view.getTag();

            if (convertHolder != null && convertHolder.resId == this.resId) {

                Log.i("ashkan", convertHolder.text1 + " - >" + this.text1);
                setContents(convertHolder);

            } else {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(this.resId, null);

                setContents(view);
                view.setTag(this);
            }

        }


        return view;
    }

    private void setContents(View view) {

        if (text1_flag) {
            txt1 = (TextView) view.findViewById(text1_resId);
            txt1.setText(text1);
        }
        if (text2_flag) {
            txt2 = (TextView) view.findViewById(text2_resId);
            txt2.setText(text1);
        }
        if (text3_flag) {
            txt3 = (TextView) view.findViewById(text3_resId);
            txt3.setText(text1);
        }

        if (image1_flag) {
            img1 = (ImageView) view.findViewById(image1_resId);
            if (image1 != null)
                img1.setImageBitmap(image1);

        }
        if (image2_flag) {
            img2 = (ImageView) view.findViewById(image2_resId);
            if (image2 != null)
                img2.setImageBitmap(image2);

        }
        if (image3_flag) {
            img3 = (ImageView) view.findViewById(image3_resId);
            if (image3 != null)
                img3.setImageBitmap(image3);

        }
    }

    public void setContents(ListViewItemRow holder) {

        if (text1_flag) {
            holder.txt1.setText(text1);
        }
        if (text2_flag) {
            holder.txt2.setText(text1);
        }
        if (text3_flag) {
            holder.txt3.setText(text1);
        }

        if (image1_flag) {
            holder.img1.setImageBitmap(image1);
        }
        if (image2_flag) {
            holder.img2.setImageBitmap(image2);
        }
        if (image3_flag) {
            holder.img3.setImageBitmap(image3);
        }
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
        this.text1_flag = true;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
        this.text2_flag = true;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
        this.text3_flag = true;
    }

    public Bitmap getImage1() {
        return image1;
    }

    public void setImage1(Bitmap image1) {
        this.image1 = image1;
        this.image1_flag = true;
    }

    public Bitmap getImage2() {
        return image2;
    }

    public void setImage2(Bitmap image2) {
        this.image2 = image2;
        this.image2_flag = true;
    }

    public Bitmap getImage3() {
        return image3;
    }

    public void setImage3(Bitmap image3) {
        this.image3 = image3;
        this.image3_flag = true;
    }

    public int getResId() {
        return resId;
    }

}
