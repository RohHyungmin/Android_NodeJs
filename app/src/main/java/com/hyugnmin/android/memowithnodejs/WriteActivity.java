package com.hyugnmin.android.memowithnodejs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hyugnmin.android.memowithnodejs.domain.Data;
import com.hyugnmin.android.memowithnodejs.domain.Qna;

import io.reactivex.Observable;

public class WriteActivity extends AppCompatActivity {

    EditText editTitle, editAuthor, editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editContent = (EditText) findViewById(R.id.editContent);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AsyncTask<String, Void, String> networkTask = new AsyncTask<String, Void, String>() {
                   @Override
                   protected String doInBackground(String... params) {
                       String title = params[0];
                       String author = params[1];
                       String content = params[2];

                       Qna qna = new Qna();
                       qna.setTitle(title);
                       qna.setName(author);
                       qna.setContent(content);

                       Gson gson = new Gson();
                       String jsonString = gson.toJson(qna);

                       String result = Remote.postJson("http://192.168.0.106:1004/" + "bbs", jsonString);
                       return result;
                   }

                   @Override
                   protected void onPostExecute(String result) {
                       super.onPostExecute(result);
                       Snackbar.make(view, result, Snackbar.LENGTH_LONG).show();
                   }
               };
               networkTask.execute(editTitle.getText().toString(),
                                    editAuthor.getText().toString(),
                                    editContent.getText().toString());
            }
        });
    }

}
