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
import com.kmangutov.happycouple.services.QuizService;

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

    protected static QuizItemFragment generateQuizItem(int id) {

        QuizItemFragment quizItem = new QuizItemFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        quizItem.setArguments(args);

        return quizItem;
    }

    public void init() {

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentQuizItem, generateQuizItem(0)).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(QuizChoiceEvent event) {

        QuizService.getInstance().select(event.mId, event.mOption);

        if(event.mId + 1 < QuizService.getInstance().getTotal())
            nextQuestion(event.mId);
        else
            Log.d("QuizFragment", "Out of quesitons!~!");
    }

    public void nextQuestion(int fromQuestion) {

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentQuizItem, generateQuizItem(fromQuestion + 1))
                .addToBackStack(null).commit();
    }
}