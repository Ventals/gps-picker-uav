package picker.module;

import org.junit.Test;
import picker.engine.model.Data;

import java.io.IOException;

import static org.junit.Assert.*;

public class ParseFileTest {

    private static final double FROM_VOID = 10_000_000;
    private static final int MILLIMETERS_IN_METERS = 1000;
    private static final int RAD = 180;
    private static final double DELTA = 0.0001;
    private ParseFile file = new ParseFile("input.csv");

    @Test
    public void parsesFileCorrectly() throws IOException {
        Data data = file.getParsedData(2);
        System.out.println("data = " + data);
        double exLat = 474315877. / FROM_VOID;
        double exLon = 379583163. / FROM_VOID;
        double exHeight = 347.33 * MILLIMETERS_IN_METERS;
        double exRoll = 5.33 / RAD;
        double exPitch = 1.84 / RAD;
        double exYaw = 103.2 / RAD;
        assertEquals("lon", exLon, data.getLon(), DELTA);
        assertEquals("lat", exLat, data.getLat(), DELTA);
        assertEquals("height", exHeight, data.getH(), DELTA);
        assertEquals("roll", exRoll, data.getRoll(), DELTA);
        assertEquals("pitch", exPitch, data.getPitch(), DELTA);
        assertEquals("yaw", exYaw, data.getYaw(), DELTA);
    }
}