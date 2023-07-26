package com.example.firstfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

import static com.example.firstfx.HelloController.*;

public class HelloApplication extends Application {
   public static Scene scene2;
   public static Scene scene;
    static BorderPane bp = new BorderPane();
    //public static BorderPane bp;
    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());







        stage.setTitle("the coin game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        int[] arr = {4, 15, 7, 3, 8, 9};
        Coin[][] coinArr = new Coin[6][6];
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                coinArr[i][j] = new Coin(0, 0);
                coinArr[i][j].player1 = 0;
                coinArr[i][j].player2 = 0;
            }
        }
        for(int j=0; j<arr.length; j++){
            for(int i=j; i>=0; i--){
                if(i==j){
                    coinArr[i][j].player1 = arr[i];
                    coinArr[i][j].player2 = 0;
                }
                else if((i+1)==j){
                    coinArr[i][j].player1 = Math.max(arr[i], arr[j]);
                    coinArr[i][j].player2 = Math.min(arr[i], arr[j]);
                }
                else{
                    if((arr[i]+coinArr[i+1][j].player2)>(arr[j]+coinArr[i][j-1].player2)){
                        coinArr[i][j].player1 = arr[i]+coinArr[i+1][j].player2;
                        // coinArr[i][j].player2 = coinArr[i][j-1].player1;
                        coinArr[i][j].player2 = coinArr[i+1][j].player1;
                    }
                    else if((arr[i]+coinArr[i+1][j].player2)<(arr[j]+coinArr[i][j-1].player2)){
                        coinArr[i][j].player1 = arr[j]+coinArr[i][j-1].player2;
                        //coinArr[i][j].player2 = coinArr[i+1][j].player1;
                        coinArr[i][j].player2 = coinArr[i][j-1].player1;
                    }
                    else if((arr[i]+coinArr[i+1][j].player2)==(arr[j]+coinArr[i][j-1].player2) && arr[j]>arr[i]){
                        coinArr[i][j].player1 = arr[j]+coinArr[i][j-1].player2;
                        coinArr[i][j].player2 = coinArr[i+1][j].player1;
                    }
                    else if((arr[i]+coinArr[i+1][j].player2)==(arr[j]+coinArr[i][j-1].player2) && arr[i]>arr[j]){
                        coinArr[i][j].player1 = arr[i]+coinArr[i+1][j].player2;
                        coinArr[i][j].player2 = coinArr[i][j-1].player1;
                    }
                }
                System.out.println(coinArr[i][j].player1 + ", " + coinArr[i][j].player2);
            }
        }
        launch();
    }

    public static class Coin {
        public int player1;
        public int player2;
        public Coin(int player1, int player2){
            this.player1 = player1;
            this.player2 = player2;
        }

    }
}