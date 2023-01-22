package com.abi.flappybird.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.abi.flappybird.R;

public class StartGameFragment extends Fragment {

    private final FragmentManager fragmentManager;

    public StartGameFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_start, container, false);

        Button playButton = rootView.findViewById(R.id.PlayButton);
        playButton.setOnClickListener(v -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame, new GameFragment()).commit();
        });

        return rootView;
    }
}
