package pl.konstanty.interfaces;

import pl.konstanty.enums.Direction;

public interface ElevatorControlSystem {

    void setDestinationFloor(int destinationFloor);

    Direction direction();

    void moveUp();

    void moveDown();

    void pickup(int pickupFloor);
}
