/**
 This code was taken from the "Java Code" section with few alterations
 From "Nearly-Optimal Mergesorts: Fast, Practical Sorting Methods That Optimally Adapt to Existing Runs" paper
 By J. Ian Munro and Sebastian Wild 
 Page 16 and 17
 **/

public class PeekSort{
    public void peeksort(int[] A, int l, int r) {
        peeksort(A, l, r, l, r, new int[r-l+1]);
    }

    public void peeksort(int[] A, int l, int r, int e, int s, int[] B) {
        if (e == r || s == l) return;
        int m = l + ((r - l)/2);

        if (m <= e) {
            peeksort(A, e+1, r, e+1, s, B);
            merge(A, l, e+1, r, B);
        }
        else if (m >= s) {
            peeksort(A, l, s-1, e, s-1, B);
            merge(A, l, s, r, B);
        }
        else {
            int i, j;

            if (A[m] <= A[m+1]) {
                i = extendRunLeftIncrease(A, m, e+1);
                j = m+1 == s ? m : extendRunRightIncrease(A, m+1, s-1);
            }
            else {
                i = extendRunLeftDecrease(A, m, e+1);
                j = m+1 == s ? m : extendRunRightDecrease(A, m+1, s-1);
                reverseRange(A, i, j);
            }

            if (i == l && j == r) return;
            if (m - 1 < j - m) {
                peeksort(A, l, i-1, e, i-1, B);
                peeksort(A, i, r, j, s, B);
                merge(A, l, i, r, B);
            }
            else {
                peeksort(A, l, j, e, i, B);
                peeksort(A, j+1, r, j+1, s, B);
                merge(A, l, j+1, r, B);
            }
        }
    }

    public void merge(int[] A, int l, int m, int r, int[] B) {
        --m;
        int i, j, k;

        for (i = m+1; i > l; --i) B[i-1] = A[i-1];
        for (j = m; j < r; ++j) B[r+m-j] = A[j+1];
        for (k=l; k <= r; ++k) A[k] = B[j] < B[i] ? B[j--] : B[i++];
    }

    public int extendRunLeftIncrease(int[] A, int i, int l){
        while (i > l && A[i-1] <= A[i]) --i;
        return i;
    }

    public int extendRunRightIncrease(int[] A, int i, int r){
        while (i < r && A[i+1] >= A[i]) ++i;
        return i;
    }
    
    public int extendRunLeftDecrease(int[] A, int i, int r){
        while (i > r && A[i-1] > A[i]) --i;
        return i;
    }

    public int extendRunRightDecrease(int[] A, int i, int r){
        while (i < r && A[i+1] < A[i]) ++i;
        return i;
    }

    public void reverseRange(int[] A, int l, int r) {
        while (l < r) {
            int temp = A[l];
            A[l++] = A[r];
            A[r--] = temp;
        }
    }
}