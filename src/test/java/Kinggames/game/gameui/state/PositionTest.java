package Kinggames.game.gameui.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    Position position;
    @BeforeEach
    void init() {
        position = new Position(4, 4);
    }

    void assertest(int expect, int expect2, Position position) {
        assertAll("position",
                () -> assertEquals(expect, position.row()),
                () -> assertEquals(expect2, position.col())
        );
    }




    @Test
    void getUp() {
        assertest(3,4 ,position.getUp());
    }

    @Test
    void getRight() {
        assertest(4,5 ,position.getRight());
    }

    @Test
    void getDown() {
        assertest(4,3 ,position.getLeft());
    }

    @Test
    void getLeft() {

        assertest(4,3 ,position.getLeft());
    }



    @Test
    void setRight() {
        position.setRight();
        assertest(4,5 ,position);
    }

    @Test
    void setDown() {
        position.setDown();
        assertest(5,4 ,position);
    }

    @Test
    void setLeft() {
        position.setLeft();
        assertest(4,3 ,position);
    }




    @Test
    void testGetPosition() {
        position.getPosition(Direction.UP);
        assertest(3,4 ,position.getPosition(Direction.UP));
    }

    @Test
    void testGetPositiontrue() {
        position.getPositiontrue();
        assertest(4,4 ,position.getPositiontrue());
    }




    @Test
    void testSetUp() {
        position.setUp();
        assertest(3,4 ,position);
    }

    @Test
    void testSetRight() {
        position.setRight();
        assertest(4,5 ,position);
    }

    @Test
    void testSetDown() {
        position.setDown();
        assertest(5,4 ,position);
    }

    @Test
    void testSetLeft() {
        position.setLeft();
        assertest(4 ,3,position);
    }

    @Test
    void testEquals() {
        assertTrue(position.equals(position));
        assertTrue(position.equals(new Position(position.row(), position.col())));
        assertFalse(position.equals(new Position(Integer.MIN_VALUE, Integer.MAX_VALUE)));
        assertFalse(position.equals(null));
        assertFalse(position.equals("none"));
    }

    @Test
    void testHashCode() {
        assertTrue(position.hashCode() == position.hashCode());
        assertTrue(position.hashCode() == new Position(position.row(), position.col()).hashCode());
    }



    @Test
    void testToString() {
        assertEquals("(4,4)", position.toString());
    }
}