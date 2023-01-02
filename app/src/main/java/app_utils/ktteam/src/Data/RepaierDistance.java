package app_utils.ktteam.src.Data;

import java.util.Date;
import java.util.UUID;

public class RepaierDistance extends Repairer {
    private double distance;

    public RepaierDistance(String hoTen, String diaChi, boolean gioiTinh, Date dob, String numberPhone, String email, String avatar, String matKhau, UUID id, boolean trangThaiHoatDong, double longitude, double distance) {
        super(hoTen, diaChi, gioiTinh, dob, numberPhone, email, avatar, matKhau, id, trangThaiHoatDong, longitude);
        this.distance = distance;
    }

    public RepaierDistance() {
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
