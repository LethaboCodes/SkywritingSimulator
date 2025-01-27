import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class SkywritingSimulator extends JFrame {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 20;
    private static char[][] grid;
    private static int currentX, currentY;
    private static int direction; // 0=right, 1=down-right, 2=down, 3=down-left, 4=left, 5=up-left, 6=up, 7=up-right
    private static boolean smokeOn;
    private static final int CELL_SIZE = 15; // Size of each cell in pixels

    public static void main(String[] args) {
        // Paste the input string directly here
        String input = "ON,90R,F4,135L,F2,90R,F2,135L,F4,OFF,90R,F2,ON,90R,F4,OFF,180,F4,90R,F2,ON,90R,F4,180,F4,135R,F4,135L,F4,OFF,90R,F2,ON,90R,F4,180,F4,135R,F4,135L,F4,OFF,90R,F2,ON,F4,180,F4,90L,F2,90L,F4,180,F4,90L,F2,90L,F4,OFF,90L,F4,90R,F2,ON,90R,F4,180,F4,90R,F3,45R,F1,90R,F1,45R,F2,180,F2,45R,F1,45R,F1,OFF";

        // Initialize the grid and process instructions
        initializeGrid();
        processInstructions(input);

        // Display the grid in GUI
        SwingUtilities.invokeLater(() -> {
            SkywritingSimulator frame = new SkywritingSimulator();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(CELL_SIZE * WIDTH + 50, CELL_SIZE * HEIGHT + 50); // Window size
            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }

    // Initialize the grid
    private static void initializeGrid() {
        grid = new char[HEIGHT][WIDTH];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }
        currentX = 0;
        currentY = 0;
        direction = 0;
        smokeOn = false;
    }

    // Process the instructions from the input string
    public static void processInstructions(String input) {
        String[] instructions = input.split(",");
        for (String instruction : instructions) {
            if (instruction.equals("ON")) {
                smokeOn = true;
                markCurrentPosition();
            } else if (instruction.equals("OFF")) {
                smokeOn = false;
            } else if (instruction.startsWith("F")) {
                int steps = Character.getNumericValue(instruction.charAt(1));
                move(steps);
            } else if (instruction.equals("45R")) {
                direction = (direction + 1) % 8;
            } else if (instruction.equals("90R")) {
                direction = (direction + 2) % 8;
            } else if (instruction.equals("135R")) {
                direction = (direction + 3) % 8;
            } else if (instruction.equals("45L")) {
                direction = (direction + 7) % 8;
            } else if (instruction.equals("90L")) {
                direction = (direction + 6) % 8;
            } else if (instruction.equals("135L")) {
                direction = (direction + 5) % 8;
            } else if (instruction.equals("180")) {
                direction = (direction + 4) % 8;
            }
        }
    }

    // Move in the current direction
    private static void move(int steps) {
        for (int i = 0; i < steps; i++) {
            switch (direction) {
                case 0: currentX++; break; // Right
                case 1: currentX++; currentY++; break; // Down-right
                case 2: currentY++; break; // Down
                case 3: currentX--; currentY++; break; // Down-left
                case 4: currentX--; break; // Left
                case 5: currentX--; currentY--; break; // Up-left
                case 6: currentY--; break; // Up
                case 7: currentX++; currentY--; break; // Up-right
            }

            // Ensure we stay within bounds
            currentX = Math.max(0, Math.min(currentX, WIDTH - 1));
            currentY = Math.max(0, Math.min(currentY, HEIGHT - 1));

            if (smokeOn) {
                markCurrentPosition();
            }
        }
    }

    // Mark the current position on the grid
    private static void markCurrentPosition() {
        grid[currentY][currentX] = '*';
    }

    // Constructor to set the frame title
    public SkywritingSimulator() {
        setTitle("Skywriting Simulator");
    }

    // Paint the grid
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Loop through the grid to draw each cell as a square
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (grid[row][col] == '*') {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }

                // Draw each cell with a fixed size
                g.fillRect(col * CELL_SIZE + 25, row * CELL_SIZE + 25, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK); // Outline color
                g.drawRect(col * CELL_SIZE + 25, row * CELL_SIZE + 25, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
