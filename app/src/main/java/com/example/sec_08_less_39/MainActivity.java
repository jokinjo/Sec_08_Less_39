package com.example.sec_08_less_39;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {ONE, TWO}
    Player currentPlayer = Player.ONE;
    Player mPlayer;
    String strWinner = "", string = "";
    Integer row=0, col=0, sum=0;
    int[][] RC = new int[3][3];
    boolean isGameOver =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void gameOver(Player mPlayer) {
        if (mPlayer == Player.ONE) strWinner = "TIGER WINS!";
        if (mPlayer == Player.TWO) strWinner = "LION WINS!";
        Toast.makeText( MainActivity.this, strWinner, Toast.LENGTH_LONG).show();
        isGameOver = true;
    };

    public void imageViewIsTapped(View imgView) {
        if (!isGameOver) {
            ImageView tappedImageView = (ImageView) imgView;
            tappedImageView.setTranslationX(-1000);
            tappedImageView.animate().alphaBy(1.0f).translationXBy(1000f).rotationXBy(360*5f).setDuration(2000);

            string = String.valueOf(tappedImageView.getId());
            switch (string) {
                case "2131165265": row = 0; col = 0; break;
                case "2131165264": row = 0; col = 1; break;
                case "2131165263": row = 0; col = 2; break;
                case "2131165262": row = 1; col = 0; break;
                case "2131165261": row = 1; col = 1; break;
                case "2131165260": row = 1; col = 2; break;
                case "2131165259": row = 2; col = 0; break;
                case "2131165258": row = 2; col = 1; break;
                case "2131165257": row = 2; col = 2; break;
                default: Toast.makeText(MainActivity.this, String.valueOf(tappedImageView.getId()), Toast.LENGTH_LONG).show();
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
}
