package org.jetbrains.assignment.web;

import org.jetbrains.assignment.enums.Direction;
import org.jetbrains.assignment.model.Location;
import org.jetbrains.assignment.model.Movement;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RobotMovementController {

    private static final Location ROBOT_LOCATION = new Location();

    @PostMapping("/locations")
    public List<Location> recordMovements(@RequestBody List<Movement> movements) {
        List<Location> result = new ArrayList<>();
        result.add(ROBOT_LOCATION.toBuilder().build()); // Add initial location

        if (!CollectionUtils.isEmpty(movements)) {
            for (Movement movement : movements) {
                switch (movement.getDirection()) {
                    case NORTH:
                        ROBOT_LOCATION.setY(ROBOT_LOCATION.getY() + movement.getSteps());
                        break;
                    case SOUTH:
                        ROBOT_LOCATION.setY(ROBOT_LOCATION.getY() - movement.getSteps());
                        break;
                    case EAST:
                        ROBOT_LOCATION.setX(ROBOT_LOCATION.getX() + movement.getSteps());
                        break;
                    case WEST:
                        ROBOT_LOCATION.setX(ROBOT_LOCATION.getX() - movement.getSteps());
                        break;
                }
                result.add(ROBOT_LOCATION.toBuilder().build()); // Add updated location to history
            }

        }


        return result;
    }


    @PostMapping("/moves")
    public ResponseEntity<List<Movement>> calculateMovements(@RequestBody List<Location> locations) {
        List<Movement> movements = new ArrayList<>();

        for (int i = 0; i < locations.size() - 1; i++) {
            Location currentLocation = locations.get(i);
            Location nextLocation = locations.get(i + 1);

            int xDifference = nextLocation.getX() - currentLocation.getX();
            int yDifference = nextLocation.getY() - currentLocation.getY();

            if (xDifference > 0) {
                movements.add(new Movement(Direction.EAST, xDifference));
            } else if (xDifference < 0) {
                movements.add(new Movement(Direction.WEST, -xDifference));
            }

            if (yDifference > 0) {
                movements.add(new Movement(Direction.NORTH, yDifference));
            } else if (yDifference < 0) {
                movements.add(new Movement(Direction.SOUTH, -yDifference));
            }
        }

        return ResponseEntity.ok(movements);
    }
}