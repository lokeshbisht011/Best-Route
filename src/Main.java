public class Main {
    public static void main(String[] args) {
        Location koramangala = new Location("Koramangala", 12.934533, 77.626579);
        Location restaurant1 = new Location("Restaurant 1", 12.935332, 77.624548);
        Location restaurant2 = new Location("Restaurant 2", 12.936543, 77.622345);
        Location consumer1 = new Location("Consumer 1", 12.938764, 77.620123);
        Location consumer2 = new Location("Consumer 2", 12.937123, 77.621567);

        Order order1 = new Order(restaurant1, consumer1, 50);
        Order order2 = new Order(restaurant2, consumer2, 5);

        DeliveryExecutive deliveryExecutive = new DeliveryExecutive("Aman", koramangala);
        DeliveryManager deliveryManager = new DeliveryManager(deliveryExecutive);

        deliveryManager.addOrder(order1);
        deliveryManager.addOrder(order2);

        deliveryManager.findBestRouteForDelivery();
    }
}