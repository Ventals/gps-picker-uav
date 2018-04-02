package picker;

import picker.engine.Controller;
import picker.engine.util.MathUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new MathUtils(37.9570196, 47.4317996, 348.78, 0.11, 1.23, 109);
        //double[][] result = new Controller().getPhotoCoords();
        //for (double[] pair : result){
          //  for (double item : pair){
            //    System.out.print(item + ",");
            //}
            //System.out.println();
        //}
    }
}