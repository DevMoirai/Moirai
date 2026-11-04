package com.moirai.moirai.veriyapilari;
/** Grafları oluşturuyoruz*/
/** Her düğüm bir dersi , her kenar bir ön koşul ilişkisini temsil eder.*/

public class Graf {
    private static class Dugum{
        String ders;
        Dugum sonraki;

        Dugum(String ders){
            this.ders = ders;
        }
    }
    private String[]dersler;
    private Dugum[]komsular;
    private int dersSayisi = 0;

    public Graf(int kapasite){
        dersler = new String[kapasite];
        komsular = new Dugum[kapasite];
    }
    /**Dersin indeksini döner, ders yoksa -1 döner.*/
    private int indeksBul(String kod) {
        for (int i = 0; i < dersSayisi; i++) {
            if (dersler[i].equals(kod)) {
                return i;
            }
        }
        return -1;
    }
    /** Ders zaten varsa ya da dizi doluysa false döner. */
    public boolean dersEkle(String kod) {
        if (indeksBul(kod) != -1 || dersSayisi == dersler.length) {
            return false;
        }
        dersler[dersSayisi] = kod;
        dersSayisi++;
        return true;
    }

    /** Ders yoksa, iki ders aynıysa ya da kenar zaten varsa false döner. */
    public boolean kenarEkle(String onKosul, String ders) {
        int i = indeksBul(onKosul);
        int j = indeksBul(ders);
        if (i == -1 || j == -1 || i == j) {
            return false;
        }
        Dugum gezen = komsular[i];
        while (gezen != null) {
            if (gezen.ders.equals(ders)) {
                return false;
            }
            gezen = gezen.sonraki;
        }
        Dugum yeni = new Dugum(ders);
        yeni.sonraki = komsular[i];
        komsular[i] = yeni;
        return true;
    }

    /** Silerse true, kenar yoksa false döner. */
    public boolean kenarSil(String onKosul, String ders) {
        int i = indeksBul(onKosul);
        if (i == -1) {
            return false;
        }
        Dugum onceki = null;
        Dugum gezen = komsular[i];
        while (gezen != null) {
            if (gezen.ders.equals(ders)) {
                if (onceki == null) {
                    komsular[i] = gezen.sonraki;
                } else {
                    onceki.sonraki = gezen.sonraki;
                }
                return true;
            }
            onceki = gezen;
            gezen = gezen.sonraki;
        }
        return false;
    }

    /** Dersin açtığı dersleri dizi olarak döner. Ders yoksa boş dizi döner. */
    public String[] komsulariGetir(String kod) {
        int i = indeksBul(kod);
        if (i == -1) {
            return new String[0];
        }
        int adet = 0;
        Dugum gezen = komsular[i];
        while (gezen != null) {
            adet++;
            gezen = gezen.sonraki;
        }
        String[] sonuc = new String[adet];
        gezen = komsular[i];
        for (int k = 0; k < adet; k++) {
            sonuc[k] = gezen.ders;
            gezen = gezen.sonraki;
        }
        return sonuc;
    }
}
