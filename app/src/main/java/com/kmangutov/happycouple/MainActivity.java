package com.kmangutov.happycouple;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    //backstack propagation hack

    @Override
    public void onBackPressed() {
        if (!returnBackStackImmediate(getFragmentManager())) {
            super.onBackPressed();
        }
    }

    private boolean returnBackStackImmediate(FragmentManager fm) {

        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentQuiz);

        if (fragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
            if (fragment.getChildFragmentManager().popBackStackImmediate()) {
                return true;
            } else {
                return returnBackStackImmediate(fragment.getChildFragmentManager());
            }
        }

        return false;
    }
}
