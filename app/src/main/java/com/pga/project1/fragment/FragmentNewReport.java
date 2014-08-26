package com.pga.project1.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.pga.project1.DataModel.Chart;
import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.Activities.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Utilities.PersianCalendar;
import com.pga.project1.Viewes.PathMapManager;
import com.pga.project1.Viewes.ViewDateTimePickerPersian;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by ashkan on 8/18/2014.
 */
public class FragmentNewReport extends Fragment {


    //{Constants-----------------------------------------------------

    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    public static final int REPORT_TYPE_WORK = 1;
    public static final int REPORT_TYPE_TASK = 2;
    final int CAMERA_REQUEST = 111;
    final int GALLERY_REQUEST = 222;
    int reportType = 0;

    EditText report;
    Button percent;
    Button timePicker;
    LinearLayout ll_image_list;
    private Chart chart;
    //filled information
    private PersianCalendar selectedDateTime;
    private int selectedPercent;
    private Menu menu;
    private CallBack callback;

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------
    public FragmentNewReport() {

    }

    // ------------------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    // ------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_report,
                container, false);


        setHasOptionsMenu(true);

        return view;
    }

    // ------------------------------------------------------------------------------------
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_image_list = (LinearLayout) view.findViewById(R.id.ll_fragmentWork_workReport_imageList);

        report = (EditText) view.findViewById(R.id.edittext_fragmentNewReportWork_reportText);
        percent = (Button) view.findViewById(R.id.btn_fragmentNewReportWork_selectPercent);
        timePicker = (Button) view.findViewById(R.id.btn_fragmentNewReportWork_selectDate);


        // set pre percent value to edit text
        selectedPercent = chart.getPercent();
        percent.setText(selectedPercent + " %");
        timePicker.setText(new PersianCalendar().getIranianDateTime());

        selectedDateTime = new PersianCalendar();


        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewDateTimePickerPersian dp;
                if (selectedDateTime == null)
                    dp = new ViewDateTimePickerPersian(getActivity());
                else
                    dp = new ViewDateTimePickerPersian(getActivity(), selectedDateTime);

                new AlertDialog.Builder(getActivity())
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

                final NumberPicker percentPicker = new NumberPicker(getActivity());
                percentPicker.setMaxValue(100);
                percentPicker.setMinValue(1);

                percentPicker.setValue(selectedPercent);

                new AlertDialog.Builder(getActivity())
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

    // ------------------------------------------------------------------------------------
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_fragment_work_report, menu);
        this.menu = menu;
    }

    // ------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.ac_work_report_save) {
            switch (reportType) {
                case REPORT_TYPE_WORK:
                    //   saveWorkReport();
                    break;
                case REPORT_TYPE_TASK:
                    saveTaskReport();
                    break;
            }


        }

        if (item.getItemId() == R.id.action_camera) {
            if (ll_image_list.getChildCount() < 5)
                attachMedia();
            else
                Toast.makeText(getActivity(), "فقط 5 تصویر میتوان افزود", Toast.LENGTH_SHORT).show();
            return true;
        }

        // save Report();

        //   Toast.makeText(getActivity(), "Sending...", Toast.LENGTH_SHORT).show();

        // close key board

        //   callback.onSuccess(null);

        return super.onOptionsItemSelected(item);
    }

    // ------------------------------------------------------------------------------------
    private void saveTaskReport() {
    }

    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity()).hideTabs();
    }

    // ------------------------------------------------------------------------------------
    @Override
    public void onDestroy() {
        super.onDestroy();

        callback.onError(null);

    }

    // ------------------------------------------------------------------------------------
    public void onDetach() {
        super.onDetach();
        PathMapManager.pop("Fragment New Report Work  onDetach");
    }

    // ------------------------------------------------------------------------------------
    public Chart getChart() {
        return chart;
    }

    // ------------------------------------------------------------------------------------
    public void setChart(Chart chart) {
        this.chart = chart;
    }

    // ------------------------------------------------------------------------------------
    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    //--------------------------------------------------------------------------------
    private void attachMedia() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
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

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
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

        ImageView temp_img = (ImageView) getActivity().getLayoutInflater().inflate(R.layout.inf_image_frame, null);


        switch (requestCode) {
            case CAMERA_REQUEST:
                // TODO Handle Picked Image From Camera
                Bitmap photo_camera = (Bitmap) data.getExtras().get("data");
                String path1 = storeThisImage(photo_camera);

                // add image to linear layout

                Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path1), 200, 200);
                temp_img.setImageBitmap(ThumbImage);
                temp_img.setTag(path1);
                ll_image_list.addView(temp_img);


                break;
            case GALLERY_REQUEST:
                //TODO Handle Picked Image From Gallery
                Uri _uri = data.getData();

                //User had pick an image.
                Cursor cursor = getActivity().getContentResolver().query(_uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
                cursor.moveToFirst();
                //Link to the image
                String path2 = cursor.getString(0);
                cursor.close();

                // create bitmap from path
                File imgFile = new File(path2);
                if (imgFile.exists()) {

                    // add image to linear layout
                    Bitmap ThumbImage2 = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path2), 200, 200);
                    temp_img.setImageBitmap(ThumbImage2);
                    temp_img.setTag(path2);
                    ll_image_list.addView(temp_img);

                }

                break;

        }

    }

    //-----------------------------------------------------------------------
    public String storeThisImage(Bitmap bitmap) {
        FileOutputStream out = null;
        String filename = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/img.jpeg";
        String path = Environment.getExternalStorageDirectory().toString();
        try {

            OutputStream fOut = null;
            File file = new File(path, "img.jpg");
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return path + "/" + "img.jpg";
    }

    //--------------------------------------------------------------------------------
    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        ((MainActivity) getActivity()).hideTabs();
    }
}
