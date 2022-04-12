package swea5604;

import java.io.*;

public class Solution {
	static long number[], res, start, end, diff, mul;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = stoi(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String[] s = br.readLine().split(" ");

			start = Long.parseLong(s[0]);
			end = Long.parseLong(s[1]);

			number = new long[10];
			res = 0;
			mul = 1;
			if (start == 0) {
				start = 1;
			}

			while (true) {
				while (start % 10 != 0 && start <= end) {
					cal(start);
					start++;
				}
				if (start > end)
					break;
				while (end % 10 != 9 && start <= end) {
					cal(end);
					end--;
				}
				diff = end / 10 - start / 10 + 1;
				for (int i = 0; i < 10; i++) {
					number[i] += diff * mul;
				}
				mul *= 10;
				start /= 10;
				end /= 10;
			}

			for (int i = 1; i < 10; i++) {
				res += (i * number[i]);
			}
			System.out.println("#" + tc + " " + res);
		}

	}

	public static void cal(long a) {
		for (long i = a; i > 0; i /= 10) {
			String s = Long.toString(i);
			int t = s.charAt(s.length() - 1) - '0';
			number[t] += mul;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
