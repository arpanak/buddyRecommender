package com.recommender.utilities;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

/**
 * This class is used to generate a collection of beans from a csv file. Due to
 * type conversion constraints, an intermediate bean with all properties of type
 * String is required.
 * 
 * @author AshwinV
 * 
 * @param <K>
 *            Intermediate bean type
 * @param <E>
 *            Final expected type
 */
public class CsvBeanMapper<K, E>
{

	public static final Logger LOGGER = LoggerFactory.getLogger(CsvBeanMapper.class);

	/**
	 * Convert csv to bean entries.
	 * 
	 * @param csvFileContents
	 *            the csv file contents
	 * @param columns
	 *            the columns
	 * @param intermediateClass
	 *            the intermediate class
	 * @param resultClass
	 *            the result class
	 * @param converter
	 *            the converter
	 * @return the list
	 */
	public List<E> convertCsvToBeanEntries(String csvFileContents, String[] columns, Class<K> intermediateClass,
			Class<E> resultClass, BeanUtilsBean beanUtilsBean)
	{
		CsvToBean<K> csvBean = new CsvToBean<K>();

		List<K> csvEntries = new ArrayList<K>();
		ColumnPositionMappingStrategy<K> csvReaderColumnMappingStrategy = new ColumnPositionMappingStrategy<K>();
		CSVReader csvReader = null;
		try
		{
			// Ignore first header row.
			csvReader = new CSVReader(new StringReader(csvFileContents), ',', '"', 1);
			csvReaderColumnMappingStrategy.setColumnMapping(columns);
			csvReaderColumnMappingStrategy.setType(intermediateClass);
			csvEntries = csvBean.parse(csvReaderColumnMappingStrategy, csvReader);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(csvReader);
		}

		List<E> beanList = new ArrayList<E>();
		int size = csvEntries.size();
		for (int i = 0; i < size; i++)
		{
			try
			{
				K csvEntry = csvEntries.get(i);
				E beanEntry = getBeanFromIntermediateType(csvEntry, resultClass, beanUtilsBean);
				beanList.add(beanEntry);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				LOGGER.warn(ex.getMessage());
			}
		}

		return beanList;
	}

	/**
	 * Gets the bean from intermediate type.
	 * 
	 * @param intermediateType
	 *            the intermediate type
	 * @param resultClass
	 *            the result class
	 * @param beanUtilsBean
	 *            the bean utils bean
	 * @return the bean from intermediate type
	 */
	private E getBeanFromIntermediateType(K intermediateType, Class<E> resultClass, BeanUtilsBean beanUtilsBean)
	{
		E resultInstance = null;
		try
		{
			resultInstance = resultClass.newInstance();
			BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
			beanUtilsBean.populate(resultInstance, new BeanMap(intermediateType));
		}
		catch (Exception e)
		{
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
		BeanUtilsBean.getInstance().getConvertUtils().deregister();
		return resultInstance;
	}
}
