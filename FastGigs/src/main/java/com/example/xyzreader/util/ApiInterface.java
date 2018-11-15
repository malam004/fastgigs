package com.example.xyzreader.util;

import android.database.Observable;

import com.example.xyzreader.model.Result;

import java.util.List;

//import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Result>> getDetails();

}
