package nl.tudelft.jpacman.points;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the DefaultPointCalculator class.
 */
public class DefaultPointCalculatorTest {

    private DefaultPointCalculator pointCalculator;
    private Player player;
    private Pellet pellet;
    private Ghost ghost;

    @BeforeEach
    void setUp() {
        pointCalculator = new DefaultPointCalculator();
        player = mock(Player.class);
        pellet = mock(Pellet.class);
        ghost = mock(Ghost.class);
    }

    /**
     * Test that when a pellet is consumed, the correct amount of points
     * are added to the player's score.
     */
    @Test
    void testConsumedAPelletAddsPoints() {
        // Arrange
        when(pellet.getValue()).thenReturn(10); // Pellet value is 10

        // Act
        pointCalculator.consumedAPellet(player, pellet);

        // Assert
        verify(player).addPoints(10); // Verify points are added to the player
    }

    /**
     * Test that when Pac-Man collides with a ghost, no points are awarded.
     */
    @Test
    void testCollidedWithGhostDoesNotAddPoints() {
        // Act
        pointCalculator.collidedWithAGhost(player, ghost);

        // Assert
        verify(player, never()).addPoints(anyInt()); // No points should be added
    }

    /**
     * Test that when Pac-Man moves, no points are awarded.
     */
    @Test
    void testPacmanMovedDoesNotAddPoints() {
        // Act
        pointCalculator.pacmanMoved(player, Direction.EAST);

        // Assert
        verify(player, never()).addPoints(anyInt()); // No points should be added
    }
}
