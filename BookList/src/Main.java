import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> list = new ArrayList<>();
        ArrayList<Book> newList = new ArrayList<>();

        Book book1 = new Book("Into The Wild", "Jon Krakauer", 224, 1996);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 355, 1960);
        Book book3 = new Book("1984", "George Orwell", 352, 1949);
        Book book4 = new Book("The People of the Abyss", "Jack London", 222, 1903);
        Book book5 = new Book("Sumerli Ludingirra", "Muazzez İlmiye Çığ", 52, 1996);
        Book book6 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 248, 1979);
        Book book7 = new Book("Veronika Decides To Die", "Paulo Coelho", 213, 1998);
        Book book8 = new Book("Blood Red Rivers", "Jean-Christophe Grange", 405, 1997);
        Book book9 = new Book("Sword of Destiny", "Andrzej Sapkowski", 440, 1992);
        Book book10 = new Book("A Wizard of Earthsea", "Ursula K. Le Guin", 192, 1968);

        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        list.add(book5);
        list.add(book6);
        list.add(book7);
        list.add(book8);
        list.add(book9);
        list.add(book10);

        Map<String, String> bookMap = new HashMap<>();
        list.forEach(book -> bookMap.put(book.getName(), book.getAuthor()));
        System.out.println();
        System.out.println("********** List of the Books **********");
        System.out.println();
        for (String b : bookMap.keySet()) {
            System.out.println("Book Name: " + b + " || " + "\t Author: " + bookMap.get(b));
        }
        System.out.println("------------------------");
        System.out.println();

        System.out.println("List of the books that has more than 100 pages are listed below");
        System.out.println();

        list.stream().filter(book -> book.getPage() > 100).forEach(book -> newList.add(book));
        System.out.println("=======================");
        newList.forEach(book -> System.out.println("Name of the book:\t" + book.getName() + " ,\tAuthor of the book:\t" + book.getAuthor()));
    }
}
//www.patika.dev