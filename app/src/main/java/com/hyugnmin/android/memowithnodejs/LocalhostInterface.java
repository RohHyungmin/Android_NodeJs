package com.hyugnmin.android.memowithnodejs;

import com.hyugnmin.android.memowithnodejs.domain.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by besto on 2017-03-24.
 */

public interface LocalhostInterface {
    @GET("bbs")
    Call<Data> getData();
}
