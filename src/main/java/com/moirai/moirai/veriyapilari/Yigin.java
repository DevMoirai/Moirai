package com.moirai.moirai.veriyapilari;

public class Yigin<T> {

    private static class
    Dugum<T> {
        T veri;
        Dugum<T> sonraki;


        Dugum(T veri) {
            this.veri = veri;
        }
    }

    private Dugum<T> tepe = null;

    public void it(T x) {
        Dugum<T> yeni = new Dugum<>(x);
        yeni.sonraki = tepe;
        tepe = yeni;
    }

    public T cek() {
        if (bosMu()) {
            return null;
        }
        T veri = tepe.veri;
        tepe = tepe.sonraki;
        return veri;
    }

    public T bak() {
        if (bosMu()) {
            return null;
        }
        return tepe.veri;
    }

    public boolean bosMu() {
        return tepe == null;
    }
}