package com.example.demo.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dictionary {


    public Map<String, List<String>> getDict() {
        return dict;
    }


    private Map<String, List<String>> dict = new LinkedHashMap<String, List<String>>();
    private List<String> keys = new ArrayList<String>();
    

    public List<String> getKeys() {
        return keys;
    }

   

    private String path;

    public Dictionary(String path) {
        this.dict = dict;
        this.path = path;
    }


    public String getPath() {
        return path;
    }


    public void parser() throws IOException {
        FileInputStream fis = new FileInputStream(new File(this.path));
        //creating workbook instance that refers to .xls file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        System.out.print(wb.getNumberOfSheets());
        XSSFSheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    keys.add(cell.getStringCellValue());
                   
                    break;
                default :
            }
        }
        int size = keys.size();
        for (int culIdx =0 ; culIdx < size ; culIdx ++){
            String currKey = keys.get(culIdx);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row currRow  = rowIterator.next();
                if(row.getLastCellNum()== 1)continue;
                Cell cell = CellUtil.getCell(currRow, culIdx);
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        if (!dict.containsKey(cell.getStringCellValue())) {
                            dict.put(cell.getStringCellValue(), new ArrayList<String>());
                        }
                        dict.get(cell.getStringCellValue()).add(currKey);
                        break;
                    default :
                }
            }
        }

    }

    public Map<String ,Integer> parserAns(String sentences) {
        Map<String,Integer> ans = new LinkedHashMap<String, Integer>();
        String[] words = sentences.split(" ");
        for (String key : keys){
            ans.put(key ,0);
        }
        for( String word : words) {
            List<String> values = dict.get(word);
            if (values != null) {
                for (String val : values) {
                    ans.put(val, ans.get(val) + 1);
                }
            }
        }
        return  ans;
    }

}