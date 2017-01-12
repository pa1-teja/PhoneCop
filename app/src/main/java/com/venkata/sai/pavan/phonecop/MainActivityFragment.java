package com.venkata.sai.pavan.phonecop;

import android.app.ActionBar;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private TextView greetring_text;
    private Animation greetingTextIn_Animation, greetingTextOut_Animation;
    private AnimationSet welcomScreen_text_Animation;
    private Handler waitOnWelcomeScreen;

    private ActionBar actionBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        // attaching the greetings strings to a text view.
        greetring_text = (TextView) view.findViewById(R.id.greeting_text);
        greetring_text.setText(greetring_text.getText() + "\n" + getContext().getString(R.string.welcome_string_initializing));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // setting up the transition effect for the welcome screen's greeting text.
            greetingTextIn_Animation = new AlphaAnimation(0.0f, 0.1f);
            greetingTextIn_Animation.setDuration(Long.parseLong(getContext().getString(R.string.welcome_screen_text_anim_duration)));
            greetingTextIn_Animation.setStartTime(500);
            greetingTextIn_Animation.setStartOffset(3000);

            greetingTextOut_Animation = new AlphaAnimation(1.0f, 0.0f);
            greetingTextOut_Animation.setDuration(Long.parseLong(getContext().getString(R.string.welcome_screen_text_anim_duration)));

            welcomScreen_text_Animation = new AnimationSet(true);
            welcomScreen_text_Animation.addAnimation(greetingTextIn_Animation);
            welcomScreen_text_Animation.addAnimation(greetingTextOut_Animation);

            // Done with setting up the transition effect for the welcome screen's greeting text.

            greetring_text.setTransitionName(String.valueOf(welcomScreen_text_Animation)); // transition is set to the text view.

        }

        waitOnWelcomeScreen = new Handler();

        // code that lets user to Tap anywhere on the screen to move from Welcome screen to the Main Screen.
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              moveToMainScreen();
            }
        });


        waitOnWelcomeScreen.postDelayed(new Runnable() {
            @Override
            public void run() { moveToMainScreen();} }, Long.parseLong(getContext().getString(R.string.fragment_change_duration)));
            return view;
        }

    private void moveToMainScreen(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // setting the fragment change transition.

//                fragmentTransaction:  replacing welcom screen with main screen
            getFragmentManager()
                    .beginTransaction() // TODO: Null Pointer Exception Occurred here and set Transition as well.
//                        .setTransition(R.transition.welcome_screen_out_to_main_screen_in_transition)
                    .replace(R.id.main_screen,new MainScreen(), getContext().getString(R.string.main_screen_tag))
                    .addToBackStack(null)
                    .commit();
        } else {
            getFragmentManager()
                    .beginTransaction() // TODO: Null Pointer Exception Occurred here.
                    .replace(R.id.main_screen,new MainScreen(), getContext().getString(R.string.main_screen_tag))
                    .addToBackStack(null)
                    .commit();
        }

    }

    }


