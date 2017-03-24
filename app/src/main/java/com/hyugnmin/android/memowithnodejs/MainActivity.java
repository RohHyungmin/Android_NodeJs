package com.hyugnmin.android.memowithnodejs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hyugnmin.android.memowithnodejs.domain.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    public void getData() {
        //레트로핏을 생성하고
        //풀 주소 = http://openapi.seoul.go.kr:8088/4c425976676b6f643437665377554c/json/SearchParkingInfo/1/10/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:1004/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //사용할 인터페이스를 설정
        LocalhostInterface service = retrofit.create(LocalhostInterface.class);

        //데이터를 가져온다
        Call<Data> result = service.getData();

        //데이터를 요청 비동기 처리 후 callback 호출
        result.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                if(response.isSuccessful()) {
                    Data data = response.body(); // 원래 반환값인 JSONString이 Data 클래스로 변환되어 리턴된다
                    //데어터 저장소에 원격서버에서 가져온 데이터를 저장해둔다
                    DataStore dataStore = DataStore.getInstance();
                    dataStore.setDatas(data.getData());

                    //데이터를 가져온 후에 List Acitivty를 호출한다
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);

                    //로딩화면인 현재 Activity는 종료한다
                    finish();
                } else {
                    Log.e("retrofit", response.message()); //정상적이지 않을 경우 메시지에 오류내용이 담겨 온다
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

}
