import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OracleBronzeCertificateList {
    private static String read_employee_file_path = "C:\\Users\\User\\Desktop\\employee.txt";
    private static String read_oracle_bronze_certificate_list_file_path = "C:\\Users\\User\\Desktop\\oracle_bronze_certificate_list.txt";
    private static String write_file_path = "C:\\Users\\User\\Desktop\\oracle_bronze_certificate_list_name.txt";
    private static int ch;
    private static StringBuilder sb;
    private static HashMap<String, String> hmap;

    public static void main(String args[]) {
        //社員番号,社員名ハッシュマップオブジェクトを作成する
        hmap = new HashMap<String, String>();
        //employee.txtテキストファイルを読む
        readFile(read_employee_file_path, 1);
        //oracle_bronze_certificate_list_name.txtテキストファイルを読む
        readFile(read_oracle_bronze_certificate_list_file_path, 2);
    }

    private static void writeFile(String writeString) {
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
                fileWriter.write(writeString);
                fileWriter.close();
            } else {
                System.out.println("ファイルに書き込めません");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void readFile(String read_employee_file_path, int type) {
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
            while ((line = bufferedReader.readLine()) != null) {
                //employee.txtテキストファイルを読む
                if (type == 1) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    //コンマを分割する
                    String[] arrayA = line.toString().split(",", 0);
                    //社員名、社員番号ハッシュマップオブジェクトに入れる
                    hmap.put(arrayA[0], arrayA[1]);
                }
                //oracle_bronze_certificate_list_name.txtテキストファイルを読む
                if (type == 2) {
                    sb.append(line);
                    //資格を取った社員番号でハッシュマップに社員名を検索する
                    for (Map.Entry<String, String> enty : hmap.entrySet()) {
                        if (line.contains(enty.getKey())) {
                            //社員名を付ける
                            sb.append(",").append(enty.getValue());
                            //資格した日付、社員番号、社員名を一行ずつ書き込み
                            writeFile(sb.toString());
                        }
                    }
                    sb.append(System.lineSeparator());
                }
            }
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
    }

}
