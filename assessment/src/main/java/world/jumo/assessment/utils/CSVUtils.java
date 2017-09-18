package world.jumo.assessment.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import world.jumo.assessment.vo.AggregatedLoan;
import world.jumo.assessment.vo.Loan;

/**
 * The Class CSVUtils.
 */
public class CSVUtils {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(CSVUtils.class);

	/**
	 * Read loan CSV.
	 *
	 * @param the
	 *            input CSV file
	 * @return the list of loans
	 */
	public static List<Loan> readLoanCSV(String inputCSVFile) {
		logger.info(">>>>>>>> Reading loans csv file starts <<<<<<<<<<");
		List<Loan> loans = new ArrayList<Loan>();
		Reader reader = null;
		try {
			reader = new FileReader(inputCSVFile);
			loans = new CsvToBeanBuilder<Loan>(reader).withType(Loan.class).build().parse();
		} catch (FileNotFoundException e) {
			logger.error(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}
		logger.info(">>>>>>>> Reading loans csv file ends  <<<<<<<<<<");
		return loans;
	}

	/**
	 * Write aggregated loans to output CSV file.
	 *
	 * @param the
	 *            aggregate loans
	 * @param the
	 *            output file name
	 */
	public static void writeOutputCSV(List<AggregatedLoan> aggregateLoans, String outputFileName) {
		logger.info(">>>>>>>> Writing aggregated loans to output file starts <<<<<<<<<<");
		Writer writer = null;
		try {
			CustomMappingStrategy<AggregatedLoan> mappingStrategy = new CustomMappingStrategy<AggregatedLoan>();
			mappingStrategy.setType(AggregatedLoan.class);

			writer = new FileWriter(outputFileName);
			StatefulBeanToCsv<AggregatedLoan> beanToCsv = new StatefulBeanToCsvBuilder<AggregatedLoan>(writer)
					.withMappingStrategy(mappingStrategy).build();
			beanToCsv.write(aggregateLoans);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);

		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}
		logger.info(">>>>>>>> Writing aggregated loans to output file ends <<<<<<<<<<");
	}

}
