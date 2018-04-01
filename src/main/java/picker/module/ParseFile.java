package picker.module;

import picker.engine.model.Data;

import java.io.*;

public class ParseFile {

    private String inputFile;

    public ParseFile (String inputFile) {
        this.inputFile  = inputFile;
    }

    private String getRaw (int id) throws IOException {
        int line = 0;
        BufferedReader reader = new BufferedReader(new FileReader(this.inputFile));
        while (line < id) {
            reader.readLine();
            line++;
        }
        return reader.readLine();
    }

    private String[] splitRaw (String raw) {
        return raw.split(",");
    }

    public Data getParsedData (int id) throws IOException{
        String raw = getRaw(id);
        String[] data = splitRaw(raw);
        return Data.builder()
                .lat(Double.parseDouble(data[12]) / 10000000)
                .lon(Double.parseDouble(data[14]) / 10000000)
                .h((int) (Double.parseDouble(data[18]) * 1000))
                .roll(Double.parseDouble(data[20]) / 180)
                .pitch(Double.parseDouble(data[22]) / 180)
                .yaw(Double.parseDouble(data[24]) / 180)
                .build();
    }

}