package com.example.yuta.tsubuyakiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // ----------fields----------

    /** つぶやき表示用リストビュー */
    private ListView listView;
    /** つぶやき入力用エディットテキスト */
    private EditText editText;

    // ----------methods----------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // レイアウトよりビューを取得
        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);

        // ArrayAdapterを作成し、ListViewにデータをセットする
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, DaoTsubuyaki.findAll(getApplicationContext()));
        listView.setAdapter(adapter);

        // つぶやきボタンにリスナーを設定する
        findViewById(R.id.commitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 入力されているつぶやきを取得し、テーブルに追加
                String comment = editText.getText().toString();
                DaoTsubuyaki.insert(getApplicationContext(), comment);

                // エディットテキストを初期化
                editText.setText("");

                // ListViewを更新
                updateListView();
            }
        });
    }

    /**
     * ListViewを更新
     */
    private void updateListView() {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        adapter.clear();
        adapter.addAll(DaoTsubuyaki.findAll(getApplicationContext()));
    }

}