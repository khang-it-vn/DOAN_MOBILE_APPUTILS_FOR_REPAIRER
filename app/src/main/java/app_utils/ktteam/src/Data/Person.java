package app_utils.ktteam.src.Data;

import java.util.Date;

public abstract class Person {


    private String hoTen ;// Họ tên

    private String diaChi;// Địa chỉ

    private boolean gioiTinh ;// Giới tính

    private Date dob ;// Ngày tháng năm sinh

    private String numberPhone ;// Số điện thoại

    private String email ;// Email liên hệ

    private String avatar ;// Tên hình ảnh
    private String matKhau ; // passwrod tài khoản

    public Person(String hoTen, String diaChi, boolean gioiTinh, Date dob, String numberPhone, String email, String avatar, String matKhau) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.dob = dob;
        this.numberPhone = numberPhone;
        this.email = email;
        this.avatar = avatar;
        this.matKhau = matKhau;
    }

    public Person() {
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
