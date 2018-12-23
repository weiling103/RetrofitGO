package io.microshow.fastokhttp.sample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.microshow.fastokhttp.sample.appbar.AppBarLayoutActivity;
import io.microshow.fastokhttp.sample.databinding.ActivityMainBinding;
import io.microshow.fastokhttp.sample.viewmode.OneViewMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setOneViewMode(ViewModelProviders.of(this).get(OneViewMode.class));
        mBinding.getOneViewMode().data.observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mBinding.result.setText(s);
            }
        });

        mBinding.test.setOnClickListener(this);
        mBinding.test2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.test) {
            mBinding.getOneViewMode().loadData();
        } else if (view.getId() == R.id.test2){
            Intent intent = new Intent(this, AppBarLayoutActivity.class);
            startActivity(intent);
        }
    }
}
