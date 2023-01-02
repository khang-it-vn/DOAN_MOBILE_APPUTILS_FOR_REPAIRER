package app_utils.ktteam.src.Apis.Prototypes;

import app_utils.ktteam.src.Data.UserLatLng;

public class DataUserLatLngApiResponse extends ApiResponse{
    private UserLatLng data;

    public DataUserLatLngApiResponse() {
    }

    public DataUserLatLngApiResponse(boolean success, String message, UserLatLng data) {
        super(success, message);
        this.data = data;
    }

    public UserLatLng getData() {
        return data;
    }

    public void setData(UserLatLng data) {
        this.data = data;
    }
}
