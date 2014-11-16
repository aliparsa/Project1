package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pga.project1.DataModel.Product;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;

public class VoroodKalaActivity extends Activity {

    private ImageView save;
    Button buttonVoroodKala;
    Button buttonTaminKonandePicker;
    private Context context;
    final int KALA_REQUEST_CODE = 1111;
    final int TAMINKONANDE_REQUEST_CODE = 2222;
    TextView selectedKala;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorood_kala);
        prepareActionBar();
        context = this;
        buttonVoroodKala = (Button) findViewById(R.id.entekhab_kala);
        buttonTaminKonandePicker = (Button) findViewById(R.id.TaminKonandePicker);
        selectedKala = (TextView) findViewById(R.id.selected_kala);

        buttonVoroodKala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KalaPickerActivity.class);
                startActivityForResult(intent, KALA_REQUEST_CODE);
            }
        });

        buttonTaminKonandePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaminKonandePickerActivity.class);
                startActivityForResult(intent, TAMINKONANDE_REQUEST_CODE);
            }
        });


    }

    private void prepareActionBar() {

        View customActionBar = getLayoutInflater().inflate(R.layout.actionbar_back, null);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(customActionBar);

        TextView title = (TextView) customActionBar.findViewById(R.id.ac_title);
        FontHelper.SetFont(this, Fonts.MAIN_FONT, title, Typeface.BOLD);

        title.setText("ورود کالا");

        LinearLayout back = (LinearLayout) customActionBar.findViewById(R.id.ac_back_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        save = (ImageView) customActionBar.findViewById(R.id.ac_action1);

        save.setImageResource(R.drawable.ic_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO save vorod kala
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KALA_REQUEST_CODE && resultCode == RESULT_OK) {
            product = (Product) data.getSerializableExtra("product");
            selectedKala.setText(product.getName());
        }
    }
}
