package com.moirai.moirai.veriyapilari;


/** Kuyruk: ilk giren ilk çıkar. */
public class Kuyruk<T> {

    private static class Dugum<T> {
        T veri;
        Dugum<T> sonraki;

        Dugum(T veri) {
            this.veri = veri;
        }
    }

    private Dugum<T> bas = null;
    private Dugum<T> son = null;

    public void ekle(T x) {
        Dugum<T> yeni = new Dugum<>(x);
        if (bosMu()) {
            bas = yeni;
            son = yeni;
        } else {
            son.sonraki = yeni;
            son = yeni;
        }
    }

    public T cikar() {
        if (bosMu()) {
            return null;
        }
        T veri = bas.veri;
        bas = bas.sonraki;
        if (bas == null) {
            son = null; // Başladıysa sona geldiğinde sıfırla.
        }
        return veri;
    }

    public T ilk() {
        if (bosMu()) {
            return null;
        }
        return bas.veri;
    }

    public boolean bosMu() {
        return bas == null;
    }
}