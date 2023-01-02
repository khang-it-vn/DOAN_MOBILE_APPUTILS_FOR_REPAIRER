package app_utils.ktteam.src.Models;

public class RepairerModel {

    private String soDienThoai;
    private String matKhau;

    public RepairerModel(String soDienThoai, String matKhau) {
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
    }

    public RepairerModel() {
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
