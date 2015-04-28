package lesson04;

/**
 * Given an array A of N integers, we draw N discs in a 2D plane such that the I-th disc is centered on (0,I) and has a radius of A[I]. We say that the J-th disc and K-th disc intersect if J ≠ K and J-th and K-th discs have at least one common point.
 * Write a function:
 *     class Solution { public int solution(int[] A); } 
 * that, given an array A describing N discs as explained above, returns the number of pairs of intersecting discs. For example, given N=6 and:
 * 	- A[0] = 1  
 * 	- A[1] = 5  
 * 	- A[2] = 2
 * 	- A[3] = 1  
 * 	- A[4] = 4  
 * 	- A[5] = 0
 * intersecting discs appear in eleven pairs of elements:
 * 	- 0 and 1,
 * 	- 0 and 2,
 * 	- 0 and 4,
 * 	- 1 and 2,
 * 	- 1 and 3,
 * 	- 1 and 4,
 * 	- 1 and 5,
 * 	- 2 and 3,
 * 	- 2 and 4,
 * 	- 3 and 4,
 * 	- 4 and 5.
 * so the function should return 11.
 * 
 * The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
 * 
 * Assume that:
 * 	- N is an integer within the range [0..100,000];
 * 	- each element of array A is an integer within the range [0..2,147,483,647].
 * 	
 * Complexity:
 * 	- expected worst-case time complexity is O(N*log(N));
 * 	- expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * 
 * Elements of input arrays can be modified.
 * 
 * @author Souchet Celine
 *
 */
public class NumberOfDiscIntersections {
	 
    public int solution(int[] A) {
		int n = A.length;
		// From N + 1, no circle can begin, because the center of the last
		// circle is in N.
		// Need 2 arrays for the event of beginning & end, because a circle can
		// start & terminate in the same time when the radius is 0. So, if it's in the same 
		// array, when calculate the number of the intersection, we can't see this circle.
		int[] starts = new int[n + 1];
		int[] ends = new int[n + 1];

		for (int i = 0; i < n; i++) {
			int discRadius = Math.abs(A[i]);
			// To tag the beginning of the circle
			long indexOfBeginning = (long) i - (long) discRadius;
			starts[(int) (indexOfBeginning < 0 ? 0 : indexOfBeginning)]++;

			// To tag the end of the circle
			long indexOfEnd = (long) i + (long) discRadius;
			ends[(int) (indexOfEnd > n ? n : indexOfEnd)]++;
		}

		int numberOfIntersections = 0;
		int numberOfActiveCircles = 0;
		for (int i = 0; i < n + 1; i++) {
			int numberOfNewCircles = starts[i];
			if (numberOfNewCircles > 0) {
				numberOfIntersections += numberOfActiveCircles * numberOfNewCircles 
						+ (numberOfNewCircles - 1) * numberOfNewCircles / 2;
				numberOfActiveCircles += numberOfNewCircles;
				if (numberOfIntersections > 10000000) {
					return -1;
				}
			}
			if (ends[i] > 0) {
				numberOfActiveCircles -= ends[i];
			}
		}
		return numberOfIntersections;
	}
}
