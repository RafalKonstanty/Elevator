package pl.konstanty;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ElevatorSystemTest {
    public static final int SECOND_FLOOR = 2;
    public static final int BASEMENT_FLOOR = -1;
    public static final int SIXTH_FLOOR = 6;
    public static final int ELEVATOR_ID = 1;

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
        elevators.get(ELEVATOR_ID).pickup(SIXTH_FLOOR);
        //then
        for (int i = 0; i < SIXTH_FLOOR; i++) {
            elevatorSystem.step();
        }
        assertEquals(SIXTH_FLOOR, elevators.get(ELEVATOR_ID).getCurrentFloor());
    }


}