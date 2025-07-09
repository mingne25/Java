package model;

public enum TicketType {
    VIP("VIP"),
    STANDARD("Tiêu chuẩn"),
    EARLY_BIRD("Vé sớm"),
    STUDENT("Sinh viên");

    private final String tenHienThi;

    TicketType(String tenHienThi) {
        this.tenHienThi = tenHienThi;
    }

    public String getTenHienThi() {
        return tenHienThi;
    }
}