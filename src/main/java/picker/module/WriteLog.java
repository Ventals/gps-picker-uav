package picker.module;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteLog {
    public WriteLog(String log){
        try {
            try (PrintWriter out = new PrintWriter("filename.txt")) {
                out.println(log);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
