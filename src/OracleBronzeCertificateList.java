import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OracleBronzeCertificateList {
    private static String EMPLOYEE_FILE_PATH = "C:/Users/User/Desktop/oracle/input/employee.txt";
    private static String ORACLE_CERTIFICATE_FILE_PATH = "C:/Users/User/Desktop/oracle/input/oracle_bronze_certificate_list.txt";
    private static String WRITE_FILE_PATH = "C:/Users/User/Desktop/oracle/output/oracle_bronze_certificate_list_name.txt";
    private static HashMap<String, String> employeeCodeMap = new HashMap<String, String>();

    public static void main(String args[]) {
        readEmployeeFile(EMPLOYEE_FILE_PATH);
        readCertificateWriteOutput(ORACLE_CERTIFICATE_FILE_PATH);
    }

    private static void readEmployeeFile(String readEmployeeFilePath) {
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(new File(readEmployeeFilePath)));
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                //employee.txtから社員名、社員番号を区別する
                String[] employeeCodeName = line.split(",");
                employeeCodeMap.put(employeeCodeName[0], employeeCodeName[1]);
            }
            bufferReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void readCertificateWriteOutput(String readEmployeeFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(new File(WRITE_FILE_PATH));
            BufferedReader bufferReader = new BufferedReader(new FileReader(new File(readEmployeeFilePath)));
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(line).append(",");
                String[] items = line.split(",");
                stringBuilder.append(employeeCodeMap.get(items[1]) + System.lineSeparator());
                fileWriter.write(stringBuilder.toString());
            }
            bufferReader.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
