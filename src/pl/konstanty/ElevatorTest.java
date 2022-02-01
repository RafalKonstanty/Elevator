package pl.konstanty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.konstanty.enums.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorTest {

    public static final int SECOND_FLOOR = 2;
    public static final int BASEMENT_FLOOR = -1;
    public static final int SIXTH_FLOOR = 6;
    private Elevator elevator;

    @BeforeEach
    void createElevatorTest() {
        elevator = new Elevator(1);
    }

    @Test
    public void addNewDestinationTest() {
        //given
//        Elevator elevator = new Elevator(1);
        //when
        elevator.setDestinationFloor(SECOND_FLOOR);
        //then
        assertEquals(SECOND_FLOOR, elevator.getDestinationFloor());
    }

    @Test
    public void addNewPickupTest() {
        //given
//        Elevator elevator = new Elevator(2);
        //when
        elevator.pickup(SIXTH_FLOOR);
        //then
        assertEquals(SIXTH_FLOOR, elevator.getPickupFloor());
    }

    @Test
    public void checkMovingDownTest() {
        //given
//        Elevator elevator = new Elevator(3);
        //when
        elevator.moveDown();
        //then
        assertEquals(BASEMENT_FLOOR, elevator.getCurrentFloor());
    }

    @Test
    public void checkMovingUpTest() {
        //given
//        Elevator elevator = new Elevator(4);
        //when
        elevator.moveUp();
        elevator.moveUp();
        //then
        assertEquals(SECOND_FLOOR, elevator.getCurrentFloor());
    }

    @Test
    public void checkDirectionUpTest() {
        //given
//        Elevator elevator = new Elevator(5);
        //when
        elevator.setDestinationFloor(SIXTH_FLOOR);
        //then
        assertEquals(Direction.MOVING_UP, elevator.direction());
    }

    @Test
    public void checkDirectionDownTest() {
        //given
//        Elevator elevator = new Elevator(6);
        //when
        elevator.setDestinationFloor(BASEMENT_FLOOR);
        //then
        assertEquals(Direction.MOVING_DOWN, elevator.direction());
    }

    @Test
    void checkDirectionHoldTest() {
        //given
//        Elevator elevator = new Elevator(7);
        //when
        elevator.setDestinationFloor(SECOND_FLOOR);
        elevator.moveUp();
        elevator.moveUp();
        //then
        assertEquals(Direction.HOLD, elevator.direction());
    }


}