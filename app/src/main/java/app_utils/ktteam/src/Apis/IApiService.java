package app_utils.ktteam.src.Apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.UUID;

import app_utils.ktteam.src.Apis.Prototypes.DataRepairerApiResponse;
import app_utils.ktteam.src.Apis.Prototypes.DataUserApiResponse;
import app_utils.ktteam.src.Apis.Prototypes.DataUserLatLngApiResponse;
import app_utils.ktteam.src.Apis.Prototypes.ObjectByteImage;
import app_utils.ktteam.src.Models.RepairerModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiService {

    Gson formatDateTimeString = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    IApiService api = new Retrofit.Builder().baseUrl("http://192.168.1.182:5000/v1/api/")
            .addConverterFactory(GsonConverterFactory.create(formatDateTimeString))
            .build()
            .create(IApiService.class);

    // Đăng nhập
    @POST("Repairer/Login")
    Call<DataRepairerApiResponse> login(@Body RepairerModel account);

    // Bật trạng thái hoạt động
    @PATCH("Repairer/Active")
    Call<DataRepairerApiResponse> active(@Query("id") UUID id);

    // Tìm kiếm thông tin user bằng số điện thoại
    @GET("Repairer/InformationUser")
    Call<DataUserLatLngApiResponse> lookupUser(@Query("soDienThoai") String soDienThoai);

    // Lấy hình ảnh
    @GET("Repairer/GetImageByName")
    Call<ObjectByteImage> fetchImage(@Query("imageName") String imageName);


}
