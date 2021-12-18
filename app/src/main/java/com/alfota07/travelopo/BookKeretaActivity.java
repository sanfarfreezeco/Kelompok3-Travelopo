package com.alfota07.travelopo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class BookKeretaActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    DatabaseReference akunGoogle;

    EditText pilihTanggal;
    Spinner asal_sp, tujuan_sp, dewasa_sp, anak_sp;

    private ListView listViewHistory;
    private List<HistoryAkun> listHistory;

    int tahun, bulan, hari;
    String strBulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_kereta);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        asal_sp = findViewById(R.id.asal);
        tujuan_sp = findViewById(R.id.tujuan);
        dewasa_sp = findViewById(R.id.dewasa);
        anak_sp = findViewById(R.id.anak);
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

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personId = acct.getId();

            akunGoogle = FirebaseDatabase.getInstance().getReference("history").child(personId);
        }
    }

    public void bookKereta(View v) {
        Intent i = new Intent(this, BookKeretaActivity.class);
        startActivity(i);
    }

    public void booking(View view) {
        String asal = asal_sp.getSelectedItem().toString();
        String tujuan = tujuan_sp.getSelectedItem().toString();
        String tanggal = pilihTanggal.getText().toString();
        String dewasa = dewasa_sp.getSelectedItem().toString();
        String anak = anak_sp.getSelectedItem().toString();

        String idPesan = akunGoogle.push().getKey();
        String pesan = "Kereta";
        String dataKeterangan = "Asal: " + asal + "\nTujuan: " + tujuan + "\n\nJumlah\nDewasa: " + dewasa + ", Anak: " + anak;

        if(!TextUtils.isEmpty(asal) && !TextUtils.isEmpty(tujuan) && !TextUtils.isEmpty(tanggal) && !TextUtils.isEmpty(dewasa) && !TextUtils.isEmpty(anak)) {
            HistoryAkun historyAkun = new HistoryAkun(pesan, dataKeterangan, tanggal, idPesan);
            akunGoogle.child(idPesan).setValue(historyAkun).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(@NonNull Void unused) {
                    Toast.makeText(BookKeretaActivity.this, "Anda Telah Memesan Tiket Kereta", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "Harap Isi Semua Kolom", Toast.LENGTH_LONG).show();
        }
    }
}