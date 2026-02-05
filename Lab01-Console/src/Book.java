import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book() {}

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public double getPrice() {
        return price;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        this.id = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập tên sách: ");
        this.title = sc.nextLine();
        System.out.print("Nhập tác giả: ");
        this.author = sc.nextLine();
        System.out.print("Nhập đơn giá: ");
        this.price = Double.parseDouble(sc.nextLine());
    }

    public void output() {
        String msg = """
                BOOK: [ID=%d, Tiêu đề=%s, Tác giả=%s, Giá=%.2f]
                """.formatted(id, title, author, price);
        System.out.print(msg);
    }
}