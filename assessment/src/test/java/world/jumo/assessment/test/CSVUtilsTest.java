package world.jumo.assessment.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import world.jumo.assessment.utils.CSVUtils;
import world.jumo.assessment.vo.AggregatedLoan;
import world.jumo.assessment.vo.Loan;

/**
 * The Class CSVUtilsTest.
 */
public class CSVUtilsTest {

	/**
	 * Test Read.
	 */
	@Test
	public void read() {
		String workingDir = System.getProperty("user.dir");
		String csvFile = workingDir + "/src/test/resources/Loans.CSV";
		List<Loan> loans = CSVUtils.readLoanCSV(csvFile);
		assertNotNull(loans);
		assertTrue(loans.size() > 0);

	}

	/**
	 * Test Write.
	 */
	@Test
	public void write() {
		String workingDir = System.getProperty("user.dir");
	    String outputCSVFile = workingDir+"/src/test/resources/Output.csv";

	    AggregatedLoan aggregateLoan =new AggregatedLoan();
	    aggregateLoan.setNetwork("Network 1");
	    aggregateLoan.setProduct("Loan Product 1");
	    aggregateLoan.setMonth("May");
	    aggregateLoan.setSum(132.00f);
	    aggregateLoan.setCount(2);
	    
	    AggregatedLoan aggregateLoan1 =new AggregatedLoan();
	    aggregateLoan1.setNetwork("Network 2");
	    aggregateLoan1.setProduct("Loan Product 2");
	    aggregateLoan1.setMonth("May");
	    aggregateLoan1.setSum(24505.00f);
	    aggregateLoan1.setCount(3);
		
		List<AggregatedLoan> aggregateLoans = new ArrayList<AggregatedLoan>();
		aggregateLoans.add(aggregateLoan);
		aggregateLoans.add(aggregateLoan1);
		CSVUtils.writeOutputCSV(aggregateLoans,outputCSVFile);
		
		File file= new File(outputCSVFile);
		 assertTrue(file.exists());

	}
}
