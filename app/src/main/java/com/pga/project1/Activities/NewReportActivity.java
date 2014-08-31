package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Report;
import com.pga.project1.Intefaces.ProgressCallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.ImageHelper;
import com.pga.project1.Utilities.PersianCalendar;
import com.pga.project1.Utilities.Webservice;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.Viewes.ViewDateTimePickerPersian;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class NewReportActivity extends Activity {

    final int CAMERA_REQUEST = 111;
    final int GALLERY_REQUEST = 222;


    LinearLayout ll_image_list;
    EditText report;
    Button percent;
    Button timePicker;
    private int selectedPercent;
    private PersianCalendar selectedDateTime;
    Context context;
    Chart chart;
    private Uri imageUri;


    //--------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        chart = (Chart) getIntent().getSerializableExtra("chart");

        PathMapManager.push(new PathObject("عملکرد جدید"));

        setContentView(R.layout.activity_new_report);

        //showCustomActionBar();


        context = this;


        ll_image_list = (LinearLayout) findViewById(R.id.ll_fragmentWork_workReport_imageList);

        report = (EditText) findViewById(R.id.edittext_fragmentNewReportWork_reportText);
        percent = (Button) findViewById(R.id.btn_fragmentNewReportWork_selectPercent);
        timePicker = (Button) findViewById(R.id.btn_fragmentNewReportWork_selectDate);


        // set pre percent value to edit text
        selectedPercent = chart.getHand_percent();
        percent.setText(selectedPercent + " %");
        timePicker.setText(new PersianCalendar().getIranianDateTime());

        selectedDateTime = new PersianCalendar();


        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp;
                if (selectedDateTime == null)
                    dp = new ViewDateTimePickerPersian(context);
                else
                    dp = new ViewDateTimePickerPersian(context, selectedDateTime);

                new AlertDialog.Builder(context)
                        .setTitle("انتخاب زمان و تاریخ")
                        .setCancelable(false)
                        .setView(dp)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedDateTime = dp.getDate();
                                        timePicker.setText(selectedDateTime.getIranianDateTime());
                                    }
                                }
                        )
                        .show();
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final NumberPicker percentPicker = new NumberPicker(context);
                percentPicker.setMaxValue(100);
                percentPicker.setMinValue(1);

                percentPicker.setValue(selectedPercent);

                new AlertDialog.Builder(context)
                        .setTitle("درصد پیشرفت")
                        .setCancelable(false)
                        .setView(percentPicker)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedPercent = percentPicker.getValue();
                                        percent.setText(selectedPercent + "%");
                                    }
                                }
                        )
                        .show();
            }
        });
    }

    //--------------------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_report, menu);
        return true;
    }
    //--------------------------------------------------------------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_camera) {
            //TODO handle Camera
            if (ll_image_list.getChildCount() < 5)
                attachMedia();
            else
                Toast.makeText(context, "فقط 5 تصویر میتوان افزود", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.ac_work_report_save) {
            //TODO Create Report Object And Return
            saveReport();
            return true;
        }


        if (id == R.id.ac_back_in_new_report) {
            //TODO Create Report Object And Return
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    //--------------------------------------------------------------------------------

    @Override
    protected void onDestroy() {

        PathMapManager.pop("new report act onBackPressed");

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //--------------------------------------------------------------------------------

    private void saveReport() {

        int type = 1;


        Report obj_report = new Report(-1, chart, -1, selectedDateTime.getIranianDate(), report.getText().toString(), selectedPercent);


        final ProgressDialog pg = new ProgressDialog(context);
        pg.setMessage("در حال ارسال");
        pg.setCancelable(false);
        pg.setMax(100);
        pg.show();


        String[] imagePathList = new String[5];

        for (int i = 0; i < ll_image_list.getChildCount(); i++) {
            imagePathList[i] = ((String) (((ImageView) ll_image_list.getChildAt(i)).getTag()));
        }
        if (imagePathList[0] == null) imagePathList = null;


        Webservice.saveWorkReport(context, obj_report, imagePathList, new ProgressCallBack() {

            @Override
            public void onSuccess(Object result) {
                pg.dismiss();

                new AlertDialog.Builder(context)
                        .setTitle("موفقیت")
                        .setMessage("عملکرد ذخیره شد!")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                finish();


                            }
                        })
                        .show();

                Log.d("ali", "Report Saved successfully");
            }

            @Override
            public void onError(String err) {
                pg.dismiss();

                new AlertDialog.Builder(context)
                        .setTitle("خطا")
                        .setMessage("عملیات انجام نشد!")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("خب", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete


                            }
                        })
                        .show();

                Log.d("ali", "Report Save Error");

            }

            @Override
            public void onProgress(int done, int total, Object result) {
                pg.setMessage("در حال ارسال " + done + " از " + total + " تصویر");
                //pg.setProgress(100 / total * done);
            }
        });

    }


    //--------------------------------------------------------------------------------

    private void attachMedia() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("افزودن تصویر")
                .setItems(new String[]{"گالری", "دوربین"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                callGallery();
                                break;
                            case 1:
                                callCamera();
                                break;
                        }
                    }
                });
        builder.show();


    }

    //--------------------------------------------------------------------------------
    private void callCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture" + new Random().nextInt());
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_REQUEST);


