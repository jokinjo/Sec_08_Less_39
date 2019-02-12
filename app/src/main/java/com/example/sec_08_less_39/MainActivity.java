package com.example.sec_08_less_39;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {ONE, TWO}
    Player currentPlayer = Player.ONE;
    String strWinner = "", string = "";
    Integer row=0, col=0, sum=0;
    int[][] RC = new int[3][3];
    boolean isGameOver = false;
    Button btnRestart;
    GridLayout mGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRestart = findViewById(R.id.btnRestart);
        mGridLayout = findViewById(R.id.gridLayout);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }
    public void gameOver(Player mPlayer) {
        if (mPlayer == Player.ONE) strWinner = "TIGER WINS!";
        if (mPlayer == Player.TWO) strWinner = "LION WINS!";
        Toast.makeText( MainActivity.this, strWinner, Toast.LENGTH_LONG).show();
        isGameOver = true;
        btnRestart.setVisibility(View.VISIBLE);
    }

    public void imageViewIsTapped(View imgView) {
        if (!isGameOver) {
            ImageView tappedImageView = (ImageView) imgView;
            tappedImageView.setTranslationX(-1000);
            tappedImageView.animate().alpha(1.0f).translationXBy(1000f).rotationXBy(360*5f).setDuration(2000);

            string = String.valueOf(tappedImageView.getTag());
            switch (string) {
                case "9": row = 0; col = 0; break;
                case "8": row = 0; col = 1; break;
                case "7": row = 0; col = 2; break;
                case "6": row = 1; col = 0; break;
                case "5": row = 1; col = 1; break;
                case "4": row = 1; col = 2; break;
                case "3": row = 2; col = 0; break;
                case "2": row = 2; col = 1; break;
                case "1": row = 2; col = 2; break;
                default: Toast.makeText(MainActivity.this, String.valueOf(tappedImageView.getTag()), Toast.LENGTH_LONG).show();
            }

            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.TWO;
                RC[row][col] = -1;
            } else if (currentPlayer == Player.TWO){
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.ONE;
                RC[row][col] = +1;
            }
            sum = 0;
            for (int r=0; r<3; r++) sum += RC[r][col];
            if (sum == -3) gameOver(Player.ONE);
            if (sum == +3) gameOver(Player.TWO);
            sum = 0;
            for (int c=0; c<3; c++) sum += RC[row][c];
            if (sum == -3) gameOver(Player.ONE);
            if (sum == +3) gameOver(Player.TWO);
            sum = 0;
            if (row == col) {
                for (int col = 0; col<3; col++) sum += RC[col][col];
                if (sum == -3) gameOver(Player.ONE);
                if (sum == +3) gameOver(Player.TWO);
            } else if(row == (4 - col)){
                for (int col = 0; col<3; col++) sum += RC[3 - col][col];
                if (sum == -3) gameOver(Player.ONE);
                if (sum == +3) gameOver(Player.TWO);
            }
        }
    }
    public void restartGame() {
        currentPlayer = Player.ONE;
        strWinner = "";
        string = "";
        row = 0;
        col = 0;
        sum = 0;
        isGameOver = false;
        btnRestart.setVisibility(View.GONE);
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) RC[i][j] = 0;

        for (int index = 0; index < mGridLayout.getChildCount(); index++) {
            ImageView imageView = (ImageView) mGridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }
    }
}