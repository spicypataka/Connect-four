package margie.patel; 
import java.util.*;

public class connectFour {

// This method attempts to put the letter of the given color in the given column.
// It returns true if successful and false if the column is filled and we cannot 
// put the letter.


public static boolean putLetter(char[][] field, int column, char color) {
    // If the first letter is there, the column is filled, returning false.
    if (field[0][column] != ' ')
        return false;

    // Checks the elements in the column.
    for (int row = 0; row < 7; ++row) {
        // If we there is a letter there it means its not zero.
        if (field[row][column] != ' ') {
            // This puts the letter on top of the one already there.
            field[row-1][column] = color;
            return true;
        }
    }
    // if there aren't any letters then it goes at the bottom.
    field[6][column] = color;
    return true;
}


// Checks rows for same letter 4 times in rows and returns the winner. 
private static char getWinnerRows(char[][] field) {

    for (int row = 0; row < 7; ++row) {
        int count = 0;
        // compares current and previous letters
        for (int column = 1; column < 7; ++column) {
            if (field[row][column] != ' ' &&
                field[row][column] == field[row][column-1])
                ++count;
            else
                count = 1;

            // Checks if there are 4 in a row.
            if (count >= 4) {
                // Returns color of the winner!
                return field[row][column];
            }
        }
    }
    return ' ';
}

// Check columns
private static char getWinnerColumns(char[][] field) {
    // Check column and see if there are 4 letters of the same color
    for (int column = 0; column < 7; ++column) {
        int count = 0;
        // compares current letters with the previous ones 
        for (int row = 1; row < 7; ++row) {
            if (field[row][column] != ' ' &&
                field[row][column] == field[row-1][column])
                ++count;
            else
                count = 1;

            // Checks if there are 4.
            if (count >= 4) {
                // Return color of the winner
                return field[row][column];
            }
        }
    }
    return ' ';
}

// Check diagonals
private static char getWinnerDagonals(char[][] field) {
    // Checks diagonals that go from top-left to bottom right
	//Start on top of each column.
    for (int column = 0; column < 7; ++column) {
        int count = 0;
        for (int row = 1; row < 7; ++row) { 
            // We stop when column gets outside the range which is 7
            if (column + row >= 7) break;
            if (field[row][column+row] != ' ' &&
                field[row-1][column + row - 1] == field[row][column+row])
                ++count;
            else
                count = 1;
            if (count >= 4) return field[row][column+row];
        }
    }

    // There are diagonals, that starts on bottom-left
    for (int row = 0; row < 7; ++row) {
        int count = 0;
        for (int column = 1; column < 7; ++column) {
            // We stop when column can get outside the range which is 7.
            if (column + row >= 7) break;
            if (field[row + column][column] != ' ' &&
                field[row+column - 1][column - 1] == field[row + column][column])
                ++count;
            else
                count = 1;
            if (count >= 4) return field[row + column][column];
        }
    }

    // Diagonals from top-right to bottom-left

    for (int column = 0; column < 7; ++column) {
        int count = 0;
        for (int row = 1; row < 7; ++row) {
            // We stop when column can get outside of the range
            if (column - row < 0) break;
            if (field[row][column-row] != ' ' &&
                field[row - 1][column - row + 1] == field[row][column-row])
                ++count;
            else
                count = 1;
            if (count >= 4) return field[row][column-row];
        }
    }

    // Diagonals that start on bottom-right to top-left
    for (int row = 0; row < 7; ++row) {
        int count = 0;
        for (int column = 5; column >= 0; --column) {
         //Stop when column gets outside of the range
            if (column - row < 0) break;
            if (field[column - row][column] != ' ' &&
                field[column - row - 1][column + 1] == field[column - row][column])
                ++count;
            else
                count = 1;
            if (count >= 4) return field[column - row][column];
        }
    }

    return ' ';
}

public static char getWinner(char[][] field) {
    char winner = getWinnerRows(field);
    if (winner != ' ') return winner;
    winner = getWinnerColumns(field);
    if (winner != ' ') return winner;
    winner = getWinnerDagonals(field);
    if (winner != ' ') return winner;

    // This checks if there are any spots left and if there aren't its a draw.
    for (int i = 0; i < field.length; ++i)
        for (int j = 0; j < field[i].length; ++j)
            if (field[i][j] == ' ') return ' ';

    return 'D';
}

public static void printField(char[][] field) {
    for (int row = 0; row < 7; ++row) {
        System.out.print("| ");
        for (int col = 0; col < 7; ++col)
            System.out.print(field[row][col] + "| ");
        System.out.println();
    }

    // Print bottom line
    for (int col = 0; col < 7; ++col)
        System.out.print("***");
    System.out.println();
}

public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    // Declare field 2D array.
    char[][] field = new char[7][7];

    // Initialize with spaces
    for (int i = 0; i < 7; ++i)
        for (int j = 0; j < 7; ++j)
            field[i][j] = ' ';
    //instructions 
    printField(field);
    System.out.println("Hey guys! Ready to play your favourite game? CONNECT FOUR!!!");
	 System.out.println ("Okay here are the instructions:");
	 System.out.println ("The first player that starts, gets the colour red and the second player gets yellow!");
	 System.out.println ("So the objective of this game is to get 4 of the first letter of your colour in a row");
	 System.out.println ("You can do this by getting four in a row, column or diagonal.");
	 System.out.println ("Red starts first, Good luck! smile emoticon");

    // This variable shows who's turn it is.
    boolean isRed = true;
    while (true) {
        if (isRed)
            System.out.println("Its red's turn!");            
        else 
            System.out.println("Its yellow's turn!");
        System.out.print("Choose column (1-7) to put your letter in:");
        // Read the position of turn and check if value is correct.
        int column = input.nextInt();
        if (column < 1 || column > 7) {
            System.out.println("Sorry! Number of column has to be between 1-7!");
            continue;
        }
        // Try to put disk in a column, method returns false if the columns
        // is filled and you cannot put a disk there.
        if (!putLetter(field, column - 1, isRed ? 'R' : 'Y')) {
            System.out.println("This column is filled! Choose another one!");
            continue;
        }

        printField(field);

        // Gets winner
        //this method returns "yay red wins!" if Red wins, "Yay yellow wins!" if Yellow wins
        // And returns "Sorry its a draw!" if it is a draw.
        char result = getWinner(field);
        if (result == 'D') {
            System.out.println("Sorry its a draw!");
            break;
        }
        else if (result == 'R') {
            System.out.println("Yay red wins!");
            break;
        }
        else if (result == 'Y') {
            System.out.println("Yay yellow wins!");
            break;
        }
        // Otherwise we just proceed to the next turn.
        isRed = !isRed;
    }
}
