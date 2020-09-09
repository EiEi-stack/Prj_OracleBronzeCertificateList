import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OracleBronzeCertificateList {
    private static String read_employee_file_path = "C:\\Users\\User\\Desktop\\employee.txt";
    private static String read_oracle_bronze_certificate_list_file_path = "C:\\Users\\User\\Desktop\\oracle_bronze_certificate_list.txt";
    private static String write_file_path = "C:\\Users\\User\\Desktop\\abb.txt";
    private static int ch;
    private static StringBuilder sb;
    private static HashMap<String, String> hmap;
    private static ArrayList<String> employeeCode;
    private static String lastOutFile;

    public static void main(String args[]) {
        //社員番号,社員名ハッシュマップオブジェクトを作成する
        hmap = new HashMap<String, String>();
        //社員番号ArrayListオブジェクトを作成する
        employeeCode = new ArrayList<String>();
        //employee.txtテキストファイルを読む
        String employeeFile = readFile(read_employee_file_path, 1);
        //oracle_bronze_certificate_list_name.txtテキストファイルを読む
        String bronzeCertificateListFile = readFile(read_oracle_bronze_certificate_list_file_path, 2);
        //社員名付きの処理
        lastOutFile = readFile(read_oracle_bronze_certificate_list_file_path, 3);
        //社員名付きの資格取得リストファイルを出力する
        writeFile();

    }

    private static void writeFile() {
        try {
            //Fileクラスのオブジェクトを作成する
            File write_file = new File(write_file_path);
            //書き込みファイルをチェックする
            if (!write_file.exists()) {
                write_file.createNewFile();
            }
            if (write_file.exists() && write_file.canWrite()) {
                //FileReaderクラスのオブジェクトを作成する
                FileWriter fileWriter = new FileWriter(write_file);
                fileWriter.write(lastOutFile);
                fileWriter.close();
            } else {
                System.out.println("ファイルに書き込めません");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String readFile(String read_employee_file_path, int type) {
        try {
            //Fileクラスのオブジェクトを作成する
            File read_file = new File(read_employee_file_path);
            //FileReaderクラスのオブジェクトを作成する
            FileReader filereader = new FileReader(read_file);
            //BufferedReaderクラスのオブジェクトを作成する
            BufferedReader bufferedReader = new BufferedReader(filereader);
            //StringBuilderクラスのオブジェクトを作成する
            sb = new StringBuilder();
            String line = null;
            int iterate = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (type == 3) {
                    //社員名付きの処理
                    sb.append(line);
                    sb.append(",");
                    //社員番号をKeyとして社員名をつける
                    sb.append(hmap.get(employeeCode.get(iterate)));
                    sb.append(System.lineSeparator());
                    if (iterate < employeeCode.size()) {
                        iterate++;
                    }
                } else {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    //ハッシュマップに入れる
                    String currentLine = null;
                    currentLine = line;
                    String[] arrayA = currentLine.toString().split(",", 0);
                    if (type == 1) {
                        //社員名、社員番号ハッシュマップオブジェクトに入れる
                        hmap.put(arrayA[0], arrayA[1]);
                    }
                    if (type == 2) {
                        //社員番号のレスト
                        employeeCode.add(arrayA[1]);
                    }
                }
            }
            //ファイルを読み込んだあと閉じる
            filereader.close();

        }
        //FileReaderクラスのオブジェクトを作成の例外
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        //readメソッドの例外
        catch (IOException e) {
            System.out.println(e);
        }
        return sb.toString();
    }

}
