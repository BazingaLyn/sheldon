package org.bazinga.sheldon.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author liguolin
 */
public class SimpleFileUtils {


    @Test
    public void testRead() throws IOException {
        File testFile = ResourceUtils.getFile("classpath:data/ml-1m/users.dat");
        List<String> list = FileUtils.readLines(testFile, "UTF-8");
        for (String eachLine : list) {
            System.out.println(eachLine);
        }
    }


    @Test
    public void testWriteToCSV() throws IOException {
        File file = createFile("cb/users.csv");
        String[] titles = new String[]{"UserID","Gender","Age","Occupation","Zip-code"};
        List<String> eachLineFromFile = getEachLineFromFile("data/ml-1m/users.dat");
        transferToCSV(titles, file, eachLineFromFile, "::");
    }


    @Test
    public void testCreateFile() throws IOException {

        File testFile = new File(getResourceBasePath(),"cb/users.csv");
        File parentFile = testFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        if(!testFile.exists()){
            testFile.createNewFile();
        }
    }

    private static String getResourceBasePath() {
        // 获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }

        String pathStr = path.getAbsolutePath();
        // 如果是在eclipse中运行，则和target同级目录,如果是jar部署到服务器，则默认和jar包同级
        pathStr = pathStr.replace("\\target\\test-classes", "");

        return pathStr;
    }


    public static void transferToCSV(String[] titles, File file, List<String> lines, String sep) {
        CSVFormat format = CSVFormat.DEFAULT.withHeader(titles).withSkipHeaderRecord();

        try (Writer out = new FileWriter(file); CSVPrinter printer = new CSVPrinter(out, format)) {

            for (String eachLine : lines) {
                List<String> records = Arrays.asList(StringUtils.split(eachLine,sep));
                printer.printRecord(records);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public File createFile(String path) throws IOException {
        File testFile = new File(getResourceBasePath(),path);

        if(testFile.exists()) return testFile;

        File parentFile = testFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        if(!testFile.exists()){
            testFile.createNewFile();
        }

        return testFile;
    }


    public static List<String> getEachLineFromFile(String path) throws IOException {
        File testFile = ResourceUtils.getFile("classpath:" + path);
        return FileUtils.readLines(testFile, "UTF-8");
    }


}
