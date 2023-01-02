package app_utils.ktteam.src.UI.Accounts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.BoringLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;

import app_utils.ktteam.src.Apis.IApiService;
import app_utils.ktteam.src.Apis.Prototypes.DataRepairerApiResponse;
import app_utils.ktteam.src.Data.Repairer;
import app_utils.ktteam.src.Models.RepairerModel;
import app_utils.ktteam.src.R;
import app_utils.ktteam.src.UI.NavigationMain;
import app_utils.ktteam.src.Utils.FilesUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private static final String ERROR_LOGIN_MESSAGE = "Sai số điện thoại hoặc mật khẩu";
    TextView txtLogo;
    EditText edtSoDienThoai;
    EditText edtMatKhau;
    TextView txtErrorLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLogo = findViewById(R.id.txtLogo_Login);
        customeColorLogo();
        // ánh xạ

        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        edtMatKhau = findViewById(R.id.edtPassword);
        txtErrorLogin = findViewById(R.id.txtErrorLogin);
    }

    private void customeColorLogo()
    {
        // get textpaint of txtLogo
        TextPaint paint = txtLogo.getPaint();

        // Get with of txtLogo div content AppUtils
        float width = paint.measureText("AppUtils");

        // create Shader use LinearGradient
        Shader textShader = new LinearGradient(0, 0, width, txtLogo.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        txtLogo.getPaint().setShader(textShader);

    }


    public void login(View view) {
        String soDienThoai = edtSoDienThoai.getText().toString();
        String matKhau = edtMatKhau.getText().toString();

        boolean stateInfo = checkInfomation(soDienThoai, matKhau);

        if(!stateInfo)
        {
            txtErrorLogin.setText(ERROR_LOGIN_MESSAGE);
            return;
        }

        IApiService.api.login(new RepairerModel(soDienThoai,matKhau)).enqueue(new Callback<DataRepairerApiResponse>() {
            @Override
            public void onResponse(Call<DataRepairerApiResponse> call, Response<DataRepairerApiResponse> response) {
                DataRepairerApiResponse res = response.body();

                if(res != null && res.isSuccess() )
                {
                    saveDataToFile(res.getData());
                    Intent intent = new Intent(Login.this, NavigationMain.class);
                    startActivity(intent);
                    return;
                }
                txtErrorLogin.setText(ERROR_LOGIN_MESSAGE);
            }

            @Override
            public void onFailure(Call<DataRepairerApiResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


    private boolean checkInfomation(String soDienThoai, String matKhau) {
        String regex = "^[0-9]{10,13}$";
        if(soDienThoai.length() == 0 || soDienThoai.matches(regex) == false || matKhau.length() == 0)
        {
            System.out.println("false");
            return  false;
        }
        return true;
    }

    private void saveDataToFile(Repairer data) {
        try {
            FilesUtil.saveInformation(data.getId().toString(),openFileOutput(FilesUtil.ID, Context.MODE_PRIVATE));
            FilesUtil.saveInformation(data.getHoTen(),openFileOutput(FilesUtil.HOTEN,Context.MODE_PRIVATE));
            FilesUtil.saveInformation(data.getDiaChi(),openFileOutput(FilesUtil.DIACHI, Context.MODE_PRIVATE));
            FilesUtil.saveInformation(data.isGioiTinh() ? "true":"false",openFileOutput(FilesUtil.GIOITINH,Context.MODE_PRIVATE));
            FilesUtil.saveInformation(data.getDob().toString(),openFileOutput(FilesUtil.DOB, Context.MODE_PRIVATE));
            FilesUtil.saveInformation(data.getNumberPhone(),openFileOutput(FilesUtil.NUMBERPHONE,Context.MODE_PRIVATE));
            FilesUtil.saveInformation(data.getEmail(),openFileOutput(FilesUtil.EMAIL, Context.MODE_PRIVATE));
            FilesUtil.saveInformation(data.getAvatar() ,openFileOutput(FilesUtil.AVATAR,Context.MODE_PRIVATE));
            FilesUtil.saveInformation(data.isTrangThaiHoatDong() ? "true":"false" ,openFileOutput(FilesUtil.TRANGTHAIHOATDONG,Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}