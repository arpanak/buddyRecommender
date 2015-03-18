package com.recommender.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import com.recommender.services.DataImportService;
import com.recommender.services.RecommenderService;

@Component("uploaderServlet")
public class Uploader implements HttpRequestHandler
{
	@Autowired
	DataImportService dataImportService;
	
	@Autowired
	RecommenderService recommenderService;

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String csvContent = new String(IOUtils.toByteArray(request.getReader()));
		csvContent = csvContent.substring(csvContent.indexOf("Name,"));
		dataImportService.importEmployeeDetails(csvContent);
		recommenderService.initializeRecommender();
	}

}
