package com.mock.mitek.idv.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mitek.idv.constants.AppConstants;

@Service
public class FileReadService {

	@Autowired
	ObjectMapper objectMapper;

	public List<String> getAllFiles() {
		return getListOfFiles();
	}

	public JsonNode getAutoResult() throws IOException {
		return objectMapper.readTree(objectMapper.writeValueAsString(readSheetOption(AppConstants.getFileToRead())));
	}
	
	public Set<Map<String, String>> readSheetOption(String fileName) throws IOException {
		if (fileName == null) {
			return Collections.emptySet();
		}
		FileInputStream file = new FileInputStream(new File(AppConstants.FILE_LOCATION + fileName));
		return readSheetOption1(file);
	}

	public Set<Map<String, String>> readSheetOption1(FileInputStream file) throws IOException {

		Set<Map<String, String>> list = new HashSet<>();
		List<String> keyList = new ArrayList<>();

		Workbook wbread = new HSSFWorkbook(file);

		Sheet sheet = wbread.getSheetAt(0);

		sheet.forEach(row -> {
			Map<String, String> obj = new HashMap<>();
			row.forEach(cell -> {
				if (row.getRowNum() == 0) {
					keyList.add(printCellValue(cell));
				} else {
					obj.put(keyList.get(cell.getColumnIndex()), printCellValue(cell));
				}
			});
			if (!obj.isEmpty()) {
				list.add(obj);
			}
		});
		wbread.close();
		return list;
	}

	private String printCellValue(Cell cell) {
		String result = "";
		switch (cell.getCellType()) {
		case BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case STRING:
			result = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				result = String.valueOf(cell.getDateCellValue());
			} else {
				result = String.valueOf(cell.getNumericCellValue());
			}
			break;
		case FORMULA:
			result = cell.getCellFormula();
			break;
		case BLANK:
			result = "";
			break;
		default:
			result = "";
		}

		return result;
	}

	private List<String> getListOfFiles() {
		File folder = new File(AppConstants.FILE_LOCATION);
		File[] listOfFiles = folder.listFiles();

		List<String> list = Arrays.stream(listOfFiles).filter(f -> f.getName().contains(".xls")).map(fn -> fn.getName())
				.collect(Collectors.toList());

		return list;
	}

	public void setFileName(String fileName, String appName) throws IOException {
		AppConstants.setFileToRead(fileName);
		AppConstants.getAppApiDetails().put(appName, readSheetOption(fileName));
	}

	public String getFileName() {
		return AppConstants.getFileToRead() != null ? AppConstants.getFileToRead() : "No file selected";
	}

	public JsonNode getAutoResultFromCache(String appName) throws JsonProcessingException, IOException {
		return objectMapper.readTree(objectMapper.writeValueAsString(AppConstants.getAppApiDetails().get(appName)));
	}

	public void readFile(MultipartFile file, String appName) throws IOException {
		AppConstants.setFileToRead(file.getOriginalFilename());
		AppConstants.getAppApiDetails().put(appName, readSheetOption1((FileInputStream) file.getInputStream()));
	}
}
