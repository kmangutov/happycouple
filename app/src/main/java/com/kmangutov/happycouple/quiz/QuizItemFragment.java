package com.kmangutov.happycouple.quiz;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.kmangutov.happycouple.R;
import com.kmangutov.happycouple.services.QuizChoiceEvent;
import com.kmangutov.happycouple.services.QuizQuestion;
import com.kmangutov.happycouple.services.QuizService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class QuizItemFragment extends Fragment {

    private int mQuestionIndex;

    @InjectView(R.id.buttonOption1)
    Button mButtonOne;

    @InjectView(R.id.buttonOption2)
    Button mButtonTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("QuizItemFragment", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_quiz_item, container, false);
        ButterKnife.inject(this, view);
        mQuestionIndex = getArguments().getInt("id");
        loadQuestion();

        return view;
    }

    public int getIndex() {

        return mQuestionIndex;
    }

    protected void loadQuestion() {

        QuizQuestion question = QuizService.getInstance().getIth(mQuestionIndex);
        mButtonOne.setText(question.OptionOne);
        mButtonTwo.setText(question.OptionTwo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.buttonOption1)
    public void button1() {

        QuizChoiceEvent event = new QuizChoiceEvent(mQuestionIndex, 0);
        post(event);
    }

    @OnClick(R.id.buttonOption2)
    public void button2() {

        QuizChoiceEvent event = new QuizChoiceEvent(mQuestionIndex, 1);
        post(event);
    }

    public void post(QuizChoiceEvent event) {

        EventBus.getDefault().post(event);
    }
}
