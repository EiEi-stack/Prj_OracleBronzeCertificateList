import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OracleBronzeCertificateList {
    private static String EMPLOYEE_FILE_PATH = "C:/Users/User/Desktop/employee.txt";
    private static String ORACLE_CERTIFICATE_FILE_PATH = "C:/Users/User/Desktop/oracle_bronze_certificate_list.txt";
    private static String WRITE_FILE_PATH = "C:/Users/User/Desktop/oracle_bronze_certificate_list_name.txt";
    private static HashMap<String, String> employeeCode = new HashMap<String, String>();

    public static void main(String args[]) {
        //employee.txtテキストファイルを読む
        readEmployeeFile(EMPLOYEE_FILE_PATH);
        //oracle_bronze_certificate_list_name.txtテキストファイルを読む
        readCertificateWriteOutput(ORACLE_CERTIFICATE_FILE_PATH);
    }

    private static void readEmployeeFile(String readEmployeeFilePath) {
        try {
            //Fileクラスのオブジェクトを作成する
            File readFile = new File(readEmployeeFilePath);
            //FileReaderクラスのオブジェクトを作成する
            FileReader fileReader = new FileReader(readFile);
            //BufferedReaderクラスのオブジェクトを作成する
            BufferedReader bufferReader = new BufferedReader(fileReader);
            //StringBuilderクラスのオブジェクトを作成する
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                //employee.txtから社員名、社員番号を区別する
                String[] employeeCodeName = line.toString().split(",", 0);
                //社員名、社員番号ハッシュマップオブジェクトに入れる
                employeeCode.put(employeeCodeName[0], employeeCodeName[1]);
            }
            fileReader.close();
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

    private static void readCertificateWriteOutput(String readEmployeeFilePath) {
        try {
            //Fileクラスのオブジェクトを作成する
            File readFile = new File(readEmployeeFilePath);
            //FileReaderクラスのオブジェクトを作成する
            FileReader fileReader = new FileReader(readFile);
            //BufferedReaderクラスのオブジェクトを作成する
            BufferedReader bufferReader = new BufferedReader(fileReader);
            //StringBuilderクラスのオブジェクトを作成する
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                stringBuilder.append(line);
                //資格を取った社員番号でハッシュマップに社員名を検索する
                for (Map.Entry<String, String> enty : employeeCode.entrySet()) {
                    if (line.contains(enty.getKey())) {
                        //社員名を付ける
                        stringBuilder.append(",").append(enty.getValue());
                        //資格した日付、社員番号、社員名を一行ずつ書き込み
                        try {
                            //Fileクラスのオブジェクトを作成する
                            File writeFile = new File(WRITE_FILE_PATH);
                            //書き込みファイルをチェックする
                            if (!writeFile.exists()) {
                                writeFile.createNewFile();
                            }
                            if (writeFile.exists() && writeFile.canWrite()) {
                                //FileReaderクラスのオブジェクトを作成する
                                FileWriter fileWriter = new FileWriter(writeFile);
                                fileWriter.write(stringBuilder.toString());
                                fileWriter.close();
                            } else {
                                System.out.println("ファイルに書き込めません");
                            }
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                }
                stringBuilder.append(System.lineSeparator());
            }
            fileReader.close();
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
