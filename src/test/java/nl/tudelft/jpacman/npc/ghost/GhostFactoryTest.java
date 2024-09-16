package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.sprite.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the GhostFactory class, now considering the Ghost class behavior.
 */
public class GhostFactoryTest {

    private PacManSprites spriteStore;
    private GhostFactory ghostFactory;

    @BeforeEach
    void setUp() {
        spriteStore = mock(PacManSprites.class); // Mock PacManSprites
        ghostFactory = new GhostFactory(spriteStore); // Create GhostFactory with the mocked sprite store
    }

    /**
     * Test that Blinky is created with the correct red ghost sprite and facing east by default.
     * Also tests the interval and sprite map.
     */
    @Test
    void testCreateBlinky() {
        // Arrange
        Sprite redGhostSprite = mock(Sprite.class);
        Map<Direction, Sprite> spriteMap = mock(Map.class);
        when(spriteMap.get(Direction.EAST)).thenReturn(redGhostSprite);
        when(spriteStore.getGhostSprite(GhostColor.RED)).thenReturn(spriteMap);

        // Act
        Ghost blinky = ghostFactory.createBlinky();

        // Assert
        assertThat(blinky).isInstanceOf(Blinky.class);
        assertThat(blinky.getSprite()).isEqualTo(redGhostSprite);
        assertThat(blinky.getDirection()).isEqualTo(Direction.EAST); // Check that Blinky is facing east by default

        // Test movement interval generation
        long interval = blinky.getInterval();
        assertThat(interval).isGreaterThan(0);

        // Verify the correct sprite was retrieved
        verify(spriteStore).getGhostSprite(GhostColor.RED);
    }

    /**
     * Test that Pinky is created with the correct pink ghost sprite and facing east by default.
     * Also tests the movement interval generation.
     */
    @Test
    void testCreatePinky() {
        // Arrange
        Sprite pinkGhostSprite = mock(Sprite.class);
        Map<Direction, Sprite> spriteMap = mock(Map.class);
        when(spriteMap.get(Direction.EAST)).thenReturn(pinkGhostSprite);
        when(spriteStore.getGhostSprite(GhostColor.PINK)).thenReturn(spriteMap);

        // Act
        Ghost pinky = ghostFactory.createPinky();

        // Assert
        assertThat(pinky).isInstanceOf(Pinky.class);
        assertThat(pinky.getSprite()).isEqualTo(pinkGhostSprite);
        assertThat(pinky.getDirection()).isEqualTo(Direction.EAST); // Check that Pinky is facing east by default

        // Test movement interval generation
        long interval = pinky.getInterval();
        assertThat(interval).isGreaterThan(0);

        // Verify the correct sprite was retrieved
        verify(spriteStore).getGhostSprite(GhostColor.PINK);
    }

    /**
     * Test that Inky is created with the correct cyan ghost sprite.
     */
    @Test
    void testCreateInky() {
        // Arrange
        Sprite cyanGhostSprite = mock(Sprite.class);
        Map<Direction, Sprite> spriteMap = mock(Map.class);
        when(spriteMap.get(Direction.EAST)).thenReturn(cyanGhostSprite);
        when(spriteStore.getGhostSprite(GhostColor.CYAN)).thenReturn(spriteMap);

        // Act
        Ghost inky = ghostFactory.createInky();

        // Assert
        assertThat(inky).isInstanceOf(Inky.class);
        assertThat(inky.getSprite()).isEqualTo(cyanGhostSprite);
        assertThat(inky.getDirection()).isEqualTo(Direction.EAST); // Check that Inky is facing east by default

        // Test movement interval generation
        long interval = inky.getInterval();
        assertThat(interval).isGreaterThan(0);

        // Verify the correct sprite was retrieved
        verify(spriteStore).getGhostSprite(GhostColor.CYAN);
    }

    /**
     * Test that Clyde is created with the correct orange ghost sprite.
     */
    @Test
    void testCreateClyde() {
        // Arrange
        Sprite orangeGhostSprite = mock(Sprite.class);
        Map<Direction, Sprite> spriteMap = mock(Map.class);
        when(spriteMap.get(Direction.EAST)).thenReturn(orangeGhostSprite);
        when(spriteStore.getGhostSprite(GhostColor.ORANGE)).thenReturn(spriteMap);

        // Act
        Ghost clyde = ghostFactory.createClyde();

        // Assert
        assertThat(clyde).isInstanceOf(Clyde.class);
        assertThat(clyde.getSprite()).isEqualTo(orangeGhostSprite);
        assertThat(clyde.getDirection()).isEqualTo(Direction.EAST); // Check that Clyde is facing east by default

        // Test movement interval generation
        long interval = clyde.getInterval();
        assertThat(interval).isGreaterThan(0);

        // Verify the correct sprite was retrieved
        verify(spriteStore).getGhostSprite(GhostColor.ORANGE);
    }
}
