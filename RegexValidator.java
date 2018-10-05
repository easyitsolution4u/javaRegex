package Search.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.relation.RelationServiceNotRegisteredException;

public class SearchParser {

	public static void main(String[] args) {

		String testValue = "%22VBRB1;;;001900%22;;;;;BHHH786;*;8767HGH;111*;;544-90-9000;;;;;900-12-8767";

		List<String> results = regexMatchWildCardSearch(testValue);
		String parsedTokes = "";

		if (results != null && results.size() > 0) {

			for (Integer index = 0; index < results.size(); index++) {

				String currentToken = results.get(index).replace("%22", "").replace(";", " ");
				
				System.out.println(currentToken);
				parsedTokes += currentToken;

				if (index != results.size() - 1) {
					parsedTokes += ";";
					
				}
			}
		}

		String finalValue = parsedTokes;
		
		

	}

	private static List<String> regexMatchSsnsSearch(String line) {

		List<String> foundWildCardTokens = new ArrayList<>();
		String pattern = "\\d{3}-?\\d{2}-?\\d{4}";
		Pattern finder = Pattern.compile(pattern);
		Matcher matcher = finder.matcher(line);
		while (matcher.find()) {
			String text = matcher.group();
			foundWildCardTokens.add(text);
		}

		return foundWildCardTokens;
	}

	private static List<String> regexMatchWildCardSearch(String line) {

		List<String> foundWildCardTokens = new ArrayList<>();
		String pattern = "(%22)?[A-Za-z0-9;#$@]{3,8}\\*(%22)?|(%22)?[A-Za-z0-9;#$@]{8}\\d{3}\\*(%22)?|(%22)?[A-Za-z0-9;#$@]{8}\\d{6}(%22)?|\\d{3}-?\\d{2}-?\\d{4}";
		Pattern finder = Pattern.compile(pattern);
		Matcher matcher = finder.matcher(line);
		while (matcher.find()) {
			String text = matcher.group();
			foundWildCardTokens.add(text);
		}

		return foundWildCardTokens;
	}

}
