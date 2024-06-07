# Best Route

This project is a simple Java application to help a delivery executive optimize his delivery route to complete a batch of orders in the shortest possible time. 
The orders involve picking up food from restaurants and delivering it to consumers.

## Features

- Calculates the optimal delivery route considering multiple orders.
- Uses the Haversine formula to calculate travel distances between geo-locations.
- Considers the preparation time of orders at restaurants.
- Prints the total delivery time and the step-by-step delivery path.

## Classes

### `Location`

Represents a geo-location with a name, latitude, and longitude.

- `String getName()`
- `double getLatitude()`
- `double getLongitude()`
- `double distanceTo(Location other)`

### `Order`

Represents an order with a restaurant location, consumer location, and preparation time.

- `Location getRestaurant()`
- `Location getConsumer()`
- `double getPreparationTime()`

### `DeliveryExecutive`

Represents a delivery executive with a name and current location.

- `Location getCurrentLocation()`
- `void setCurrentLocation(Location location)`
- `double travelTime(Location destination)`

### `DeliveryManager`

Manages the delivery process for the executive and calculates the optimal route.

- `void addOrder(Order order)`
- `double calculateTotalTime()`
- `void executeDelivery()`

## Implementation Details

- For finding the best route for delivery, we are iterating over all possible valid routes for delivery.
- We are considering the fact that the order for a consumer needs to be picked up before the delivery, to find the valid routes.
- We are considering an average speed of 20km/hr for calculating time to travel between two locations.
- We are considering the preparation time for a restaurant before picking up the order.

## Assumptions

- Average speed of 20km/hr.
- A delivery partner can be assigned more than 2 orders at a time.
- We can pick up multiple orders before delivering to ensure fastest delivery.

## Limitations

- Iterating over all possible valid routes works for a small number of orders 
but as the number of order increases the time required to calculate the best route 
increases significantly.