package com.abi.flappybird.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abi.flappybird.R;
import com.abi.flappybird.game.GameView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        FloatingActionButton pauseButton = rootView.findViewById(R.id.PauseButton);
        pauseButton.setOnClickListener(l -> {
            GameView gameView = rootView.findViewById(R.id.MainGameView);
            gameView.togglePause();
        });
        return rootView;
    }
}
