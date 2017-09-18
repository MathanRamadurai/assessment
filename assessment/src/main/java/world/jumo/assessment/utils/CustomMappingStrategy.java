package world.jumo.assessment.utils;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomMappingStrategy.
 *
 * @param <T> the generic type
 */
public class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
	
	
	/* (non-Javadoc)
	 * @see com.opencsv.bean.ColumnPositionMappingStrategy#generateHeader()
	 */
	@Override
	public String[] generateHeader() {
		final int numColumns = findMaxFieldIndex();
		if (!isAnnotationDriven() || numColumns == -1) {
			return super.generateHeader();
		}

		header = new String[numColumns + 1];

		BeanField beanField;
		for (int i = 0; i <= numColumns; i++) {
			beanField = findField(i);
			String columnHeaderName = extractHeaderName(beanField);
			header[i] = columnHeaderName;
		}
		return header;
	}

	/**
	 * Extract header name.
	 *
	 * @param beanField the bean field
	 * @return the string
	 */
	private String extractHeaderName(final BeanField beanField) {
		if (beanField == null || beanField.getField() == null
				|| beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
			return StringUtils.EMPTY;
		}

		final CsvBindByName bindByNameAnnotation = beanField.getField()
				.getDeclaredAnnotationsByType(CsvBindByName.class)[0];
		return bindByNameAnnotation.column();
	}
}