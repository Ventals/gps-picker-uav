package MathEngine.model;

/**
 * Created by Nick on 23.04.17.
 */
public class Point {
    private double lat; //широта точки
    private double lon; //долгота точки

    public Point(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
