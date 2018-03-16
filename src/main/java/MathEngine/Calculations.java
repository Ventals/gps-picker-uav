package MathEngine;

import MathEngine.model.Vector;
import MathEngine.utils.Const;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Vental on 23.04.2017.
 */
public class Calculations {
    private double matrixHeight; //высота матрицы
    private double matrixWidth; // ширина матрицы
    private double matrixFocus; // фокус матрицы

    private double roll; // крен
    private double pitch; // тангаж
    private double yaw; // курс (рыскание)

    private double alt_msl; // высота беспилотника над уровнем моря считываемая из GPS приемника
    private double alt_rel; // высота беспилотника относительно поверхности земли
    private double lat; // координата широты беспилотника
    private double lon; // координата долготы беспилотника

    private double corner_lat; // угол обзора по направлению самолета, betta
    private double corner_lon; // угол обзора перпендикулярно направлению беспилотника, alpha
    private ArrayList<Vector> vectors = new ArrayList<>();

    public Calculations(double matrixHeight, double matrixWidth, double matrixFocus, double roll, double pitch, double yaw, double alt_msl, double alt_rel, double lat, double lon) {
        this.matrixHeight = matrixHeight;
        this.matrixWidth = matrixWidth;
        this.matrixFocus = matrixFocus;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
        this.alt_msl = alt_msl;
        this.alt_rel = alt_rel;
        this.lat = lat;
        this.lon = lon;
        corner_lat = calcCornerSeeking(matrixFocus, matrixHeight);
        corner_lon = calcCornerSeeking(matrixFocus, matrixWidth);
    }

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        Vector vector1_1 = new Vector(calculateVectorLength(Const.LEFT_TOP_TAG), anglesVectors(Const.LEFT_TOP_TAG), Const.LEFT_TOP_TAG);
        Vector vector1_2 = new Vector(calculateVectorLength(Const.LEFT_TOP_TAG), -anglesVectors(Const.LEFT_TOP_TAG) + Math.PI * 2.0, Const.LEFT_TOP_TAG);
        Vector vector2_1 = new Vector(calculateVectorLength(Const.RIGHT_TOP_TAG), anglesVectors(Const.RIGHT_TOP_TAG), Const.RIGHT_TOP_TAG);
        Vector vector2_2 = new Vector(calculateVectorLength(Const.RIGHT_TOP_TAG), -anglesVectors(Const.RIGHT_TOP_TAG) + Math.PI, Const.RIGHT_TOP_TAG);
        Vector vector3_1 = new Vector(calculateVectorLength(Const.LEFT_BOTTOM_TAG), anglesVectors(Const.LEFT_BOTTOM_TAG) + Math.PI * 1.5, Const.LEFT_BOTTOM_TAG);
        Vector vector3_2 = new Vector(calculateVectorLength(Const.LEFT_BOTTOM_TAG), anglesVectors(Const.LEFT_BOTTOM_TAG) - Math.PI * 1.5, Const.LEFT_BOTTOM_TAG);
        Vector vector4_1 = new Vector(calculateVectorLength(Const.RIGHT_BOTTOM_TAG), anglesVectors(Const.RIGHT_BOTTOM_TAG) + Math.PI * 0.5, Const.RIGHT_BOTTOM_TAG);
        Vector vector4_2 = new Vector(calculateVectorLength(Const.RIGHT_BOTTOM_TAG), -anglesVectors(Const.RIGHT_BOTTOM_TAG) + Math.PI * 0.5, Const.RIGHT_BOTTOM_TAG);
        vectors.add(vector1_1);
        vectors.add(vector1_2);
        vectors.add(vector2_1);
        vectors.add(vector2_2);
        vectors.add(vector3_1);
        vectors.add(vector3_2);
        vectors.add(vector4_1);
        vectors.add(vector4_2);


