
import com.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author INTEL&AMD
 */
public class DatasetConverter {
    // lokasi dan nama file keluaran
    private static final String FILENAME = "C:\\Users\\INTEL&AMD\\Desktop\\skripsi\\2_Program\\Dataset\\dataset.csv";

    public static void masin(String[] args) throws FileNotFoundException, IOException {
        final Path path = Paths.get("C:\\Users\\INTEL&AMD\\Desktop\\skripsi\\2_Program\\Dataset");
        final Path txt = path.resolve("20090829.txt");
        final Path csv = path.resolve("20090829.csv");
        final Charset utf8 = Charset.forName("UTF-8");
        try (
                final Scanner scanner = new Scanner(Files.newBufferedReader(txt, utf8));
                final PrintWriter pw = new PrintWriter(Files.newBufferedWriter(csv, utf8, StandardOpenOption.CREATE_NEW))) {
            while (scanner.hasNextLine()) {
                pw.println(scanner.nextLine().replace('\t', ','));
            }
        }
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\INTEL&AMD\\Desktop\\skripsi\\2_Program\\Dataset\\20090829.csv"));
        String[] nextLine;
        String tampung = null;
        BufferedWriter bw = null;
        FileWriter fw = null;
        File file = new File(FILENAME);
        try {
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            // menulis nama kolom
            bw.write("Duration,Service,Source bytes,Destination bytes,Count,Same srv rate,Serror rate,Srv serror rate,Dst host count,Dst host srv count,Dst host same src port rate,Dst host serror rate,Dst host srv serror rate,Flag,Label");
            bw.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // konversi nilai kolom Label
        while ((nextLine = reader.readNext()) != null) {
            switch (nextLine[17]) {
                case "1":
                    tampung = "normal";
                    break;
                case "-1":
                    tampung = "serangan";
                    break;
                case "-2":
                    tampung = "serangan_tak_dikenal";
                    break;
                default:
                    tampung = "null";
                    break;
            }
            try {
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                // true = append file
                // menuliskan instances pada file
                if ("1".equals(nextLine[17]) || "-1".equals(nextLine[17])
                   || "-2".equals(nextLine[17])) {
                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);
                    bw.write(nextLine[0]);
                    bw.write(",");
                    bw.write(nextLine[1]);
                    bw.write(",");
                    bw.write(nextLine[2]);
                    bw.write(",");
                    bw.write(nextLine[3]);
                    bw.write(",");
                    bw.write(nextLine[4]);
                    bw.write(",");
                    bw.write(nextLine[5]);
                    bw.write(",");
                    bw.write(nextLine[6]);
                    bw.write(",");
                    bw.write(nextLine[7]);
                    bw.write(",");
                    bw.write(nextLine[8]);
                    bw.write(",");
                    bw.write(nextLine[9]);
                    bw.write(",");
                    bw.write(nextLine[10]);
                    bw.write(",");
                    bw.write(nextLine[11]);
                    bw.write(",");
                    bw.write(nextLine[12]);
                    bw.write(",");
                    bw.write(nextLine[13]);
                    bw.write(",");
                    bw.write(tampung);
                    bw.write(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("Selesai menulis ke file");
    }
}
