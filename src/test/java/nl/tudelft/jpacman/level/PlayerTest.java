package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for Player class.
 * This test focuses on verifying the behavior of the isAlive() method.
 * Other methods can be tested similarly in the future.
 */
public class PlayerTest {

    private static final PacManSprites SPRITE_STORE = new PacManSprites();
    private PlayerFactory playerFactory;
    private Player player;

    /**
     * Setting up the test environment before each test case.
     * PlayerFactory is initialized with PacManSprites, and Player is instantiated.
     */
    @BeforeEach
    void setUp() {
        playerFactory = new PlayerFactory(SPRITE_STORE);
        player = playerFactory.createPacMan();
    }

    /**
     * Test to check if the player is alive upon creation.
     */
    @Test
    void testIsAliveWhenCreated() {
        // Player should be alive immediately after being created
        assertThat(player.isAlive()).isTrue();
    }

    /**
     * Test to check if the player is not alive after being set to dead.
     */
    @Test
    void testIsAliveAfterDeath() {
        // Simulate the player "dying"
        player.setAlive(false);

        // Player should not be alive anymore
        assertThat(player.isAlive()).isFalse();
    }
}
