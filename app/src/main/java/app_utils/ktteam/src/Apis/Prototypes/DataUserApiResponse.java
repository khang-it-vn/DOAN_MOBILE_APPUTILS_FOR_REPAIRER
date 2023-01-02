package app_utils.ktteam.src.Apis.Prototypes;


import app_utils.ktteam.src.Data.User;

public class DataUserApiResponse extends ApiResponse {

    public DataUserApiResponse(boolean success, String message, User data) {
        super(success, message);
        this.data = data;
    }

    public DataUserApiResponse() {
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    private User data;
}
