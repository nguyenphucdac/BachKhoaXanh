package com.dsd26.bachkhoaxanh.business;

import java.util.ArrayList;
import java.util.List;

/*
 * author: Nguyễn Phúc Đạc
 */

public class TimDuongDi {
	public static int MAX_COST = 1000;// gia tri chi phi lon nhat
	public static int[][] minCost = new int[Map.Size_X][Map.Size_Y];
	public static boolean[][] freePoint = new boolean[Map.Size_X][Map.Size_Y];
	public static Point[][] trace = new Point[Map.Size_X][Map.Size_Y];
	public static List<Point> resultWay = new ArrayList<>();
	public static int around;

	public static List<Point> timDuongDi(Point startPoint, Point targetPoint) {

		initValue(startPoint);

		System.out.println("Dang tim kiem xin doi...!!!");

		Point currPoint = new Point(0, 0);
		int min;
		
		while (true) {
			min = MAX_COST;
			for (int i = 0; i < Map.Size_X; i++) {
				for (int j = 0; j < Map.Size_Y; j++) {
					if (freePoint[i][j] == true && minCost[i][j] < min && Map.map[i][j] == 4) {
						min = minCost[i][j];
						currPoint.x = i;
						currPoint.y = j;
					}
				}
			}

			if (currPoint.x == targetPoint.x && currPoint.y == targetPoint.y) {
				break;
			}

			freePoint[currPoint.x][currPoint.y] = false;
			
			around = 0;
			
			if(isInMap(currPoint.x + 1, currPoint.y) == true) {
				if (freePoint[currPoint.x + 1][currPoint.y] == true
						&& Map.map[currPoint.x + 1][currPoint.y] == 4
						&& minCost[currPoint.x + 1][currPoint.y] > minCost[currPoint.x][currPoint.y] + 1

				) {

					minCost[currPoint.x + 1][currPoint.y] = minCost[currPoint.x][currPoint.y] + 1;
					trace[currPoint.x + 1][currPoint.y] = new Point(currPoint.x, currPoint.y);

				}
				else {
					
					around++;
				}
			}
			

			if(isInMap(currPoint.x - 1, currPoint.y) == true) {
				if (freePoint[currPoint.x - 1][currPoint.y] == true
						&& Map.map[currPoint.x - 1][currPoint.y] == 4
						&& minCost[currPoint.x - 1][currPoint.y] > minCost[currPoint.x][currPoint.y] + 1

				) {
					minCost[currPoint.x - 1][currPoint.y] = minCost[currPoint.x][currPoint.y] + 1;
					trace[currPoint.x - 1][currPoint.y] = new Point(currPoint.x, currPoint.y);

				}
				else {
					
					around++;
				}
			}
			
			
			if(isInMap(currPoint.x, currPoint.y + 1) == true) {
				if (freePoint[currPoint.x][currPoint.y + 1] == true
						&& Map.map[currPoint.x][currPoint.y + 1] == 4
						&& minCost[currPoint.x][currPoint.y + 1] > minCost[currPoint.x][currPoint.y] + 1

				) {
					minCost[currPoint.x][currPoint.y + 1] = minCost[currPoint.x][currPoint.y] + 1;
					trace[currPoint.x][currPoint.y + 1] = new Point(currPoint.x, currPoint.y);

				}
				else {
					
					around++;
				}
			}
			
			
			if(isInMap(currPoint.x, currPoint.y - 1) == true) {
				if (freePoint[currPoint.x][currPoint.y - 1] == true
						&& Map.map[currPoint.x][currPoint.y - 1] == 4
						&& minCost[currPoint.x][currPoint.y - 1] > minCost[currPoint.x][currPoint.y] + 1

				) {
					minCost[currPoint.x][currPoint.y - 1] = minCost[currPoint.x][currPoint.y] + 1;
					trace[currPoint.x][currPoint.y - 1] = new Point(currPoint.x, currPoint.y);

				}
				else {
					
					around++;
				}
			}
			
			
			if(around == 4 ) {
				System.out.println("Xung quanh k cos duong");
				break;
			}
			
		}

		System.out.println("ket qua tim kiem duong di ngan nhat :");
		//resultWay.add(targetPoint);
		int i = targetPoint.x, j = targetPoint.y;
		while (true) {
			if(trace[i][j] == null) {
				break;
			}
			Point point = new Point(trace[i][j].x, trace[i][j].y);
			resultWay.add(point);

			i = point.x;
			j = point.y;

			if (i == startPoint.x && j == startPoint.y) {
				break;
			}
		}

		return resultWay;

	}

	public static boolean isInMap(int x, int y) {
		if (x < 0 || x >= Map.Size_X || y < 0 || y >= Map.Size_Y) {
			return false;
		}
		return true;
	}

	public static void initValue(Point startPoint) {
		resultWay = new ArrayList<>();
		trace = new Point[Map.Size_X][Map.Size_Y];
		
		for (int i = 0; i < Map.Size_X; i++) {
			for (int j = 0; j < Map.Size_Y; j++) {
				if (Map.map[i][j] == 4) {
					freePoint[i][j] = true;
					minCost[i][j] = MAX_COST;

				} else {
					freePoint[i][j] = false;
				}

			}
		}
		minCost[startPoint.x][startPoint.y] = 0;
	}

}
