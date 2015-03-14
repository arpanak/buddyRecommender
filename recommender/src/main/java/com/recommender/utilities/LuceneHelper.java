package com.recommender.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import com.recommender.domain.Employee;

/**
 * This class handles interaction with the lucene framework.
 * 
 * @author ashwinvinod
 *
 */
public class LuceneHelper
{

	private static Map<String, Float> propertyName2priority = new HashMap<String, Float>();

	// 0. Specify the analyzer for tokenizing text.
	// The same analyzer should be used for indexing and searching
	private static StandardAnalyzer analyzer = new StandardAnalyzer();

	// 1. create the index
	private static Directory index = new RAMDirectory();
	private static IndexReader reader = null;

	public static void initialize(List<Employee> employees, Map<String, Float> propertyName2priority) throws Exception
	{
		buildIndex(employees);
		LuceneHelper.propertyName2priority = propertyName2priority;
	}

	private static void buildIndex(List<Employee> employees) throws Exception
	{

		IndexWriterConfig config = new IndexWriterConfig(analyzer);

		IndexWriter w = new IndexWriter(index, config);

		for (Employee employee : employees)
		{
			Map<String, String> propertyName2PropertyValue = new HashMap<String, String>();
			java.lang.reflect.Field[] fields = Employee.class.getDeclaredFields();
			for (java.lang.reflect.Field field : fields)
			{
				field.setAccessible(true);
				String name = field.getName();
				Object propertyValue = field.get(employee);
				String value = propertyValue == null ? "" : (String) propertyValue;
				propertyName2PropertyValue.put(name, value);
			}
			addEmployee(w, propertyName2PropertyValue);
		}
		w.close();
		reader = DirectoryReader.open(index);
	}

	private static void addEmployee(IndexWriter w, Map<String, String> propertyName2PropertyValue) throws IOException
	{
		Document doc = new Document();
		for (Entry<String, String> property : propertyName2PropertyValue.entrySet())
		{
			try
			{
				TextField field = new TextField(property.getKey(), property.getValue(), Field.Store.YES);
				field.setBoost(getBoost(property.getKey()));
				doc.add(field);
			}
			catch (Exception ex)
			{
				System.out.println(ex);
			}
		}
		w.addDocument(doc);
	}

	private static Float getBoost(String propertyName)
	{
		Float boost = propertyName2priority.get(propertyName);
		if (boost == null)
		{
			boost = 0.5f;
		}
		return boost;
	}

	public static List<Employee> runQuery(Map<String, String> filterName2filterValue, int hitsPerPage) throws Exception
	{
		BooleanQuery employeeQuery = buildQuery(filterName2filterValue);

		// 3. search
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
		searcher.search(employeeQuery, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		// 4. display results
		System.out.println("Found " + hits.length + " hits.");
		List<Employee> recommendedEmployees = new ArrayList<Employee>();
		for (int i = 0; i < hits.length; ++i)
		{
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			recommendedEmployees.add(convertDocumentToEmployee(d));
			System.out.println((i + 1) + ". " + d.get("name") + "\t" + d.get("graduateInstitute") + "\t"
					+ d.get("currentTeam"));
		}
		return recommendedEmployees;
	}

	public static Set<String> runQueryForSingleProperty(String propertyName, String propertyValue, int numberOfResponsesRequired) throws Exception
	{
		Set<String> result = new TreeSet<String>();
		BooleanQuery employeeQuery = new BooleanQuery();
		Query fuzzyQuery = new QueryParser(propertyName, analyzer).parse(propertyValue.trim()+"~");
		Query wildcardQuery = new QueryParser(propertyName, analyzer).parse(propertyValue.trim()+"*");
		employeeQuery.add(fuzzyQuery, BooleanClause.Occur.SHOULD);
		employeeQuery.add(wildcardQuery, BooleanClause.Occur.SHOULD);

		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(numberOfResponsesRequired);
		searcher.search(employeeQuery, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		for(int i = 0 ; i < hits.length ; i++)
		{
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			result.add(d.get(propertyName));
		}
		return result;
	}
	
	/**
	 * Builds a query consisting of multiple clauses. Each query clause is
	 * generated from an entry in the map provided as argument.
	 * 
	 * @param filterName2filterValue
	 * @return
	 * @throws ParseException
	 */
	private static BooleanQuery buildQuery(Map<String, String> filterName2filterValue) throws ParseException
	{
		BooleanQuery employeeQuery = new BooleanQuery();
		for (Entry<String, String> filter : filterName2filterValue.entrySet())
		{
			Query q = new QueryParser(filter.getKey(), analyzer).parse(filter.getValue());
			employeeQuery.add(q, BooleanClause.Occur.SHOULD);
		}
		return employeeQuery;
	}

	private static Employee convertDocumentToEmployee(Document document) throws Exception
	{
		java.lang.reflect.Field[] fields = Employee.class.getDeclaredFields();
		Employee employee = new Employee();
		for (java.lang.reflect.Field field : fields)
		{
			field.setAccessible(true);
			String fieldName = field.getName();
			String fieldValue = document.get(fieldName);
			field.set(employee, fieldValue);
		}
		return employee;
	}

	public static void close()
	{
		try
		{
			// reader can only be closed when there
			// is no need to access the documents any more.
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}