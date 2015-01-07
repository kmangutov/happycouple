package com.kmangutov.happycouple.services;

import java.util.IdentityHashMap;

/**
 * Created by kmangutov on 1/7/15.
 */
public class QuizService
{
    private static QuizService mQuizServiceInstance;
    public static QuizService getInstance() {
        if(mQuizServiceInstance == null)
            mQuizServiceInstance = new QuizService();
        return mQuizServiceInstance;
    }

    final static String data =
            "I like to receive notes of affirmation.:0;" +
            "I like to be hugged.:4;" +
            "I like to spend one-to-one time with a person who is special to me.:1;" +
            "I feel loved when someone gives practical help to me.:3;" +
            "I like it when people give me gifts.:2;" +
            "I like leisurely visits with friends and loved ones.:1;" +
            "I feel loved when people do things to help me.:3;" +
            "I feel loved when people touch me.:4";

    private QuizQuestion[] mQuestions = new QuizQuestion[data.split(";").length/2];

    public QuizService() {

        String[] split = data.split(";");
        for(int i = 0; i < split.length; i += 2) {
            QuizQuestion qq = new QuizQuestion();
            String[] one = split[i].split(":");
            String[] two = split[i + 1].split(":");

            qq.OptionOne = one[0];
            qq.OptionOneMapping = Integer.parseInt(one[1]);
            qq.OptionTwo = two[0];
            qq.OptionTwoMapping = Integer.parseInt(two[1]);

            mQuestions[i/2] = qq;
        }
    }

    public int getTotal() {
        return mQuestions.length;
    }

    public QuizQuestion getIth(int i) {

        return mQuestions[i];
    }

    public void select(int id, int selection) {

        mQuestions[id].Selection = selection;
    }
}