/*        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
    }

    //--------------------------------------------------------------------------------
    private void callGallery() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    //--------------------------------------------------------------------------------
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView temp_img = (ImageView) this.getLayoutInflater().inflate(R.layout.inf_image_frame, null);


        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case CAMERA_REQUEST:
                    // TODO Handle Picked Image From Camera
                    try {

                        // get photo captured by camera
                        Bitmap photo_camera = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);


                        //resize photo to estimated size
                        ImageHelper imh = new ImageHelper();
                        Bitmap lowQualityBitmap = imh.resizeImage(1024, photo_camera);
                        String ImagePath = storeThisImage(lowQualityBitmap).getAbsolutePath();


                        // add image to linear layout
                        Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(ImagePath), 200, 200);
                        temp_img.setImageBitmap(ThumbImage);

                        // add image path as tag to view
                        temp_img.setTag(ImagePath);
                        ll_image_list.addView(temp_img);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
                case GALLERY_REQUEST:
                    //TODO Handle Picked Image From Gallery
                    Uri _uri = data.getData();

                    //User had pick an image.
                    Cursor cursor = context.getContentResolver().query(_uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
                    cursor.moveToFirst();

                    //Link to the image
                    String path = cursor.getString(0);
                    cursor.close();

                    //resize photo to estimated size
                    ImageHelper imh = new ImageHelper();
                    Bitmap lowQualityBitmap = imh.resizeImage(1024, path);
                    String ImagePath = storeThisImage(lowQualityBitmap).getAbsolutePath();

                    // add image to linear layout
                    Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(ImagePath), 200, 200);
                    temp_img.setImageBitmap(ThumbImage);

                    // add image path as tag to view
                    temp_img.setTag(ImagePath);
                    ll_image_list.addView(temp_img);


                    break;

            }
        }

        temp_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ImageView selectedImageView = (ImageView) view;

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("افزودن تصویر")
                        .setItems(new String[]{"نمایش", "حذف"},
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int item) {
                                        switch (item) {
                                            case 0:
                                                ShowImage(selectedImageView);
                                                break;
                                            case 1:
                                                DeleteImage(selectedImageView);
                                                break;
                                        }
                                    }
                                }
                        );
                builder.show();
            }
        });
    }

    //-----------------------------------
    private void DeleteImage(ImageView selectedImageView) {

        ll_image_list.removeView(selectedImageView);
    }

    //------------------------------------
    private void ShowImage(ImageView selectedImageView) {

//        String[]  imagesPath = new String[ ll_image_list.getChildCount()];
//
//        for (int i = 0; i < ll_image_list.getChildCount(); i++) {
//            imagesPath[i]= (String)(ll_image_list.getChildAt(i).getTag());
//        }
        Intent intent = new Intent(this, ShowImageActivity.class);
        intent.putExtra("image", (String) selectedImageView.getTag());
        startActivity(intent);

    }

    //-----------------------------------------------------------------------
    public File storeThisImage(Bitmap bitmap) {
        String path = Environment.getExternalStorageDirectory().toString();
        try {

            OutputStream fOut = null;
            File file = new File(path, new Random().nextLong() + ".jpg");
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            long x = file.getTotalSpace();
            fOut.flush();
            fOut.close();
            return file;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void showCustomActionBar() {
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_action_bar, null);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(v);
    }

    public String normalizeImage(String path) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        Bitmap b = BitmapFactory.decodeFile(path);


        Bitmap out = Bitmap.createScaledBitmap(b, 1024, 768, false);

        Random rand = new Random();
        Long longNum = rand.nextLong();
        File file = new File(dir, longNum + ".png");
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);

            out.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            b.recycle();
            out.recycle();

        } catch (Exception e) { // TODO

        }
        return dir.getAbsolutePath() + "/" + longNum + ".png";
    }
}
