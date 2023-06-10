import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileTools {

    public static void fileWriter(String filePath, String fileContent){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(fileContent);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
