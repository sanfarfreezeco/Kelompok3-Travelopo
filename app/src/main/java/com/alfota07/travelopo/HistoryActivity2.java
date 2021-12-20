package com.alfota07.travelopo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoryActivity2 extends AppCompatActivity {

    TextView viewPesan, viewKeterangan, viewTanggal;

    String idPesan;

    GoogleSignInClient mGoogleSignInClient;
    DatabaseReference akunGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);

        idPesan = getIntent().getStringExtra("IDPesan");
        viewPesan = findViewById(R.id.textViewPesan2);
        viewKeterangan = findViewById(R.id.textViewKeterangan2);
        viewTanggal = findViewById(R.id.textViewTanggal2);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personId = acct.getId();

            akunGoogle = FirebaseDatabase.getInstance().getReference("history").child(personId).child(idPesan);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        akunGoogle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HistoryAkun historyAkun = snapshot.getValue(HistoryAkun.class);
                if(historyAkun != null) {
                    String pesan = historyAkun.getData_pesan();
                    String keterangan = historyAkun.getData_keterangan();
                    String tanggal = historyAkun.getData_tanggal();
                    viewPesan.setText(pesan);
                    viewKeterangan.setText(keterangan);
                    viewTanggal.setText(tanggal);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void batalPesan(View view) {
        akunGoogle.removeValue().addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(HistoryActivity2.this, "Anda Telah Membatalkan Pesanan Anda", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}