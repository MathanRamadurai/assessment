package world.jumo.assessment.vo;

import com.opencsv.bean.CsvBindByName;

public class Loan {

	@CsvBindByName(column = "MSISDN", required = true)
	private String msisdn;
	@CsvBindByName(column = "Network", required = true)
	private String network;
	@CsvBindByName(column = "Date", required = true)
	private String date;
	@CsvBindByName(column = "Product", required = true)
	private String product;
	@CsvBindByName(column = "Amount", required = true)
	private Float amount;

	private String month;

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.setMonth(date);
		this.date = date;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		String[] dateArr = month.split("-");
		this.month = dateArr[1];
	}

	@Override
	public String toString() {
		return "Loan [msisdn=" + msisdn + ", network=" + network + ", date=" + date + ", product=" + product
				+ ", amount=" + amount + ", month=" + month + "]";
	}

}
