package picker.engine.util;

import java.text.MessageFormat;

import static picker.engine.model.Constant.*;
import static java.lang.Math.*;

public class MathUtils {
    private double get_g_pitch(double height) {
        return height * sqrt(pow(LENS_FOCUS, 2) + pow(FRAME_WIDTH, 2) / 4);
    }

    private double get_g_roll(double height) {
        return height * sqrt(pow(LENS_FOCUS, 2) + pow(FRAME_HEIGHT, 2) / 4);
    }




    private double get_beta_pitch() {
        return atan(FRAME_WIDTH / (2 * LENS_FOCUS));
    }




    private double get_beta_roll() {
        return atan(FRAME_HEIGHT / (2 * LENS_FOCUS));
    }

    private double calc_x1(double x_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = get_g_pitch(height);
        double g_roll = get_g_roll(height);
        double beta_pitch = get_beta_pitch();
        double beta_roll = get_beta_roll();

        double x_pitch =  pitch > 0 ? g_pitch / cos(pitch + beta_pitch) : g_pitch / cos(pitch - beta_pitch);
        double x_roll = roll > 0? height * (sin(beta_roll) / pow(cos(roll), 2)) : height / (cos(roll) * cos(beta_roll) + sin(roll) * sin(beta_roll));

        return (x_center + x_pitch + x_roll);
    }




    private double calc_x2(double x_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = get_g_pitch(height);
        double g_roll = get_g_roll(height);
        double beta_pitch = get_beta_pitch();
        double beta_roll = get_beta_roll();

        double x_pitch =  pitch > 0 ? g_pitch / cos(pitch - beta_pitch) : g_pitch / cos(pitch + beta_pitch);
        double x_roll = roll > 0 ? height * (sin(beta_roll) / pow(cos(roll), 2)) : height / (cos(roll) * cos(beta_roll) + sin(roll) * sin(beta_roll));

        return (x_center + x_pitch + x_roll);
    }




    private double calc_x3(double x_center, double  height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = get_g_pitch(height);
        double g_roll = get_g_roll(height);
        double beta_pitch = get_beta_pitch();
        double beta_roll = get_beta_roll();

        double x_pitch = pitch > 0 ? -g_pitch / cos(pitch - beta_pitch) : -g_pitch / cos(pitch + beta_pitch);
        double x_roll = roll > 0 ? -height / (cos(roll) * cos(beta_roll) + sin(roll) * sin(beta_roll)) : -height * (sin(beta_roll) / pow(cos(roll), 2));

        return (x_center + x_pitch + x_roll);
    }




    private double calc_x4(double x_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = get_g_pitch(height);
        double g_roll = get_g_roll(height);
        double beta_pitch = get_beta_pitch();
        double beta_roll = get_beta_roll();

        double x_pitch = pitch > 0 ? -g_pitch / cos(pitch + beta_pitch) : -g_pitch / cos(pitch - beta_pitch);
        double x_roll = roll > 0 ? -height / (cos(roll) * cos(beta_roll) + sin(roll) * sin(beta_roll)) : -height * (sin(beta_roll) / pow(cos(roll), 2));

        return (x_center + x_pitch + x_roll);
    }



    private double calc_y1(double y_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = get_g_pitch(height);
        double g_roll = get_g_roll(height);
        double beta_pitch = get_beta_pitch();
        double beta_roll = get_beta_roll();

        double y_pitch = pitch > 0 ? height * (sin(beta_pitch) / pow(cos(pitch), 2)) : height / (cos(pitch) * cos(beta_pitch) + sin(pitch) * sin(beta_pitch));
        double y_roll = roll > 0 ? g_roll / cos(roll + beta_roll) : g_roll / cos(roll - beta_roll);

        return (y_center + y_pitch + y_roll);
    }




    private double calc_y2(double y_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = get_g_pitch(height);
        double g_roll = get_g_roll(height);
        double beta_pitch = get_beta_pitch();
        double beta_roll = get_beta_roll();

        double y_pitch = -height / (cos(pitch) * cos(beta_pitch) + sin(pitch) * sin(beta_pitch));
        double y_roll = roll > 0 ? -g_roll / cos(roll + beta_roll) : -g_roll / cos(roll - beta_roll);

        return (y_center + y_pitch + y_roll);
    }




