package com.github.xuchen93.other.util.poi;

import com.github.xuchen93.model.excel.ExcelCellValueModel;
import com.github.xuchen93.model.word.WordExcelCellValueModel;
import com.github.xuchen93.model.word.WordTextValueModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;

public class PoiUtil {

	/**
	 * 更新word中表格值
	 */
	public static void updateWordTableInfo(XWPFDocument doc, List<WordExcelCellValueModel> list) {
		list.forEach(item -> {
			doc.getTableArray(item.getTableIndex()).getCTTbl()
					.getTrArray(item.getRowIndex()).getTcArray(item.getCellIndex())
					.getPArray(0).getRArray(0).getTArray(0).setStringValue(item.getValue());
		});
	}

	/**
	 * 更新word中文本值
	 */
	public static void updateWordTextInfo(XWPFDocument doc, List<WordTextValueModel> list) {
		list.forEach(item -> {
			XWPFRun run = doc.getParagraphArray(item.getParagraphIndex())
					.getRuns().get(item.getRunIndex());
			String text = run.getText(0);
			run.setText(text.replace(item.getPlaceholder(), item.getValue()), 0);
		});
	}

	/**
	 * 向excel中插入行数据
	 *
	 * @param rowData           插入的行数据
	 * @param startRow          插入行，对应excel行数的下一行
	 * @param fillPreviousValue 如果某个值为空，则从原excel上一行获取数据
	 */
	@SuppressWarnings("rawtypes")
	public static void insertExcel(List<List> rowData, int startRow, boolean fillPreviousValue, XSSFSheet sheet) {
		sheet.shiftRows(startRow, sheet.getLastRowNum(), rowData.size(), true, false);
		for (int i = 0; i < rowData.size(); i++) {
			XSSFRow newRow = sheet.createRow(startRow + i);
			for (int columnIndex = 0; columnIndex < rowData.get(i).size(); columnIndex++) {
				XSSFCell cell = newRow.createCell(columnIndex);
				Object o = rowData.get(i).get(columnIndex);
				if (o == null) {
					if (fillPreviousValue) {
						cell.setCellValue(sheet.getRow(startRow - 1).getCell(columnIndex).getStringCellValue());
					} else {
						cell.setCellValue("");
					}
				} else {
					if (o instanceof String) {
						cell.setCellValue((String) o);
					} else {
						cell.setCellValue(Double.parseDouble(String.valueOf(o)));
					}
				}
				cell.setCellStyle(sheet.getRow(startRow - 1).getCell(columnIndex).getCellStyle());
			}
		}
	}

	/**
	 * 给单元格赋值
	 */
	public static void setCellValue(List<ExcelCellValueModel> valueModelList, XSSFSheet sheet) {
		for (ExcelCellValueModel model : valueModelList) {
			sheet.getRow(model.getRowIndex()).getCell(model.getColumnIndex()).setCellValue(model.getValue());
		}
	}

}
