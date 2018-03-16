package MathEngine.model;

import MathEngine.utils.Const;

/**
 * Created by Nick on 23.04.17.
 */
public class Vector {
    private double length;
    private double cornerFromNormale;
    private double projectionToNormale;
    private double projectionToHeight;
    private int tag;

    public Vector(double length, double cornerFromNormale, int tag) {
        this.length = length;
        this.cornerFromNormale = cornerFromNormale;
        this.tag = tag;
//        switch (tag) {
//            case Const.LEFT_TOP_TAG:
//                cornerFromNormale = Math.PI * 2 - cornerFromNormale;
//                break;
//            case Const.RIGHT_TOP_TAG:
//                cornerFromNormale += 0;
//                break;
//            case Const.LEFT_BOTTOM_TAG:
//                cornerFromNormale = Math.PI - cornerFromNormale;
//                break;
//            case Const.RIGHT_BOTTOM_TAG:
//                cornerFromNormale = Math.PI + cornerFromNormale;
//                break;
//        }
        projectionToNormale = length * Math.cos(cornerFromNormale);
        projectionToHeight = length * Math.sin(cornerFromNormale);
    }

    public double getLength() {
        return length;
    }

    public double getCornerFromNormale() {
        return cornerFromNormale;
    }

    public double getProjectionToNormale() {
        return projectionToNormale;
    }

    public double getProjectionToHeight() {
        return projectionToHeight;
    }

    public int getTag() {
        return tag;
    }
}
