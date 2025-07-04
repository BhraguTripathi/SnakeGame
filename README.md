# Snake Game

A classic Snake game implementation in Java using Swing GUI framework.

## Features

- **Classic Snake Gameplay**: Control a snake to eat food and grow longer
- **High Score Tracking**: Keeps track of your best score during the session
- **Instant Restart**: Press any key after game over to restart immediately
- **Visual Grid**: Clear grid lines for better gameplay visibility
- **Collision Detection**: Game ends when snake hits walls or itself
- **Responsive Controls**: Smooth arrow key controls with direction locking

## How to Play

1. **Movement**: Use arrow keys to control the snake
   - ↑ Up Arrow: Move up
   - ↓ Down Arrow: Move down
   - ← Left Arrow: Move left
   - → Right Arrow: Move right

2. **Objective**: Guide the snake to eat the red food blocks
   - Each food eaten increases your score by 1
   - The snake grows longer with each food consumed

3. **Game Over**: The game ends when:
   - The snake hits the walls
   - The snake collides with its own body

4. **Restart**: Press any key after game over to start a new game

## Game Interface

- **Score**: Displayed in the top-left corner
- **High Score**: Displayed in the top-right corner
- **Game Over Screen**: Shows final score and restart instructions
- **Grid**: Visual grid helps with precise movement

## Technical Details

- **Language**: Java
- **GUI Framework**: Swing
- **Game Loop**: Timer-based animation (100ms intervals)
- **Grid Size**: 25x25 pixel tiles
- **Board Size**: 600x600 pixels

## File Structure

```
SnakeGame/
├── App.java           # Main application entry point
├── SnakeGame.java     # Core game logic and GUI
├── README.md          # This file
└── *.class           # Compiled Java bytecode files
```

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed
- Command line access (Terminal/Command Prompt/PowerShell)

### Compilation and Execution

1. **Navigate to the project directory**:
   ```bash
   cd path/to/SnakeGame
   ```

2. **Compile the Java files**:
   ```bash
   javac *.java
   ```

3. **Run the game**:
   ```bash
   java App
   ```

### Alternative (if using an IDE)
- Open the project in your Java IDE (Eclipse, IntelliJ IDEA, VS Code, etc.)
- Run the `App.java` file

## Game Controls Summary

| Key | Action |
|-----|--------|
| ↑ | Move Up |
| ↓ | Move Down |
| ← | Move Left |
| → | Move Right |
| Any Key (during game over) | Restart Game |

## Game Rules

1. The snake starts moving automatically to the right
2. You cannot reverse direction immediately (e.g., if moving right, you cannot immediately move left)
3. Food appears randomly on the grid after being consumed
4. Score equals the length of the snake's body
5. High score persists only during the current session

## Future Enhancements

Potential improvements that could be added:
- [ ] Progressive speed increase as score grows
- [ ] Persistent high score storage in file
- [ ] Sound effects
- [ ] Different difficulty levels
- [ ] Power-ups and special food types
- [ ] Pause functionality
- [ ] Multiple snake skins/themes

## Development Notes

- **Object-Oriented Design**: Uses classes for Tile objects and clean separation of concerns
- **Event-Driven**: Implements ActionListener for game loop and KeyListener for user input
- **Graphics Rendering**: Custom paintComponent method for smooth graphics
- **Collision System**: Efficient collision detection for walls and self-collision

## Contributing

Feel free to fork this project and submit pull requests for improvements or new features!

## License

This project is open source and available under the [MIT License](LICENSE).

---

**Enjoy playing Snake! 🐍**
