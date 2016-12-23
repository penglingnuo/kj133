package com.telezone.Test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class binarySearch extends TestCase{
	public void testBinarySearch() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i < 1000001; i ++) {
			list.add(i);
		}
		
		int i = binarySearchFunction(list, 0, list.size(), 255551);
		
		System.out.println(i);
	}
	
	public int binarySearchFunction(List<Integer> list, int start, int end, int inputValue) {
		int low = 0;
		int high = list.size()- 1;
		
		while (low <= high) {
			int mid = (low + high) / 2;
			int midVal = list.get(mid).intValue();
			if (midVal < inputValue)
				low = mid + 1;
			else if (midVal > inputValue)
				high = mid - 1;
			else
				return mid; // key found
		}
		
		return -(low + 1);  // key not found
	}
}
