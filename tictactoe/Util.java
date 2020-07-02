package tictactoe;

public class Util {
    static char ch;

    static void showBoard(char[][] coordinate) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(coordinate[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static String isOccupied(int x, int y, char[][] coordinate) {
        if (!(coordinate[3-y][x-1] == ' ')) {
            System.out.println("This cell is occupied! Choose another one!");
            return "try again";
        } else {
            coordinate[3-y][x-1] = greater(coordinate);
            showBoard(coordinate);
            if (gameOver(coordinate))
                return "game over";
            else
                return "not finish";
        }
    }

    private static boolean gameOver(char[][] coordinate) {
        if (win(coordinate, 'X'))
            System.out.println("X wins");
        else if (win(coordinate, 'O'))
            System.out.println("O wins");
        else if (isComplete(coordinate))
            System.out.println("Draw");
        else
            return false;
        return true;
    }

    private static int isDone(char[][] coordinate) {
        int result = 10;
        if (win(coordinate, 'X')){
//            System.out.println(ch == 'X' ? 1 : -1+" "+ch);
            return ch == 'X' ? 1 : -1;}
        else if (win(coordinate, 'O')){
//            System.out.println(ch == 'O' ? 1 : -1+" "+ch);
            return ch == 'O' ? 1 : -1;}
        else if (isComplete(coordinate))
            return 0;
        return result;
    }

    static boolean win(char[][] arr, char i) {
        boolean row1 = (arr[0][0] == i && arr[0][1] == i && arr[0][2] == i);
        boolean row2 = (arr[1][0] == i && arr[1][1] == i && arr[1][2] == i);
        boolean row3 = (arr[2][0] == i && arr[2][1] == i && arr[2][2] == i);
        boolean column1 = (arr[0][0] == i && arr[1][0] == i && arr[2][0] == i);
        boolean column2 = (arr[0][1] == i && arr[1][1] == i && arr[2][1] == i);
        boolean column3 = (arr[0][2] == i && arr[1][2] == i && arr[2][2] == i);
        boolean diagonal1 = (arr[0][0] == i && arr[1][1] == i && arr[2][2] == i);
        boolean diagonal2 = (arr[0][2] == i && arr[1][1] == i && arr[2][0] == i);
        return (row1 || row2 || row3 || column1 || column2 || column3 || diagonal1 || diagonal2);
    }

    private static boolean isComplete(char[][] coordinate) {
        for (char[] chars : coordinate) {
            for (char c : chars) {
                if (c == ' ')
                    return false;
            }
        }
        return true;
    }

    private static char greater(char[][] coordinate) {
        int[] xoCount = refactorMethod(coordinate);
        int countX = xoCount[0];
        int countO = xoCount[1];
        return countO < countX ? 'O' : 'X';
    }

    private static int[] refactorMethod(char[][] coordinate) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (coordinate[i][j] == 'X')
                    countX++;
                else if (coordinate[i][j] == 'O')
                    countO++;
            }
        }
        return new int[]{countX, countO};
    }

    static boolean easyMakeMove(char[][] coordinate, char c) throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Making move level \"easy\"");
        boolean isGameOver = randomMove(coordinate, c);
        return isGameOver;
    }

    static boolean randomMove(char[][] coordinate, char c) {
        boolean isGameOver = true;
        while (true) {
            int x = (int) Math.round((Math.random() * 2 + 1));
            int y = (int) Math.round((Math.random() * 2 + 1));
            if (coordinate[3 - y][x - 1] == ' ') {
                coordinate[3-y][x-1] = c;
                showBoard(coordinate);
                isGameOver = gameOver(coordinate);
                break;
            }
        }
        return isGameOver;
    }

    static boolean mediumMakeMove(char[][] coordinate, char c) throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Making move level \"medium\"");
        boolean isGameOver = false;
        if (userTwo(coordinate, c, "win")) {
            showBoard(coordinate);
            isGameOver = gameOver(coordinate);
        } else if (userTwo(coordinate, c, "block")) {
            showBoard(coordinate);
            isGameOver = gameOver(coordinate);
        } else {
            isGameOver = randomMove(coordinate, c);
        }
        return isGameOver;
    }

    static boolean userTwo(char[][] coordinate, char c, String type) {
        if (isFill(new int[]{0,0}, new int[]{0,1}, new int[]{0,2}, coordinate, c, type))
            return true;
        if (isFill(new int[]{1,0}, new int[]{1,1}, new int[]{1,2}, coordinate, c, type))
            return true;
        if (isFill(new int[]{2,0}, new int[]{2,1}, new int[]{2,2}, coordinate, c, type))
            return true;
        if (isFill(new int[]{0,0}, new int[]{1,0}, new int[]{2,0}, coordinate, c, type))
            return true;
        if (isFill(new int[]{0,1}, new int[]{1,1}, new int[]{2,1}, coordinate, c, type))
            return true;
        if (isFill(new int[]{0,2}, new int[]{1,2}, new int[]{2,2}, coordinate, c, type))
            return true;
        if (isFill(new int[]{0,0}, new int[]{1,1}, new int[]{2,2}, coordinate, c, type))
            return true;
        if (isFill(new int[]{0,2}, new int[]{1,1}, new int[]{2,0}, coordinate, c, type))
            return true;
        
        return false;
    }
    
    private static boolean isFill(int[] fCell, int[] sCell, int[] tCell, char[][] coordinate, char c, String type) {
        c = type.equals("win") ? c : c == 'X' ? 'O' : 'X';
        int[] coord = fillCoord(fCell, sCell, tCell, coordinate, c);
        if (coord.length != 0) {
            if (type.equals("win"))
                coordinate[coord[0]][coord[1]] = c;
            else
                coordinate[coord[0]][coord[1]] = c == 'X' ? 'O' : 'X';
            return true;
        }
        return false;
    }

    private static int[] fillCoord(int[] fCell, int[] sCell, int[] tCell, char[][] coord, char c) {
        char opposite = c == 'X' ? 'O' : 'X';
        if (coord[fCell[0]][fCell[1]] == c && coord[sCell[0]][sCell[1]] == c && coord[tCell[0]][tCell[1]] != opposite)
            return tCell;
        else if (coord[fCell[0]][fCell[1]] == c && coord[tCell[0]][tCell[1]] == c && coord[sCell[0]][sCell[1]] != opposite)
            return sCell;
        else if (coord[sCell[0]][sCell[1]] == c && coord[tCell[0]][tCell[1]] == c && coord[fCell[0]][fCell[1]] != opposite)
            return fCell;
        return new int[]{};
    }

    static void clone(char[][] coordinate, char[][] coord) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                coord[i][j] = coordinate[i][j];
            }
        }
    }

    static int minimax(char[][] coordinate, char c, boolean isMaximize) {
        if (isMaximize) {
            int max = Integer.MIN_VALUE;
            int result;
            char[][] coordToSend = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (coordinate[i][j] == ' ') {
                        clone(coordinate, coordToSend);
                        coordToSend[i][j] = c;
                        result = isDone(coordToSend);
//                        System.out.println(result +" max");
//                        showBoard(coordToSend);
                        if (result == 10) {
                            result = minimax(coordToSend, c == 'X' ? 'O' : 'X', !isMaximize);
                        } else {
//                            showBoard(coordToSend);
                            result *= fillCount(coordToSend)+1;
                        }
                        if (max < result) {
                            max = result;
                        }
                    }
                }
            }
