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
import com.kmangutov.happycouple.services.EventBusProvider;
import com.kmangutov.happycouple.services.QuizChoiceEvent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class QuizItemFragment extends Fragment {


    @InjectView(R.id.buttonOption1)
    Button mButtonOne;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("QuizItemFragment", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_quiz_item, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.buttonOption1)
    public void button1() {

        Log.d("QuizItemFragment", "button1 click");
        Toast.makeText(getActivity().getApplicationContext(), "CLICK", Toast.LENGTH_SHORT).show();

        QuizChoiceEvent event = new QuizChoiceEvent(0, 0);
        post(event);
    }

    @OnClick(R.id.buttonOption2)
    public void button2() {

        QuizChoiceEvent event = new QuizChoiceEvent(0, 1);
        post(event);
    }

    public void post(QuizChoiceEvent event) {

        EventBus.getDefault().post(event);
    }
}
