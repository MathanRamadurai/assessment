package world.jumo.assessment.vo;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class AggregatedLoan {

	
	@CsvBindByName(column = "Network", required = true)
	@CsvBindByPosition(position = 0)
	private String network;
	
	@CsvBindByName(column = "Product", required = true)
	@CsvBindByPosition(position = 1)
	private String product;
	
	@CsvBindByName(column = "Month", required = true)
	@CsvBindByPosition(position = 2)
	private String month;
	
	@CsvBindByName(column = "Sum", required = true)
	@CsvBindByPosition(position = 3)
	private Float sum;
	
	@CsvBindByPosition(position = 4)
	@CsvBindByName(column = "Count", required = true)
	private Integer count;

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	
	public Float getSum() {
		return sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "AggregatedLoan [network=" + network + ", product=" + product + ", month=" + month + ", sum=" + sum
				+ ", count=" + count + "]";
	}
}
