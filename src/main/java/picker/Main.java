package picker;

import picker.engine.Controller;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        double[][] result = new Controller().getPhotoCoords();
        for (double[] pair : result){
            for (double item : pair){
                System.out.print((int)item + " ");
            }
            System.out.println();
        }
    }
}