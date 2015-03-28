//============================================================================
// Name        : LeetCodeCPP.cpp
// Author      : ArrowZzz Coder
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
using namespace std;

#include <iostream>
#include <string>


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


private:
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
		while ( low>=0 && high < s.length()){
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
	string str = "baccae";
	//printf("Result is %s\n", leetcode.longestPalindrome(str));
	cout << leetcode.longestPalindrome(str) << endl;
	return 0;

}

