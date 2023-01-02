package app_utils.ktteam.src.Apis.Prototypes;

import app_utils.ktteam.src.Data.Repairer;

public class DataRepairerApiResponse extends ApiResponse{
    private Repairer data;

    public DataRepairerApiResponse(boolean success, String message, Repairer data) {
        super(success, message);
        this.data = data;
    }

    public DataRepairerApiResponse() {
    }

    public Repairer getData() {
        return data;
    }

    public void setData(Repairer data) {
        this.data = data;
    }
}
