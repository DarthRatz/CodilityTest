package hello;

import java.util.*;

public class HelloWorld {

	private static final String S1 = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090";
	private static final String S2 = "00:01:32,400-234-090\n00:05:01,701-080-080\n00:05:00,643-777-213";

	public static void main(String[] args) {
		int result = solution(S1);
		System.out.println(S1);
		System.out.println(result);
		
		result = solution(S2);
		System.out.println(S2);
		System.out.println(result);
	}
	
	public static int solution(String S) {
		int result = 0;
		int longestCall = 0;
		Map<String, Integer> listOfPhoneNumbers = new HashMap<String, Integer>();
		
		String[] data = S.split("\n");
		
		for (int i=0; i < data.length; i++) {
			String[] line = data[i].split(",");
			
			int callDuration = 0;
			
			String phoneNumber = line[1];
			if (listOfPhoneNumbers.containsKey(phoneNumber)) {
				callDuration += listOfPhoneNumbers.get(phoneNumber);
			}
			
			String[] timeOfCall = line[0].split(":");			
			callDuration += getCallDurationSeconds(timeOfCall);
			
			listOfPhoneNumbers.put(phoneNumber, callDuration);
		}
		
		for(String key: listOfPhoneNumbers.keySet()) {
			int price = 0;
			
			int callDuration = listOfPhoneNumbers.get(key);
			price = getPrice(callDuration);
			
			if (callDuration > longestCall)
			{
				longestCall = callDuration;
			}
			
			result += price;
		}
		
		result -= getPrice(longestCall);
		
		return result;
	}
	
	public static int getPrice(int callDuration) {
		int price = 0;
		Double acurateDouble = Double.valueOf(callDuration);
		if (acurateDouble < 300) {
			price = (int) (acurateDouble * 3);
		}else {
			price = (int) (Math.ceil(acurateDouble/60) * 150);				
		}

		return price;
	}

	public static int getCallDurationSeconds(String[] timeOfCall) {
		int callDuration = 0;
		
		callDuration += Integer.parseInt(timeOfCall[0])*60*60;
		callDuration += Integer.parseInt(timeOfCall[1])*60;
		callDuration += Integer.parseInt(timeOfCall[2]);

		return callDuration;
	}
}
