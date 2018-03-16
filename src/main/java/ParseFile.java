import java.io.*;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Михаил on 23.04.2017.
 */
public class ParseFile {
    public static int photoId;
    public static String line = "";
    public static String[] infCSV ;

    private static ParseFile ourInstance = new ParseFile();

    public static ParseFile getInstance() {
        return ourInstance;
    }

    public  void getFiles(String csv,String outputFile) throws IOException {

        int iteration = 0;

        BufferedReader br = new BufferedReader(new FileReader(csv));

        File file = new File(outputFile);
        if (!file.exists()) {
            while ((line = br.readLine()) != null) {

                if(iteration == 0){
                    iteration++;
                    continue;
                }
                String[] infCSV = line.split(",");

                FileWriter writer = new FileWriter(outputFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

               /* String[] output = {
                        "FileName" + photoId++ +
                                ", Lat= " + infCSV[12] + ", Lon=" + infCSV[14] +  //положение беспилотника в момент съемки
                                ", [" + "Lat1" + ", " + "Lon1" + "]" +            //координаты
                                ", [" + "Lat2" + ", " + "Lon2" + "]" +
                                ", [" + "Lat3" + ", " + "Lon3" + "]" +
                                ", [" + "Lat4" + ", " + "Lon4" + "]" +
                                ", [" + "Lat" + ", " + "Lon" + "]"};              //координата проекции центра снимка на поверхность земли*/
                String[] output = {"Photo" + photoId++ + " date = " + infCSV[0] + ", time = " + infCSV[1] +
                        ", latitude = " + infCSV[12] + ", longitude =" + infCSV[14] + ", altitude mean sea level =" + infCSV[16] +
                        ", altitude relative = " + infCSV[18] + ", roll =" + infCSV[20] + ", pitch =" + infCSV[22] +
                        ", yaw =" + infCSV[24]};
                /*String yaw = infCSV[12];
                long l = Long.parseLong(yaw);
                double d = (double)l/10000000;
                System.out.println(d);*/

                for (String anOutput : output) {
                    /*int i = anOutput.indexOf("Lat= ");
                    String test = anOutput.substring(i,anOutput.lastIndexOf(", Lon="));
                    String lat = test.substring(5);
                    System.out.println(lat);*/

                    bufferedWriter.write(anOutput);
                }


                bufferedWriter.newLine();
                bufferedWriter.close();
            }
        }
    }
    public static double divisionCoordinates(int num){
        String s = infCSV[num];
        long l = Long.parseLong(s);
        return (double)l/10000000;
    }
    public static double nonDivision(int n){
        String s = infCSV[n];
        return Double.parseDouble(s);
    }

    public  int getPhotoId() {
        return photoId;
    }

    public  double getLat(){
        return divisionCoordinates(12);
    }

    public  double getLon(){
        return divisionCoordinates(14);
    }

    public  double getAltMSL(){
        return nonDivision(16);
    }

    public  double getAltR(){
        return nonDivision(18);
    }

    public  double getRoll(){
        return nonDivision(20);
    }

    public  double getPitch(){
        return nonDivision(22);
    }
    public  double getYaw(){
        return nonDivision(24);
    }
}