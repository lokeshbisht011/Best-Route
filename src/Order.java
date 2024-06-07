class Order {
    //Location of the restaurant
    private Location restaurant;

    // Location of the consumer
    private Location consumer;

    // Preparation time for the order
    private double preparationTime;

    public Order(Location restaurant, Location consumer, double preparationTime) {
        this.restaurant = restaurant;
        this.consumer = consumer;
        this.preparationTime = preparationTime;
    }

    public Location getRestaurant() {
        return restaurant;
    }

    public Location getConsumer() {
        return consumer;
    }

    public double getPreparationTime() {
        return preparationTime;
    }
}