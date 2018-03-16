package MathEngine;

import java.util.ArrayList;

/**
 * Created by Vental on 23.04.2017.
 */
public class Controller {
    public static ArrayList example = new ArrayList();



    public static int setEx(){
        ArrayList d = new ArrayList();
     String [] t = {"", ""};
        for (int i = 1; i < t.length; i++) {
          example.add(new Calculations().getPoints());
        }
        for(int i = 0; i< example.size(); i++){
            example.get(i);
        }
    }
}
