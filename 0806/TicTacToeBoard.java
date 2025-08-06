public class TicTacToeBoard {
    static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    public static void main(String[] args) {
        placeMark(0, 0, 'X');
        placeMark(1, 1, 'O');
        placeMark(0, 1, 'X');
        placeMark(2, 2, 'O');
        placeMark(0, 2, 'X');

        printBoard();
        if (checkWin('X')) System.out.println("X贏了");
        else if (checkWin('O')) System.out.println("O贏了");
        else if (isFull()) System.out.println("平手");
        else System.out.println("遊戲未結束");
    }

    static boolean placeMark(int r, int c, char p) {
        if (board[r][c] == ' ') {
            board[r][c] = p;
            return true;
        }
        return false;
    }

    static boolean checkWin(char p) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == p && board[i][1] == p && board[i][2] == p) ||
                (board[0][i] == p && board[1][i] == p && board[2][i] == p)) return true;
        return (board[0][0] == p && board[1][1] == p && board[2][2] == p) ||
               (board[0][2] == p && board[1][1] == p && board[2][0] == p);
    }

    static boolean isFull() {
        for (char[] r : board) for (char c : r) if (c == ' ') return false;
        return true;
    }

    static void printBoard() {
        for (char[] r : board) {
            for (char c : r) System.out.print(c + "|");
            System.out.println();
        }
    }
}
