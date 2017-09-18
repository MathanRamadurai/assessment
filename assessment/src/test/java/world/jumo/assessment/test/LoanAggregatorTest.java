package world.jumo.assessment.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.sf.jagg.AggregateFunction;
import net.sf.jagg.Aggregations;
import net.sf.jagg.CountAggregator;
import net.sf.jagg.SumAggregator;
import net.sf.jagg.model.AggregateValue;

import world.jumo.assessment.utils.CSVUtils;
import world.jumo.assessment.vo.AggregatedLoan;
import world.jumo.assessment.vo.Loan;

/**
 * The Class LoanAggregatorTest.
 */
public class LoanAggregatorTest {

	/**
	 * Test Aggregate loans.
	 */
	@Test
	public void aggregateLoans() {

		String workingDir = System.getProperty("user.dir");
		String inpuCsvFile = workingDir + "/src/test/resources/Loans.CSV";

		List<Loan> loans = CSVUtils.readLoanCSV(inpuCsvFile);
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

		assertNotNull(aggregatedLoans);
		assertTrue(aggregatedLoans.size() > 0);

	}

}
