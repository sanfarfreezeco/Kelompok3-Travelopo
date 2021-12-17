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
    List<HistoryAkun> list_history;
    public listview_history(Activity context, List<HistoryAkun> historyArray) {
        super(context, R.layout.layout_history, historyArray);
        this.context = context;
        this.list_history = historyArray;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_history, null, true);
        TextView textViewPesan = listViewItem.findViewById(R.id.textViewPesan);
        TextView textViewTanggal = listViewItem.findViewById(R.id.textViewTanggal);
        HistoryAkun history = list_history.get(position);
        textViewPesan.setText(history.getData_pesan());
        textViewTanggal.setText(history.getData_tanggal());
        return listViewItem;
    }
}
