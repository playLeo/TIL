# 퀵 정렬 (Merge Sort)
___

하나의 리스트를 피벗(pivot)을 기준으루 두개의 부분 리스트로 나누어 하나는 피벗보다 작은값들의 부분리스트, 다른 하난는 피벗보다 큰 값들의 부분리스트로 정렬한 다음, 각 부분리스트들에 대해 다시 위 처럼 재귀적으로 수행하는 정렬방법

제자리 정렬이며, 서로 떨어진 원소의 교환이 이루어 지기 때문에 불안전 정렬이다.

## 퀵 정렬 장점 및 단점

**[장점]**
- 특정 상태가 아닌 이상 평균 O(N logN) 보장
- 다른 O(N logN) 알고리즘에 비해 2 ~ 3배 빠르다
- 제자리정렬이기 때문에 메모리를 적게 사용한다

**[단점]**
- 특정 조건에서는 성능이 급격히 떨어진다(선택하는 피벗이 최대, 최소일 때)
- 재귀를 사용하지 않고 구현시 매우 복잡하다.


**재귀를 이용한 Top-Down 구현**

```java
public class Main {

    static int[] array = {6, 8, 5, 7, 2, 3, 4, 1};

    public static void main(String[] args) {
        quickSort(0, array.length-1);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    static void quickSort(int start, int end) {
        int pivot = array[(start + end) / 2]; //임의의 기준값 pivot
        int lo = start;
        int hi = end;

        do {
            while(array[lo] < pivot) lo++;
            while(array[hi] > pivot) hi--;

            if (lo <= hi) {
                System.out.println(lo + " " + hi);
                swap(lo, hi);
                lo++;
                hi--;
            }
        }while(lo<=hi);

        if(lo < end) quickSort(start, lo);
        if(hi > start) quickSort(hi, end);
    }

    static void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

```

___
## 정렬 알고리즘별 시간복잡도
![정렬 알고리즘 table](https://img1.daumcdn.net/thumb/R1920x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FIfrns%2Fbtq6GpMWO0O%2FpEbND8sKYxTWyMtFqCHmVk%2Fimg.png)
