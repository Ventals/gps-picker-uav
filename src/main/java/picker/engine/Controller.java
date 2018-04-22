package picker.engine;

import picker.engine.model.Constant;
import picker.engine.model.Data;
import picker.engine.util.MathUtils;
import picker.module.ParseFile;

import java.io.IOException;

public class Controller {
    Data data;
    ParseFile parseFile = new ParseFile("input.csv");

    // refactor to the camelCase
    public void OnePhotoStart(int id) throws IOException {
        data = parseFile.getParsedData(id);
        new MathUtils(data);
    }
    public void SetPhotoStart() throws IOException{
        int count = parseFile.getCount();
        for (int id = 1; id < count; id++){
            data = parseFile.getParsedData(id);
            new MathUtils(data);
        }
    }
}
