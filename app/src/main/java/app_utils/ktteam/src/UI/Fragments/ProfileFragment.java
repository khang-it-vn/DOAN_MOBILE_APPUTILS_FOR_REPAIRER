package app_utils.ktteam.src.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import app_utils.ktteam.src.R;
import app_utils.ktteam.src.Utils.FilesUtil;

public class ProfileFragment extends Fragment {

    TextView txtHoten;
    TextView txtSoDienThoai;
    TextView txtGioiTinh;
    TextView txtEmail;
    TextView txtDiaChi;
    TextView txtDOB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        // Ánh xạ
        txtHoten = view.findViewById(R.id.txtProfileHoTen);
        txtSoDienThoai = view.findViewById(R.id.txtProfileNumberPhone);
        txtGioiTinh = view.findViewById(R.id.txtProfileGioiTinh);
        txtEmail = view.findViewById(R.id.txtProfileEmail);
        txtDiaChi = view.findViewById(R.id.txtProfileDiaChi);
        txtDOB = view.findViewById(R.id.txtProfileDOB);
        setDataToView();
        return view;
    }

    private void setDataToView() {
        try {
            String hoTen = FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.HOTEN));
            txtHoten.setText(hoTen);

            String soDienThoai = FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.NUMBERPHONE));
            txtSoDienThoai.setText(soDienThoai);

            String gioiTinh =  FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.GIOITINH));
            if(gioiTinh.equals("true"))
            {
                txtGioiTinh.setText("Nam");
            }else
            {
                txtGioiTinh.setText("Nữ");
            }
            String email =  FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.EMAIL));
            txtEmail.setText(email);

            String diaChi =  FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.DIACHI));
            txtDiaChi.setText(diaChi);

            String dob = FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.DOB));
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
            String date = DATE_FORMAT.format(new Date(dob));
            txtDOB.setText(date);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
