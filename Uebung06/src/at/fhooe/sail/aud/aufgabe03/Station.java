package at.fhooe.sail.aud.aufgabe03;

import at.fhooe.sail.aud.aufgabe01.MyVertex;

public class Station implements MyVertex {
    protected String name;

    public Station(String stationName) {
        name = stationName;
    }

    public String toString() {
        return name;
    }
}
