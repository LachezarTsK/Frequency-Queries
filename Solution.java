import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	private static Map<Integer, Integer> frequencyPerNumber = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> frequencyPerFrequency = new HashMap<Integer, Integer>();
	private static StringBuilder results = new StringBuilder();

	/**
	 * In order to improve time complexity, each operation is processed immediately
	 * and the outcome is stored in the appropriate instance variable.
	 */
	private static void freqQuery(int operation, int input) {

		if (operation == 1) {
			int newFrequency = 0;
			if (frequencyPerNumber.containsKey(input)) {
				int previousFrequency = frequencyPerNumber.get(input);
				newFrequency = frequencyPerNumber.get(input) + 1;
				frequencyPerNumber.put(input, newFrequency);
				decreaseFrequencyOfFrequency(previousFrequency);
			} else {
				newFrequency = 1;
				frequencyPerNumber.put(input, newFrequency);
			}
			increaseFrequencyOfFrequency(newFrequency);
		} else if (operation == 2) {
			if (frequencyPerNumber.containsKey(input) && frequencyPerNumber.get(input) > 0) {
				int previousFrequency = frequencyPerNumber.get(input);
				int newFrequency = frequencyPerNumber.get(input) - 1;
				frequencyPerNumber.put(input, newFrequency);
				decreaseFrequencyOfFrequency(previousFrequency);
				increaseFrequencyOfFrequency(newFrequency);
			}
		} else if (operation == 3) {
			if (frequencyPerFrequency.containsKey(input) && frequencyPerFrequency.get(input) > 0) {
				results.append("1\n");
			} else {
				results.append("0\n");
			}
		}
	}

	private static void increaseFrequencyOfFrequency(int newFrequency) {
		if (!frequencyPerFrequency.containsKey(newFrequency)) {
			frequencyPerFrequency.put(newFrequency, 1);
		} else {
			int newValue = frequencyPerFrequency.get(newFrequency) + 1;
			frequencyPerFrequency.put(newFrequency, newValue);
		}
	}

	private static void decreaseFrequencyOfFrequency(int previousFrequency) {
		int newValue = frequencyPerFrequency.get(previousFrequency) - 1;
		frequencyPerFrequency.put(previousFrequency, newValue);
	}

	/**
	 * In order to improve time complexity, input is handled by BufferedReader.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int numberOfQueries = Integer.parseInt(stringTokenizer.nextToken());

		for (int i = 0; i < numberOfQueries; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int operation = Integer.parseInt(stringTokenizer.nextToken());
			int input = Integer.parseInt(stringTokenizer.nextToken());
			freqQuery(operation, input);
		}
		bufferedReader.close();
		System.out.println(results.toString().trim());
	}
}