//            System.out.println(max +" max");
            return max;
        } else {
            int min = Integer.MAX_VALUE;
            int result;
            char[][] coordToSend = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (coordinate[i][j] == ' ') {
                        clone(coordinate, coordToSend);
                        coordToSend[i][j] = c;
                        result = isDone(coordToSend);
//                        System.out.println(result +" min");
//                        showBoard(coordToSend);
                        if (result == 10) {
                            result = minimax(coordToSend, c == 'X' ? 'O' : 'X', !isMaximize);
                        } else {
//                            showBoard(coordToSend);
                            result *= fillCount(coordToSend) + 1;
                        }
                        if (min > result) {
                            min = result;
                        }
                    }
                }
            }
//            System.out.println(min +" min");
            return min;
        }

    }

    static int fillCount(char[][] coordinate) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (coordinate[i][j] == ' ')
                    count++;
            }
        }
        return 9 - count;
    }

    static boolean hardMakeMove(char[][] coordinate, char c) throws InterruptedException {
        ch = c;
        Thread.sleep(200);
        System.out.println("Making move level \"hard\"");
        boolean isGameOver = false;
        if (fillCount(coordinate) == 0)
            return randomMove(coordinate, c);

        int max = Integer.MIN_VALUE;
        int result;
        int J = 0;
        int I = 0;
        char[][] coordToSend = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (coordinate[i][j] == ' ') {
                    clone(coordinate, coordToSend);
                    coordToSend[i][j] = c;
                    result = isDone(coordToSend);
                    if (result == 10) {
                        result = minimax(coordToSend, c == 'X' ? 'O' : 'X', false);
                    } else {
                        result *= fillCount(coordToSend)+1;
                    }
                    if (max < result) {
                        max = result;
                        J = j;
                        I = i;
                    }
                }
            }
        }
        coordinate[I][J] = c;
        showBoard(coordinate);
        isGameOver = gameOver(coordinate);
        return isGameOver;
    }
}
