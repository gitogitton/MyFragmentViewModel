package com.example.myfragmentviewmodel.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.IntentService;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.myfragmentviewmodel.R;

public class MainFragment extends Fragment implements View.OnClickListener {
    private final String TAG = getClass().toString();
    private MainViewModel mViewModel;
    private View mView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        setHasOptionsMenu(true); //menuを使う、と指定
        mView = inflater.inflate(R.layout.main_fragment, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.init();
        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                Log.d(TAG, "onChanged()");
                TextView textView = (TextView) mView.findViewById(R.id.message);
                textView.setText(String.valueOf(mViewModel.getCounter().getValue()));
            }
        };

        mViewModel.getCounter().observe(getViewLifecycleOwner(), observer);

        Button button = mView.findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.button ){
            Log.d(TAG, "(if) View ID = "+v.getId());
            mViewModel.addCounter(1);

            TextView textView = (TextView) mView.findViewById(R.id.message);
            textView.setText(String.valueOf(mViewModel.getCounter().getValue()));
        }
        else {
            Log.d(TAG, "(else) View ID = "+v.getId());
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu()");
//        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected()");
        switch (item.getItemId()){
            case R.id.menu_main_1 :
                Log.d(TAG, "onOptionsItemSelected() R.id.menu_main_1");
                break;
            case R.id.menu_main_2 :
                Log.d(TAG, "onOptionsItemSelected() R.id.menu_main_2");
                break;
            case R.id.menu_main_3 :
                Log.d(TAG, "onOptionsItemSelected() R.id.menu_main_3");
                break;
            case R.id.menu_sub_1 :
                Log.d(TAG, "onOptionsItemSelected() R.id.menu_sub_1");
                break;
            case R.id.menu_sub_2 :
                Log.d(TAG, "onOptionsItemSelected() R.id.menu_sub_2");
                break;
            default:
                Log.d(TAG, "onOptionsItemSelected() default");
                break;
        }
        return false; //boolean Return false to allow normal menu processing to proceed, true to consume it here.
    }
}