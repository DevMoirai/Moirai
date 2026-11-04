package com.moirai.moirai.veriyapilari;
import java.util.Arrays;

/** Dört yapının testleri. Çalıştırma: javac *.java && java Testler */
public class Testler {

    private static int hata = 0;

    private static void kontrol(String ad, Object beklenen, Object gelen) {
        boolean tamam = beklenen == null ? gelen == null : beklenen.equals(gelen);
        System.out.println((tamam ? "TAMAM " : "HATA  ") + ad + " -> " + gelen);
        if (!tamam) {
            hata++;
        }
    }

    public static void main(String[] args) {
        yiginTesti();
        kuyrukTesti();
        hashTesti(16);
        hashTesti(1); // Hepsi aynı zincire düşer.
        grafTesti();
        System.out.println(hata == 0 ? "\nTüm testler geçti." : "\nHatalı test sayısı: " + hata);
    }

    private static void yiginTesti() {
        System.out.println("--- Yığın ---");
        Yigin<Integer> y = new Yigin<>();
        y.it(1);
        y.it(2);
        y.it(3);
        kontrol("bak", 3, y.bak());
        kontrol("cek", 3, y.cek());
        kontrol("cek", 2, y.cek());
        kontrol("cek", 1, y.cek());
        kontrol("boş yığında cek", null, y.cek());
        kontrol("bosMu", true, y.bosMu());
    }

    private static void kuyrukTesti() {
        System.out.println("--- Kuyruk ---");
        Kuyruk<Integer> k = new Kuyruk<>();
        k.ekle(1);
        k.ekle(2);
        k.ekle(3);
        kontrol("ilk", 1, k.ilk());
        kontrol("cikar", 1, k.cikar());
        kontrol("cikar", 2, k.cikar());
        kontrol("cikar", 3, k.cikar());
        kontrol("boş kuyrukta cikar", null, k.cikar());
        k.ekle(4);
        kontrol("tek öğe çıkar", 4, k.cikar());
        k.ekle(5);
        k.ekle(6);
        kontrol("boşaldıktan sonra cikar", 5, k.cikar());
        kontrol("boşaldıktan sonra cikar", 6, k.cikar());
        kontrol("bosMu", true, k.bosMu());
    }

    private static void hashTesti(int kapasite) {
        System.out.println("--- Hash tablosu (kapasite " + kapasite + ") ---");
        HashTablosu h = new HashTablosu(kapasite);
        kontrol("ekle", true, h.ekle("2026001", "Ali"));
        kontrol("ekle", true, h.ekle("2026017", "Ayşe"));
        kontrol("ekle", true, h.ekle("2026003", "Veli"));
        kontrol("bul", "Ali", h.bul("2026001"));
        kontrol("bul", "Ayşe", h.bul("2026017"));
        kontrol("bul", "Veli", h.bul("2026003"));
        kontrol("tekrar ekleme reddi", false, h.ekle("2026001", "Başkası"));
        kontrol("boyut", 3, h.getBoyut());
        kontrol("olmayanı sil", false, h.sil("2026999"));
        kontrol("sil", true, h.sil("2026017"));
        kontrol("silineni ara", null, h.bul("2026017"));
        kontrol("kalan duruyor", "Ali", h.bul("2026001"));
        kontrol("kalan duruyor", "Veli", h.bul("2026003"));
        kontrol("boyut", 2, h.getBoyut());
    }

    private static void grafTesti() {
        System.out.println("--- Graf ---");
        Graf g = new Graf(10);
        g.dersEkle("BM101");
        g.dersEkle("BM102");
        g.dersEkle("BM201");
        g.dersEkle("BM203");
        kontrol("aynı ders reddi", false, g.dersEkle("BM101"));
        kontrol("kenar", true, g.kenarEkle("BM101", "BM102"));
        kontrol("kenar", true, g.kenarEkle("BM102", "BM201"));
        kontrol("kenar", true, g.kenarEkle("BM201", "BM203"));
        kontrol("aynı kenar reddi", false, g.kenarEkle("BM101", "BM102"));
        kontrol("kendine kenar reddi", false, g.kenarEkle("BM101", "BM101"));
        kontrol("olmayan ders reddi", false, g.kenarEkle("BM101", "BM999"));
        kontrol("komşular BM101", "[BM102]", Arrays.toString(g.komsulariGetir("BM101")));
        kontrol("komşular BM201", "[BM203]", Arrays.toString(g.komsulariGetir("BM201")));
        kontrol("kenar sil", true, g.kenarSil("BM201", "BM203"));
        kontrol("olmayan kenar sil", false, g.kenarSil("BM201", "BM203"));
        kontrol("silince komşular BM201", "[]", Arrays.toString(g.komsulariGetir("BM201")));
    }
}