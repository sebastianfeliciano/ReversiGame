# Reversi Game

A 2D multi-player Reversi game built using MVC principles with both hexagonal and square board support.


![Screen Recording Oct 29 2025](https://github.com/user-attachments/assets/f9c2c6b3-5ccb-4e6c-9faa-e72adc212fab)


## Features

- **Two Board Types**: Hexagonal and Square Reversi
- **Multiple Player Types**: Human vs Human, Human vs AI, AI vs AI
- **AI Strategies**: Capture, Corner Control, and Chained Strategies
- **Interactive GUI**: Mouse clicks, keyboard controls, and visual feedback
- **Hint System**: Press 'H' to toggle move hints (hexagonal only)

## How to Run

```bash
# Hexagonal board with human players
java -jar Reversi.jar hex 7 human human

# Square board with AI players
java -jar Reversi.jar square 8 capture corner

# Human vs AI with chained strategy
java -jar Reversi.jar hex 7 human capture corner
```

## Command Line Format

```
java -jar Reversi.jar <board_type> <board_size> <player1> <player2>
```

- **board_type**: `hex` or `square`
- **board_size**: Board size (odd for hex, even for square)
- **player1/player2**: `human`, `capture`, `corner`, `capture corner`, `corner capture`, or `providerstrategy1`

## Controls

- **Mouse**: Click hexagons to select moves
- **Enter**: Confirm selected move
- **P**: Pass turn (hexagonal)
- **S**: Skip turn (square)
- **H**: Toggle hints (hexagonal only)

## Strategies

- **Capture**: Maximizes pieces flipped per turn
- **Corner**: Prioritizes corner positions
- **Chained**: Combines multiple strategies (e.g., "capture corner")
