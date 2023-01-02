package app_utils.ktteam.src.UI.Fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.io.FileNotFoundException;
import java.util.UUID;

import app_utils.ktteam.src.Apis.IApiService;
import app_utils.ktteam.src.Apis.Prototypes.ApiResponse;
import app_utils.ktteam.src.Apis.Prototypes.DataRepairerApiResponse;
import app_utils.ktteam.src.R;
import app_utils.ktteam.src.Utils.FilesUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveFragment extends Fragment {

    private final String ACTIVE = "ĐANG HOẠT ĐỘNG";
    private final String NON_ACTIVE = "KÍCH HOẠT";
    private final  String MESSAGE_NON_ACTIVE = "Khi kích hoạt, mọi người xung quanh sẽ nhìn thấy bạn";
    private final  String MESSAGE_ACTIVE = "Mọi người xung quanh có thể nhìn thấy bạn";
    private boolean stateCurrent = false;
    TextView txtMessage;
    Button btnActive;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active,container,false);

        // Ánh xạ
        txtMessage = view.findViewById(R.id.txtActiveMessage);
        btnActive = view.findViewById(R.id.btnActive);

        setTitleMessageActive();
        btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStateActive();
            }
        });
        return view;
    }

    private void updateStateActive() {
       String id = "";
        try {
             id = FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.ID));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        IApiService.api.active(UUID.fromString(id)).enqueue(new Callback<DataRepairerApiResponse>() {
            @Override
            public void onResponse(Call<DataRepairerApiResponse> call, Response<DataRepairerApiResponse> response) {
                DataRepairerApiResponse res = response.body();
                if(res != null && res.isSuccess())
                {
                    try {
                        FilesUtil.saveInformation(res.getData().isTrangThaiHoatDong() ? "true":"false",getActivity().openFileOutput(FilesUtil.TRANGTHAIHOATDONG, Context.MODE_PRIVATE));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    setTitleMessageActive();
                    return;
                }
                System.out.println("có lỗi xảy ra khi active");
            }

            @Override
            public void onFailure(Call<DataRepairerApiResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void setTitleMessageActive() {
        try {
            String state = FilesUtil.readInformation(getActivity().openFileInput(FilesUtil.TRANGTHAIHOATDONG));
            if(state.equals("true"))
            {
                btnActive.setText(ACTIVE);
                txtMessage.setText(MESSAGE_ACTIVE);
                txtMessage.setTextColor(Color.BLUE);
                stateCurrent = true;
                updateBackgroundForBtnActive(true);
            }
            else
            {
                btnActive.setText(NON_ACTIVE);
                txtMessage.setText(MESSAGE_NON_ACTIVE);
                txtMessage.setTextColor(Color.RED);
                stateCurrent = false;
                updateBackgroundForBtnActive(false);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateBackgroundForBtnActive(boolean b) {
        Drawable d = getResources().getDrawable(R.drawable.custome_btn_circle_active);
        if(b)
        {
             d = getResources().getDrawable(R.drawable.custome_circle_btn_active_22);
            btnActive.setBackground(d);
        }
        btnActive.setBackground(d);
    }
}
