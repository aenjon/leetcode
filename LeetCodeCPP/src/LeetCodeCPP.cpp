//============================================================================
// Name        : LeetCodeCPP.cpp
// Author      : ArrowZzz Coder
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <string>

using namespace std;



class LeetCode{
public:
	void test(){
		cout << "this is leetcode cpp test" << endl;
	}


	/**
	 * Problem #4: Median of Two Sorted Arrays
	 */
	int getkth(int s[], int m, int l[], int n, int k) {
		// let m <= n
		if (m > n)
			return getkth(l,n,s,m,k);
        // s is empty now, just return the result from l
		if (m == 0)
			return l[k-1];
		if (k==1)
			return min(s[0],l[0]);
		int i = min(m, k/2);
		int j = min(n, k/2);
		if (s[i-1] > l[j-1])
        	/*
        	 * All elements before l[j-1] are less than s[i-1]
        	 * Because i <= k/2, kth element must not be an element less than l[j-1],
        	 * so elements before l[j-1] will not be the kth element.
        	 */
			return getkth(s,m,l+j,n-j,k-j);
		else
        	/*
        	 * All element before s[i-1] are less than l[i-1], for same reason
        	 * elements before s[i-1] will not be the kth element.
        	 */
			return getkth(s+i,m-i,l,n,k-i);
		return 0;
	}

    double findMedianSortedArrays(int A[], int m, int B[], int n) {
    	/*
    	 * +1 and +2 garauntee the right median will be calculated.
    	 * To illustrate, see below examples
    	 * if m = n = 5 (odd), the merged array has 10 lements, and we are looking
    	 * for the average of 5th and 6th element, i.e. 5.5
    	 * (m+n+1) >> 1 = 5 and (m + n + 2) >> 1 = 6, so we search 5th and 6th element in the merged array
    	 * Otherwise, if we choose (m + n) >> 1 = 5, (m + n + 1) >> 1 =5. we will get 5 (it's wrong)
    	 * Same case applies to m = n = 6 (even)
    	 *
    	 *  If m =5, n = 6, the merged array has 11 elements, and we are looking
    	 *  for the 6th element.
    	 *  So (m + n + 1) >> 1 = 6, (m + n + 2) >> 1 = 6
    	 *
    	 *  Note: here the order number starting with 1, rather than index 0 in array.
    	 */
    	int l = (m + n + 1) >> 1;
    	int r = (m + n + 2) >> 1;
    	if (l != r)
    		return (getkth(A,m,B,n,l) + getkth(A,m,B,n,r))/2.0;
    	else
    		return getkth(A,m,B,n,l);
    }

    /**
     * Problem #5: Longest Palindromic Substring
     */
    char *longestPalindrome(char *s) {
    	int start = 0;
    	int maxlen = 1;
    	int i = 1;
    	while (*(s+i) != '\0'){
    		findlongestpal(s,i, &start, &maxlen, 0);
    		findlongestpal(s,i, &start, &maxlen, 1);
    		i++;
    	}
    	s[start+maxlen] ='\0';
    	return s+start;
    }

    string longestPalindrome(string s) {
    	//if (s == NULL || s.length() <=1) return s;
    	size_t start = 0, maxlen = 1;
    	for (int i = 1; i<s.length(); i++){
    		findlongestpalcpp(s, i, &start, &maxlen, 0);
    		findlongestpalcpp(s, i, &start, &maxlen, 1);
    	}
    	return s.substr(start, maxlen);
    }

    string longestPalindromeN(string s) {
    	string T = preProcess(s);
    	int n = T.length();
    	int* P = new int[n];
    	int C = 0, R = 0;
    	for (int i = 1; i<n-1; i++){
    		int i_mirror = 2*C - i;
    		P[i] = R > i ? min(R-i,P[i_mirror]) : 0;

    		while (T[i+1+P[i]] == T[i-1-P[i]])
    			P[i]++;
    		if (P[i]+i > R){
    			R = i + P[i];
    			C = i;
    		}
    	}
    	int maxlen = 0, indexcenter = 0;
    	for (int i=1;i<n-1;i++){
    		if(P[i] > maxlen){
    			maxlen = P[i];
    			indexcenter = i;
    		}
    	}
        delete[] P;
        return s.substr((indexcenter-1-maxlen)/2, maxlen);
    }

