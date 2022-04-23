package swea4013;

import java.awt.Point;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

public class Solution {
	static int K, N, M;
	static Deque<Integer>[] dq;
	static ArrayList<Integer>[] idx;
	static ArrayList<Point> order;
	static boolean[][] eq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = stoi(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			K = stoi(br.readLine());

			dq = new ArrayDeque[5];
			idx = new ArrayList[5];
			eq = new boolean[5][5]; // 12 23 34
			order = new ArrayList<>();
			for (int i = 1; i <= 4; i++) {
				dq[i] = new ArrayDeque<>();
				idx[i] = new ArrayList<>();
			}

			for (int i = 1; i <= 4; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					dq[i].add(stoi(s[j]));
					idx[i].add(stoi(s[j]));
				}
			}

			for (int i = 0; i < K; i++) {
				String[] s2 = br.readLine().split(" ");
				order.add(new Point(stoi(s2[0]), stoi(s2[1])));
			}

			for (int i = 0; i < order.size(); i++) {

				boolSetting();
				work(order.get(i));
				idxSetting();
			}
			System.out.println("#" + tc + " " + cal());
		}
	}

	public static int cal() {
		int sum = 0;
		for (int i = 1; i <= 4; i++) {
			sum += dq[i].peekFirst() * Math.pow(2, i - 1);
		}
		return sum;
	}

	public static void boolSetting() {
		eq = new boolean[5][5];

		if (idx[1].get(2) == idx[2].get(6)) {
			eq[1][2] = true;
			eq[2][1] = true;
		}
		if (idx[2].get(2) == idx[3].get(6)) {
			eq[2][3] = true;
			eq[3][2] = true;
		}
		if (idx[3].get(2) == idx[4].get(6)) {
			eq[3][4] = true;
			eq[4][3] = true;
		}

	}

	public static void work(Point p) {
		int x = p.x;
		int dir = p.y;

		if (x == 1) {

			if (dir == 1) {
				dq[x].addFirst(dq[x].pollLast());
			} else if (dir == -1) {
				dq[x].addLast(dq[x].pollFirst());
			}

			if (eq[1][2]) {
			} else if (!eq[1][2]) {
				if (dir == 1) {
					dq[2].addLast(dq[2].pollFirst());
				} else if (dir == -1) {
					dq[2].addFirst(dq[2].pollLast());
				}

				if (eq[2][3]) {
				} else if (!eq[2][3]) {
					if (dir == 1) {
						dq[3].addFirst(dq[3].pollLast());
					} else if (dir == -1) {
						dq[3].addLast(dq[3].pollFirst());
					}

					if (eq[3][4]) {
					} else if (!eq[3][4]) {
						if (dir == 1) {
							dq[4].addLast(dq[4].pollFirst());
						} else if (dir == -1) {
							dq[4].addFirst(dq[4].pollLast());
						}
					}
				}
			}
		} else if (x == 2) {

			if (dir == 1) {
				dq[x].addFirst(dq[x].pollLast());
			} else if (dir == -1) {
				dq[x].addLast(dq[x].pollFirst());
			}

			if (eq[1][2]) {
			} else if (!eq[1][2]) {
				if (dir == 1) {
					dq[1].addLast(dq[1].pollFirst());
				} else if (dir == -1) {
					dq[1].addFirst(dq[1].pollLast());
				}
			}
			if (eq[2][3]) {
			} else if (!eq[2][3]) {
				if (dir == 1) {
					dq[3].addLast(dq[3].pollFirst());
				} else if (dir == -1) {
					dq[3].addFirst(dq[3].pollLast());
				}

				if (eq[3][4]) {
				} else if (!eq[3][4]) {
					if (dir == 1) {
						dq[4].addFirst(dq[4].pollLast());
					} else if (dir == -1) {
						dq[4].addLast(dq[4].pollFirst());
					}
				}
			}
		} else if (x == 3) {

			if (dir == 1) {
				dq[x].addFirst(dq[x].pollLast());
			} else if (dir == -1) {
				dq[x].addLast(dq[x].pollFirst());
			}

			if (eq[3][2]) {
			} else if (!eq[3][2]) {
				if (dir == 1) {
					dq[2].addLast(dq[2].pollFirst());
				} else if (dir == -1) {
					dq[2].addFirst(dq[2].pollLast());
				}

				if (eq[2][1]) {
				} else if (!eq[2][1]) {
					if (dir == 1) {
						dq[1].addFirst(dq[1].pollLast());
					} else if (dir == -1) {
						dq[1].addLast(dq[1].pollFirst());
					}
				}
			}

			if (eq[3][4]) {
			} else if (!eq[3][4]) {
				if (dir == 1) {
					dq[4].addLast(dq[4].pollFirst());
				} else if (dir == -1) {
					dq[4].addFirst(dq[4].pollLast());
				}
			}
		}

		else if (x == 4) {

			if (dir == 1) {
				dq[x].addFirst(dq[x].pollLast());
			} else if (dir == -1) {
				dq[x].addLast(dq[x].pollFirst());
			}

			if (eq[4][3]) {
			} else if (!eq[4][3]) {
				if (dir == 1) {
					dq[3].addLast(dq[3].pollFirst());
				} else if (dir == -1) {
					dq[3].addFirst(dq[3].pollLast());
				}

				if (eq[3][2]) {
				} else if (!eq[3][2]) {
					if (dir == 1) {
						dq[2].addFirst(dq[2].pollLast());
					} else if (dir == -1) {
						dq[2].addLast(dq[2].pollFirst());
					}

					if (eq[2][1]) {
					} else if (!eq[2][1]) {
						if (dir == 1) {
							dq[1].addLast(dq[1].pollFirst());
						} else if (dir == -1) {
							dq[1].addFirst(dq[1].pollLast());
						}
					}
				}
			}
		}

	}

	public static void idxSetting() {
		for (int i = 1; i <= 4; i++) {
			idx[i] = new ArrayList<>();
			for (Iterator<Integer> it = dq[i].iterator(); it.hasNext();) {
				idx[i].add(it.next());
			}
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
