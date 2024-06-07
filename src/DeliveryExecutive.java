/**
 * This class is used to represent a DeliveryExecutive
 */
class DeliveryExecutive {

    //name of the delivery executive
    private String name;

    //current location of the devlivery executive
    private Location currentLocation;

    public DeliveryExecutive(String name, Location currentLocation) {
        this.name = name;
        this.currentLocation = currentLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    /**
     * @param destination
     * @return time taken by the delivery executive to
     * travel to the destination assuming an average speed of 20km/hr
     *
     */
    public double travelTime(Location destination) {
        double distance = currentLocation.distanceTo(destination);
        return distance / 20;
    }
}