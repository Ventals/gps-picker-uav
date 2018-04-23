package picker.engine.util;

import picker.engine.model.Data;

import static picker.engine.model.Constant.*;
import static java.lang.Math.*;

public class MathUtils {

    private double getGPitch(double height) {
        return height * sqrt(pow(LENS_FOCUS, 2) + pow(FRAME_WIDTH, 2) / 4);
    }

    private double getGRoll(double height) {
        return height * sqrt(pow(LENS_FOCUS, 2) + pow(FRAME_HEIGHT, 2) / 4);
    }

    private double getBetaPitch() {
        return atan(FRAME_WIDTH / (2 * LENS_FOCUS));
    }

    private double getBetaRoll() {
        return atan(FRAME_HEIGHT / (2 * LENS_FOCUS));
    }

    private double calcX1(double x_center, double height, double pitch, double roll, double yaw) {
        double g_pitch = getGPitch(height);
        double beta_pitch = getBetaPitch();
        double beta_roll = getBetaRoll();

        double x_pitch =  pitch > 0 ? g_pitch / cos(pitch + beta_pitch) : g_pitch / cos(pitch - beta_pitch);
        double x_roll = roll > 0? height * (sin(beta_roll) / pow(cos(roll), 2)) : height / (cos(roll) * cos(beta_roll) + sin(roll) * sin(beta_roll));

        return (x_center + x_pitch + x_roll);
    }

    private double calcX2(double x_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = getGPitch(height);
        double beta_pitch = getBetaPitch();
        double beta_roll = getBetaRoll();

        double x_pitch =  pitch > 0 ? g_pitch / cos(pitch - beta_pitch) : g_pitch / cos(pitch + beta_pitch);
        double x_roll = roll > 0 ? height * (sin(beta_roll) / pow(cos(roll), 2)) : height / (cos(roll) * cos(beta_roll) + sin(roll) * sin(beta_roll));

        return (x_center + x_pitch + x_roll);
    }

    private double calcX3(double x_center, double  height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = getGPitch(height);
        double beta_pitch = getBetaPitch();
        double beta_roll = getBetaRoll();

        double x_pitch = pitch > 0 ? -g_pitch / cos(pitch - beta_pitch) : -g_pitch / cos(pitch + beta_pitch);
        double x_roll = roll > 0 ? -height / (cos(roll) * cos(beta_roll) + sin(roll) * sin(beta_roll)) : -height * (sin(beta_roll) / pow(cos(roll), 2));

        return (x_center + x_pitch + x_roll);
    }

    private double calcX4(double x_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = getGPitch(height);
        double beta_pitch = getBetaPitch();
        double beta_roll = getBetaRoll();

        double x_pitch = pitch > 0 ? -g_pitch / cos(pitch + beta_pitch) : -g_pitch / cos(pitch - beta_pitch);
        double x_roll = roll > 0 ? -height / (cos(roll) * cos(beta_roll) + sin(roll) * sin(beta_roll)) : -height * (sin(beta_roll) / pow(cos(roll), 2));

        return (x_center + x_pitch + x_roll);
    }

    private double calcY1(double y_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_roll = getGRoll(height);
        double beta_pitch = getBetaPitch();
        double beta_roll = getBetaRoll();

        double y_pitch = pitch > 0 ? height * (sin(beta_pitch) / pow(cos(pitch), 2)) : height / (cos(pitch) * cos(beta_pitch) + sin(pitch) * sin(beta_pitch));
        double y_roll = roll > 0 ? g_roll / cos(roll + beta_roll) : g_roll / cos(roll - beta_roll);

        return (y_center + y_pitch + y_roll);
    }

    private double calcY2(double y_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_roll = getGRoll(height);
        double beta_pitch = getBetaPitch();
        double beta_roll = getBetaRoll();

        double y_pitch = -height / (cos(pitch) * cos(beta_pitch) + sin(pitch) * sin(beta_pitch));
        double y_roll = roll > 0 ? -g_roll / cos(roll + beta_roll) : -g_roll / cos(roll - beta_roll);

        return (y_center + y_pitch + y_roll);
    }

    private double calcY3(double y_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_roll = getGRoll(height);
        double beta_pitch = getBetaPitch();
        double beta_roll = getBetaRoll();

        double y_pitch = -height / (cos(pitch) * cos(beta_pitch) + sin(pitch) * sin(beta_pitch));
        double y_roll = roll > 0 ? -g_roll / cos(roll - beta_roll) : -g_roll / cos(roll + beta_roll);

        return (y_center + y_pitch + y_roll);
    }

