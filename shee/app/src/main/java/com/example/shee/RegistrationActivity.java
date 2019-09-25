package com.example.shee;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.shee.rest.ApiClient;
import com.example.shee.rest.ApiInterface;
import com.example.shee.rest.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerUser("lovelace@gmail.com","wednesday");
    }

    public void registerUser(String email,String password){
        ApiInterface apiInterface = ApiClient.createRetrofit().create(ApiInterface.class);
        Call<RegistrationResponse> registrationCall = apiInterface.registerUser(email,password);
        registrationCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                RegistrationResponse registrationResponse = response.body();
                SharedPreferences mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = mySharedPrefs.edit();
                editor.putString("AUTH_TOKEN",registrationResponse.getAuthToken());
                editor.putInt("USER_ID",registrationResponse.getUserId());
                editor.apply();

            Log.d("success",response.toString());
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
            Log.d("faiure",t.getMessage());
            }
        });
    }
}
