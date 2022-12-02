package BookSorter;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> list = new ArrayList<>();

        list.add(new Book("Into The Wild", "Jon Krakauer", 224, 1996));
        list.add(new Book("To Kill a Mockingbird", "Harper Lee", 355, 1960));
        list.add(new Book("1984", "George Orwell", 352, 1949));
        list.add(new Book("The People of the Abyss", "Jack London", 222, 1903));
        list.add(new Book("Sumerli Ludingirra", "Muazzez İlmiye Çığ", 152, 1996));

        TreeSet<Book> setName = new TreeSet<>(list);
        System.out.println("Sort by book name : ");
        for (Book a : setName) {
            a.showBookInfo();
        }

        TreeSet<Book> setByPageNumber = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Integer.compare(b1.getPageNumber(), b2.getPageNumber());
            }
        });

        setByPageNumber.addAll(list);
        System.out.println("Sort by page number : ");
        for (Book b : setByPageNumber) {
            b.showBookInfo();
        }
    }
}