        return points;
    }

    private double calcCornerSeeking(double focus, double matrixSide) {
        return Math.acos(1.0 - (2.0 * matrixSide * matrixSide) / (matrixSide * matrixSide + 4.0 * focus * focus));
    }

    private double calcTriangleHeight(int tag) {
        double height = 0.0;
        switch (tag) {
            case Const.LEFT_TOP_TAG:
                height = alt_rel * Math.tan(corner_lat / 2 + pitch);
                break;
            case Const.RIGHT_TOP_TAG:
                height = alt_rel * Math.tan(corner_lat / 2 - pitch);
                break;
            case Const.LEFT_BOTTOM_TAG:
                height = alt_rel * Math.tan(corner_lon / 2 + roll);
                break;
            case Const.RIGHT_BOTTOM_TAG:
                height = alt_rel * Math.tan(corner_lon / 2 - roll);
                break;
        }

        return height;
    }//1

    private double calcHipotenuza(int tag, double height) {
        double hipotenuza = 0;
        switch (tag) {
            case Const.LEFT_TOP_TAG:
                hipotenuza = height / Math.sin(corner_lon / 2 + pitch);
                break;
            case Const.RIGHT_TOP_TAG:
                hipotenuza = height / Math.sin(corner_lon / 2 - pitch);
                break;
            case Const.LEFT_BOTTOM_TAG:
                hipotenuza = height / Math.sin(corner_lat / 2 + roll);
                break;
            case Const.RIGHT_BOTTOM_TAG:
                hipotenuza = height / Math.sin(corner_lat / 2 - roll);
                break;
        }

        return hipotenuza;
    }//2

    private double secondTriangleCat(int tag) {
        double hipotenuza = calcHipotenuza(tag, calcTriangleHeight(tag));
        double viewingAngles = 0.0;
        switch (tag) {
            case Const.LEFT_TOP_TAG:
                viewingAngles = corner_lat;
                break;
            case Const.RIGHT_TOP_TAG:
                viewingAngles = corner_lat;
                break;
            case Const.LEFT_BOTTOM_TAG:
                viewingAngles = corner_lon;
                break;
            case Const.RIGHT_BOTTOM_TAG:
                viewingAngles = corner_lon;
                break;
        }
        double distanse = hipotenuza * (Math.tan(viewingAngles) / 2);
        return distanse;
        //idea huina
        //sam ti huina
    }//3

    private double calculateVectorLength(int tag) {

        double height = calcTriangleHeight(tag);
        double distanceToPointFromHipotenuza = calcTriangleHeight(tag);


        return Math.sqrt(height * height + distanceToPointFromHipotenuza * distanceToPointFromHipotenuza);
    }//4

    private double anglesVectors(int tag) {
        double value = (secondTriangleCat(tag)) / (calcTriangleHeight(tag));
        return Math.atan(value);
    }// 5

    private double idealDisplacementVector() {
        double a3 = 2 * alt_rel * Math.tan((lon / 2));
        double b3 = 2 * alt_rel * Math.tan((lat / 2));
        double idealVector = Math.sqrt(((a3 * a3) + (b3 * b3))) / 2;
        return idealVector;
    }//6
    boolean t = true;



    private Vector calculateMinusVector(Vector v1) {
        double corner = 0;
        switch (v1.getTag()) {
            case Const.LEFT_TOP_TAG:
                corner = Math.PI / 4.0;
                break;
            case Const.RIGHT_TOP_TAG:
                corner = Math.PI / 4.0 + Math.PI / 2.0;
                break;
            case Const.LEFT_BOTTOM_TAG:
                corner = Math.PI / 4.0 + Math.PI;
                break;
            case Const.RIGHT_BOTTOM_TAG:
                corner = Math.PI / 4.0 + Math.PI * 1.5;
                break;
        }
        Vector idealVector = new Vector(idealDisplacementVector(), corner, v1.getTag());

        double projectionToNormale = v1.getProjectionToNormale() - idealVector.getProjectionToNormale();
        double projectionToHeight = v1.getProjectionToHeight() - idealVector.getProjectionToHeight();
        double angleToNormale = 0;
        switch (v1.getTag()) {
            case Const.LEFT_TOP_TAG:
                angleToNormale = -Math.atan(projectionToHeight/projectionToNormale)+2.0*Math.PI;
                break;
            case Const.RIGHT_TOP_TAG:
                angleToNormale = Math.atan(projectionToHeight/projectionToNormale);
                break;
            case Const.LEFT_BOTTOM_TAG:
                angleToNormale = -Math.atan(projectionToHeight/projectionToNormale)+0.5*Math.PI;
                break;
            case Const.RIGHT_BOTTOM_TAG:
                angleToNormale = -Math.atan(projectionToHeight/projectionToNormale)+1.0*Math.PI;
                break;
        }

        Vector answer = new Vector(Math.sqrt(projectionToNormale*projectionToNormale+projectionToHeight*projectionToHeight), angleToNormale, v1.getTag());

        return answer;
    }
    //7

    private void plusVectors(Vector v1, Vector v2){
        double projectionToNormale = v1.getProjectionToNormale() + v2.getProjectionToNormale();
        double projectionToHeight = v1.getProjectionToHeight() + v2.getProjectionToHeight();
    }




}
