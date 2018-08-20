import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class TopBloc {

    private static final String FILE_1 = "data/Data1.xlsx";
    private static final String FILE_2 = "data/Data2.xlsx";

    public static void main(String[] args) throws IOException, UnirestException {

        Data d1 = parseSpreadsheet(FILE_1);
        Data d2 = parseSpreadsheet(FILE_2);

        JSONArray numberSetOneResults = new JSONArray();
        JSONArray numberSetTwoResults = new JSONArray();
        JSONArray wordSetOneResults = new JSONArray();

        // assume that all have same # of rows
        for (int i = 0; i < d1.getNumberSetOne().size(); i++) {
            numberSetOneResults.put(d1.getNumberSetOne(i) * d2.getNumberSetOne(i));
            numberSetTwoResults.put(d1.getNumberSetTwo(i) / d2.getNumberSetTwo(i));
            wordSetOneResults.put(d1.getWordSetOne(i) + ' ' + d2.getWordSetOne(i));
        }
        
        JSONObject json = new JSONObject();
        json.put("id", "mattbeis@yahoo.com");
        json.put("numberSetOne", numberSetOneResults);
        json.put("numberSetTwo", numberSetTwoResults);
        json.put("wordSetOne", wordSetOneResults);
        
        System.out.println(json);
        
            Unirest.post("http://34.239.125.159:5000/challenge")
                .header("Content-Type", "application/json")
                .body(json)
                .asJson();
    }

    private static Data parseSpreadsheet(String file) throws IOException {

        FileInputStream fis = new FileInputStream(new File(file));

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = spreadsheet.iterator();

        Data d = new Data();
        rowIterator.next(); // skip title row

        while (rowIterator.hasNext()) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                d.setNumberSetOne((int) cellIterator.next().getNumericCellValue());
                d.setNumberSetTwo((int) cellIterator.next().getNumericCellValue());
                d.setWordSetOne(cellIterator.next().toString());
            }

        }
        fis.close();
        return d;
    }
}
