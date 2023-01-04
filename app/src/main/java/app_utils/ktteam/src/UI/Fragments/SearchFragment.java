package app_utils.ktteam.src.UI.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app_utils.ktteam.src.ENVIROMENT;
import app_utils.ktteam.src.Apis.IApiService;
import app_utils.ktteam.src.Apis.Prototypes.DataUserLatLngApiResponse;
import app_utils.ktteam.src.Data.User;
import app_utils.ktteam.src.R;
import app_utils.ktteam.src.Utils.LoadImageService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    EditText edtSearchNumberPhone;
    ImageView imgViewSearch, imgviewSearchHinhAnh;
    TextView txtSerchHoTen;
    TextView txtSearchDiaChi;
    ImageView imgAddress;
    private double latCurrent;
    private double lngCurrent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        // ánh xạ
        edtSearchNumberPhone = view.findViewById(R.id.edtSearchNumberPhone);
        imgViewSearch = view.findViewById(R.id.imgViewSearch);
        imgviewSearchHinhAnh = view.findViewById(R.id.imgViewSearchHinhAnh);
        txtSerchHoTen = view.findViewById(R.id.txtSerchHoTen);
        txtSearchDiaChi = view.findViewById(R.id.txtSearchDiaChi);
        imgAddress = view.findViewById(R.id.iconSearchAddress);

        imgViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        txtSearchDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+latCurrent+","+ lngCurrent);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });

        return view;
    }

    private void getData() {
        String sdt = edtSearchNumberPhone.getText().toString();
        boolean state = checkInfomation(sdt);
        if(!state)
        {
            Toast.makeText(getContext(),"Số điện thoại không tìm thấy",Toast.LENGTH_LONG);
            return;
        }
        IApiService.api.lookupUser(sdt).enqueue(new Callback<DataUserLatLngApiResponse>() {
            @Override
        public void onResponse(Call<DataUserLatLngApiResponse> call, Response<DataUserLatLngApiResponse> response) {
                DataUserLatLngApiResponse res = response.body();
                if(res != null && res.isSuccess() )
                {
                    latCurrent = res.getData().getLatitude();
                    lngCurrent = res.getData().getLongitude();
                    setupInformationUser(res.getData());
                    return;
                }
                Toast.makeText(getContext(),"Không tìm thấy thông tin của số điện thoại này",Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<DataUserLatLngApiResponse> call, Throwable t) {

            }
        });
    }

    private void setupInformationUser(User data) {
        txtSerchHoTen.setText(data.getHoTen());
        txtSearchDiaChi.setText(data.getDiaChi());
        imgAddress.setImageResource(R.drawable.address);

        LoadImageService.loadImageFor(new LoadImageService(imgviewSearchHinhAnh, ENVIROMENT.DOMAIN_GET_IMAGE,data.getAvatar()));
    }

    private boolean checkInfomation(String soDienThoai) {
        String regex = "^[0-9]{10,13}$";
        if(soDienThoai.length() == 0 || soDienThoai.matches(regex) == false )
        {
            System.out.println("false số điẹnthoại");
            return  false;
        }
        return true;
    }
}
