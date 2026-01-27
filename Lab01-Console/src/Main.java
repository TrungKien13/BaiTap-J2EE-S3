import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        //dữ liệu mẫu
        listBook.add(new Book(1, "Lập trình Java căn bản", "Nguyễn Văn A", 150000));
        listBook.add(new Book(2, "Cấu trúc dữ liệu và Giải thuật", "Trần Thị B", 220000));
        listBook.add(new Book(4, "Lập trình Web với Node.js", "Lê Văn C", 120000));
        listBook.add(new Book(5, "Cơ sở dữ liệu MySQL", "Phạm Văn D", 95000));
        listBook.add(new Book(6, "Thiết kế hệ thống", "Lê Văn C", 450000));

        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n--- QUẢN LÝ SÁCH ---");
            System.out.println("1. Thêm sách | 4. Xuất tất cả");
            System.out.println("2. Xóa 1 danh sách");
            System.out.println("3. Thay đỏi cuốn sách");
            System.out.println("4. Xuất tất cả");
            System.out.println("5. Tìm sách có chữ 'Lập trình'");
            System.out.println("6. Tìm K cuốn sách có giá <= P");
            System.out.println("7. Tìm sách theo danh sách tác giả");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1 -> {
                    Book b = new Book();
                    b.input();
                    listBook.add(b);
                }
                case 2 -> {
                    System.out.print("Nhập mã sách cần xóa: ");
                    int bookId = Integer.parseInt(sc.nextLine());
                    boolean removed = listBook.removeIf(p -> p.getId() == bookId);

                    if (removed) {
                        System.out.println("Đã xóa sách thành công!");
                    } else {
                        System.out.println("Không tìm thấy mã sách này.");
                    }
                }
                case 3 -> {
                    System.out.print("Nhập mã sách cần điều chỉnh: ");
                    int bookId = Integer.parseInt(sc.nextLine());

                    Optional<Book> foundBook = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst();

                    if (foundBook.isPresent()) {
                        System.out.println("Nhập thông tin mới cho sách:");
                        foundBook.get().input();
                        System.out.println("Cập nhật thành công!");
                    } else {
                        System.out.println("Không tìm thấy mã sách để sửa.");
                    }
                }
                case 4 -> listBook.forEach(Book::output);

                case 5 -> {

                    System.out.println("Kết quả tìm kiếm:");
                    listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains("lập trình"))
                            .forEach(Book::output);
                }

                case 6 -> {

                    System.out.print("Nhập số lượng tối đa (K): ");
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập mức giá tối đa (P): ");
                    double p = Double.parseDouble(sc.nextLine());

                    listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }

                case 7 -> {

                    System.out.print("Nhập danh sách tác giả (cách nhau bởi dấu phẩy): ");
                    String inputAuthors = sc.nextLine();
                    Set<String> authorSet = Arrays.stream(inputAuthors.split(","))
                            .map(String::trim)
                            .collect(Collectors.toSet()); // Chuyển sang Set

                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor())) // Filter tác giả nằm trong Set
                            .forEach(Book::output);
                }
            }
        } while (chon != 0);
    }
}