package world.jumo.assessment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.jagg.AggregateFunction;
import net.sf.jagg.Aggregations;
import net.sf.jagg.CountAggregator;
import net.sf.jagg.SumAggregator;
import net.sf.jagg.model.AggregateValue;

import world.jumo.assessment.utils.CSVUtils;
import world.jumo.assessment.vo.AggregatedLoan;
import world.jumo.assessment.vo.Loan;

/**
 * The Class LoanAggregator.
 */
public class LoanAggregator {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(LoanAggregator.class);

	/**
	 * The main method.
	 *
	 * @param the arguments
	 */
	public static void main(String[] args) {
		try {
			// Check to see if the program runs with the command line argument
			if (args.length < 1) {
				logger.info("Please make sure the command line argument strcuture as any one of the below."
						+ "\n java -jar jarName.jar /Path/InputFileName.csv"
						+ "\n java -jar jarName.jar /Path/InputFileName.csv /Path/OutputFileName.csv");
				throw new FileNotFoundException("Improper command line argument structure");
			} else if (args.length == 1 && isFileNameValid(args[0])) {
				logger.info(":::::: Input file :: " + args[0]);

				// Read loans from Input File
				List<Loan> loans = CSVUtils.readLoanCSV(args[0]);
				// Aggregate loan data
				List<AggregatedLoan> aggregatedLoans = aggregateLoan(loans);
				// Write aggregated loans to Outputfile
				CSVUtils.writeOutputCSV(aggregatedLoans, "Output.csv");

			} else if (args.length == 2 && isFileNameValid(args[0]) && isFileNameValid(args[1])) {
				logger.info(":::::: Input file :: " + args[0]);
				logger.info(":::::: Output file :: " + args[1]);

				// Read loans from Input File
				List<Loan> loans = CSVUtils.readLoanCSV(args[0]);
				// Aggregate loan data
				List<AggregatedLoan> aggregatedLoans = aggregateLoan(loans);
				// Write aggregated loans to Outputfile
				CSVUtils.writeOutputCSV(aggregatedLoans, args[1]);
			}

		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	 * Aggregate loans.
	 *
	 * @param the list of loans
	 * @return the list of AggregatedLoans
	 */
	private static List<AggregatedLoan> aggregateLoan(List<Loan> loans) {
		logger.info(">>>>>>>> Aggregating loans starts <<<<<<<<<<");
		List<AggregatedLoan> aggregatedLoans = new ArrayList<AggregatedLoan>();

		List<String> properties = new ArrayList<String>();
		properties.add("network");
		properties.add("product");
		properties.add("month");

		List<AggregateFunction> aggregators = new ArrayList<AggregateFunction>();
		aggregators.add(new CountAggregator("*"));
		aggregators.add(new SumAggregator("amount"));

		List<AggregateValue<Loan>> aggValues = Aggregations.groupBy(loans, properties, aggregators, true);

		for (AggregateValue<Loan> aggregateValue : aggValues) {

			Loan loan = aggregateValue.getObject();

			AggregatedLoan aggregatedLoan = new AggregatedLoan();
			aggregatedLoan.setNetwork(loan.getNetwork());
			aggregatedLoan.setProduct(loan.getProduct());
			aggregatedLoan.setMonth(loan.getMonth());

			for (AggregateFunction aggregator : aggregators) {

				if (aggregator.toString() != null && aggregator.toString().contains("CountAggregator")) {
					aggregatedLoan.setCount(Integer.parseInt(aggregateValue.getAggregateValue(aggregator).toString()));
				} else if (aggregator.toString() != null && aggregator.toString().contains("SumAggregator")) {
					aggregatedLoan.setSum(Float.parseFloat(aggregateValue.getAggregateValue(aggregator).toString()));
				}

			}

			aggregatedLoans.add(aggregatedLoan);
		}
		logger.info(">>>>>>>> Aggregating loans ends <<<<<<<<<<");
		return aggregatedLoans;
	}

	/**
	 * Checks if file name is valid.
	 *
	 * @param the fileName
	 * @return true, if file name is valid
	 * @throws the exception
	 */
	private static boolean isFileNameValid(String fileName) throws Exception {
		if (fileName.length() > 0 && !fileName.endsWith(".csv")) {
			throw new Exception("Invalid file format. Please input .csv file");
		} else {
			return true;
		}
	}
}
