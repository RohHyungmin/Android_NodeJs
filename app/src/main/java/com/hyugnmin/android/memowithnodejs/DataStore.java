package com.hyugnmin.android.memowithnodejs;

import com.hyugnmin.android.memowithnodejs.domain.Data;
import com.hyugnmin.android.memowithnodejs.domain.Qna;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by besto on 2017-03-24.
 */

public class DataStore {
    private static DataStore instance = null;

    private DataStore() {datas = new ArrayList<>();}

    public static DataStore getInstance() {
     if(instance == null) {
         instance = new DataStore();
     }
        return instance;
    }

    private List<Qna> datas;

    public List<Qna> getDatas() {
        return datas;
    }

    public void setDatas(List<Qna> datas) {

        this.datas.clear();
                for(Qna qna : datas){
                    this.datas.add(qna);
                    }
    }

    public void addData(Qna qna) {
        this.datas.add(qna);
    }


}
