package pl.konstanty;

import pl.konstanty.enums.Direction;
import pl.konstanty.interfaces.ElevatorControlSystem;

import java.util.LinkedList;
import java.util.Queue;

import static pl.konstanty.enums.Direction.MOVING_DOWN;
import static pl.konstanty.enums.Direction.MOVING_UP;

public class Elevator implements ElevatorControlSystem {

    private int elevatorId;
    private int currentFloor;
    private Direction direction;
    private Queue<Integer> destinationFloors;
    private Queue<Integer> pickupFloors;

    public Elevator(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 0;
        this.destinationFloors = new LinkedList<>();
        this.pickupFloors = new LinkedList<>();
        this.direction = Direction.HOLD;
    }

    public Queue<Integer> getPickupFloors() {
        return pickupFloors;
    }

    public Queue<Integer> getDestinationFloors() {
        return destinationFloors;
    }

    /**
     * Remove the head of the destination queue.
     */
    public void removeDestination() {
        this.destinationFloors.poll();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public void pickup(int pickupFloor) {
        pickupFloors.add(pickupFloor);
    }

    @Override
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloors.add(destinationFloor);
    }

    @Override
    public Direction direction() {
        if (destinationFloors.size() > 0) {
            if (currentFloor < destinationFloors.peek()) {
                return MOVING_UP;
            } else if (currentFloor > destinationFloors.peek()) {
                return MOVING_DOWN;
            }
        }
        return Direction.HOLD;
    }

    @Override
    public void moveUp() {
        this.currentFloor++;
    }

    @Override
    public void moveDown() {
        this.currentFloor--;
    }

    @Override
    public String toString() {
        return
                "{Elevator number: " + elevatorId +
                " | currentFloor: " + currentFloor +
                " | destinationFloor: " + destinationFloors +
                " | pickupFloors: " + pickupFloors +
                " | elevator direction: " + direction() +
                " } \n";
    }
}
