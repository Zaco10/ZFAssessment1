package org.assessment;

public class HCFFinder {
    public static void main(String[] args) {
        int[] arr = {6, 24, 36, 48};
        int hcf = findHCF(arr);
        System.out.println("HCF: " + hcf);
    }

    public static int findHCF(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = findHCF(result, arr[i]);
        }
        return result;
    }

    public static int findHCF(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return findHCF(b, a % b);
        }
    }
}