    private double calc_y3(double y_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = get_g_pitch(height);
        double g_roll = get_g_roll(height);
        double beta_pitch = get_beta_pitch();
        double beta_roll = get_beta_roll();

        double y_pitch = -height / (cos(pitch) * cos(beta_pitch) + sin(pitch) * sin(beta_pitch));
        double y_roll = roll > 0 ? -g_roll / cos(roll - beta_roll) : -g_roll / cos(roll + beta_roll);

        return (y_center + y_pitch + y_roll);
    }




    private double calc_y4(double y_center, double height, double pitch, double roll, double yaw) {
        // local constants
        double g_pitch = get_g_pitch(height);
        double g_roll = get_g_roll(height);
        double beta_pitch = get_beta_pitch();
        double beta_roll = get_beta_roll();

        double y_pitch = pitch > 0 ? height * (sin(beta_pitch) / pow(cos(pitch), 2)) : height / (cos(pitch) * cos(beta_pitch) + sin(pitch) * sin(beta_pitch));
        double y_roll = roll > 0 ? g_roll / cos(roll - beta_roll) : g_roll / cos(roll + beta_roll);

        return (y_center + y_pitch + y_roll);
    }




    private double delta_lat_2_meter(double lat_1,double  lat_2) {
        double mid = 111134.861111; // meters in degree
        return (lat_2 - lat_1) * mid;
    }




    private double delta_long_2_meter(double long_1, double long_2) {
        double mid = 111321.377778 * cos(long_1);
        return (long_2 - long_1) * mid;
    }





    private double delta_meter_2_lat(double m_1, double m_2) {
        double dim = 1 / 111134.861111; // meters in degree
        return (m_2 - m_1) *dim;
    }




    private double delta_meter_2_long(double m_1, double m_2, double longe) {
        double dim = 1 / (111321.377778 * cos(longe));
        return (m_2 - m_1) * dim;
    }





    public MathUtils(double x_0, double y_0, double height, double pitch_, double roll_, double yaw_) {

        // pitch = pitch_
        // roll = roll_
        // yaw = yaw_
        double pitch = pitch_/180 * PI;
        double roll = roll_/180 * PI;
        double yaw = yaw_/180 * PI;

        double x_center = height * tan(roll);
        double y_center = height * tan(pitch);

        double x_1 = calc_x1(x_center, height, pitch, roll, yaw);
        double x_2 = calc_x2(x_center, height, pitch, roll, yaw);
        double x_3 = calc_x3(x_center, height, pitch, roll, yaw);
        double x_4 = calc_x4(x_center, height, pitch, roll, yaw);

        double y_1 = calc_y1(y_center, height, pitch, roll, yaw);
        double y_2 = calc_y2(y_center, height, pitch, roll, yaw);
        double y_3 = calc_y3(y_center, height, pitch, roll, yaw);
        double y_4 = calc_y4(y_center, height, pitch, roll, yaw);

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
        double x_center_geo = delta_meter_2_long(0, x_center_yaw, x_0) + x_0;
        double y_center_geo = delta_meter_2_long(0, y_center_yaw, y_0) + y_0;

        double x1_geo = delta_meter_2_long(0, x1_yaw, x_0) + x_0;
        double x2_geo = delta_meter_2_long(0, x2_yaw, x_0) + x_0;
        double x3_geo = delta_meter_2_long(0, x3_yaw, x_0) + x_0;
        double x4_geo = delta_meter_2_long(0, x4_yaw, x_0) + x_0;

        double y1_geo = delta_meter_2_lat(0, y1_yaw) + y_0;
        double y2_geo = delta_meter_2_lat(0, y2_yaw) + y_0;
        double y3_geo = delta_meter_2_lat(0, y3_yaw) + y_0;
        double y4_geo = delta_meter_2_lat(0, y4_yaw) + y_0;

        // return y_center_geo, x_center_geo, y1_geo, x1_geo, y2_geo, x2_geo, y3_geo, x3_geo, y4_geo, x4_geo

        System.out.println(String.format("%s,%s\n%s,%s\n%s,%s\n%s,%s\n%s,%s\n", y_center_geo, x_center_geo, y1_geo, x1_geo, y2_geo, x2_geo, y3_geo, x3_geo, y4_geo, x4_geo));

    }


}
