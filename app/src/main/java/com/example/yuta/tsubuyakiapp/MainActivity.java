package com.example.yuta.tsubuyakiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    /** つぶやき表示用レイアウト */
    private ListView listView;
    /** つぶやき入力用エディットテキスト */
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);

        /**
         * ArrayAdapterの作成、リストビューにセットする
         * 第一引数：コンテキスト
         * 第二引数：レイアウト
         * 第三引数：ArrayAdapterにセットするデータ
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                DaoTsubuyaki.findAll(getApplicationContext()
                ));

        /** リストビューにデータをセット */
        listView.setAdapter(adapter);

        findViewById(R.id.commitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** editTextに入力されている文字列を取得 */
                String comment = editText.getText().toString();

                /** テーブルにデータを追加 */
                DaoTsubuyaki.insert(getApplicationContext(), comment);

                /** EditTextに入力されている文字列の初期化 */
                editText.setText("");

                /** 再度、テーブルのデータを取得し、ListViewを更新する */
                updateListView();
            }
        });
    }

    private void updateListView() {
        /** データを取り出す */
        ArrayAdapter<String> adapter = (ArrayAdapter) listView.getAdapter();

        /** 入っているデータを消す */
        adapter.clear();

        /** 再度データベースからデータを取得して入れ直す */
        adapter.addAll(DaoTsubuyaki.findAll(getApplicationContext()));
    }
}
