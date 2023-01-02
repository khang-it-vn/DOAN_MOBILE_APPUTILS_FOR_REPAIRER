package app_utils.ktteam.src.Data;

import java.util.Date;
import java.util.UUID;

public class Repairer extends Person{
    private UUID id;
    private boolean trangThaiHoatDong;

    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double longitude;

    public Repairer(String hoTen, String diaChi, boolean gioiTinh, Date dob, String numberPhone, String email, String avatar, String matKhau, UUID id, boolean trangThaiHoatDong, double longitude) {
        super(hoTen, diaChi, gioiTinh, dob, numberPhone, email, avatar, matKhau);
        this.id = id;
        this.trangThaiHoatDong = trangThaiHoatDong;
        this.longitude = longitude;
    }

    public Repairer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isTrangThaiHoatDong() {
        return trangThaiHoatDong;
    }

    public void setTrangThaiHoatDong(boolean trangThaiHoatDong) {
        this.trangThaiHoatDong = trangThaiHoatDong;
    }
}
