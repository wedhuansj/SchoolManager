package view;

import java.util.Scanner;

public class SchoolView {
    private static final Scanner inp = new Scanner(System.in);
    public static int menuMain() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║         HỆ THỐNG QUẢN LÝ TRƯỜNG HỌC       ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║   1. Quản lý học sinh                     ║");
        System.out.println("║   2. Quản lý giáo viên                    ║");
        System.out.println("║   3. Quản lý lớp học                      ║");
        System.out.println("║   4. Quản lý môn học                      ║");
        System.out.println("║   5. Quản lý điểm                         ║");
        System.out.println("║   6. Quản lý thời khóa biểu               ║");
        System.out.println("║   0. Thoát hệ thống                       ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        try {
            System.out.print("Chọn chức năng: ");
            return Integer.parseInt(inp.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static int menuSub(String title) {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        if (title.equals("học sinh")) {
            System.out.println("║        HỆ THỐNG QUẢN LÝ HỌC SINH          ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║   1. Thêm học sinh                        ║");
            System.out.println("║   2. Sửa thông tin học sinh               ║");
            System.out.println("║   3. Xóa học sinh                         ║");
            System.out.println("║   4. Tìm kiếm theo mã                     ║");
            System.out.println("║   5. Hiển thị danh sách                   ║");
            System.out.println("║   6. Sắp xếp theo GPA                     ║");
            System.out.println("║   0. Quay lại                             ║");
        } else if (title.equals("giáo viên")) {
            System.out.println("║        HỆ THỐNG QUẢN LÝ GIÁO VIÊN         ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║   1. Thêm giáo viên                       ║");
            System.out.println("║   2. Sửa thông tin giáo viên              ║");
            System.out.println("║   3. Xóa giáo viên                        ║");
            System.out.println("║   4. Tìm kiếm theo tên                    ║");
            System.out.println("║   5. Hiển thị danh sách                   ║");
            System.out.println("║   6. Phân công môn dạy bổ sung            ║");
            System.out.println("║   0. Quay lại                             ║");
        } else if (title.equals("lớp học")) {
            System.out.println("║         HỆ THỐNG QUẢN LÝ LỚP HỌC          ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║   1. Tạo lớp học mới                      ║");
            System.out.println("║   2. Thêm học sinh vào lớp                ║");
            System.out.println("║   3. Xóa học sinh khỏi lớp                ║");
            System.out.println("║   4. Chuyển lớp cho học sinh              ║");
            System.out.println("║   5. Hiển thị tất cả các lớp              ║");
            System.out.println("║   6. In danh sách học sinh của lớp        ║");
            System.out.println("║   0. Quay lại                             ║");
        } else if (title.equals("môn học")) {
            System.out.println("║         HỆ THỐNG QUẢN LÝ MÔN HỌC          ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║   1. Thêm môn học mới                     ║");
            System.out.println("║   0. Quay lại                             ║");
        } else if (title.equals("điểm")) {
            System.out.println("║           HỆ THỐNG QUẢN LÝ ĐIỂM           ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║   1. Nhập / Sửa điểm môn học              ║");
            System.out.println("║   0. Quay lại                             ║");
        } else if (title.equals("thời khóa biểu")) {
            System.out.println("║       HỆ THỐNG QUẢN LÝ THỜI KHÓA BIỂU     ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║   1. Tạo thời khóa biểu                   ║");
            System.out.println("║   2. In thời khóa biểu theo lớp           ║");
            System.out.println("║   0. Quay lại                             ║");
        }
        System.out.println("╚═══════════════════════════════════════════╝");
        try {
            System.out.print("Chọn chức năng: ");
            return Integer.parseInt(inp.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static String inpString(String msg) {
        System.out.print("Nhập " + msg + ": ");
        return inp.nextLine();
    }
    public static int inpInt(String msg) {
        System.out.print("Nhập " + msg + ": ");
        try {
            return Integer.parseInt(inp.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public static double inpDouble(String msg) {
        System.out.print("Nhập " + msg + ": ");
        try {
            return Double.parseDouble(inp.nextLine());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    public static void pressEnter() {
        System.out.print("\nNhấn Enter để tiếp tục...");
        inp.nextLine();
    }
    public static void msg(String m) {
        System.out.println(m);
    }
}