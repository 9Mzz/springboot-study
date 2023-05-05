package org.example;

import java.util.*;

public class BakalProg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받을 공대원의 수
        System.out.print("공대원의 수를 입력하세요");
        int n = scanner.nextInt();

        // 입력 받을 딜러의들 수를 배열에 저장
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "딜러의 수를 입력하세요");
            numbers[i] = scanner.nextInt();
        }

        // 중복 제거한 숫자들의 개수
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (int i = 0; i < n; i++) {
            uniqueNumbers.add(numbers[i]);
        }
        int uniqueCount = uniqueNumbers.size();

        // 중복 제거한 숫자들을 배열에 저장
        int[] uniqueArray = new int[uniqueCount];
        int index = 0;
        for (int number : uniqueNumbers) {
            uniqueArray[index] = number;
            index++;
        }

        // 그리디 알고리즘
        int[][] array = new int[4][uniqueCount];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < uniqueCount; j++) {
                if (i == 0) {
                    array[i][j] = uniqueArray[j];
                } else {
                    int minValue = Integer.MAX_VALUE;
                    int minIndex = -1;
                    for (int k = 0; k < uniqueCount; k++) {
                        if (uniqueArray[k] != array[i - 1][j]) {
                            int value = findMaxValue(array, i - 1, k) - uniqueArray[k];
                            if (value < minValue) {
                                minValue = value;
                                minIndex = k;
                            }
                        }
                    }
                    array[i][j] = uniqueArray[minIndex];
                }
            }
        }

        // 생성된 배열을 출력
        System.out.println("생성된 배열:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < uniqueCount; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int findMaxValue(int[][] array, int i, int k) {
    }