package hello;

import java.util.*;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "00:01:32,400-234-090\n00:05:01,701-080-080\n00:05:00,643-777-213";
		String s2 ="00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090";
		
		int result = solution(s);
		System.out.println(result);
		
		result = solution(s2);
		System.out.println(result);
	}
	
	public static int solution(String S) {
		int result = 0;
		double longestCall = 0;
		Map<String, Integer> listOfPhoneNumbers = new HashMap<String, Integer>();
		
		String[] data = S.split("\n");
		
		for (int i=0; i < data.length; i++) {
			String[] phone = data[i].split(",");
			
			int callDuration = 0;
			
			String phoneNumber = phone[1];
			if (listOfPhoneNumbers.containsKey(phoneNumber)) {
				callDuration += listOfPhoneNumbers.get(phoneNumber);
			}
			
			String[] timeOfCall = phone[0].split(":");			
			callDuration += Integer.parseInt(timeOfCall[0])*60*60;
			callDuration += Integer.parseInt(timeOfCall[1])*60;
			callDuration += Integer.parseInt(timeOfCall[2]);
			
			listOfPhoneNumbers.put(phoneNumber, callDuration);
		}
		
		for(String key: listOfPhoneNumbers.keySet()) {
			int price =0;
			
			double callDuration = listOfPhoneNumbers.get(key);
			if (callDuration < 300) {
				price = (int) (callDuration * 3);
			}else {
				price = (int) (Math.ceil(callDuration/60) * 150);				
			}
			
			if (callDuration > longestCall)
			{
				longestCall = (int) callDuration;
			}
			
			result += price;
		}
		
		if (longestCall < 300) {
			result -= longestCall * 3;
		}else {	
			result -= (int) (Math.ceil(longestCall / 60) * 150);			
		}
		
		return result;
    }
}
