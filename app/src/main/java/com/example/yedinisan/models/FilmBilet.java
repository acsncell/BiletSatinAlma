package com.example.yedinisan.models;

public class FilmBilet {

    private  String gorsel;
    private String filmicerik;
    private String ucreti;
public FilmBilet(){

    }

    public FilmBilet(String gorsel, String filmicerik, String ucreti) {
        this.gorsel = gorsel;
        this.filmicerik = filmicerik;
        this.ucreti = ucreti;

    }

    public String  getGorsel() {
        return gorsel;
    }

    public void setGorsel(String gorsel) {
        this.gorsel = gorsel;
    }

    public String getFilmicerik() {
        return filmicerik;
    }

    public void setFilmicerik(String filmicerik) {
        this.filmicerik = filmicerik;
    }

    public String getUcreti() {
        return ucreti;
    }

    public void setUcreti(String ucreti) {
        this.ucreti = ucreti;
    }


}
