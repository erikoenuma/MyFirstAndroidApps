package com.example.myfirstapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfirstapplication.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    // 変数を定義
    TextView showCountTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        // XMLファイルからViewを作る
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
        // showCountTextViewの中身をidで取得して代入
        showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);
        // 画面上のアクティブビューにする
        return fragmentFirstLayout;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.random_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*
                画面遷移時のデータ渡し: 送信側
                Safe ArgsというGradle pluginを使用すると型の安全性が保証されるため推奨されている

                Safe Argsを使うと、
                送信側デスティネーションの名前+Directionsというクラスが生成され、格アクション用のメソッドが格納される(FirstFragmentDirections)
                数を渡すために使用されるアクションごとに、アクションに基づく名前を持つ内部クラスが作成される(ActionFirstFragmentToSecondFragment)
                navigation graphによってデスティネーション・アクションを定義しておく
                 */

                /*
                int型とInteger型の違い
                - int型: プリミティブ型、null利用不可
                - Integer型: オブジェクト型（クラス型）、null利用可能

                Integer型はint型のラッパー
                コンピューターはプリミティブ型を処理する
                データを加工したり保存したり、操作したい場合に使うのがInteger型
            　  */
                int currentCount = Integer.parseInt(showCountTextView.getText().toString());
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action);
            }
        });

        //　idでviewを指定する
        view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Hello Toast!", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        view.findViewById(R.id.count_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countMe(view);
            }
        });
    }

    private void countMe(View view) {
        String countString = showCountTextView.getText().toString();
        Integer count = Integer.parseInt(countString);
        count++;
        showCountTextView.setText(count.toString());
    }

}

/*
- NullPointerException(ぬるぽ）: 参照型変数にnull値が格納されている時に、参照型変数を参照しようとした場合に発生する例外
- ClassCastException: あるオブジェクトを継承関係にないサブクラスにキャストしようとした場合に発生する例外
 */