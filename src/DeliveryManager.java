import java.util.ArrayList;
import java.util.List;

class DeliveryManager {
    private DeliveryExecutive executive;
    private List<Order> orders;
    private List<String> deliveryPath;

    public DeliveryManager(DeliveryExecutive executive) {
        this.executive = executive;
        this.orders = new ArrayList<>();
        this.deliveryPath = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * @param route
     * @return total time taken to go through the route
     */
    private double calculateRouteTime(List<Location> route) {
        double totalTime = 0;
        double currentTime = 0;
        Location previousLocation = executive.getCurrentLocation();

        for (Location location : route) {
            double travelTime = previousLocation.distanceTo(location) / 20;
            currentTime += travelTime;
            totalTime += travelTime;

            //handle the scenario where delivery executive has to wait for the preparation of the order
            for (Order order : orders) {
                if (order.getRestaurant().equals(location) && currentTime < order.getPreparationTime()) {
                    double waitTime = order.getPreparationTime() - currentTime;
                    totalTime += waitTime;
                    currentTime += waitTime;
                }
            }

            previousLocation = location;
        }

        return totalTime;
    }

    /**
     * @return list of all the valid routes considering
     * all the constraints
     */
    private List<Location> generateValidRoutes() {
        List<Location> restaurants = new ArrayList<>();
        List<Location> consumers = new ArrayList<>();

        for (Order order : orders) {
            restaurants.add(order.getRestaurant());
            consumers.add(order.getConsumer());
        }

        List<Location> allLocations = new ArrayList<>(restaurants);
        allLocations.addAll(consumers);

        List<List<Location>> allRoutes = new ArrayList<>();
        permute(restaurants, consumers, new ArrayList<>(), allRoutes);

        List<Location> bestRoute = null;
        double bestTime = Double.MAX_VALUE;

        for (List<Location> route : allRoutes) {
            double time = calculateRouteTime(route);
            if (time < bestTime) {
                bestTime = time;
                bestRoute = route;
            }
        }

        return bestRoute;
    }

    /**
     * @param restaurants
     * @param consumers
     * @param currentRoute
     * @param allRoutes
     * recursive method to get all the possible routes for delivery
     */
    private void permute(List<Location> restaurants, List<Location> consumers, List<Location> currentRoute, List<List<Location>> allRoutes) {
        if (currentRoute.size() == restaurants.size() + consumers.size()) {
            allRoutes.add(new ArrayList<>(currentRoute));
            return;
        }

        for (Location restaurant : restaurants) {
            if (!currentRoute.contains(restaurant)) {
                currentRoute.add(restaurant);
                permute(restaurants, consumers, currentRoute, allRoutes);
                currentRoute.removeLast();
            }
        }

        for (Location consumer : consumers) {
            Order correspondingOrder = findOrderByConsumer(consumer);
            assert correspondingOrder != null;
            if (currentRoute.contains(correspondingOrder.getRestaurant()) && !currentRoute.contains(consumer)) {
                currentRoute.add(consumer);
                permute(restaurants, consumers, currentRoute, allRoutes);
                currentRoute.removeLast();
            }
        }
    }

    /**
     * @param consumer
     * @return order of the consumer
     */
    private Order findOrderByConsumer(Location consumer) {
        for (Order order : orders) {
            if (order.getConsumer().equals(consumer)) {
                return order;
            }
        }
        return null;
    }


    /**
     * @return total time taken to complete the delivery
     * taking the most optimum route
     */
    public double calculateTotalTime() {
        List<Location> optimalRoute = generateValidRoutes();
        double totalTime = calculateRouteTime(optimalRoute);

        Location previousLocation = executive.getCurrentLocation();
        for (Location location : optimalRoute) {
            if (location.equals(previousLocation)) {
                continue;
            }

            boolean isConsumer = false;
            for (Order order : orders) {
                if (order.getConsumer().equals(location)) {
                    isConsumer = true;
                    break;
                }
            }

            if (isConsumer) {
                deliveryPath.add("Deliver to " + location.getName());
            } else {
                deliveryPath.add("Travel to " + location.getName());
            }

            previousLocation = location;
        }

        return totalTime;
    }

    /**
     * finds the best route for delivery and
     * prints the route and total time taken for the delivery
     */
    public void findBestRouteForDelivery() {
        double totalTime = calculateTotalTime();
        System.out.println("Delivery Path: ");
        for (String step : deliveryPath) {
            System.out.println(step);
        }
        System.out.printf("Total time to complete delivery: %.2f hours\n", totalTime);
    }
}