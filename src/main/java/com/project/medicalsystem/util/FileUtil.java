package com.project.medicalsystem.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static void writeToFile(String file, String data) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFile(String file) {
        List<String> list = new ArrayList<>();
        try {
            File f = new File(file);
            if (!f.exists()) {
                f.createNewFile(); // create if not exist
            }

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void overwriteFile(String file, List<String> data) {
        try (FileWriter fw = new FileWriter(file)) {
            for (String line : data) {
                fw.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}