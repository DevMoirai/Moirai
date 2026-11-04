package com.moirai.moirai.veriyapilari;
public class HashTablosu {

    private static class Dugum {
        String anahtar;
        String deger;
        Dugum sonraki;

        Dugum(String anahtar, String deger) {
            this.anahtar = anahtar;
            this.deger = deger;
        }
    }

    private Dugum[] tablo;
    private int boyut = 0;

    public HashTablosu() {
        this(16);
    }

    public HashTablosu(int kapasite) {
        tablo = new Dugum[kapasite];
    }

    private int indeksBul(String anahtar) {
        int toplam = 0;
        for (int i = 0; i < anahtar.length(); i++) {
            toplam = toplam * 31 + anahtar.charAt(i);
        }
        int indeks = toplam % tablo.length;
        if (indeks < 0) {
            indeks += tablo.length; // negatif olursa önlemi alalaım nour nolmaz
        }
        return indeks;
    }

    /** Anahtar zaten varsa eklemez ve false döner. */
    public boolean ekle(String anahtar, String deger) {
        int i = indeksBul(anahtar);
        Dugum gezen = tablo[i];
        while (gezen != null) {
            if (gezen.anahtar.equals(anahtar)) {
                return false;
            }
            gezen = gezen.sonraki;
        }
        Dugum yeni = new Dugum(anahtar, deger);
        yeni.sonraki = tablo[i];
        tablo[i] = yeni;
        boyut++;
        return true;
    }

    /** Değeri döner, anahtar yoksa null döner. */
    public String bul(String anahtar) {
        Dugum gezen = tablo[indeksBul(anahtar)];
        while (gezen != null) {
            if (gezen.anahtar.equals(anahtar)) {
                return gezen.deger;
            }
            gezen = gezen.sonraki;
        }
        return null;
    }

    public boolean sil(String anahtar) {
        int i = indeksBul(anahtar);
        Dugum onceki = null;
        Dugum gezen = tablo[i];
        while (gezen != null) {
            if (gezen.anahtar.equals(anahtar)) {
                if (onceki == null) {
                    tablo[i] = gezen.sonraki;
                } else {
                    onceki.sonraki = gezen.sonraki;
                }
                boyut--;
                return true;
            }
            onceki = gezen;
            gezen = gezen.sonraki;
        }
        return false;
    }

    public int getBoyut() {
        return boyut;
    }
}