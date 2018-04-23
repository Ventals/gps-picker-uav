package picker.engine.util;

import static java.lang.Math.cos;

public class ConversionUtils {
    public static double deltaMeterToLat(double m_1, double m_2) {
        double dim = 1 / 111134.861111; // meters in degree
        return (m_2 - m_1) *dim;
    }

    public static double deltaMeterToLon(double m_1, double m_2, double longe) {
        double dim = 1 / (111321.377778 * cos(longe));
        return (m_2 - m_1) * dim;
    }
}