    /**
     * Problem #6: ZigZag Conversion
     */
    string convert(string s, int nRows) {
    	if (s.empty() || nRows <= 1) return s;
    	string ret = "";
    	int step = 2*nRows - 2;
    	int len = s.length();
    	for(int i=0; i<nRows; i++){
    		if (i==0 || i == nRows-1){
    			int curindex = i;
    			while (curindex < len){
    				ret += s.substr(curindex,1);
    				curindex += step;
    			}
    		}else{
    			int lindex = i;
    			int rindex = 2*nRows-2-i;
    			while(true){
    				if (lindex < len){
    					ret += s.substr(lindex,1);
    					lindex += step;
    				}else
    					break;
    				if (rindex < len){
    					ret += s.substr(rindex,1);
    					rindex += step;
    				}else
    					break;
    			}
    		}
    	}
    	return ret;
    }

    /**
     * Problem #8: String to Integer (atoi)
     */
    int atoi(string str) {
    	if (str.empty()) return 0;
    	int ret = 0;
    	bool sign = true;
    	int i = 0;
    	int len = str.length();
    	while (i<len){
    		if (str[i] != ' ')
    			break;
    		i++;
    	}

    	if (i < len){
    		if (str[i] == '+'){
    			sign = true;
    			i++;
    		}else if (str[i] == '-'){
    			sign = false;
    			i++;
    		}else
    			sign = true;
    	}

    	while (i<len){
    		int curdigit = str[i] - 48;
    		if (curdigit < 0 || curdigit > 9)
    			break;
    		if (sign && (ret > INT_MAX/10 || (ret == INT_MAX/10 && curdigit > 7)))
    			return INT_MAX;
    		if (!sign && (ret > INT_MAX/10 || (ret == INT_MAX/10 && curdigit > 8)))
    			return INT_MIN;
    		ret = ret * 10 + curdigit;
    		i++;
    	}

    	return sign ? ret : -ret;
    }
private:

    string preProcess(string s){
    	if (s.length() == 0)
    		return "^$";
    	string ret = "^";
    	int n = s.length();
    	for (int i=0; i<n; i++)
    		ret += "#" + s.substr(i,1);
    	ret += "#$";
    	return ret;
    }

    void findlongestpal(char* s, int i, int* start, int* maxlen, int odd){
    	int low = i-1;
    	int high = odd == 1 ? i+1 : i;
		while (i>=0 && *(s+high) != '\0' && *(s+low) == *(s+high)){
			if (high-low+1 > *maxlen){
				*start = low;
				*maxlen = high-low+1;
			}
			low--;
			high++;
		}
    }

    void findlongestpalcpp(string s, int i, size_t* start, size_t* maxlen, int odd){
    	int low = i-1;
    	int high = odd == 1 ? i+1 : i;
    	int len = s.length();
		while ( low>=0 && high < len){
			if (s.at(low) != s.at(high))
				break;
			if (high-low+1 > *maxlen){
				*start = low;
				*maxlen = high-low+1;
			}
			low--;
			high++;
		}
    }
};

int main() {
	LeetCode leetcode;
	/* Problem #4: Median of Two Sorted Arrays */
	// int A[] = {2, 5, 6, 10};
	// int B[] = {1, 4, 11, 12};

	/* Problem #5: longestPalindrome */
	//char teststrint[] = "ccb";
	string str = " -1";
	//printf("Result is %s\n", leetcode.longestPalindrome(str));
	cout << leetcode.atoi(str) << endl;
	return 0;

}

