import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * 
 */

/**
 * @author Daniel Leong
 * 
 */
public class Parser {

	private Component frame = null;
	private PrintWriter pWriter = null; 
	
	// recommended to use the date of the today
	private final String fileName = "2014 04 28.txt";  

	/**
	 * 
	 */
	public Parser() {
		try {
			pWriter = new PrintWriter(new File(fileName));

			pWriter.println("Name\tVolume/Size\tSKU\tSpecial Price\tNormal Price\tPrice Diff");
		} catch (FileNotFoundException e) {
		} 
		
	}

	/**
	 * This constructor is used for GUI implementation. 
	 * @param frame
	 */
	public Parser(Component frame) {
		this.frame = frame;
		try {
			pWriter = new PrintWriter(new File(fileName));
			pWriter.println("Name\tVolume/Size\tSKU\tSpecial Price\tNormal Price\tPrice Diff");
		} catch (FileNotFoundException e) {
		} 
	}

	/**
	 * This runs the query on the web page. 
	 * 
	 * @param aisleNo
	 * @return -1 if there is "Read Time Out" error, else 0 is return. 
	 */
	public int execute(String aisleNo) {
		try {
			
			// Had connected with the main deals page
			// could have connected with
			// http://shop.countdown.co.nz/Shop/DealsAisle/5
			// where the last number is the Aisle number which could be
			// iterated.
			// snapback could be implemented with "%2FShop%2FDealsAisle%2F" and
			// Aisle number
			// Now it is running via aisle
			Document doc = Jsoup
					.connect(
							"http://shop.countdown.co.nz/Shop/UpdatePageSize?pageSize=400&snapback=%2FShop%2FDealsAisle%2F"
									+ aisleNo).get();
			
			// have not manage to include precise error handling
			if (doc.text().equals("Read timed out")) return -1; 
			
			// only pick out the ones that does not involve one card member
			Elements prices = doc.select("div.price-container");
			
			// iterate through each element
			for (Element e : prices) {
				
				// select the parent element containing all the details of an product
				Element product = e.parent();
				
				// name of the product
				String test = product.child(0).toString();
				String name = test.substring(test.indexOf("img alt=")+9,test.indexOf("class",test.indexOf("img alt=")+9)-2).trim();
				String sku = test.substring(test.indexOf("Stockcode")+10,test.indexOf("amp")-1);
				String volume = product.child(0).select("span.volume-size")
						.text();
				
				// check for symbols in name or formatting issues
				if (name.contains("&amp;"))
					name = name.replace("&amp;", "&"); 
				if (name.contains("  "))
					name = name.replace("  ", " "); 
				
				// special price for the product
				String special = product.child(1)
						.select("span.price.special-price").text()
						.replace("$", "");
				double sValue = Math.round(Double.parseDouble(special) * 100.00) / 100.00; 
				
				// normal price for the product
				String normal = product.child(1).select("span.was-price")
						.text().replace("was", "").replace("$", "").trim();
				double nValue = Math.round(Double.parseDouble(normal) * 100.00) / 100.00; 
				
				// value diff
				double diff = Math.round((nValue - sValue) * 100.00) / 100.00; 
				
				// String to be printed
				String print = name + "\t" + volume + "\t" + sku + "\t" + sValue + "\t"
						+ nValue + "\t" + diff; 
				System.out.println(print);
				pWriter.println(print);
			}
		} catch (IOException e) {
			if (frame != null)
				new JMessage(frame, "" + e.getMessage(),
						JOptionPane.ERROR_MESSAGE);
			else {
				System.out.println(e.getMessage());
				return -1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		Parser p = new Parser();
		
		try {
		
			for (int i = 0; i < 50; i++) {
				if(p.execute("" + i) == -1) System.out.println("" + i);
			}
		
			// prevent the server from getting overloaded
			// due to our flood of request
			// wait for 1 min
			Thread.sleep(60000);
		
			for (int i = 51; i < 100; i++) {
				if(p.execute("" + i) == -1) System.out.println("" + i);
			}
		
			// prevent the server from getting overloaded
			// due to our flood of request
			// wait for 5 min
			Thread.sleep(300000);
		
			for (int i = 101; i < 200; i++) {
				if(p.execute("" + i) == -1) System.out.println("" + i);
			}
		
			// prevent the server from getting overloaded
			// due to our flood of request
			// wait for 1 min
			Thread.sleep(60000);
		
			for (int i = 201; i < 300; i++) {
				if(p.execute("" + i) == -1) System.out.println("" + i);
			}
			
			
		} catch (InterruptedException e) {
		}
	}

}
