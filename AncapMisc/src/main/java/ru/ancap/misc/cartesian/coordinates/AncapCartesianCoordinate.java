package ru.ancap.misc.cartesian.coordinates;

public abstract class AncapCartesianCoordinate {

    private double coordinate;

    public AncapCartesianCoordinate(double coordinate) {
        this.coordinate = coordinate;
    }

    public AncapCartesianCoordinate(AncapCartesianCoordinate coordinate) {
        this(coordinate.getCoordinate());
    }

    public boolean roundEquals(AncapCartesianCoordinate coordinate) {
        return this.getRoundCoordinate() == coordinate.getRoundCoordinate();
    }

    protected int getRoundCoordinate() {
        return ((int) this.getCoordinate());
    }

    protected double getCoordinate() {
        return this.coordinate;
    }

    @Override
    public String toString() {
        return String.valueOf(coordinate);
    }
}
