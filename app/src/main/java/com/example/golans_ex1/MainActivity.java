package com.example.golans_ex1;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Timer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private long startTime;
    private boolean timerOn = false;
    private static final long DELAY = 750L;
    private MaterialButton main_BTN_left;
    private MaterialButton main_BTN_right;
    private AppCompatImageView[] main_IMG_racingCars;
    private AppCompatImageView[] main_IMG_hearts;
    private AppCompatImageView[][] main_IMG_cones;
    private GameManager gameManager;

    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        gameManager = new GameManager(main_IMG_cones.length, main_IMG_cones[0].length, main_IMG_hearts.length);
        initViews();
    }


    private void initViews() {
        main_BTN_left.setOnClickListener(View -> moveLeft());
        main_BTN_right.setOnClickListener(View -> moveRight());
        if (!timerOn) {
            startTime = System.currentTimeMillis();
            timerOn = true;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> conesUI());
                }
            },0L,DELAY);
        }
    }


    private void moveRight() {
        if (main_IMG_racingCars[0].getVisibility() == View.VISIBLE) {
            main_IMG_racingCars[0].setVisibility(View.INVISIBLE);
            main_IMG_racingCars[1].setVisibility(View.VISIBLE);
        } else if (main_IMG_racingCars[1].getVisibility() == View.VISIBLE) {
            main_IMG_racingCars[1].setVisibility(View.INVISIBLE);
            main_IMG_racingCars[2].setVisibility(View.VISIBLE);
        } else if (main_IMG_racingCars[2].getVisibility() == View.VISIBLE) {
            main_IMG_racingCars[2].setVisibility(View.INVISIBLE);
            main_IMG_racingCars[0].setVisibility(View.VISIBLE);
        }

        refreshUI();
    }

    private void moveLeft() {
        if (main_IMG_racingCars[0].getVisibility() == View.VISIBLE) {
            main_IMG_racingCars[0].setVisibility(View.INVISIBLE);
            main_IMG_racingCars[2].setVisibility(View.VISIBLE);
        } else if (main_IMG_racingCars[1].getVisibility() == View.VISIBLE) {
            main_IMG_racingCars[1].setVisibility(View.INVISIBLE);
            main_IMG_racingCars[0].setVisibility(View.VISIBLE);
        } else if (main_IMG_racingCars[2].getVisibility() == View.VISIBLE) {
            main_IMG_racingCars[2].setVisibility(View.INVISIBLE);
            main_IMG_racingCars[1].setVisibility(View.VISIBLE);
        }

        refreshUI();
    }

    private void refreshUI() {
        //lost
        if (gameManager.isGameLost()) {
            changeActivity("GAME OVER!");
        } else {
            if (gameManager.getNumOfCrashes() != 0) {
                main_IMG_hearts[main_IMG_hearts.length - gameManager.getNumOfCrashes()].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void changeActivity(String status) {
        Intent intent = new Intent(this, EndGameActivity.class);
        intent.putExtra(EndGameActivity.KEY_SCORE,0);
        intent.putExtra(EndGameActivity.KEY_STATUS,status);
        startActivity(intent);
        timer.cancel();
        finish();
    }


    public boolean isCrash() {
        if ((main_IMG_cones[main_IMG_cones.length - 1][0].getVisibility() == View.VISIBLE && main_IMG_racingCars[0].getVisibility() == View.VISIBLE) ||
            (main_IMG_cones[main_IMG_cones.length - 1][1].getVisibility() == View.VISIBLE && main_IMG_racingCars[1].getVisibility() == View.VISIBLE) ||
            (main_IMG_cones[main_IMG_cones.length - 1][2].getVisibility() == View.VISIBLE && main_IMG_racingCars[2].getVisibility() == View.VISIBLE))
        {
            gameManager.setNumOfCrashes(gameManager.getNumOfCrashes() + 1);
            return true;
        }
        return false;
    }


    private void findViews() {

        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_right = findViewById(R.id.main_BTN_right);

        main_IMG_racingCars = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_racingCar1),
                findViewById(R.id.main_IMG_racingCar2),
                findViewById(R.id.main_IMG_racingCar3)
        };

        main_IMG_hearts = new AppCompatImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)
        };

        main_IMG_cones = new AppCompatImageView[][]{
                {
                        findViewById(R.id.main_IMG_cone1),
                        findViewById(R.id.main_IMG_cone2),
                        findViewById(R.id.main_IMG_cone3)
                }, {
                findViewById(R.id.main_IMG_cone4),
                findViewById(R.id.main_IMG_cone5),
                findViewById(R.id.main_IMG_cone6)
        }, {
                findViewById(R.id.main_IMG_cone7),
                findViewById(R.id.main_IMG_cone8),
                findViewById(R.id.main_IMG_cone9)
        }, {
                findViewById(R.id.main_IMG_cone10),
                findViewById(R.id.main_IMG_cone11),
                findViewById(R.id.main_IMG_cone12)
        }, {
                findViewById(R.id.main_IMG_cone13),
                findViewById(R.id.main_IMG_cone14),
                findViewById(R.id.main_IMG_cone15)
        }, {
                findViewById(R.id.main_IMG_cone16),
                findViewById(R.id.main_IMG_cone17),
                findViewById(R.id.main_IMG_cone18)
        }, {
                findViewById(R.id.main_IMG_cone19),
                findViewById(R.id.main_IMG_cone20),
                findViewById(R.id.main_IMG_cone21)
        }, {
                findViewById(R.id.main_IMG_cone22),
                findViewById(R.id.main_IMG_cone23),
                findViewById(R.id.main_IMG_cone24)
        }
        };

    }

    private void conesUI() {
        long currentTime = System.currentTimeMillis();
        conesMoving(currentTime - startTime);
        refreshUI();
        if (isCrash()) {
            toastAndVibrate("You lose " + gameManager.getNumOfCrashes() + " life!");
        }
    }

    public void conesMoving(Long sec){
        int numOfRows = main_IMG_cones.length;
        int numOfCols = main_IMG_cones[0].length;
        for (int i = numOfRows - 1; i >= 0 ; i--) {
            for (int j = numOfCols - 1 ; j >= 0 ; j--) {
                if(i == numOfRows - 1 && main_IMG_cones[i][j].getVisibility() == View.VISIBLE){
                    main_IMG_cones[i][j].setVisibility(View.INVISIBLE);
                }
                if(main_IMG_cones[i][j].getVisibility() == View.VISIBLE) {
                    main_IMG_cones[i][j].setVisibility(View.INVISIBLE);
                    main_IMG_cones[i + 1][j].setVisibility(View.VISIBLE);
                }
            }
        }
        if(sec %2 ==0){
            startNewColumn();
        }
    }

    private void startNewColumn() {
        int randomColumn = random.nextInt(main_IMG_cones[0].length);
        main_IMG_cones[0][randomColumn].setVisibility(View.VISIBLE);
    }

    private void toastAndVibrate(String text) {
        vibrate();
        toast(text);
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

}
