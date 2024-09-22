
import java.util.Random;
import java.util.Scanner;

public class SnakesGame {

    private static final int BOARD_SIZE = 20;
    private static final int SNAKE_LENGTH = 5;
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int[] snakeX = new int[SNAKE_LENGTH];
    private int[] snakeY = new int[SNAKE_LENGTH];
    private int snakeLength = 1;
    private int direction = 0;
    private int foodX, foodY;
    private boolean gameOver = false;

    public SnakesGame() {
        initGame();
    }

    private void initGame() {
        snakeX[0] = BOARD_SIZE / 2;
        snakeY[0] = BOARD_SIZE / 2;
        generateFood();
    }

    private void generateFood() {
        Random random = new Random();
        foodX = random.nextInt(BOARD_SIZE);
        foodY = random.nextInt(BOARD_SIZE);
        while (foodX == snakeX[0] && foodY == snakeY[0]) {
            foodX = random.nextInt(BOARD_SIZE);
            foodY = random.nextInt(BOARD_SIZE);
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            printBoard();
            System.out.println("Enter direction (W/A/S/D): ");
            char input = scanner.next().charAt(0);
            switch (input) {
                case 'W':
                case 'w':
                    direction = 3;
                    break;
                case 'A':
                case 'a':
                    direction = 1;
                    break;
                case 'S':
                case 's':
                    direction = 2;
                    break;
                case 'D':
                case 'd':
                    direction = 0;
                    break;
            }
            updateSnakePosition();
            checkCollision();
            checkFood();
        }
        System.out.println("Game Over!");
    }

    private void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i == snakeX[0] && j == snakeY[0]) {
                    System.out.print("S ");
                } else if (i == foodX && j == foodY) {
                    System.out.print("F ");
                } else {
                    boolean isSnakeBody = false;
                    for (int k = 1; k < snakeLength; k++) {
                        if (i == snakeX[k] && j == snakeY[k]) {
                            System.out.print("s ");
                            isSnakeBody = true;
                            break;
                        }
                    }
                    if (!isSnakeBody) {
                        System.out.print(". ");
                    }
                }
            }
            System.out.println();
        }
    }

    private void updateSnakePosition() {
        for (int i = snakeLength - 1; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
        snakeX[0] += DIRECTIONS[direction][0];
        snakeY[0] += DIRECTIONS[direction][1];
    }

    private void checkCollision() {
        if (snakeX[0] < 0 || snakeX[0] >= BOARD_SIZE || snakeY[0] < 0 || snakeY[0] >= BOARD_SIZE) {
            gameOver = true;
        }
        for (int i = 1; i < snakeLength; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                gameOver = true;
                break;
            }
        }
    }

    private void checkFood() {
        if (snakeX[0] == foodX && snakeY[0] == foodY) {
            snakeLength++;
            generateFood();
        }
    }

    public static void main(String[] args) {
        SnakesGame game = new SnakesGame();
        game.playGame();
    }
}


