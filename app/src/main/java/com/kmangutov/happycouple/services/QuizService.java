package com.kmangutov.happycouple.services;

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

    private QuizQuestion[] mQuestions = new QuizQuestion[10];

    public QuizService() {
        for(int i = 0; i < mQuestions.length; i++) {
            mQuestions[i] = new QuizQuestion();
            mQuestions[i].OptionOne = "Option one of " + i;
            mQuestions[i].OptionTwo = "Option two of " + i;
        }
    }

    public int getTotal() {
        return mQuestions.length;
    }

    public QuizQuestion getIth(int i) {

        return mQuestions[i];
    }
}
