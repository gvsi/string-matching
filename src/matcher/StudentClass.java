package matcher;

public class StudentClass {

	private String text;
	private String pattern;
	private int textLen;
	private int patternLen;
	private int[] prefixFunction;
	private Queue matchIndices;

	public StudentClass(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		this.textLen = text.length();
		this.patternLen = pattern.length();
		this.prefixFunction = new int[patternLen + 1];
		this.matchIndices = new Queue();
	}

	public int[] getPrefixFunction() {
		return prefixFunction;
	}

	public void buildPrefixFunction() {
		prefixFunction = ComputePrexFunction(pattern);
	}

	public Queue getMatchIndices() {
		return matchIndices;
	}

	public void search() {
		matchIndices = KMPMatcher(text, pattern);
	}

	public static int[] ComputePrexFunction(String P) {
		int m = P.length();
		int pi[] = new int[m]; // initialise new array
		pi[0] = 0;
		int k = 0;
		for (int q = 1; q < m; ++q) {
			while (k > 0 && P.charAt(k) != P.charAt(q))
				k = pi[k - 1];

			if (P.charAt(k) == P.charAt(q))
				++k;

			pi[q] = k;
		}
		return pi;
	}

	public static Queue KMPMatcher(String T, String P) {
		int n = T.length();
		int m = P.length();
		int[] pi = ComputePrexFunction(P);
		int q = 0;
		Queue Q = new Queue();

		// End program if pattern is longer than text
		if (m > n) {
			return Q;
		}

		for (int i = 0; i < n; ++i) {
			while (q > 0 && P.charAt(q) != T.charAt(i))
				q = pi[q-1];

			if (P.charAt(q) == T.charAt(i))
				++q;

			if (q == m) {
				Q.enqueue(i - m + 1); //
				q = pi[q-1];
			}

		}
		return Q;
	}

	public static void main(String[] args) {

		// Test against naivematcher
//		NaiveMatcher nm = new NaiveMatcher("hellofromtheotherside", "he");
//		nm.search();
//		System.out.println(KMPMatcher("hellofromtheotherside","he").equals(nm.getMatchIndices()));


		// Tests with Function tests
//		System.out.println(Matcher.PrexFunctionTest("bbcbbabbc"));
//		Matcher.KMPMatcherTest(1000, 100);

		// Task 2 commands
//		Matcher.getRuntimes(10, 100, "matcherTimes.txt");
//		Matcher.getRatios(10, 100, 70000, "matcherRatios.txt");
//		Matcher.plotRuntimes(.010603, .008380, "matcherTimes.txt");
	}
}