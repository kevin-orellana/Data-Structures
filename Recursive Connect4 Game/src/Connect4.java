/**
 * Created by kevinorellana on 7/11/17.
 */
import java.util.Scanner;

public class Connect4 {
    static  int B = 1, R = 2;
    static final int NUM_COLUMNS = 4;
    static final int NUM_IN_ROW = 4;
    static Scanner input = new Scanner(System.in);
    static int firstplayer;
    static int p1 = 0,p2 = 0;
    static long count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            int[][] list = new int[NUM_COLUMNS][NUM_COLUMNS];
            firstplayer = B;
            p1 = 0; p2 = 0; count = 0;
            list[NUM_COLUMNS - 1][i] = B;
            System.out.println ("Blue NetWins for column " + i + ": " + Play(list , R)); //passes current board to opponent R
            System.out.println ("Total recursion calls: " + count);
            System.out.println("Blue Wins:  "+ p1 +"  |   Red Wins:"  + p2);
            System.out.println("******************\n");
        }
    }

    public static int Play(int[][] inlist, int caller) {
        count++;
        int result = checkBoard(inlist, caller);
        // 0 - board full, 1- B wins  2 = R wins   3-keep playing
        if (result < 3) {
            if (result == 0) {return 0;
            }
            else {
                if (result == firstplayer) {p1++; return 1;}
                else {p2++; return -1;}
            }
        }

        result = 0;

        //   update the board for this move
        for (int j = 0; j < NUM_COLUMNS; j++){ //column
            for(int i = 1; i <= NUM_COLUMNS; i++){ //row
                // for each space that can be the next move
                //    make a copy of board (next lines)

                if (inlist[NUM_COLUMNS - i][j] == 0) {
                    int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
                    for (int row = 0; row < NUM_COLUMNS; row++) {
                        for (int col = 0; col < NUM_COLUMNS; col++) {
                            clonelist[row][col] = inlist[row][col];
                        }
                    }

                    clonelist[NUM_COLUMNS - i][j] = caller;
//                    printlist(clonelist);
                    result += Play(clonelist, 3 - caller);
                    break;
                }
            }
        }
        return result;
        }


    public static boolean isFull(int[][] inlist){
        boolean empty = true;
        for (int i = 0 ; i<NUM_COLUMNS ; i++ ) {
            for (int i2 = 0 ; i2<NUM_COLUMNS ; i2++ ) {
                if (inlist[i][i2] ==0   ) { empty = false; break;}
            }
        }
        return empty;
    }


    public static int checkBoard(int[][] inlist , int caller){
        //chkclr is the opponent
        int chkclr = 3 - caller;

        //HORIZONTAL CHECK
        for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
            int colcnt = 0;
            for (int j=0; j<NUM_COLUMNS; j++) {
                if (inlist[i][j] == chkclr) {
                    colcnt++;
                    if (colcnt == NUM_IN_ROW)
                    { return chkclr; } //return 2 is R wins in this game.. or 1 if B wins
                }
                else {
                    colcnt =0;
                }
            }
        }

        //South VERTICAL CHECK
        for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
            int colcnt = 0;
            for (int j=0; j<NUM_COLUMNS; j++) {
                if (inlist[j][i] == chkclr) {
                    colcnt++;
                    if (colcnt == NUM_IN_ROW)  { return chkclr;}
                    //return 2 is R wins in this game.. or 1 if B wins
                }  else {
                    colcnt = 0;
                }
            }
        }
        //SouthEast DIAGONAL CHECK
        int colcnt = 0;
        for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
            if (inlist[i][i] == chkclr) {
                colcnt++;
                if (colcnt == NUM_IN_ROW)  {return chkclr;}
                //return 2 is R wins in this game.. or 1 if B wins
            }  else {
                colcnt =0;
            }
        }
        //NorthEast DIAGONAL CHECK
        colcnt = 0;
        for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
            if (inlist[NUM_COLUMNS-1-i][i] == chkclr) {
                colcnt++;
                if (colcnt == NUM_IN_ROW)  { return chkclr;}
                //return 2 is R wins in this game.. or 1 if B wins
            }  else {
                colcnt =0;
            }
        }
        if (isFull(inlist)) {  return 0;
            //0 if the board is full
        } else {
            return 3;
            //return 3 if nobody wins
        }

    }
    public static void printlist(int[][] inlist) {
        for (int i =0; i<inlist.length; i++) {
            for (int j =0; j<inlist.length; j++) {
                System.out.print(inlist[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
