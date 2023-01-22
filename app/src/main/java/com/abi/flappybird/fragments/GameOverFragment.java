package com.abi.flappybird.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abi.flappybird.MainActivity;
import com.abi.flappybird.R;

public class GameOverFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_over, container, false);

        Button playButton = rootView.findViewById(R.id.PlayAgainButton);
        playButton.setOnClickListener(v -> {
            MainActivity.getInstance().changeFragment(new GameFragment());
        });

        return rootView;
    }
}
