package com.kmangutov.happycouple.services;

/**
 * Created by kmangutov on 1/7/15.
 */
public class QuizChoiceEvent
{
    public int mId;
    public int mOption;

    public QuizChoiceEvent(int id, int option) {

        mId = id;
        mOption = option;
    }
}
