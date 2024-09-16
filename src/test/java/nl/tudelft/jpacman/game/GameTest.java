package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the Game class.
 */
public class GameTest {

    private Game game;
    private Level level;
    private Player player;
    private PointCalculator pointCalculator;

    @BeforeEach
    void setUp() {
        // Mocks
        level = mock(Level.class);
        player = mock(Player.class);
        pointCalculator = mock(PointCalculator.class);

        // Create concrete implementation of the abstract Game class
        game = new Game(pointCalculator) {
            @Override
            public List<Player> getPlayers() {
                return Collections.singletonList(player);
            }

            @Override
            public Level getLevel() {
                return level;
            }
        };
    }

    @Test
    void testGameStartsSuccessfully() {
        // Arrange
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(10);

        // Act
        game.start();

        // Assert
        assertThat(game.isInProgress()).isTrue();
        verify(level).addObserver(game);
        verify(level).start();
    }

    @Test
    void testGameDoesNotStartWhenNoPlayersAreAlive() {
        // Arrange
        when(level.isAnyPlayerAlive()).thenReturn(false);

        // Act
        game.start();

        // Assert
        assertThat(game.isInProgress()).isFalse();
        verify(level, never()).start();
    }

    @Test
    void testGameDoesNotStartWhenNoPelletsRemain() {
        // Arrange
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(0);

        // Act
        game.start();

        // Assert
        assertThat(game.isInProgress()).isFalse();
        verify(level, never()).start();
    }

    @Test
    void testGameStopsSuccessfully() {
        // Arrange
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(10);
        game.start();

        // Act
        game.stop();

        // Assert
        assertThat(game.isInProgress()).isFalse();
        verify(level).stop();
    }

    @Test
    void testMovePlayerWhenGameIsInProgress() {
        // Arrange
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(10);
        game.start();

        // Act
        game.move(player, Direction.EAST);

        // Assert
        verify(level).move(player, Direction.EAST);
        verify(pointCalculator).pacmanMoved(player, Direction.EAST);
    }

    @Test
    void testMovePlayerWhenGameIsNotInProgress() {
        // Act
        game.move(player, Direction.EAST);

        // Assert
        verify(level, never()).move(any(Player.class), any(Direction.class));
        verify(pointCalculator, never()).pacmanMoved(any(Player.class), any(Direction.class));
    }

    @Test
    void testLevelWonStopsGame() {
        // Arrange
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(10);
        game.start();

        // Act
        game.levelWon();

        // Assert
        assertThat(game.isInProgress()).isFalse();
        verify(level).stop();
    }

    @Test
    void testLevelLostStopsGame() {
        // Arrange
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(10);
        game.start();

        // Act
        game.levelLost();

        // Assert
        assertThat(game.isInProgress()).isFalse();
        verify(level).stop();
    }
}
