package com.example.firstfx;


import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.util.*;


import static com.example.firstfx.HelloApplication.bp;
import static com.example.firstfx.HelloApplication.scene;

public class HelloController extends Thread{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Text insertLabel;

    @FXML
    private TextField txtfieldcoin;

    @FXML
   private Button enterButton;

    @FXML
    private Button result;

    ArrayList<Integer> coins;
    public static String coinStr = "";

public static ArrayList<Integer> coinList = new ArrayList<Integer>();
   public void enter(ActionEvent event) throws Exception{
       try {
coinStr+= txtfieldcoin.getText();
           String[] coinStrArr = coinStr.split(" ");

               int[] coins = new int[coinStrArr.length];
               for (int i = 0; i < coinStrArr.length; i++) {
                   coins[i] = Integer.parseInt(coinStrArr[i]);
                   coinList.add(Integer.parseInt(coinStrArr[i]));
                   System.out.print(coinList.get(i) + ",");
               }
           if((coinList.size()%2)==0) {
               scene.setRoot(bp);


               //LIGHTBLUE background
               bp.setStyle("-fx-background-color:#ADD8E6");
               VBox vb = new VBox(10);
               HBox hb2 = new HBox();
               bp.setBottom(vb);
               Label lb = new Label("");
               vb.getChildren().addAll(hb2, lb);


               hb2.setStyle("-fx-background-image: url('file:360_F_181389116_Ur1VLvYgmIuqVmU0f30rIcvL2boxTAJo.jpg');" +
                       "-fx-background-repeat: stretch;" +
                       "-fx-background-size: 1000 700;" +
                       "-fx-background-position: center center;");
               hb2.setPadding(new Insets(0, 10, 100, 10));
               vb.setPadding(new Insets(0, 10, 10, 10));


               HelloApplication.Coin[][] coinArr = new HelloApplication.Coin[coins.length][coins.length];
               for (int i = 0; i < coins.length; i++) {
                   for (int j = 0; j < coins.length; j++) {
                       coinArr[i][j] = new HelloApplication.Coin(0, 0);
                       coinArr[i][j].player1 = 0;
                       coinArr[i][j].player2 = 0;
                   }
               }
               int[] pleyer2coins = new int[(coins.length)];
               for (int i = 0; i < pleyer2coins.length; i++) {
                   pleyer2coins[i] = 0;
               }
               // int[] pleyer2coins = new int[(coins.length/2)];

               for (int j = 0; j < coins.length; j++) {
                   for (int i = j; i >= 0; i--) {
                       if (i == j) {
                           coinArr[i][j].player1 = coins[i];
                           if ((j) % 2 == 0) {
                               pleyer2coins[i] = coins[i];
                           }
                           coinArr[i][j].player2 = 0;
                       } else if ((i + 1) == j) {
                           coinArr[i][j].player1 = Math.max(coins[i], coins[j]);
                           if (coins[i] >= coins[j]) {
                               if ((i) % 2 == 0) {
                                   pleyer2coins[i] = Math.max(coins[i], coins[j]);
                               }
                           } else if (coins[i] < coins[j]) {
                               if ((j) % 2 == 0) {
                                   pleyer2coins[j] = Math.max(coins[i], coins[j]);
                               }
                           }

                           coinArr[i][j].player2 = Math.min(coins[i], coins[j]);

                       } else {
                           if ((coins[i] + coinArr[i + 1][j].player2) > (coins[j] + coinArr[i][j - 1].player2)) {
                               coinArr[i][j].player1 = coins[i] + coinArr[i + 1][j].player2;
                               if ((i) % 2 == 0) {
                                   pleyer2coins[i] = coins[i];
                               }
                               coinArr[i][j].player2 = coinArr[i + 1][j].player1;

                           } else if ((coins[i] + coinArr[i + 1][j].player2) < (coins[j] + coinArr[i][j - 1].player2)) {
                               coinArr[i][j].player1 = coins[j] + coinArr[i][j - 1].player2;
                               if ((j) % 2 == 0) {
                                   pleyer2coins[j] = coins[j];
                               }
                               coinArr[i][j].player2 = coinArr[i][j - 1].player1;
                           } else if ((coins[i] + coinArr[i + 1][j].player2) == (coins[j] + coinArr[i][j - 1].player2) && coins[j] >= coins[i]) {
                               coinArr[i][j].player1 = coins[j] + coinArr[i][j - 1].player2;
                               if ((j) % 2 == 0) {
                                   pleyer2coins[j] = coins[j];
                               }
                               coinArr[i][j].player2 = coinArr[i + 1][j].player1;
                           } else if ((coins[i] + coinArr[i + 1][j].player2) == (coins[j] + coinArr[i][j - 1].player2) && coins[i] >= coins[j]) {
                               coinArr[i][j].player1 = coins[i] + coinArr[i + 1][j].player2;
                               if ((i) % 2 == 0) {
                                   pleyer2coins[i] = coins[i];
                               }
                               coinArr[i][j].player2 = coinArr[i][j - 1].player1;
                           }
                       }
                       System.out.println(coinArr[i][j].player1 + ", " + coinArr[i][j].player2);
                   }
               }
               System.out.print("coins of player 2: ");
               for (int i = 0; i < pleyer2coins.length; i++) {
                   System.out.println(pleyer2coins[i] + ",");
               }

               VBox resultvb = new VBox(20);

               Text resultTxt = new Text("Result: ");
               resultTxt.setStyle("-fx-font-size:30;-fx-font-family:Showcard Gothic, serif; -fx-font-weight:bold");
               resultTxt.setFill(Color.CHOCOLATE);
               resultTxt.setFont(Font.font("Showcard Gothic", FontWeight.EXTRA_BOLD, 100));
               Text result = new Text("");
               result.setText("" + coinArr[0][coinArr.length - 1].player1 + " coins");
               result.setStyle("-fx-font-size:30;-fx-font-family:Showcard Gothic, serif; -fx-font-weight:bold");
               result.setFill(Color.WHITE);
               resultvb.setPadding(new Insets(20, 0, 0, 20));

               HBox resultHb = new HBox(20);
               resultHb.getChildren().addAll(resultTxt, result);

               Text title = new Text("The Coin Game");
               title.setFill(Color.MIDNIGHTBLUE);
               title.setStyle("-fx-font-size:40; -fx-font-weight:bold; ");
               title.setFont(Font.font("Showcard Gothic", FontWeight.EXTRA_BOLD, 200));
               HBox hbTitle = new HBox(50);
               Label lbT = new Label("");
               hbTitle.setAlignment(Pos.CENTER);
               hbTitle.getChildren().addAll(title);
               bp.setTop(hbTitle);
               HBox coinHb = new HBox(10);
               Text coinTxtHb = new Text("Coins: ");
               coinTxtHb.setStyle("-fx-font-size:30;-fx-font-family:Showcard Gothic, serif; -fx-font-weight:bold");
               coinTxtHb.setFill(Color.CHOCOLATE);
               coinTxtHb.setFont(Font.font("Showcard Gothic", FontWeight.EXTRA_BOLD, 30));
               coinHb.getChildren().add(coinTxtHb);


               for (int i = 0; i < coins.length; i++) {

                   Circle circle = new Circle(30, 30, 30);
                   circle.setFill(Color.ORANGE);
                   Text text = new Text("" + coins[i]);
                   text.setFill(Color.WHITE);
                   text.setStyle("-fx-font-size:20; -fx-font-weight:bold");
                   text.setBoundsType(TextBoundsType.VISUAL);
                   StackPane stack = new StackPane();
                   stack.getChildren().addAll(circle, text);
                   coinHb.getChildren().addAll(stack);
               }
               resultvb.getChildren().addAll(resultHb, coinHb);
               String tableStr = "";
               String newLine = System.getProperty("line.separator");
               GridPane gridPane = new GridPane();
               gridPane.setHgap(10);
               gridPane.setVgap(10);


               for (int i = 0; i < coinArr.length; i++) {
                   for (int j = 0; j < coinArr.length; j++) {
                       if (i == 0 && j == 0) {
                           Text txt = new Text(j + "");
                           gridPane.add(txt, j, i, 1, 1);
                           txt.setFill(Color.BROWN);
                       }
                       if (i == 0) {
                           Text txt = new Text(j + 1 + "");
                           gridPane.add(txt, j + 1, i, 1, 1);
                           txt.setFill(Color.BROWN);
                       }
                       if (j == 0) {
                           Text txt = new Text(i + 1 + "");
                           txt.setFill(Color.BROWN);
                           gridPane.add(txt, j, i + 1, 1, 1);

                       }
                   }
               }
               for (int i = 1; i <= coinArr.length; i++) {
                   for (int j = 1; j <= coinArr.length; j++) {

                       if (i > j) {
                           //   tableStr+= "x" + " ";
                           Text txt = new Text("x");
                           gridPane.add(txt, j, i, 1, 1);

                       } else {
                           // tableStr += "" + coinArr[i][j].player1 + " ";
                           Text txt = new Text("" + coinArr[i - 1][j - 1].player1);
                           gridPane.add(txt, j, i, 1, 1);
                       }
                   }
               }

               gridPane.setStyle("-fx-background-color: white; -fx-border-color:black; -fx-font-size:20");

               VBox tablevb = new VBox(20);
               Text tableTxt = new Text("DP TABLE");
               tableTxt.setStyle("-fx-font-size:30;-fx-font-family:Showcard Gothic, serif; -fx-font-weight:bold");
               tableTxt.setFill(Color.GREEN);
               tableTxt.setFont(Font.font("Showcard Gothic", FontWeight.EXTRA_BOLD, 30));
               tablevb.setAlignment(Pos.CENTER);
               tablevb.getChildren().addAll(tableTxt, gridPane);

               //bp.setRight(tablevb);
               resultvb.getChildren().addAll(tablevb);
               tablevb.setPadding(new Insets(40, 20, 0, 0));
               // tableStr+= "\n";


               System.out.println(tableStr);
               // System.out.println(coin);

               bp.setLeft(resultvb);

               HBox simulationHb = new HBox(10);
               VBox players = new VBox(60);
               VBox centerVb = new VBox();
               Label playerOneLb = new Label("Player 1: ");
               playerOneLb.setAlignment(Pos.TOP_CENTER);
               playerOneLb.setTextFill(Color.MEDIUMPURPLE);
               playerOneLb.setStyle("-fx-font-size:20; -fx-font-weight:bold");
               Label playerTwoLb = new Label("Player 2: ");
               playerTwoLb.setAlignment(Pos.BOTTOM_CENTER);
               playerTwoLb.setTextFill(Color.MEDIUMPURPLE);
               playerTwoLb.setStyle("-fx-font-size:20; -fx-font-weight:bold");
               players.getChildren().addAll(playerOneLb, playerTwoLb);
               simulationHb.getChildren().add(players);
               int[] player1coins = new int[coins.length];
               for (int i = 0; i < coins.length; i++) {
                   player1coins[i] = 0;
                   if (pleyer2coins[i] == 0) {
                       player1coins[i] = coins[i];
                   }
               }
               int sumPlayer1 = 0;
               int sumPlayer2 = 0;
               for (int i = 0; i < coins.length; i++) {
                   sumPlayer1 += player1coins[i];
                   sumPlayer2 += pleyer2coins[i];
               }
               for (int i = 0; i < coins.length; i++) {
                   Circle circle = new Circle(40, 40, 40);
                   circle.setFill(Color.WHITE);
                   Text text = new Text("" + coins[i]);
                   text.setFill(Color.BLACK);
                   text.setStyle("-fx-font-size:20; -fx-font-weight:bold");
                   text.setBoundsType(TextBoundsType.VISUAL);
                   StackPane stack = new StackPane();
                   stack.getChildren().addAll(circle, text);
                   VBox circleVb = new VBox();
                   circleVb.getChildren().add(stack);
                   stack.setAlignment(Pos.CENTER);
                   simulationHb.getChildren().addAll(stack);

                   if (pleyer2coins[i] != 0) {
                       if (sumPlayer2 <= sumPlayer1) {
                           TranslateTransition t = new TranslateTransition();
                           t.setDuration(Duration.seconds(2));
                           t.setByY(100);

                           t.setNode(stack);
                           t.play();
                       } else {
                           TranslateTransition t = new TranslateTransition();
                           t.setDuration(Duration.seconds(2));
                           t.setByY(-100);

                           t.setNode(stack);
                           t.play();
                       }
                   } else {
                       if (sumPlayer2 <= sumPlayer1) {
                           TranslateTransition t = new TranslateTransition();
                           t.setDuration(Duration.seconds(2));
                           t.setByY(-100);

                           t.setNode(stack);
                           run();
                           t.play();
                       } else {
                           TranslateTransition t = new TranslateTransition();
                           t.setDuration(Duration.seconds(2));
                           t.setByY(100);

                           t.setNode(stack);
                           run();
                           t.play();
                       }
                   }

               }


               centerVb.getChildren().addAll(simulationHb);
               centerVb.setAlignment(Pos.CENTER);
               bp.setCenter(centerVb);


           }
           else{
               txtfieldcoin.setText("insert even number of coins seperated by space only!");
           }
       } catch (Exception e) {
           txtfieldcoin.setText("insert even number of coins seperated by space only!");
       }
   }

    public void moveOnButton(MouseEvent event) {
        enterButton.setStyle("-fx-background-color: PALEGOLDENROD; ");
    }
    public void release(MouseEvent event) {
        enterButton.setStyle("-fx-background-color: WHITE; ");
    }

@Override
    public void run(){
    try {
        Thread.sleep(200);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
    }