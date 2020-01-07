package com.sln.arrays_and_strings;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// System.out.println(question1_1("abca"));
		// System.out.println(question1_1_byte("abca"));
		// System.out.println(question1_1_additional_structure("abca"));
		// System.out.println(question1_1_bruteforce("abca"));
		// System.out.println(question1_3("Mr 3ohn Smith "));
		// System.out.println("aabbccdd ->" + question1_4("aabbccdd"));
		// System.out.println("true->"+question1_5("pale","pae"));
		// System.out.println("true->"+question1_5("pale","bale"));
		// System.out.println("true->"+question1_5("pales","ales"));
		// System.out.println("false->"+question1_5("pale","bake"));
		// System.out.println("false->"+question1_5("pale", "pas"));
		// System.out.println(question1_6("aabcccccaaaCc"));
		// System.out.println(question1_6("abcde"));
		System.out.println(question1_9_isRotation("bottlewater","erbottlewa"));

	}

	// 1.1 Is Unique: Implement an algorithm to determine if a string has all unique
	// characters.
	// What if you cannot use additional data structures?

	public static boolean question1_1(String str) {
		if (str.length() > 128)
			return false;
		boolean[] charSet = new boolean[128];
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (charSet[val]) {
				return false;

			} else
				charSet[val] = true;

		}
		return true;

	}

	// lowercase letters a to z
	public static boolean question1_1_byte(String str) {
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {

			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0) {
				return false;
			}
			checker |= (1 << val);
			System.out.println("checker: " + checker);
		}
		return true;
	}

	public static boolean question1_1_additional_structure(String str) {
		if (str == null || str.length() <= 1)
			return true;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < str.length(); i++) {
			if (map.get(str.charAt(i)) == null) {
				map.put(str.charAt(i), 1);
			} else
				return false;
		}
		return true;
	}

	public static boolean question1_1_bruteforce(String str) {
		if (str == null || str.length() <= 1)
			return true;

		for (int i = 0; i < str.length() - 1; i++) {

			for (int j = i + 1; j < str.length(); j++) {

				if (str.charAt(i) == str.charAt(j)) {
					return false;
				}
			}

		}
		return true;
	}

	// 1.2 Check Permutation: Given two strings, write a method to decide if one is
	// a permutation of the other.

	public static boolean question1_2(String str, String str2) {
		// assumed that the character set was ASCII.
		if (str.length() != str2.length())
			return false;

		int[] set = new int[128];

		for (int i = 0; i < str.length(); i++) {

			int value = str.charAt(i);
			set[value]++;
		}
		for (int i = 0; i < str.length(); i++) {
			int value = str.charAt(i);
			set[value]--;
		}

		for (int i = 0; i < set.length; i++) {
			if (set[i] != 0)
				return false;
		}

		return true;
	}

	// URLify: Write a method to replace all spaces in a string with '%20'. You may
	// assume that the string
	// has sufficient space at the end to hold the additional characters, and that
	// you are given the "true"
	// length of the string.
	public static String question1_3(String str) {

		boolean controlFlag = false;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.trim().length(); i++) {

			if (str.charAt(i) == ' ') {
				if (!controlFlag) {
					sb.append("%20");
					controlFlag = true;
				}
			} else {
				controlFlag = false;
				sb.append(str.charAt(i));
			}

		}

		return sb.toString();
	}

	// 1.4 Palindrome Permutation: Given a string, write a function to check if it
	// is a permutation of a palindrome.
	public static boolean question1_4(String str) {
		int bitvector = createBitVector(str);
		return bitvector == 0 || checkOnlyOneBit(bitvector);

	}

	static int createBitVector(String str) {
		int bitvector = 0;
		for (Character c : str.toCharArray()) {
			if (c != ' ') {
				int i = c;
				System.out.println("char -> " + c);
				bitvector = toggle(bitvector, i);
			}
		}
		return bitvector;
	}

	static int toggle(int bitvector, int index) {
		if (index < 0)
			return bitvector;
		int mask = 1 << index;
		if ((bitvector & mask) == 0) {
			bitvector |= mask;
			System.out.println("bitvector if  " + Integer.toBinaryString(bitvector));
		} else {
			// if even char, make it 0
			bitvector &= (byte) ~mask;
			System.out.println("bitvector else  " + Integer.toBinaryString(bitvector));
		}
		System.out.println("mask " + Integer.toBinaryString(mask));
		System.out.println("-mask  " + Integer.toBinaryString((byte) ~mask));
		return bitvector;
	}

	static boolean checkOnlyOneBit(int bitvector) {
		return (bitvector & (bitvector - 1)) == 0;
	}
	// ------------------------------------------------------------------------------1.4
	// end

	// 1.5 One Away: There are three types of edits that can be performed on
	// strings: insert a character,
	// remove a character, or replace a character. Given two strings, write a
	// function to check if they are
	// one edit (or zero edits) away.

	public static boolean question1_5(String str, String str2) {

		int diff = Math.abs(str.length() - str2.length());
		if (diff > 1)
			return false;
		if (diff == 0) {
			// replacement
			boolean control = false;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != str2.charAt(i)) {
					if (control)
						return false;
					control = true;
				}
			} // end for

		} else {
			// insert/remove is the same if there is one change
			// find the longer one
			String longer;
			String shorter;
			if (str.length() > str2.length()) {
				longer = str;
				shorter = str2;
			} else {
				longer = str2;
				shorter = str;
			}
			// check if there is one change
			boolean control = false;
			int indexLonger = 0;
			int indexShorter = 0;
			while (indexLonger < longer.length() && indexShorter < shorter.length()) {
				if (longer.charAt(indexLonger) != shorter.charAt(indexShorter)) {
					if (control) {
						return false;
					}
					control = true;
					indexLonger++;
				} else {
					indexLonger++;
					indexShorter++;
				}
			}

		}

		return true;
	}

	// String Compression: Implement a method to perform basic string compression
	// using the counts
	// of repeated characters. For example, the string aabcccccaaa would become
	// a2blc5a3, If the
	// "compressed" string would not become smaller than the original string, your
	// method should return
	// the original string. You can assume the string has only uppercase and
	// lowercase letters (a - z).

	public static String question1_6(String str) {
		str = str.trim();
		if (str == null)
			return null;
		if (str.equals(""))
			return str;
		StringBuilder result = new StringBuilder();
		Character chr = str.charAt(0);
		int count = 0;
		boolean control = false;
		for (int i = 0; i < str.length(); i++) {
			if (count > 1)
				control = true;
			if (str.charAt(i) != chr) {

				result.append(chr + String.valueOf(count));
				chr = str.charAt(i);
				count = 1;
			} else {
				count++;

			}
		}
		result.append(chr + String.valueOf(count));

		return control ? result.toString() : str;
	}

	// Rotate Matrix: Given an image represented by an NxN matrix, where each pixel
	// in the image is 4
	// bytes, write a method to rotate the image by 90 degrees. Can you do this in
	// place?
	public void question1_7(String str) {

	}

	// Zero Matrix: Write an algorithm such that if an element in an MxN matrix is
	// 0, its entire row and
	// column are set to 0.
	public void question1_8(String str) {

	}

	// String Rotation; Assume you have a method isSubs t rin g which checks if one
	// word is a substring
	// of another. Given two strings, si and s2, write code to check if s2 is a
	// rotation of si using only one
	// call to isSubs t rin g [e.g., "wate r bottle " is a rotation
	// oP'erbottlewat"),

	public static boolean isSubstring(String str, String str2) {
		if (str.contains(str2))
			return true;

		return false;
	}

	public static boolean question1_9_isRotation(String str, String str2) {

		if (str.length() == str2.length()) {

			String checkStr = str + str;
			return isSubstring(checkStr, str2);

		}
		return false;

	}

}