    private double calcY4(double y_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_roll = getGRoll(height);
        double beta_pitch = getBetaPitch();
        double beta_roll = getBetaRoll();

        double y_pitch = pitch > 0 ? height * (sin(beta_pitch) / pow(cos(pitch), 2)) : height / (cos(pitch) * cos(beta_pitch) + sin(pitch) * sin(beta_pitch));
        double y_roll = roll > 0 ? g_roll / cos(roll - beta_roll) : g_roll / cos(roll + beta_roll);

        return (y_center + y_pitch + y_roll);
    }

    public String getResult(Data data){
        double pitch_ = data.getPitch();
        double roll_ = data.getRoll();
        double yaw_ = data.getYaw();
        double height = data.getH();
        double x_0 = data.getLat();
        double y_0 = data.getLon();

        double pitch = pitch_/180 * PI;
        double roll = roll_/180 * PI;
        double yaw = yaw_/180 * PI;

        double x_center = height * tan(roll);
        double y_center = height * tan(pitch);

        double x_1 = calcX1(x_center, height, pitch, roll, yaw);
        double x_2 = calcX2(x_center, height, pitch, roll, yaw);
        double x_3 = calcX3(x_center, height, pitch, roll, yaw);
        double x_4 = calcX4(x_center, height, pitch, roll, yaw);

        double y_1 = calcY1(y_center, height, pitch, roll, yaw);
        double y_2 = calcY2(y_center, height, pitch, roll, yaw);
        double y_3 = calcY3(y_center, height, pitch, roll, yaw);
        double y_4 = calcY4(y_center, height, pitch, roll, yaw);

        //// rotate
        double x_center_yaw = x_center * cos(yaw) + y_center * sin(yaw);
        double y_center_yaw = -x_center * sin(yaw) + y_center * cos(yaw);

        double x1_yaw = x_1 * cos(yaw) + y_1 * sin(yaw);
        double x2_yaw = x_2 * cos(yaw) + y_2 * sin(yaw);
        double x3_yaw = x_3 * cos(yaw) + y_3 * sin(yaw);
        double x4_yaw = x_4 * cos(yaw) + y_4 * sin(yaw);

        double y1_yaw = -x_1 * sin(yaw) + y_1 * cos(yaw);
        double y2_yaw = -x_2 * sin(yaw) + y_2 * cos(yaw);
        double y3_yaw = -x_3 * sin(yaw) + y_3 * cos(yaw);
        double y4_yaw = -x_4 * sin(yaw) + y_4 * cos(yaw);

        //// to geolocation
        double x_center_geo = ConversionUtils.deltaMeterToLon(0, x_center_yaw, x_0) + x_0;
        double y_center_geo = ConversionUtils.deltaMeterToLon(0, y_center_yaw, y_0) + y_0;

        double x1_geo = ConversionUtils.deltaMeterToLon(0, x1_yaw, x_0) + x_0;
        double x2_geo = ConversionUtils.deltaMeterToLon(0, x2_yaw, x_0) + x_0;
        double x3_geo = ConversionUtils.deltaMeterToLon(0, x3_yaw, x_0) + x_0;
        double x4_geo = ConversionUtils.deltaMeterToLon(0, x4_yaw, x_0) + x_0;

        double y1_geo = ConversionUtils.deltaMeterToLat(0, y1_yaw) + y_0;
        double y2_geo = ConversionUtils.deltaMeterToLat(0, y2_yaw) + y_0;
        double y3_geo = ConversionUtils.deltaMeterToLat(0, y3_yaw) + y_0;
        double y4_geo = ConversionUtils.deltaMeterToLat(0, y4_yaw) + y_0;

        // return y_center_geo, x_center_geo, y1_geo, x1_geo, y2_geo, x2_geo, y3_geo, x3_geo, y4_geo, x4_geo
        String result = String.format("%s,%s\n%s,%s\n%s,%s\n%s,%s\n%s,%s\n", y_center_geo, x_center_geo, y1_geo, x1_geo, y2_geo, x2_geo, y3_geo, x3_geo, y4_geo, x4_geo);
        System.out.println(result);
        return result;
    }
}
