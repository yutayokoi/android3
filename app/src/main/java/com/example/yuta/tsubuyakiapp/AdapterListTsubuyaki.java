package com.example.yuta.tsubuyakiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * つぶやき表示のためのAdapterクラス
 * Created by yuta on 2015/10/14.
 */
public class AdapterListTsubuyaki extends ArrayAdapter<DtoTsubuyaki> {

    /**
     * コンストラクタ
     *
     * @param context  コンテキスト
     * @param resource ListView用のレイアウトリソースID
     * @param objects  表示するデータのリスト
     */
    public AdapterListTsubuyaki(Context context, int resource, ArrayList<DtoTsubuyaki> objects) {
        super(context, resource, objects);
    }

    /**
     * ListViewの1行分を作り出す
     *
     * @param position    これから作成する行が何行目かを示す番号
     * @param convertView 初めて表示する場合はnull、そうでなければ作成したビュー
     * @param parent      親のビューグループ(ここでは使わない)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // データを初めて取得する場合(convertViewがnullの場合)
        if (convertView == null) {

            // LayoutInflaterを取得し、list_tsubuyaki.xmlを取得してconvertViewにセット
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_tsubuyaki, null);
            holder = new ViewHolder(convertView);

            // 1行分のビューをメモリ上にセット
            convertView.setTag(holder);
        } else {

            // メモリ上に保存されたViewHolderを取得
            holder = (ViewHolder) convertView.getTag();
        }

        // positionに該当する行のデータを取得する
        DtoTsubuyaki dto = getItem(position);

        // 取得したデータを各TextViewにセット
        holder.idTextView.setText(String.valueOf(dto.id));
        holder.commentTextView.setText(dto.comment);

        return convertView;
    }

    /**
     * 内部クラス
     * 1行分のビューをレイアウトから取得
     */
    private class ViewHolder {

        /** ID表示用テキストビュー */
        public TextView idTextView;
        /** コメント表示用テキストビュー */
        public TextView commentTextView;

        public ViewHolder(View view) {

            // 各行で利用するビューをレイアウトから取得
            idTextView = (TextView) view.findViewById(R.id.idTextView);
            commentTextView = (TextView) view.findViewById(R.id.commentTextView);
        }
    }
}