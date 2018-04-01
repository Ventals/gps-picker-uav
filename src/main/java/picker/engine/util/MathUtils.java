package picker.engine.util;

import picker.engine.model.Data;

import static java.lang.Math.*;
import static picker.engine.model.Constant.*;

public class MathUtils {
    public static final double MATRIX_SQRT = sqrt(MATRIX_FOCUS * MATRIX_FOCUS + (MATRIX_WIDTH * MATRIX_WIDTH / 4));
    public static final double MATRIX_ATAN = atan(MATRIX_HEIGHT / (2 * MATRIX_FOCUS));

    public static double[][] getPitchCorrections(Data data) {
        double[][] corrections = getPitchUpCorrections(data);
        boolean needReverse = data.getPitch() < 0;
        if (needReverse) {
            swapCorrections(corrections, 0, 0, 1);
            swapCorrections(corrections, 0, 2, 3);
            swapCorrections(corrections, 1, 0, 1);
            swapCorrections(corrections, 1, 2, 3);
        }
        return corrections;
    }

    private static void swapCorrections(double[][] corrections, int index, int left, int right) {
        double leftValue = corrections[left][index];
        corrections[left][index] = corrections[right][index];
        corrections[right][index] = leftValue;
    }

    private static double[][] getPitchUpCorrections(Data data) {
        double[][] corrections = new double[4][2];
        double angle = data.getPitch();
        double height = data.getH();
        double Gt = height * MATRIX_SQRT;
        double Bt = MATRIX_ATAN;

        corrections[0][0] = Gt / cos(angle + Bt);
        corrections[1][0] = Gt / cos(angle - Bt);
        corrections[2][0] = -(Gt / cos(angle - Bt));
        corrections[3][0] = -(Gt / cos(angle + Bt));

        double cos = cos(angle);
        corrections[0][1] = height * sin(Bt) / (cos * cos);
        corrections[1][1] = height / (cos * cos(Bt) + sin(angle) * sin(Bt));
        corrections[2][1] = corrections[1][1];
        corrections[3][1] = corrections[0][1];
        return corrections;
    }

    public static double[][] getRollCorrections(Data data) {
        double[][] corrections = getRollUpCorrections(data);
        boolean needReverse = data.getPitch() < 0;
        if (needReverse) {
            swapCorrections(corrections, 0, 0, 1);
            swapCorrections(corrections, 0, 2, 3);
            swapCorrections(corrections, 1, 0, 3);
            swapCorrections(corrections, 1, 1, 2);
            corrections[2][0] *= -1;
            corrections[3][0] *= -1;
        }
        return corrections;
    }

    private static double[][] getRollUpCorrections(Data data) {
        double[][] corrections = new double[4][2];
        double height = data.getH();
        double angle = data.getRoll();
        double Gk = height * sqrt(MATRIX_FOCUS * MATRIX_FOCUS + (MATRIX_HEIGHT * MATRIX_HEIGHT / 4));
        double Bk = atan(MATRIX_HEIGHT / 2 * MATRIX_FOCUS);

        corrections[0][0] = height * (sin(Bk) / cos(angle) * cos(angle));
        corrections[1][0] = corrections[0][0];
        corrections[2][0] = height / (cos(angle) * cos(Bk) + sin(angle) * sin(Bk));
        corrections[3][0] = corrections[2][0];

        corrections[0][1] = Gk / cos(angle + Bk);
        corrections[1][1] = -(Gk / cos(angle + Bk));
        corrections[2][1] = -(Gk / cos(angle - Bk));
        corrections[3][1] = Gk / cos(angle - Bk);
        return corrections;
    }

    public static double[] getYawCorrections(Data data) {
        double[] corrections = new double[2];
        double y = ((Double) null);
        double x = ((Double) null);
        corrections[0] = y * sin(data.getYaw());
        corrections[1] = x * cos(data.getYaw());
        return corrections;
    }
}
