package picker.engine.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Data {
    long lat;
    long lon;
    int height;

    int roll;
    int pich;
    int yaw;
}
