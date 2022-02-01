package pl.konstanty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ElevatorSystemTest {
    public static final int SECOND_FLOOR = 2;
    public static final int BASEMENT_FLOOR = -1;
    public static final int SIXTH_FLOOR = 6;

    private ElevatorSystem elevatorSystem;

    @BeforeEach
    public void testInitialization() {
        elevatorSystem = new ElevatorSystem();
    }

    @Test
    void goToOneFloorTest() {

    }

}