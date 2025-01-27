Skywriting Simulator ✈️
A Java Swing application that simulates skywriting by parsing directional commands and rendering smoke trails on a grid. The program visualizes how an aircraft would draw patterns in the sky using "ON/OFF" smoke commands and directional movements.

<img width="1213" alt="Screenshot 2025-01-27 at 20 19 05" src="https://github.com/user-attachments/assets/8adf7297-e12f-4780-af3b-a953866fd0d4" />

Features

Grid-based GUI (80x20 cells) with adjustable cell size
Directional commands: 8 directions (right, down-right, down, etc.)
Smoke control: Toggle smoke with ON/OFF commands
Input parsing: Processes complex command strings (e.g., F4,90R,135L)

How to Run
Prerequisites

Ensure Java JDK is installed

Installation

Compile the source code:
bashCopyjavac SkywritingSimulator.java

Execute the program:
bashCopyjava SkywritingSimulator


Input Instructions
Commands are provided as a comma-separated string. Supported actions:

ON/OFF: Toggle smoke
F{steps}: Move forward (e.g., F4 = move 4 steps)
{degrees}{L/R}: Turn left (L) or right (R) by 45°, 90°, or 135° (e.g., 90R, 135L)
180: Reverse direction

Example Input
The default input in the code is:
javaCopyString input = "ON,90R,F4,135L,F2,90R,F2,135L,F4,OFF,90R,F2,ON,90R,F4,OFF,180,F4,90R,F2,ON,90R,F4,180,F4,135R,F4,135L,F4,OFF,90R,F2,ON,90R,F4,180,F4,135R,F4,135L,F4,OFF,90R,F2,ON,F4,180,F4,90L,F2,90L,F4,180,F4,90L,F2,90L,F4,OFF,90L,F4,90R,F2,ON,90R,F4,180,F4,90R,F3,45R,F1,90R,F1,45R,F2,180,F2,45R,F1,45R,F1,OFF";
How It Works

Grid Initialization: A 2D array (grid[HEIGHT][WIDTH]) is created, filled with . (empty cells)
Command Parsing: The input string is split into tokens, and each command updates the aircraft's position/direction
GUI Rendering: The grid is drawn using Java Swing, with * representing smoke trails

Code Structure

SkywritingSimulator.java: Main class handling GUI setup and painting
Directional logic: 8-direction movement using modulo arithmetic
Bounds checking: Ensures the aircraft stays within the grid
