package lesson06;

import java.util.stream.IntStream;


/**
 * A non-empty zero-indexed array A consisting of N integers is given.
 * The leader of this array is the value that occurs in more than half of the elements of A.
 * An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
 * 
 * For example, given array A such that:
 * 	- A[0] = 4
 * 	- A[1] = 3
 * 	- A[2] = 4
 * 	- A[3] = 4
 * 	- A[4] = 4
 * 	- A[5] = 2
 * we can find two equi leaders:
 * 	- 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
 * 	- 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
 * 
 * The goal is to count the number of equi leaders.
 * 
 * Write a function:
 * 		class Solution { public int solution(int[] A); } 
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.
 * 
 * For example, given:
 * 	- A[0] = 4
 * 	- A[1] = 3
 * 	- A[2] = 4
 * 	- A[3] = 4
 * 	- A[4] = 4
 * 	- A[5] = 2
 * the function should return 2, as explained above.
 * 
 * Assume that:
 * 	- N is an integer within the range [1..100,000];
 * 	- each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 * 
 * Complexity:
 * 	- expected worst-case time complexity is O(N);
 * 	- expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * 
 * Elements of input arrays can be modified.
 * 
 * @author Souchet Celine
 *
 */
public class EquiLeader {

	public int solution(int[] A) {
		int leader = getLeader(A);

		// Find the number of the occurrences of the leader
		int numberOfOccurences = (int) IntStream.of(A).filter(x -> x == leader).count();

		// Calculate the number of the equi leaders
		int numberOfEquiLeaders = 0;
		int leftCount = 0;
		int n = A.length;
		int i = 0;
		while (i < n && numberOfOccurences - leftCount != 0) {
			if (A[i] == leader) {
				leftCount++;
			}
			if (leftCount > (i + 1) / 2
					&& (numberOfOccurences - leftCount) > (n - i - 1) / 2) {
				numberOfEquiLeaders++;
			}
			i++;
		}
		return numberOfEquiLeaders;
	}

	private int getLeader(int[] A) {
		int n = A.length;
		int size = 0;
		int value = 0;

		for (int i = 0; i < n; i++) {
			if (size == 0) {
				size++;
				value = A[i];
			} else if (value != A[i]) {
				size--;
			} else {
				size++;
			}
		}

		int candidate = size > 0 ? candidate = value : -1;
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (A[i] == candidate) {
				count++;
			}
		}

		return count > n / 2 ? candidate : -1;
	}
}
