package com.alfota07.travelopo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class BookKeretaActivity extends AppCompatActivity {

    EditText pilihTanggal;

    int tahun, bulan, hari;
    String strBulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_kereta);

        pilihTanggal = findViewById(R.id.tanggal_berangkat);
        pilihTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar kalender = Calendar.getInstance();
                tahun = kalender.get(Calendar.YEAR);
                bulan = kalender.get(Calendar.MONTH);
                hari = kalender.get(Calendar.DAY_OF_MONTH);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(BookKeretaActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int dpTahun, int dpBulan, int dpHari) {
                            if(dpBulan == 1) {
                                strBulan = "Januari";
                            } else if (dpBulan == 2) {
                                strBulan = "Februari";
                            } else if (dpBulan == 3) {
                                strBulan = "Maret";
                            } else if (dpBulan == 4) {
                                strBulan = "April";
                            } else if (dpBulan == 5) {
                                strBulan = "Mei";
                            } else if (dpBulan == 6) {
                                strBulan = "Juni";
                            } else if (dpBulan == 7) {
                                strBulan = "Juli";
                            } else if (dpBulan == 8) {
                                strBulan = "Agustus";
                            } else if (dpBulan == 9) {
                                strBulan = "September";
                            } else if (dpBulan == 10) {
                                strBulan = "Oktober";
                            } else if (dpBulan == 11) {
                                strBulan = "November";
                            } else if (dpBulan == 12) {
                                strBulan = "Desember";
                            }

                            pilihTanggal.setText(dpHari + " " + strBulan + " " + dpTahun);
                        }
                    }, tahun, bulan, hari);
                    datePickerDialog.show();
                }
            }
        });
    }

    public void bookKereta(View v) {
        Intent i = new Intent(this, BookKeretaActivity.class);
        startActivity(i);
    }
}