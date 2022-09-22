package com.fourrunstudios.nutechwallet.repos;

import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fourrunstudios.nutechwallet.api.APIManager;
import com.fourrunstudios.nutechwallet.api.APIResponse;
import com.fourrunstudios.nutechwallet.api.HistoryData;
import com.fourrunstudios.nutechwallet.api.NutechAPI;
import com.fourrunstudios.nutechwallet.api.NutechData;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NutechRepo {
    private final NutechAPI api = APIManager.getNutechAPI();
    private static NutechRepo instance;
    private MutableLiveData<String> token;
    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> registered;
    private MutableLiveData<Integer> balance;
    private MutableLiveData<NutechData> data;
    private MutableLiveData<List<HistoryData>> historyData;

    public static NutechRepo getInstance() {
        if(instance==null){
            instance = new NutechRepo();
        }
        return instance;
    }
    public void register(String email, String password, String first_name, String last_name) {
        message = null;
        api.register(email, password, first_name, last_name).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    switch (response.body().getStatus()){
                        case 0:
                            Log.d("registered 0", "onResponse: ");
                            registered.setValue(true);
                            break;
                        case 103:
                            Log.d("registered 103", "onResponse: ");
                            message.setValue(response.body().getMessage());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }
    public LiveData<Boolean> getRegistered(){

        if(registered==null){
            Log.d("registered", "Instantiating registered!");
            registered = new MutableLiveData<>();
        }
        return registered;
    }
    public void login(String email, String password) {
        message = null;
        api.login(email, password).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    token.setValue(response.body().getData().getToken());
                }
                else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        message.setValue(jObjError.getString("message"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }
    public LiveData<String> getLiveToken() {
        if(token==null){
            Log.d("Token", "Instantiating Token");
            token = new MutableLiveData<>();
        }
        return token;
    }
    public void updateProfile(String first_name, String last_name) {
        api.updateProfile("Bearer "+getToken(), first_name, last_name).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    Log.d("Data", response.body().getData().getFirst_name()+" "+response.body().getData().getLast_name());
                    switch (response.body().getStatus()){
                        case 0:
                            getProfile();
                            break;
                        case 108:
                            message.setValue(response.body().getMessage());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<NutechData> getProfile() {
        if(data==null){
            data = new MutableLiveData<>();
        }
        api.getProfile("Bearer "+getToken()).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    Log.d("Data", response.body().getData().getFirst_name()+" "+response.body().getData().getLast_name());
                    switch (response.body().getStatus()){
                        case 0:
                            data.setValue(response.body().getData());
                            break;
                        case 108:
                            message.setValue(response.body().getMessage());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
        return data;
    }

    public void topUp(int amount) {
        api.topUp("Bearer "+getToken(), amount).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 0){
                        getLiveBalance();
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }

    public void transfer(int amount) {
        api.transfer("Bearer "+getToken(), amount).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 0){
                        getLiveBalance();
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<List<HistoryData>> getHistory() {
        if(historyData==null){
            historyData = new MutableLiveData<>();
        }
        api.getHistory(getToken()).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    switch (response.body().getStatus()){
                        case 0:

                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
        return historyData;

    }

    public LiveData<Integer> getLiveBalance() {
        message=null;
        if(balance==null){
            balance = new MutableLiveData<>();
        }
        api.getBalance("Bearer "+getToken()).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    Log.d("Curr Token: ", getToken());
                    switch (response.body().getStatus()){
                        case 0:
                            if(response.body().getData().getBalance()==null){
                                balance.setValue(0);
                            }
                            else {
                                balance.setValue(response.body().getData().getBalance());
                            }
                            break;
                        case 108:
                            message.setValue(response.body().getMessage());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
        return balance;
    }
    public String getToken() {
        if (token==null){
            token = new MutableLiveData<>();
        }

        return token.getValue();
    }

    public LiveData<String> getMessage(){
        if(message == null){
            message = new MutableLiveData<>();
        }
        return message;
    }

    public void resetToken() {
        token=null;
    }
}
