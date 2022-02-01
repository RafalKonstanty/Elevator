package pl.konstanty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class ElevatorSystemTest {
    public static final int SECOND_FLOOR = 2;
    public static final int SIXTH_FLOOR = 6;
    public static final int ELEVATOR_ID_ONE = 1;
    public static final int ELEVATOR_ID_TWO = 2;

    private ElevatorSystem elevatorSystem;
    private List<Elevator> elevators;

    @BeforeEach
    public void testInitialization() {
        elevatorSystem = new ElevatorSystem();
        elevators = elevatorSystem.getElevators();
        for (int i = 0; i < 16; i++) {
            Elevator elevator = new Elevator(i + 1);
            elevators.add(elevator);
        }
    }

    @Test
    void goToFloorWhenOnlyPickupOccursTest() {
        //when
        elevators.get(ELEVATOR_ID_ONE).pickup(SIXTH_FLOOR);
        for (int i = 0; i < SIXTH_FLOOR; i++) {
            elevatorSystem.step();
        }
        //then
        assertEquals(SIXTH_FLOOR, elevators.get(ELEVATOR_ID_ONE).getCurrentFloor());
    }

    @Test
    void pickupHandlerOnMultipleElevatorsTest() {
        //when
        elevators.get(ELEVATOR_ID_ONE).pickup(SECOND_FLOOR);
        elevators.get(ELEVATOR_ID_TWO).pickup(SIXTH_FLOOR);
        for (int i = 0; i < SIXTH_FLOOR; i++) {
            elevatorSystem.step();
        }
        //then
        assertEquals(SECOND_FLOOR, elevators.get(ELEVATOR_ID_ONE).getCurrentFloor());
        assertEquals(SIXTH_FLOOR, elevators.get(ELEVATOR_ID_TWO).getCurrentFloor());
    }

    @Test
    void checkIfPickedWhenMovingToDestinationFromGroundFloorTest() {
        //when
        String message = "";
        elevators.get(ELEVATOR_ID_ONE).setDestinationFloor(SIXTH_FLOOR);
        elevators.get(ELEVATOR_ID_ONE).pickup(SECOND_FLOOR);
        for (int i = 0; i < SIXTH_FLOOR; i++) {
            elevatorSystem.step();
        }
        //then
        assertNull(elevators.get(ELEVATOR_ID_ONE).getPickupFloors().poll());
    }

    @Test
    void checkIfDestinationIsReachedTest() {
        //when
        elevators.get(ELEVATOR_ID_ONE).setDestinationFloor(SIXTH_FLOOR);
        for (int i = 0; i < SIXTH_FLOOR; i++) {
            elevatorSystem.step();
        }
        //then
        assertEquals(SIXTH_FLOOR, elevators.get(ELEVATOR_ID_ONE).getCurrentFloor());
    }

    @Test
    void checkIfMultipleElevatorsDestinationIsReachedTest(){
        //when
        elevators.get(ELEVATOR_ID_ONE).setDestinationFloor(SECOND_FLOOR);
        elevators.get(ELEVATOR_ID_TWO).setDestinationFloor(SIXTH_FLOOR);
        for (int i = 0; i < SIXTH_FLOOR; i++) {
            elevatorSystem.step();
        }
        //then
        assertEquals(SECOND_FLOOR, elevators.get(ELEVATOR_ID_ONE).getCurrentFloor());
        assertEquals(SIXTH_FLOOR, elevators.get(ELEVATOR_ID_TWO).getCurrentFloor());
    }


}