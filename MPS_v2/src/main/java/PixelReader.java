import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Class that reads the data from a .csv file and parse it into a structure that we will use in
 * testing our trees.
 * There is only one function that takes as parameter the path to the .csv we want to parse.
 */
public class PixelReader {
    String REGEX = ",";

    public List<Pixel> readPixelFromCsv(String path) {

        List<Pixel> pixelList = new ArrayList<>();
        double value;
        int groundTruth;
        List<Double> pixelsList;

        BufferedReader reader;
        try {

            reader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(REGEX);


                value = Double.parseDouble(tokens[0]);
                groundTruth = Integer.parseInt(tokens[1]);
                pixelsList = new ArrayList<>();

                for (int i = 2; i < 12; i++) {
                    pixelsList.add(Double.parseDouble(tokens[i]));
                }

                Pixel pixel = new Pixel(value, groundTruth, pixelsList);
                pixelList.add(pixel);

            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pixelList;

    }
}

class Pixel {
    public double value;
    public int groundTruth;
    public List<Double> pixelMap;

    public Pixel(double value, int groundTruth, List<Double> pixelMap) {
        this.value = value;
        this.groundTruth = groundTruth;
        this.pixelMap = pixelMap;
    }
}