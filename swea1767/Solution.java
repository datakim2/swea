package swea1767;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static int N, ans, size;
	static int[][] map;
	static int[][] originMap;
	static boolean[] check;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Point> p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = stoi(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			map = new int[N][N];
			originMap = new int[N][N];
			size = 0;
			p = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = stoi(s[j]);
					originMap[i][j] = map[i][j];
					if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						p.add(new Point(i, j));
						size++; // 코어 개수
					}
				}
			}

			check = new boolean[size];
			ans = Integer.MAX_VALUE;
			for (int i = size; i >= 0; i--) {
				backTracking(0, 0, i);
				if (ans != Integer.MAX_VALUE)
					break;
			}

			System.out.printf("#" + tc + " " + ans + "\n");
		}

	}

	public static void dfs(int idx, int cnt) {
		if (idx == size) {
			ans = Math.min(ans, cnt);
			return;
		}

		if (!check[idx]) {
			dfs(idx + 1, cnt);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = p.get(idx).x;
			int ny = p.get(idx).y;
			int lineSize = 0;
			boolean isFinal = false;
			while (true) {
				nx += dx[i];
				ny += dy[i];
				if (!isAccesible(nx, ny)) {
					isFinal = true;
					break;
				}
				if (map[nx][ny] != 0)
					break;
				map[nx][ny] = 2;
				lineSize++;
			}

			if (isFinal) {
				dfs(idx + 1, cnt + lineSize);
			}

			while (true) {
				nx -= dx[i];
				ny -= dy[i];
				if (nx == p.get(idx).x && ny == p.get(idx).y)
					break;
				map[nx][ny] = 0;
			}
			for(int k=0; k<N; k++) {
				for(int j=0; j<N; j++) {
					System.out.print(map[k][j]+" ");
				}
				System.out.println();
			}

			/*
			 * for (int t = 0; t < N; t++) { for (int p = 0; p < N; p++) { map[t][p] =
			 * originMap[t][p]; } }
			 */

		}
	}

	static boolean isAccesible(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		else
			return true;
	}

	public static void backTracking(int start, int depth, int coreSize) {
		if (depth == coreSize) {
			dfs(0, 0);
			return;
		}

		for (int i = start; i < size; i++) {
			check[i] = true;
			backTracking(i + 1, depth + 1, coreSize);
			check[i] = false;
		}

	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
