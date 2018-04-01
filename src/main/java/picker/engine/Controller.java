package picker.engine;

import picker.engine.model.Constant;
import picker.engine.model.Data;
import picker.engine.util.MathUtils;
import picker.module.ParseFile;

import java.io.IOException;

public class Controller {
    public double[][] getPhotoCoords () throws IOException {
        Data data = new ParseFile("input.csv").getParsedData(1);
        double[] centralCoords = getCentralCoords(data);
        for (double item : centralCoords) {
            System.out.print(item + " ");
        }
        System.out.println("\n ^ central ------");
        double[][] pitchCorrection = MathUtils.getPitchCorrections(data);
        for (double[] pair : pitchCorrection){
            for (double item : pair){
                System.out.print(item + " ");
            }
            System.out.println("\n^ pitch ------");
        }
        double[][] rollCorrection = MathUtils.getRollCorrections(data);
        for (double[] pair : pitchCorrection){
            for (double item : pair){
                System.out.print(item + " ");
            }
            System.out.println("\n^ roll ------");
        }
        double[][] result = new double[4][2];
        for (int i = 0; i < rollCorrection.length; i++){
            double x = ( centralCoords[0] + pitchCorrection[i][0] + rollCorrection[i][0] ) * Math.cos(data.getYaw());
            double y = ( centralCoords[1] + pitchCorrection[i][1] + rollCorrection[i][1] ) * Math.sin(data.getYaw());
            result[i] = new double[]{x / Constant.ONE_LONGITUDE + data.getLat(),
                                     y / Constant.ONE_LATITUDE + data.getLon()};
        }
        return result;
    }

    private double[] getCentralCoords (Data data) {
        return new double[]{
                data.getH() * Math.sin(data.getRoll()),
                data.getH() * Math.sin(data.getPitch())
        };
    }
}
