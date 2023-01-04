package app_utils.ktteam.src.UI.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import app_utils.ktteam.src.Apis.IApiService;
import app_utils.ktteam.src.Apis.Prototypes.ObjectByteImage;
import app_utils.ktteam.src.R;
import app_utils.ktteam.src.Utils.FilesUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private static final int REQUEST_CODE = 15;
    TextView txtHoten;
    TextView txtSoDienThoai;
    TextView txtGioiTinh;
    TextView txtEmail;
    TextView txtDiaChi;
    TextView txtDOB;
    ImageView imgProfileAvatar;

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
        imgProfileAvatar = view.findViewById(R.id.imgProfileAvatar);

        imgProfileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        setDataToView();
        setImageToView();
        return view;
    }



    private void setImageToView() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            String avatar = FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.AVATAR));

            URL url = new URL("http://192.168.1.182:5000/v1/api/Repairer/GetImageByName?imageName="+avatar);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imgProfileAvatar.setImageBitmap(bmp);

        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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


    ////
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        resultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK)
            {
                Intent data = result.getData();
                if(data == null)
                {
                    return;
                }
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                    ImageView imgProfileAvatar = getActivity().findViewById(R.id.imgProfileAvatar);
                    imgProfileAvatar.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
}
