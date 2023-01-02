package app_utils.ktteam.src.Data;

import java.util.Date;
import java.util.UUID;

public class User extends Person {
    private UUID uid ;

    public User(String hoTen, String diaChi, boolean gioiTinh, Date dob, String numberPhone, String email, String avatar, String matKhau, UUID uid) {
        super(hoTen, diaChi, gioiTinh, dob, numberPhone, email, avatar, matKhau);
        this.uid = uid;
    }

    public User() {}

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }
}
