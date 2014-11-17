package com.pga.project1.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pga.project1.DataModel.Anbar;
import com.pga.project1.DataModel.AnbarTransaction;
import com.pga.project1.DataModel.PathObject;
import com.pga.project1.DataModel.Product;
import com.pga.project1.DataModel.TaminKonande;
import com.pga.project1.Helpers.DatabaseHelper;
import com.pga.project1.R;
import com.pga.project1.Utilities.FontHelper;
import com.pga.project1.Utilities.Fonts;
import com.pga.project1.Utilities.PersianCalendar;
import com.pga.project1.Viewes.PathMapManager;

import java.io.Serializable;

public class VoroodKalaActivity extends Activity {

    private static final int ANBAR_REQUEST_CODE = 3333;
    private ImageView save;
    Button buttonVoroodKala;
    Button buttonTaminKonandePicker;
    Button buttonAnbarPicker;
    private Context context;
    final int KALA_REQUEST_CODE = 1111;
    final int TAMINKONANDE_REQUEST_CODE = 2222;
    TextView selectedKala;
    TextView selectedtaminKonande;
    EditText mizanKala;
    Product product;
    private PathMapManager pm;
    private TaminKonande taminKonande;
    private Anbar anbar;
    private Anbar anbarMa;
    private EditText tozihat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorood_kala);
        anbarMa = (Anbar) getIntent().getSerializableExtra("anbar");
        prepareActionBar();
        context = this;

        buttonVoroodKala = (Button) findViewById(R.id.entekhab_kala);
        buttonTaminKonandePicker = (Button) findViewById(R.id.TaminKonandePicker);
        buttonAnbarPicker = (Button) findViewById(R.id.anbar_picker);
        selectedKala = (TextView) findViewById(R.id.selected_kala);
        selectedtaminKonande = (TextView) findViewById(R.id.selected_tamin_konande);
        mizanKala = (EditText) findViewById(R.id.mizanKala);
        tozihat = (EditText) findViewById(R.id.tozihat);

        pm = (PathMapManager) findViewById(R.id.pmm);
        pm.push(new PathObject("ورود کالا"));
        pm.refresh();

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

        buttonAnbarPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnbarPickerActivity.class);
                intent.putExtra("from_vorood_kala", 1);
                intent.putExtra("anbarMa", anbarMa);
                startActivityForResult(intent, ANBAR_REQUEST_CODE);
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
                String message = "";
                if (product == null) {
                    message += "کلا انتخاب نشده است" + "\n";
                }

                if (taminKonande == null && anbar == null) {
                    message += "مبدا تعیین نشده است" + "\n";
                }

                if (mizanKala.length() < 1) {
                    message += "میزان کالا وارد نشده" + "\n";
                }

                if (message.length() > 0) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // TODO SAVE TRANSACTION
                    AnbarTransaction anbarTransaction = null;

                    if (taminKonande != null) {
                        anbarTransaction = new AnbarTransaction(
                                1,
                                product.getId(),
                                anbarMa.getId(),
                                taminKonande.getId(),
                                -1,
                                -1,
                                Integer.parseInt(mizanKala.getText().toString()),
                                new PersianCalendar().getGregorianDateTime(),
                                tozihat.getText().toString(),
                                0,
                                0);

                    }

                    if (anbar != null) {
                        anbarTransaction = new AnbarTransaction(
                                3,
                                product.getId(),
                                anbarMa.getId(),
                                -1,
                                -1,
                                anbar.getId(),
                                Integer.parseInt(mizanKala.getText().toString()),
                                new PersianCalendar().getGregorianDateTime(),
                                tozihat.getText().toString(),
                                0,
                                0);

                    }
                    DatabaseHelper db = new DatabaseHelper(context);
                    db.insertAnbarTransaction(anbarTransaction);
                    finish();

                }
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

        if (requestCode == TAMINKONANDE_REQUEST_CODE && resultCode == RESULT_OK) {
            taminKonande = (TaminKonande) data.getSerializableExtra("tamin_konande");
            selectedtaminKonande.setText(taminKonande.getName() + "  " + taminKonande.getOwner());
            anbar = null;
        }

        if (requestCode == ANBAR_REQUEST_CODE && resultCode == RESULT_OK) {
            anbar = (Anbar) data.getSerializableExtra("anbar");
            selectedtaminKonande.setText(anbar.getName());
            taminKonande = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        PathMapManager.pop("destroy vorodkala");
    }
}
