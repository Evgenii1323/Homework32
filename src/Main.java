import java.io.*;
import java.util.zip.*;


public class Main {

    public static void main(String[] args) {

        GameProgress save1 = new GameProgress(100, 3, 100, 2.5);
        GameProgress save2 = new GameProgress(50, 3, 70, 2.0);
        GameProgress save3 = new GameProgress(20, 3, 20, 1.5);

        saveGame("C:/Games/savegames/save1.dat", save1);
        saveGame("C:/Games/savegames/save2.dat", save2);
        saveGame("C:/Games/savegames/save3.dat", save3);

        String[] list = {"C:/Games/savegames/save1.dat", "C:/Games/savegames/save2.dat", "C:/Games/savegames/save3.dat"};

        zipFiles("C:/Games/temp/zip.zip", list);

        File dir = new File("C:/Games/savegames");

        if (dir.isDirectory()) {
            for (File i : dir.listFiles()) {
                if (i.delete()) {
                    System.out.println("Файл " + i.getName() + " удален");
                } else {
                    System.out.println("Файл " + i.getName() + " не удален");
                }
            }
        }
    }

    public static void saveGame(String path, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(save);
        } catch (Exception ex) {
             System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, String[] list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
                for (String i : list) {
                    File file = new File(i);
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry entry = new ZipEntry(file.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    zout.write(buffer);
                    fis.close();
                }
                zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}