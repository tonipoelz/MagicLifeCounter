package com.dongo.magiclifecounter.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongo.magiclifecounter.R;

/**
 * Created by dongo on 20.06.2015.
 */
public class MainActivity extends FragmentActivity {

    private int playerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(playerCount < 2) // set default value
            playerCount = 2;

        setPlayerTileBackgroundColor(playerCount);

        setSettingVisibility(View.INVISIBLE);
    }

    /**
     *
     * @param view
     */
    public void onClickPlayerTile(View view) {
        Animation animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.button_pressed_anim);
        view.startAnimation(animBlink);

        ViewParent vp = view.getParent();
        LinearLayout parent = null;
        if(vp instanceof LinearLayout ){
            parent = (LinearLayout)vp;
        }

        if(parent != null) {
            TextView healthText, poisonText;

            switch (view.getId()) {
                case R.id.minusOne:
                    //Toast.makeText(this, "minus 1!", Toast.LENGTH_LONG).show();
                    healthText = (TextView)parent.findViewById(R.id.healthText);
                    healthText.setText(String.valueOf(Integer.valueOf(healthText.getText().toString()) - 1));
                    break;
                case R.id.plusOne:
                    healthText = (TextView)parent.findViewById(R.id.healthText);
                    healthText.setText(String.valueOf(Integer.valueOf(healthText.getText().toString()) + 1));
                    break;
                case R.id.healthText:
                    healthText = (TextView)parent.findViewById(R.id.healthText);
                    healthText.setText(String.valueOf(Integer.valueOf(healthText.getText().toString()) - 1));
                    break;
                //poison part
                case R.id.minusOnePoison:
                    poisonText = (TextView)parent.findViewById(R.id.poisonText);
                    poisonText.setText(String.valueOf(Integer.valueOf(poisonText.getText().toString()) - 1));
                    break;
                case R.id.plusOnePoison:
                    poisonText = (TextView)parent.findViewById(R.id.poisonText);
                    poisonText.setText(String.valueOf(Integer.valueOf(poisonText.getText().toString()) + 1));
                    break;
                case R.id.poisonText:
                    poisonText = (TextView)parent.findViewById(R.id.poisonText);
                    poisonText.setText(String.valueOf(Integer.valueOf(poisonText.getText().toString()) + 1));
                    break;
            }
        }else{
            //TODO error handling
        }
    }

    /**
     *
     * @param view
     */
    public void onClickOpenSettings(View view) {
        if(((FloatingActionButton) findViewById(R.id.fab_settings)).isChecked()) {
            setSettingVisibility(View.VISIBLE);
        }else{
            setSettingVisibility(View.INVISIBLE);
        }
    }

    /**
     *
     * @param view
     */
    public void onClickPlayer2(View view) {
        setContentView(R.layout.activity_main);
        playerCount = 2;
        setPlayerTileBackgroundColor(playerCount);

        closeSettingsMenu();
        reset(20, view);
    }

    /**
     *
     * @param view
     */
    public void onClickPlayer3(View view) {
        setContentView(R.layout.four_player_layout);
        playerCount = 3;
        setPlayerTileBackgroundColor(playerCount);

        closeSettingsMenu();
        reset(20, view);
    }

    /**
     *
     * @param view
     */
    public void onClickPlayer4(View view) {
        setContentView(R.layout.four_player_layout);
        playerCount = 4;
        setPlayerTileBackgroundColor(playerCount);

        closeSettingsMenu();
        reset(20, view);
    }

    /**
     *
     * @param view
     */
    public void onClickReset20(View view) {
        reset(20, view);
        closeSettingsMenu();
    }

    /**
     *
     * @param view
     */
    public void onClickReset30(View view) {
        reset(30, view);
        closeSettingsMenu();
    }

    /**
     *
     * @param view
     */
    public void onClickReset40(View view) {
        reset(40, view);
        closeSettingsMenu();
    }

    /**
     *
     * @param value
     * @param view
     */
    private void reset(int value, View view){
        ((TextView) findViewById(R.id.p1).findViewById(R.id.healthText)).setText(String.valueOf(value));
        ((TextView) findViewById(R.id.p1).findViewById(R.id.poisonText)).setText(String.valueOf(0));
        ((TextView) findViewById(R.id.p2).findViewById(R.id.healthText)).setText(String.valueOf(value));
        ((TextView) findViewById(R.id.p2).findViewById(R.id.poisonText)).setText(String.valueOf(0));

        if(playerCount >= 3) {
            ((TextView) findViewById(R.id.p3).findViewById(R.id.healthText)).setText(String.valueOf(value));
            ((TextView) findViewById(R.id.p3).findViewById(R.id.poisonText)).setText(String.valueOf(0));

            if (playerCount == 3) { //set player 4 life to 0 if player count is 3
                ((TextView) findViewById(R.id.p4).findViewById(R.id.healthText)).setText(String.valueOf(0));
                ((TextView) findViewById(R.id.p4).findViewById(R.id.poisonText)).setText(String.valueOf(0));
            }
        }

        if(playerCount >= 4) {
            ((TextView) findViewById(R.id.p4).findViewById(R.id.healthText)).setText(String.valueOf(value));
            ((TextView) findViewById(R.id.p4).findViewById(R.id.poisonText)).setText(String.valueOf(0));
        }
    }

    /**
     *
     * @param visibility
     */
    private void setSettingVisibility(int visibility){
        findViewById(R.id.fab_reset20).setVisibility(visibility);
        findViewById(R.id.fab_reset30).setVisibility(visibility);
        findViewById(R.id.fab_reset40).setVisibility(visibility);
        findViewById(R.id.fab_p2).setVisibility(visibility);
        findViewById(R.id.fab_p3).setVisibility(visibility);
        findViewById(R.id.fab_p4).setVisibility(visibility);
    }

    /**
     *
     */
    private void closeSettingsMenu(){
        setSettingVisibility(View.INVISIBLE);
        ((FloatingActionButton) findViewById(R.id.fab_settings)).setChecked(false);
    }

    /**
     *
     * @param playerCnt
     */
    private void setPlayerTileBackgroundColor(int playerCnt){

        switch(playerCnt){
            case 2:
                findViewById(R.id.p1).setBackgroundColor(getResources().getColor(R.color.p1_tile_backgrnd));
                findViewById(R.id.p2).setBackgroundColor(getResources().getColor(R.color.p2_tile_backgrnd));
                break;
            case 3:
            case 4:
                findViewById(R.id.p1).setBackgroundColor(getResources().getColor(R.color.p1_tile_backgrnd));
                findViewById(R.id.p2).setBackgroundColor(getResources().getColor(R.color.p2_tile_backgrnd));
                findViewById(R.id.p3).setBackgroundColor(getResources().getColor(R.color.p3_tile_backgrnd));
                findViewById(R.id.p4).setBackgroundColor(getResources().getColor(R.color.p4_tile_backgrnd));
                break;
            default:
                break;
        }

    }
}



