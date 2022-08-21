package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final TextView[][] txtMoves = new TextView[3][3];
    private Button btnNewGame;
    private TextView txtMoveOX;
    static final int[][] txtIds = {
            {R.id.txtPosition00, R.id.txtPosition01, R.id.txtPosition02},
            {R.id.txtPosition10, R.id.txtPosition11, R.id.txtPosition12},
            {R.id.txtPosition20, R.id.txtPosition21, R.id.txtPosition22},
    };
    static byte turn=0; //turn:0 -> 'O', turn:1 -> 'X'
    static byte movesCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btnNewGame = findViewById(R.id.btn_new_game);
        txtMoveOX = findViewById(R.id.txt_move_ox);
        txtMoveOX.setText("Move : O");

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                txtMoves[i][j] = findViewById(txtIds[i][j]);
                int i1=i, j1=j;
                txtMoves[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        move(i1,j1);
                    }
                });
            }
        }
    }

    private void move(int i,int j){
        if(txtMoves[i][j].getText()==""){
            movesCount++;
            if(turn==0){
                txtMoveOX.setText("Move : X");
                turn=1;
                txtMoves[i][j].setText("O");
                if(movesCount>4 && checkWon("O")){
                    Toast.makeText(this, "Player O Won 🎉", Toast.LENGTH_SHORT).show();
                    newGame();
                }
            }else{
                txtMoveOX.setText("Move : O");
                turn=0;
                txtMoves[i][j].setText("X");
                if( movesCount>4 && checkWon("X")){
                    Toast.makeText(this, "Player X Won 🎉", Toast.LENGTH_SHORT).show();
                    newGame();
                }
            }
            if(movesCount>=9){
                newGame();
                Toast.makeText(this, "Draw ✌", Toast.LENGTH_SHORT).show();
            }
        }
//        else{
//            Toast.makeText(MainActivity.this, "Move next", Toast.LENGTH_SHORT).show();
//        }
    }

    private void newGame(){
        movesCount=0;
        turn=0;
        txtMoveOX.setText("Move : O");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                txtMoves[i][j].setText("");
            }
        }
    }

    private boolean checkWon(String val){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(txtMoves[i][j].getText()!=val){
                    break;
                }
                if(j==2){
                    return true;
                }
            }
            for(int j=0;j<3;j++){
                if(txtMoves[j][i].getText()!=val){
                    break;
                }
                if(j==2){
                    return true;
                }
            }
        }
        for(int i=0;i<3;i++){
            if(txtMoves[i][i].getText()!=val){
                break;
            }
            if(i==2){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(txtMoves[i][2-i].getText()!=val){
                break;
            }
            if(i==2){
                return true;
            }
        }
        return false;
    }

}