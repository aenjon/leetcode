package cn.arrow.careercup;
import java.util.Stack;

public class CareerCup {
	
	public int searchRotated(int[] A, int start, int end, int x){
		while (start <= end){
			int mid = (start + end) / 2;
			if (A[mid] == x)
				return mid;
			else if (A[start] <= A[mid]){
				// the left part of the array is increasing.
				// The smallest number is at the right
				if (x > A[mid]) 
					// x is bigger than any number at the left
					start = mid  + 1;
				else if ( x >= A[start])
					// x is among the numbers at the lef
					end = mid - 1;
				else
					// x is larger than the smallest
					start = mid + 1;
			}
			// The smallest number is at left or just the middle, the right side is always increasing			
			else if (x < A[mid]) end = mid - 1; // x is between the smallest and the mid
			else if (x <= A[end]) start = mid  + 1; // x is at the increasing right
			else end = mid - 1; // x is equal or less then the largest but larger then the end 
		}
		return -1;
	}
	
	public Stack<Integer> sortStack(Stack<Integer> s){
		Stack<Integer> r = new Stack<Integer>();
		
		while(!s.isEmpty()){
			int tmp = s.pop();
			while (!r.isEmpty() && r.peek() <= tmp)
				s.push(r.pop());
			r.push(tmp);
		}
		return r;
	}
	
	public boolean isRotation(String s1, String s2){
		if (s1.length() != s2.length())
			return false;
		
		String s2s2 = s2+s2;
		return isSubstring(s1,s2s2);
	}
	
	public boolean isSubstring(String s1, String s2){
		int i,j;
		boolean result = true;
		
		if (s1.length() > s2.length())
			return false;
		
		for (i=0; i<s2.length(); ++i){
			result = true;
			if (i+s1.length() > s2.length()) return false;
			for(j=0; j<s1.length(); ++j)
				if(s1.charAt(j) != s2.charAt(i+j))
				{
					result = false;
					break;
				}
			if (result)
				break;
		}
		return result;
	}
	
	public void setZeros(int[][] matrix){
		int[] row = new int[matrix.length];
		int[] col = new int[matrix[0].length];
		
		for (int i=0; i<matrix.length; ++i)
			for (int j=0; j<matrix[0].length; ++j)
				if (matrix[i][j] == 0){
					row[i] = 1;
					col[j] = 1;
				}
		for (int i=0; i<matrix.length; ++i)
			for (int j=0; j<matrix[0].length; ++j)
				if (row[i]==1 || col[j]==1)
					matrix[i][j] = 0;		
	}
	
	public String replaceSpace(String str){
		int spaceCount = 0, newLength, i = 0;
		for (i = 0; i < str.length(); i++)
			if (str.charAt(i) == ' ')
				spaceCount++;
		
		newLength = str.length() + spaceCount * 2;
		char[] new_str = new char[newLength];
		
		for (i = str.length() - 1; i >= 0; --i) {
			if (str.charAt(i) == ' ') {
				new_str[newLength-1] = '0';
				new_str[newLength - 2] = '2';
				new_str[newLength - 3] = '%';
				newLength -= 3;
			} else {
			new_str[newLength - 1] = str.charAt(i);
			--newLength;
			}
		}
		
		return String.valueOf(new_str);
	}


	public boolean anagram(String s, String t){
		if (s.length() != t.length()) return false;
		int[] letters = new int[256]; // 8-bit ASC II{}
		
		int num_unique_char = 0;
		int num_complete_t = 0;
		
		for (int i=0; i<s.length(); ++i){
			if (letters[s.charAt(i)] == 0)
				++num_unique_char;
			++letters[s.charAt(i)];
		}
		
		for (int i=0; i<t.length(); ++i){
			if (letters[t.charAt(i)] == 0)
				return false;
			if (--letters[t.charAt(i)] == 0){
				++num_complete_t;
				
				if (num_unique_char == num_complete_t)
					return i == t.length()-1;
			}
		}
		
		return false;
	}
	
	public void removeDuplicates2(char[] str){
		if (str == null) return;
		int len = str.length;
		if (len < 2) return;
		
		boolean[] hit = new boolean[256]; // 8-bit ASCII
		for (int i=0; i<256; ++i)
			hit[i] = false;
		hit[str[0]] = true;
		int tail = 1;
		
		for (int i=1; i<len; ++i){
			if (!hit[str[i]]){ // Unique char
				str[tail] = str[i]; // put the unique char to the end of the char array
				hit[str[i]] = true;
				++tail;
			}
		}
		
		if (tail < len)
			str[tail] = 0;
	}

	public void removeDuplicates(char[] str){
		if (str == null) return;
		int len = str.length;
		if (len < 2) return;
		
		int tail = 1;
		for (int i=1; i< len; ++i){
			int j;
			for (j=0; j<tail; ++j)
				if (str[i] == str[j]) break;
			if (j == tail){
				str[tail] = str[i];
				++tail;
			}
		}
		
		if (tail < len)
			str[tail] = 0;
	}
	
	
	public void printASCII(){
		for (int i = 0; i< 256; i++){
			char ch= (char)i;
			System.out.println(i + ":" + ch);
		}
	}

	/**
	 * Determine if a string has all unique characters. Can be extended to check all chars.
	 * No boundary is checked.
	 * @param str
	 * @return
	 */
	public boolean checkUniqueLetters(String str){
		int checker = 0;
		
		for(int i = 0; i<str.length(); i++){
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0)
				return false;
			checker |= 1 << val;
		}		
		return true;
	}
}
