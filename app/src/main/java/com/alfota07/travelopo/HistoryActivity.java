package com.alfota07.travelopo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;

    DatabaseReference akunGoogle;

    private ListView listViewHistory;
    private List<HistoryAkun> listHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listViewHistory = findViewById(R.id.list_history);
        listHistory = new ArrayList<>();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personId = acct.getId();

            akunGoogle = FirebaseDatabase.getInstance().getReference("history").child(personId);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        akunGoogle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listHistory.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()) {
                   HistoryAkun historyAkun = postSnapshot.getValue(HistoryAkun.class);
                   listHistory.add(historyAkun);
                }
                listview_history historyList_adapter = new listview_history(HistoryActivity.this, listHistory);
                listViewHistory.setAdapter(historyList_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}