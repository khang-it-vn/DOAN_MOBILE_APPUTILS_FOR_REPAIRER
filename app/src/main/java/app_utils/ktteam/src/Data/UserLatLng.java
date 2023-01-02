package app_utils.ktteam.src.Data;

import java.util.Date;
import java.util.UUID;

public class UserLatLng extends User{
    private double latitude;
    private double longitude;

    public UserLatLng(String hoTen, String diaChi, boolean gioiTinh, Date dob, String numberPhone, String email, String avatar, String matKhau, UUID uid, double latitude, double longitude) {
        super(hoTen, diaChi, gioiTinh, dob, numberPhone, email, avatar, matKhau, uid);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserLatLng() {
    }

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
}
