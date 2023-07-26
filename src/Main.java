import java.util.*;
public class Main {
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
    }
}