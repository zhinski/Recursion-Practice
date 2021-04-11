// Michael Dobrzanski

public class RecursionIntro {
	public static long eduodd(long n) { // even digits up, odd digits down, check practice-it
		if (n < 0){
			return -eduodd(-n); // make positive, make result negative
		}
		if (n % 2 == 0)
			n++;  //increase even by one
		else
			n--; //decrease odd by one
		if (n < 10)
			return n;
		return 10 * eduodd(n / 10) + n % 10;
	}

	public static int fibby(int n) {
		if(n < 0) {
			throw new IllegalArgumentException(); // cause a runtime exception
		}
		if (n == 0) { // base case
			return 1;
		}
		return fibby(n / 4) + fibby(3 * n / 4);
	}

	public static void printsparsetable(int start, int end) { // print from start to end, no duplicates within range
		System.out.println(start + " " + fibby(start)); //automatically print start
		start++;
		fibbyCheck (start, end); //increase and check, don't print duplicates
	}
	public static void fibbyCheck (int start, int end) { //skip duplicate fibby output
		if (start != end + 1){ 
			if (fibby(start) == (fibby(start-1))) { // check next against previous, if equal skip
				start++;
				fibbyCheck (start, end);
			}
			else {
				System.out.println(start + " " + fibby(start));
				start++;
				fibbyCheck (start, end);
			}
		}
	}

	public static double median3(int[] a) { //split into 3 sections, find median of each, find median of medians
		return medianFinder (a, 0, a.length - 1);
	}

	public static double medianFinder(int[] a, int start, int end) {
		if (a.length == 1 || start == end) {
			return a[start];
		}
		if (a.length == 2 || end - start == 1) {
			return ((a[start] + a[end]) / 2.0); //return double since average
		}
		if (a.length == 3 || end - start == 2) { //rock paper scissors logic
			if (a[start] > a[start + 1] && a[start] < a[end] || a[start] < a[start + 1] && a[start] > a[end]){
				return a[start];
			}
			else if (a[start + 1] > a[start] && a[start + 1] < a[end] || a[start + 1] < a[start] && a[start + 1] > a[end]) {
				return a[start + 1];
			}
			else {
				return a[end];
			}
		}
		return medianHelper(a, start, end);
	}

	public static double medianHelper (int[] a, int start, int end) { //when array or split median > 3
		double x = 0;
		double y = 0;
		double z = 0;
		if ((end - start + 1) % 3 == 1) {  //+1 eliminates off-by-one, add start to starts when array > 9
			x = medianFinder(a, start, (end - start) / 3 - 1 + start);
			y = medianFinder(a, (end - start) / 3 + start, 2 * ((end - start) / 3) + start);
			z = medianFinder(a, 2 * ((end - start) / 3) + start + 1, end);
		}
		if ((end - start + 1) % 3 == 2) {
			x = medianFinder(a, start, (end - start) / 3 + start);
			y = medianFinder(a, (end - start) / 3 + start + 1, 2 * ((end - start) / 3) + start);
			z = medianFinder(a, 2 * ((end - start) / 3) + start + 1, end);
		}
		if ((end - start + 1) % 3 == 0) { 
			x = medianFinder(a, start, (end - start) / 3 + start);
			y = medianFinder(a, (end - start) / 3 + start + 1, 2 * ((end- start) / 3) + start + 1);
			z = medianFinder(a, 2 * ((end- start) / 3) + start + 2, end);
		}
		if (x > y && x < z || x < y && x > z ) {  //see if x is the median
			return x;
		}
		if (y > x && y < z || y < x && y > z) {   //see if y is the median
			return y;
		}
		else {
			return z;
		}
	}
}