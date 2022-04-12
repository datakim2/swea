package swea9760;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class card implements Comparable<card> {
	char pattern;
	int num;

	public card(char pattern, int num) {
		super();
		this.pattern = pattern;
		this.num = num;
	}

	@Override
	public int compareTo(card o) {
		if (this.num == o.num)
			return this.pattern - o.pattern;
		else
			return this.pattern - o.pattern;
	}
}

public class Solution {
	static ArrayList<card> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = stoi(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			list = new ArrayList<>();

			String[] s = br.readLine().split(" ");

			for (int i = 0; i < 5; i++) {
				String[] s2 = s[i].split("");
				char pat = s2[0].charAt(0);
				int num = 0;
				if (s2[1].equals("A")) {
					num = 14;
				} else if (s2[1].equals("T")) {
					num = 10;
				} else if (s2[1].equals("J")) {
					num = 11;
				} else if (s2[1].equals("Q")) {
					num = 12;
				} else if (s2[1].equals("K")) {
					num = 13;
				} else {
					num = stoi(s2[1]);
				}
				list.add(new card(pat, num));
			}

			Collections.sort(list);

			int[] cnt = new int[4];
			int[] nCnt = new int[15];
			for (int i = 0; i < list.size(); i++) {
				char pat = list.get(i).pattern;
				if (pat == 'S')
					cnt[0]++;
				else if (pat == 'D')
					cnt[1]++;
				else if (pat == 'H')
					cnt[2]++;
				else if (pat == 'C')
					cnt[3]++;

				int num = list.get(i).num;
				nCnt[num]++; // 숫자 카드;
			}

			String ans = "";

			nCnt[1] = nCnt[14];

			for (int i = 0; i < 4; i++) {
				if (cnt[i] == 5) {
					boolean royal = true;
					for (int j = 1; j <= 10; j++) {
						if (nCnt[j] == 1 && nCnt[j + 1] == 1 && nCnt[j + 2] == 1 && nCnt[j + 3] == 1
								&& nCnt[j + 4] == 1) {
							royal = false;
						}
					}
					if (royal)
						ans = "Flush";
					else
						ans = "Straight Flush";
				}
			}
			if (ans.equals("")) {
				int triple = 0;
				int pair = 0;
				for (int i = 2; i <= 14; i++) {
					if (nCnt[i] == 4) {
						ans = "Four of a Kind";
						break;
					} else if (nCnt[i] == 3) {
						triple++;
					} else if (nCnt[i] == 2) {
						pair++;
					}
				}

				if (triple == 1 && pair == 1) {
					ans = "Full House";
				} else {
					for (int i = 1; i <= 10; i++) {
						if (nCnt[i] == 1 && nCnt[i + 1] == 1 && nCnt[i + 2] == 1 && nCnt[i + 3] == 1
								&& nCnt[i + 4] == 1) {
							ans = "Straight";
						}
					}

					if (ans.equals("")) {

						if (triple == 1) {
							ans = "Three of a kind";
						} else if (pair == 2) {
							ans = "Two pair";
						} else if (pair == 1) {
							ans = "One pair";
						} else {
							ans = "High card";
						}
					}
				}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
