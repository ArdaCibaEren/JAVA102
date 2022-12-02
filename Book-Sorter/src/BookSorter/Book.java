package BookSorter;

public class Book implements Comparable<Book> {
    public String name;
    public String author;
    public int pageNumber;
    public int publishDate;

    public Book(String name, String author, int pageNumber, int publishDate) {
        this.name = name;
        this.author = author;
        this.pageNumber = pageNumber;
        this.publishDate = publishDate;
    }

    @Override
    public int compareTo(Book b) {
        return this.getName().compareTo(b.getName());
    }

    public void showBookInfo() {
        System.out.println(this.getName()
                + " is written by " + this.getAuthor()
                + " page number : " + this.getPageNumber()
                + " published in " + this.getPublishDate());
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}