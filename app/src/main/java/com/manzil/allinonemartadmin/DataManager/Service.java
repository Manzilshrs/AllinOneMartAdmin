package com.manzil.allinonemartadmin.DataManager;

import android.util.Log;

import com.manzil.allinonemartadmin.Model.ListofCategory;
import com.manzil.allinonemartadmin.Model.ListofStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Service {
    ApiInterface apiService;

    public Service() {
        apiService = ApiClient.getApiClient().create(ApiInterface.class);
    }
    public interface GetJobCallback{
        void onSuccessCategory(List<ListofCategory> listofCategory);
        void onSuccessStatus(List<ListofStatus> listofStatus);
        void onError(String e);
    }

    public void getCategoryList(final GetJobCallback callback){
        Call<List<ListofCategory>> l = apiService.getCategoryList();
        l.enqueue(new Callback<List<ListofCategory>>(){
            @Override
            public void onResponse(Call<List<ListofCategory>> call, Response<List<ListofCategory>> response) {
                List<ListofCategory> s = response.body();
                if (s!=null && s.size() > 0){
                    callback.onSuccessCategory(s);
                }else{
                    callback.onError("No data available");
                }
            }
            @Override
            public void onFailure(Call<List<ListofCategory>> call, Throwable t) {
                Log.d("dfdfd", "onFailure: " + t.getCause());
            }
        });
    }

    public void getStatusList(final GetJobCallback callback){
        Call<List<ListofStatus>> l = apiService.getStatusList();
        l.enqueue(new Callback<List<ListofStatus>>(){
            @Override
            public void onResponse(Call<List<ListofStatus>> call, Response<List<ListofStatus>> response) {
                List<ListofStatus> statuses = response.body();
                if (statuses!=null && statuses.size() > 0){
                    callback.onSuccessStatus(statuses);
                }else{
                    callback.onError("No data available");
                }
            }
            @Override
            public void onFailure(Call<List<ListofStatus>> call, Throwable t) {
                Log.d("dfdfd", "onFailure: " + t.getCause());
            }
        });
    }

}
