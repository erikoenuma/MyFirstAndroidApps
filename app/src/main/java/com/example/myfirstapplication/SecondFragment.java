package com.example.myfirstapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfirstapplication.databinding.FragmentSecondBinding;
import java.util.Random;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        /*
        キャメルケースのファイル名+Bindingでバインディングクラスが生成される
        inflate()クラスでバインディングクラスをインスタンス化する
        bindingインスタンスを使用するとidを持っているviewを簡単に呼び出すことができる
         */
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        画面遷移時のデータ渡し: 受信側
        受信側のデスティネーション用クラス = デスティネーション名+Argsが自動で作成される
        fromBundleメソッドを使って引数を取得する
        getArguments()メソッドでbundleを取得し、定義した名前の値を取得する
         */
        Integer count = SecondFragmentArgs.fromBundle(getArguments()).getMyArg();

        /*
        可変の文字列リソース
        - %s: 文字列
        - %d: 数値
        指定しない場合はgetString()の第二引数がバインドされる
        引数を複数持つ場合:
            *XML*
            <string name="counts">%1$s：%2$d</string>
            <string name="cake">ショートケーキ</string>

            *.java*
            getString(R.string.counts, R.string.cake, 5);

            => ショートケーキ：5
         */
        String countText = getString(R.string.random_heading, count);
        TextView headerView = view.getRootView().findViewById(R.id.textview_header);
        headerView.setText(countText);

        // ランダムな数字を作成して表示させる
        Random random = new java.util.Random();
        Integer randomNumber = 0;
        if (count > 0) {
            // RandomクラスのnextInt()で範囲指定の乱数を作成
            randomNumber = random.nextInt(count + 1);
        }
        TextView randomView = view.getRootView().findViewById(R.id.textView_random);
        randomView.setText(randomNumber.toString());

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}