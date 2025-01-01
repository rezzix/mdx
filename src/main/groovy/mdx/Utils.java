package mdx;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.stream.Collectors;


public class Utils {
    static SimpleDateFormat detailedDateFormater = new SimpleDateFormat("EEEEE dd MMM yyyy", new Locale("fr"));
	static SimpleDateFormat dayDateFormater = new SimpleDateFormat("EEEEE dd/MM/yy", new Locale("fr"));
	static SimpleDateFormat hourFormater = new SimpleDateFormat("HH:mm");
	static SimpleDateFormat headerDateFormater = new SimpleDateFormat("EEEEE dd MMM", new Locale("fr"));
    static SimpleDateFormat internalDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	static SimpleDateFormat jsonDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat jsonDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        
	static DecimalFormat    priceFormater = new DecimalFormat("###,###,###,##0.00", new DecimalFormatSymbols(Locale.FRANCE));
	
	static String capitalize(String str) {
		if (str==null)
			return null;
		
		String[] strs = str.split(" ");
		StringBuffer ret=new StringBuffer();
		for (String s: strs) {
			if (ret.length()>0 && s.length()>0){
				ret.append(" ").append(s.substring(0,1).toUpperCase());
			} else if (ret.length()==0 && s.length()>0) {
				ret.append(s.substring(0,1).toUpperCase());
			}
			if (s.length()>1) {
				ret.append(s.substring(1).toLowerCase());
			}
		}
		return ret.toString();
	}
}
