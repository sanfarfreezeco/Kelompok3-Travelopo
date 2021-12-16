package com.alfota07.travelopo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class listview_history extends ArrayAdapter {
    private Activity context;
    List<Akun> list_history;
    public listview_history(Activity context, List<Akun> historyArray) {
        super(context, R.layout.layout_history, historyArray);
        this.context = context;
        this.list_history = historyArray;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parrent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_history, null, true);
        TextView textViewNama = listViewItem.findViewById(R.id.textViewNama);
        TextView textViewEmail = listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewID = listViewItem.findViewById(R.id.textViewID);
        Akun akun = list_history.get(position);
        textViewNama.setText(akun.getNama_akun());
        textViewEmail.setText(akun.getEmail_akun());
        textViewID.setText(akun.getId_akun());
        return listViewItem;
    }
}
