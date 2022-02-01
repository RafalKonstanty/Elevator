# Simple console elevator design system.

## Elevator
Is defined by: 
- (int) Id number, 
- (int) current floor,
- (queue) destination floor,
- (queue) pickup floors,
- (enum) direction.

The application asks for the number of elevators to be created.
After starting up all the elevators it provide console interface to the user.
There you can choose between one of four options:
- Pick handler,
- Elevator update,
- Simulation step,
- Check system step.
or pressing 'q' to exit the program.

## Pickup handler
Asks you to choose one of the elevators and provide a floor on which pickup occured.
(After every pickup handler you should use "4. Check system state" to see the changes)

## Elevator update
Asks you to choose one of the elevators, @current floor you want to set for that elevator and @destination floor.
(After every update you should use "4. Check system state" to see the changes)

## Simulation step
Executes single simulation step on all elevators.
(After every simulation steop you should use "4. Check system state" to see the changes)

## Check system state
Returns the list of all elevators and their state. After any update, pickup handling or simulation step you can check here what happend.

### Pickup handling is done like this:
- when an elevator is on a floor 1st and someone want's to go up for e.g. to the floor 5th, if there are pickups on the way the elevator will stop (Thread.sleep(1000)) to pickup passenger and continue it's way to the destination floor. 
- rest of the pickups that weren't on the elevator way will be handled later.
- if the elevator is IDLE (MOVING.HOLD) and pickup occured it will set that floor as new destination.
