package com.shopping_point.user_shopping_point.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import com.shopping_point.user_shopping_point.model.Image;
import com.shopping_point.user_shopping_point.net.RetrofitClient;
import com.shopping_point.user_shopping_point.storage.LoginUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserImageRepository {

    private static final String TAG = UserImageRepository.class.getSimpleName();
    private Application application;

    public UserImageRepository(Application application) {
        this.application = application;
    }

    public LiveData<Image> getUserImage(int userId) {
        final MutableLiveData<Image> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().getUserImage(LoginUtils.getInstance(application).getUserInfo().getId()).enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                Log.d("onResponse", "" + response.code());
                Toast.makeText(application, response.body() + " IMAGEEEEEEEEEEEEEEEEEEEEEEEEE", Toast.LENGTH_SHORT).show();
                Image responseBody = response.body();

                if (response.body() != null) {
                    mutableLiveData.setValue(responseBody);
                }
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }


}
