package com.recommender.core;

import java.util.Set;
import org.json.simple.JSONArray;

/**
 * This class queries the lucene index to find a list of values of a particular
 * property that match an input string.
 * 
 * @author ashwinvinod
 *
 */
public class SuggestionHelper
{

	/**
	 * Gets the suggestions for the given property.
	 * 
	 * @param property the name of the property to query
	 * @param value the string to match property values
	 * @param numberOfResponses number of responses required
	 * @return json string response - list of property values
	 */
	@SuppressWarnings("unchecked")
	public static String getSuggestions(String property, String value, int numberOfResponses) throws Exception
	{
		Set<String> suggestions = LuceneHelper.runQueryForSingleProperty(property, value, numberOfResponses);
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(suggestions);
		return jsonArray.toJSONString();
	}
}
