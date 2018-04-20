package picker;

import picker.engine.Controller;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        //controller.OnePhotoStart(1);
        controller.SetPhotoStart();
    }
}