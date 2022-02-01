package pl.konstanty;

import pl.konstanty.enums.Direction;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ElevatorSystem {
    private List<Elevator> elevators;

    public ElevatorSystem() {
        elevators = new ArrayList<>();
    }


    public void initialization() {
        Scanner scanner = new Scanner(System.in);
        boolean isInitialized = false;
        System.out.println("####### ELEVATOR SYSTEM v.0.1 #######");
        try {
            while (!isInitialized) {
                System.out.println("Please provide the number of the elevators between 1-16.");
                int n = scanner.nextInt();
                scanner.nextLine();
                if (n >= 1 && n <= 16) {
                    isInitialized = true;
                    for (int i = 0; i < n; i++) {
                        Elevator elevator = new Elevator(i + 1);
                        elevators.add(elevator);
                        System.out.println("Elevator number: " + (i + 1) + " is running!");
                        try {
                            TimeUnit.MILLISECONDS.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Must be a number!");
            initialization();
        }
        System.out.println("All elevators are up and running !");
        displayDashboard();
    }

    /**
     * Returns the status of the program by displaying all states in the console.
     * @return
     */
    public List<Elevator> getElevators() {
        return elevators;
    }

    /**
     * Update existing elevator.
     * @param id Id of an elevator to be updated.
     * @param currentFloor representation of the current floor.
     * @param destinationFloor representation of the destination.
     * @return returns updated list of elevators.
     */
    public List<Elevator> update(int id, int currentFloor, int destinationFloor) {
        try {
            Elevator elevator = new Elevator(id);
            elevator.setCurrentFloor(currentFloor);
            elevator.setDestinationFloor(destinationFloor);
            elevators.set(id - 1, elevator);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("System Error!");
            System.out.println("Please provide elevator number between 1 and " + elevators.size());
        }
        return elevators;
    }

    /**
     * Simulation step, should be used after every Update or Pickup handler to see changes made by program.
     */
    public void step() {
        for (Elevator elevator : elevators) {
            if (elevator.getPickupFloors().contains(elevator.getCurrentFloor())) {
                try {
                    System.out.println("Opening doors and waiting for passengers.");
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Moving to destination floor.");
                elevator.getPickupFloors().poll();
            }
            if (elevator.direction() == Direction.HOLD && !elevator.getPickupFloors().isEmpty()) {
                elevator.setDestinationFloor(elevator.getPickupFloors().poll());
                System.out.println("Moving to pickup floor.");
            }
            switch (elevator.direction()) {
                case MOVING_UP -> elevator.moveUp();
                case MOVING_DOWN -> elevator.moveDown();
                case HOLD -> elevator.removeDestination();
            }
        }

    }

    /**
     * A console interface for the program.
     */
    public void displayDashboard() {
        String selectedOption = "";
        while (!selectedOption.equals("q")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please select one of the options below or type 'Q' to quit.");
            System.out.println("1 - Pickup handler.");
            System.out.println("2 - Elevator update.");
            System.out.println("3 - Simulation step.");
            System.out.println("4 - Check system state.");
            selectedOption = scanner.next().toLowerCase();
            switch (selectedOption) {
                case "1":
                    try {
                        System.out.println("Please enter elevator number between 1 - " + elevators.size() + " to perform a pickup call.");
                        int elevatorId = scanner.nextInt();
                        if (elevatorId >= 1 && elevatorId <= elevators.size()) {
                            scanner.nextLine();
                        } else {
                            System.out.println("Value must be a number between 1 and " + elevators.size() + " !");
                            displayDashboard();
                        }
                        System.out.println("On which floor you want to perform a pickup call?");
                        int pickupFloor = scanner.nextInt();
                        elevators.get(elevatorId - 1).pickup(pickupFloor);
                    } catch (InputMismatchException e) {
                        System.out.println("System error!");
                        System.out.println("Input must be a number!");
                    }
                    break;
                case "2":
                    try {
                        System.out.println("Please enter elevator number between 1 - " + elevators.size() + " to update: ");
                        int id = scanner.nextInt();
                        if (id >= 0 && id <= elevators.size()) {
                            scanner.nextLine();
                        } else {
                            System.out.println("Value must be a number between 1 and " + elevators.size() + " !");
                            displayDashboard();
                        }
                        System.out.println("Enter current floor of the elevator: ");
                        int currentFloor = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter destination floor of the elevator: ");
                        int destination = scanner.nextInt();
                        scanner.nextLine();
                        update(id, currentFloor, destination);
                    } catch (InputMismatchException e) {
                        System.out.println("System error!");
                        System.out.println("Input must be a number!");
                    }
                    break;
                case "3":
                    step();
                    break;
                case "4":
                    System.out.println(getElevators());
                    break;
                case "q":
                    System.out.println("Exiting program...");
                    break;

            }
        }
    }

}
