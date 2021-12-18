package com.alfota07.travelopo;

public class HistoryAkun {
    private String data_pesan,data_keterangan, data_tanggal;

    public HistoryAkun() {}

    public HistoryAkun(String pesan, String keterangan, String tanggal) {
        this.data_pesan = pesan;
        this.data_keterangan = keterangan;
        this.data_tanggal = tanggal;
    }

    public String getData_pesan() {
        return data_pesan;
    }
    public String getData_keterangan() {
        return data_keterangan;
    }
    public String getData_tanggal() {
        return data_tanggal;
    }
}
