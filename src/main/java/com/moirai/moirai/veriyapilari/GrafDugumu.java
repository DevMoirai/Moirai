package com.moirai.moirai.veriyapilari;

/** Graf oluşturuyoruz: Her ders bir düğüm, her GrafDugumu bir kenar.
 *  Kenar, listenin sahibi olan dersten içindeki derse giden oku tutar. */
class GrafDugumu {
    String ders;          // okun gittiği ders
    GrafDugumu sonraki;   // aynı dersin bir sonraki kenarı

    GrafDugumu(String ders) {
        this.ders = ders;
    }
}