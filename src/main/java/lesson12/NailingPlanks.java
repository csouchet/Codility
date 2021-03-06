package lesson12;

import java.util.Arrays;

/**
 * You are given two non-empty zero-indexed arrays A and B consisting of N integers. 
 * These arrays represent N planks. More precisely, A[K] is the start and B[K] the end of the K−th plank.
 * 
 * Next, you are given a non-empty zero-indexed array C consisting of M integers. 
 * This array represents M nails. More precisely, C[I] is the position where you can hammer in the I−th nail.
 * 
 * We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
 * 
 * The goal is to find the minimum number of nails that must be used until all
 * the planks are nailed. In other words, you should find a value J such that
 * all planks will be nailed after using only the first J nails. More precisely,
 * for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail
 * C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].
 * 
 * For example, given arrays A, B such that:
 * 		- A[0] = 1 		B[0] = 4 
 * 		- A[1] = 4 		B[1] = 5 
 * 		- A[2] = 5 		B[2] = 9 
 * 		- A[3] = 8 		B[3] = 10
 * four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
 * 
 * Given array C such that:
 * 		- C[0] = 4 
 * 		- C[1] = 6 
 * 		- C[2] = 7 
 * 		- C[3] = 10 
 * 		- C[4] = 2
 * if we use the following nails:
 * 		- 0, then planks [1, 4] and [4, 5] will both be nailed. 
 * 		- 0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed. 
 * 		- 0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed. 
 * 		- 0, 1, 2, 3, then all the planks will be nailed.
 * 
 * Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
 * 
 * Write a function:
 * 		class Solution { public int solution(int[] A, int[] B, int[] C); }
 * that, given two non-empty zero-indexed arrays A and B consisting of N
 * integers and a non-empty zero-indexed array C consisting of M integers,
 * returns the minimum number of nails that, used sequentially, allow all the
 * planks to be nailed.
 * 
 * If it is not possible to nail all the planks, the function should return −1.
 * 
 * For example, given arrays A, B, C such that:
 * 		- A[0] = 1 		B[0] = 4 
 * 		- A[1] = 4 		B[1] = 5 
 * 		- A[2] = 5 		B[2] = 9 
 * 		- A[3] = 8 		B[3] = 10
 * 
 * 		- C[0] = 4 
 * 		- C[1] = 6 
 * 		- C[2] = 7 
 * 		- C[3] = 10 
 * 		- C[4] = 2
 * the function should return 4, as explained above.
 * 
 * Assume that:
 * 		- N and M are integers within the range [1..30,000]; each element of arrays A,
 * 		- B, C is an integer within the range [1..2*M]; A[K] ≤ B[K].
 * 
 * Complexity:
 * 		- expected worst-case time complexity is O((N+M)*log(M)); 
 * 		- expected worst-case space complexity is O(M), beyond input storage (not counting the storage
 * required for input arguments).
 * 
 * Elements of input arrays can be modified.
 * 
 * @author Souchet Celine
 *
 */
public class NailingPlanks {

	public int solution(int[] A, int[] B, int[] C) {
		int n = A.length;
		int m = C.length;

		// 2 dimensions to save the original index of array C
		int[][] sortedNail = new int[m][2];
		for (int i = 0; i < m; i++) {
			sortedNail[i][0] = C[i];
			sortedNail[i][1] = i;
		}
		// Use the lambda expression to sort two dimension array
		Arrays.sort(sortedNail, (int x[], int y[]) -> x[0] - y[0] == 0 ? x[1]
				- y[1] : x[0] - y[0]);

		// The main algorithm is getting the minimal index of nails which
		// is needed to nail every plank by using the binary search
		int result = 0;
		for (int i = 0; i < n; i++) {
			int minIndex = getMinIndex(sortedNail, A[i], B[i], result);
			if (minIndex == -1) {
				return -1;
			}
			result = Math.max(result, minIndex);
		}
		return result + 1;
	}

	// For each plank, we use the binary search to get the minimal index of the
	// sorted array of nails.
	public int getMinIndex(final int[][] nails, final int startPlank,
			final int endPlank, final int maxResult) {
		int begin = 0;
		int end = nails.length - 1;
		int nailIndex = Integer.MAX_VALUE;
		while (begin <= end) {
			int mid = (begin + end) / 2;
			if (nails[mid][0] < startPlank) {
				begin = mid + 1;
			} else if (nails[mid][0] > endPlank) {
				end = mid - 1;
			} else {
				end = mid - 1;
				nailIndex = mid;
			}
		}
		if (nailIndex == Integer.MAX_VALUE) {
			return -1;
		}

		int minIndex = Integer.MAX_VALUE;
		for (int i = nailIndex; i < nails.length && nails[i][0] <= endPlank
				&& minIndex > maxResult; i++) {
			minIndex = Math.min(minIndex, nails[i][1]);
		}
		return minIndex;
	}

}
