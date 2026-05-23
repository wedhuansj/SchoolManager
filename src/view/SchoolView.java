package view;
import java.util.Scanner;
public class SchoolView {
    private static Scanner inp = new Scanner(System.in);
    public static int menuMain() {
        System.out.println("===== SCHOOL MANAGEMENT =====\n" +
                "1. Quản lý học sinh\n" +
                "2. Quản lý giáo viên\n" +
                "3. Quản lý lớp học\n" +
                "4. Quản lý môn học\n" +
                "5. Quản lý điểm\n" +
                "6. Quản lý thời khóa biểu\n" +
                "0. Thoát");
        int cmd = inp.nextInt();
        inp.nextLine();
        return cmd;
    }
    public static int menuSub(String title) {
        if (title.equals("học sinh")) {
            System.out.println("1. Thêm\n" +
                    "2. Xóa\n" +
                    "3. Cập nhật\n" +
                    "4. Tìm kiếm\n" +
                    "5. Hiển thị tất cả\n" +
                    "6. Sắp xếp theo GPA\n" +
                    "0. Quay lại");
        }
        else if (title.equals("giáo viên")) {
            System.out.println("1. Thêm\n" +
                    "2. Xóa\n" +
                    "3. Cập nhật\n" +
                    "4. Tìm kiếm\n" +
                    "5. Hiển thị tất cả\n" +
                    "6. Phân công lớp\n" +
                    "0. Quay lại");
        }
        else if (title.equals("lớp học")) {
            System.out.println("1. Tạo lớp\n" +
                    "2. Thêm HS vào lớp\n" +
                    "3. Xóa HS khỏi lớp\n" +
                    "4. Chuyển lớp học sinh\n" +
                    "5. Hiển thị tất cả lớp\n" +
                    "6. In danh sách HS của lớp\n" +
                    "0. Quay lại");
        }
        else if (title.equals("môn học")) {
            System.out.println("1. Thêm môn\n" +
                    "2. Xóa môn\n" +
                    "3. Cập nhật\n" +
                    "4. Gán môn cho giáo viên\n" +
                    "5. Đăng ký môn cho học sinh\n" +
                    "0. Quay lại");
        }
        else if (title.equals("điểm")) {
            System.out.println("1. Nhập điểm\n" +
                    "2. Sửa điểm\n" +
                    "3. Xóa điểm\n" +
                    "0. Quay lại");
        }
        else if (title.equals("thời khóa biểu")) {
            System.out.println("1. Tạo thời khóa biểu\n" +
                    "2. In thời khóa biểu lớp\n" +
                    "0. Quay lại");
        }
        System.out.print("Chọn");
        int cmd = inp.nextInt();
        inp.nextLine();
        return cmd;
    }
    public static String inpString(String msg) {
        System.out.print("Nhập " + msg + ": ");
        return inp.nextLine();
    }
    public static int inpInt(String msg) {
        System.out.print("Nhập " + msg + ": ");
        int dummy = inp.nextInt(); inp.nextLine();
        return dummy;
    }
    public static double inpDouble(String msg) {
        System.out.print("Nhập " + msg + ": ");
        double dummy = inp.nextDouble(); inp.nextLine();
        return dummy;
    }
    public static void msg(String m) { System.out.println(m); }
}
