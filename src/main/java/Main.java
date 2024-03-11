import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int TOTAL_BOARD_SPACE = 42;
        int totalMoves = 0;
        int playerChoiceX, playerTurn, availableY;
        boolean isAvailableY;
        Board board = new Board();
        playerTurn = 1;
        while (true) {
            System.out.println("The player turn is " + playerTurn);
            playerChoiceX = playerMove();
            isAvailableY = isAvailableY(playerChoiceX, board);
            if (!isAvailableY) {
                while (!isAvailableY) {
                    System.out.println("Row full, try enter a different row number");
                    playerChoiceX = playerMove();
                    isAvailableY = isAvailableY(playerChoiceX, board);
                }
            }
            availableY = getAvailableY(playerChoiceX, board);
            board.placeSquare(playerChoiceX, availableY, playerTurn);
            if (checkWinner(board, playerTurn)) {
                System.out.println("player" + playerTurn + " is the winner");
                break;
            }
            totalMoves++;
            if (totalMoves == TOTAL_BOARD_SPACE) {
                System.out.println("Game Over, no winner due to full board.");
                break;
            }
            if (playerTurn == 1) {
                playerTurn = 2;
            } else {
                playerTurn = 1;
            }

        }

        //Method 1: Board board = new Board();
        //This method shows the initial empty board


        //Method 2: placeSquare (int x, int y, int player)
        //This method gets 3 arguments
        //x is the x position of the square to be placed
        //y is the y position of the square to be placed
        //player can be either of value 1 or 2. For the value 1, a red square is being placed, For the value 2, a yellow square


        //Method 2: int getPlayerInSquare (int x, int y)
        //This method gets 2 arguments
        //x is the x position of the asked square
        //y is the y position of the asked square
        //The method returns an int value: if the value is 0 - the square is empty, if the value is 1 - the square is occupied by
        //player 1, if the value is 2 - the square is occupied by player 2

    }

    public static int playerMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose row number to place square");
        int playerChoiceX = scanner.nextInt();
        while (playerChoiceX <= 0 || playerChoiceX > 7) {
            System.out.println("Invalid number, enter a row number between 1=7");
            playerChoiceX = scanner.nextInt();
        }
        return playerChoiceX;
    }

    public static boolean isAvailableY(int x, Board board) {
        boolean isAvailableY = false;
        for (int y = 1; y <= 6; y++) {
            if (board.getPlayerInSquare(x, y) == 0) {
                isAvailableY = true;
                break;
            }
        }
        return isAvailableY;
    }

    public static int getAvailableY(int x, Board board) {
        int availableY = 1;
        for (int y = 1; y <= 6; y++) {
            int IsAvailableY = board.getPlayerInSquare(x, y);
            if (IsAvailableY == 0) {
                break;
            }
            availableY++;
        }
        return availableY;
    }

    public static boolean checkWinner(Board board, int player) {
        return (checkWinnerRows(board, player) || checkWinnerColumns(board, player) || checkWinnerDiagonals(board, player));
    }
    public static boolean checkWinnerRows(Board board, int player){
        boolean winner = false;
        for (int x = 1; x <= 7; x++) {
            int countY = 0;
            for (int y = 1; y <= 6; y++) {
                if (board.getPlayerInSquare(x, y) == player) {
                    countY++;
                    if (countY == 4) {
                        winner = true;
                        break;
                    }
                } else {
                    countY = 0;
                }
            }
        }
        return winner;
    }
    public static boolean checkWinnerColumns(Board board, int player){
        boolean winner = false;
        for (int y = 1; y <= 6; y++) {
            int countX = 0;
            for (int x = 1; x <= 7; x++) {
                if (board.getPlayerInSquare(x, y) == player) {
                    countX++;
                    if (countX == 4) {
                        winner = true;
                        break;
                    }
                } else {
                    countX = 0;
                }
            }
        }
        return winner;
    }
    public static boolean checkWinnerDiagonals(Board board, int player){
        boolean winner = false;
        int count1, count2;
        for (int y = 1; y <= 6; y++) {
            count1 = 0;
            for (int x = 1; x <= 7; x++) {
                if (y + count1 <= 6) {
                    count1 = board.getPlayerInSquare(x, y + count1) == player ? count1 + 1 : 0;
                }
                if (count1 == 4) {
                    winner = true;
                    break;
                }
            }
        }
        if (!winner) {
            for (int y = 6; y >= 1; y--) {
                count2 = 0;
                for (int x = 1; x <= 7; x++) {
                    if (y - count2 >= 1) {
                        count2 = board.getPlayerInSquare(x, y - count2) == player ? count2 + 1 : 0;
                    }
                    if (count2 == 4) {
                        winner = true;
                        break;
                    }
                }
            }
        }
        return winner;
    }
}
