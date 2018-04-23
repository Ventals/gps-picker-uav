package picker.module;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteLog {
    public void writeLogToFile(String log) throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try (PrintWriter out = new PrintWriter(bufferedWriter)) {
            out.println(log);
        }
    }
}
