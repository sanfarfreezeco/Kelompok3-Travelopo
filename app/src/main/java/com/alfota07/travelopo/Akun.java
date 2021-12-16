package com.alfota07.travelopo;

public class Akun {
    private String id_akun, nama_akun, email_akun;

    public Akun() {}

    public Akun(String idAkun, String namaAkun, String emailAkun) {
        this.id_akun = idAkun;
        this.nama_akun = namaAkun;
        this.email_akun = emailAkun;
    }

    public String getId_akun() {
        return id_akun;
    }
    public String getNama_akun() {
        return nama_akun;
    }
    public String getEmail_akun() {
        return email_akun;
    }
}
