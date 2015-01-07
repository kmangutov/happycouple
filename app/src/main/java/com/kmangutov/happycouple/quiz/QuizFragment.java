package com.kmangutov.happycouple.quiz;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kmangutov.happycouple.R;
import com.kmangutov.happycouple.services.QuizChoiceEvent;

import de.greenrobot.event.EventBus;

public class QuizFragment extends Fragment {

    private int mQuestionIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        init();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void init() {

        QuizItemFragment quizItem = new QuizItemFragment();
        Bundle args = new Bundle();
        args.putInt("id", mQuestionIndex);
        quizItem.setArguments(args);

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentQuizItem, quizItem).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(QuizChoiceEvent event) {

        //Toast.makeText(getActivity().getApplicationContext(), "Option " + event.mOption, Toast.LENGTH_SHORT).show();
        nextQuestion();
    }

    public void nextQuestion() {

        QuizItemFragment quizItem = new QuizItemFragment();
        Bundle args = new Bundle();
        args.putInt("id", ++mQuestionIndex);
        quizItem.setArguments(args);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentQuizItem, quizItem)
                .addToBackStack(null).commit();
    }
}