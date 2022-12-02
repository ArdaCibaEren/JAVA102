import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        System.out.println("Takım sayısı giriniz : ");
        int sayi = inp.nextInt();

        List<String> takimlar = new ArrayList<>();

        for (int i = 0; i < sayi; i++) {
            System.out.println("Takım adını giriniz : ");
            String takim = inp.next();
            takimlar.add(takim);
        }

        if (sayi % 2 != 0) {
            takimlar.add("Bay");
            sayi += 1;
        }

        List<String> geciciListe = new ArrayList<>();

        while (takimlar.size() > 0) {
            int index = (int) (Math.random() * takimlar.size());
            geciciListe.add(takimlar.get(index));
            takimlar.remove(takimlar.get(index));
        }

        takimlar = geciciListe;

        int totalRound = sayi - 1;
        int macSayisi = sayi / 2;

        LinkedHashMap<String, ArrayList<ArrayList<String>>> roundlar = new LinkedHashMap<>();

        for (int i = 0; i < totalRound; i++) {
            String round = String.valueOf(i + 1);
            roundlar.put(round, new ArrayList<ArrayList<String>>());
        }

        for (int i = 0; i < totalRound; i++) {
            ArrayList<String> evSahibi = new ArrayList<>();
            ArrayList<String> deplasman = new ArrayList<>();

            for (int k = 0; k < totalRound; k++) {
                evSahibi.add(takimlar.get(k));
                deplasman.add(takimlar.get((sayi - 1) - k));
            }
            String round = String.valueOf(i + 1);
            roundlar.get(round).add(evSahibi);
            roundlar.get(round).add(deplasman);

            List<String> yeniTakimlar = new ArrayList<>();

            yeniTakimlar.add(takimlar.get(0));
            yeniTakimlar.add(takimlar.get(sayi - 1));

            for (int l = 1; l < takimlar.size(); l++) {
                yeniTakimlar.add(takimlar.get(l));
            }

            takimlar = yeniTakimlar;
        }
        System.out.println();

        for (int i = 0; i < 2 * totalRound; i++) {
            System.out.println((i + 1) + " .Round");
            for (int k = 0; k < macSayisi; k++) {
                if (i < totalRound) {
                    System.out.println(roundlar.get(String.valueOf(i + 1)).get(0).get(k) + " vs " + roundlar.get(String.valueOf(i + 1)).get(1).get(k));
                } else {
                    System.out.println(roundlar.get(String.valueOf(i + 1 - totalRound)).get(1).get(k) + " vs " + roundlar.get(String.valueOf(i + 1 - totalRound)).get(0).get(k));
                }
            }
            System.out.println();
        }
    }
}