package lesson04;

import java.util.Arrays;

/**
 * A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
 *       - A[P] + A[Q] > A[R],
 *       - A[Q] + A[R] > A[P],
 *       - A[R] + A[P] > A[Q].
 * 
 * For example, consider array A such that:
 *       - A[0] = 10    
 *       - A[1] = 2    
 *       - A[2] = 5
 *       - A[3] = 1     
 *       - A[4] = 8    
 *       - A[5] = 20
 * Triplet (0, 2, 4) is triangular.
 * 
 * Write a function:
 *     class Solution { public int solution(int[] A); } 
 * that, given a zero-indexed array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
 * 
 * For example, given array A such that:
 *       - A[0] = 10    
 *       - A[1] = 2    
 *       - A[2] = 5
 *       - A[3] = 1     
 *       - A[4] = 8    
 *       - A[5] = 20
 * the function should return 1, as explained above. 
 * Given array A such that:
 *       - A[0] = 10    
 *       - A[1] = 50    
 *       - A[2] = 5
 *       - A[3] = 1
 * the function should return 0.
 * 
 * Assume that:
 *       - N is an integer within the range [0..100,000];
 *       - each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 *
 * Complexity:
 *       - expected worst-case time complexity is O(N*log(N));
 *       - expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * 
 * Elements of input arrays can be modified.
 * 
 * @author Souchet Celine
 *
 */
public class Triangle {
    public int solution(int[] A) {
    	if (A.length < 3) {
            return 0;
    	}
            
    	/* By sorting the array, we have for P < Q < R, if A[P] > 0 :
    	 * 	- A[R] < A[P] + A[R]
    	 * 	- A[R] < A[Q] + A[R]
    	 * 	- A[P] < A[Q] < A[R]
    	 * So, A[Q] < A[P] + A[R] and A[P] < A[Q] + A[R]
    	 */
    	Arrays.sort(A);

    	/*
    	 * To check A[P] + A[Q] > A[R], we need to check at least A[Q-1] + A[Q] > A[Q+1], because :
    	 * 	- R >= Q+1 and A[R] < A[R+1] < A[R+2]... So if with R=Q+1, A[P] + A[Q] <= A[R], it's impossible to have A[P] + A[Q] > A[Q+2].
    	 * 	- P <= Q-1 and A[P] > A[P-1] > A[P-2]... So if with P=Q-1, A[P] + A[Q] <= A[R], it's impossible to have A[P-2] + A[Q] > A[R].
    	 */
    	for (int i = 1; i < A.length-1; i++) {
			if (Long.valueOf(A[i-1]) + Long.valueOf(A[i]) > Long.valueOf(A[i+1])) {
				return 1;
			}
		}
    	return 0;
    }
}
