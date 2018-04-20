package picker.engine.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Data {
    double lat;
    double lon;
    int h;

    double roll;
    double pitch;
    double yaw;

}
