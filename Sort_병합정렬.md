# 합병정렬 / 병합정렬 (Merge Sort)
___

분할정복(Divide and Comquer) 알고리즘 기반으로 정렬되는 방식

비교 정렬이며 데이터 외에 추가적인 공간을 필요로 하기 때문에 제자리 정렬이 아닌다.

합병정렬의 구조상 최대한 작게 문제를 쪼개어 차례대로 합치기 때문에 안정정렬(Stable Sort) 알고리즘 이다.

>안정정렬이란 ?
> 
> 동일한 키 값의 요소 순서가 정렬 후 유지가 되는 알고리즘.
> 
> 버블정렬, 삽입정렬, 병합정렬



## 병합정렬 장점 및 단점

**[장점]**
- 최악의 경우에도 O(N longN) 보장한다.
- 안정정렬이다.

**[단점]**
- 정렬과정에서 보조 배열을 사용하기 때문에 메모리 사용량이 많다.
- 데이터가 많을 경우 보조배열에서 원본배열로 데이터를 복사하는 과정에서 많은 시간이 소요된다.


**재귀를 이용한 Top-Down 구현**

```java
public class Main {

    static int[] array = {6, 8, 5, 7, 2, 3, 4, 1};
    static int[] dummy_array = new int[array.length];

    public static void main(String[] args) {
        merge_sort(0, array.length-1);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    static void merge_sort(int start, int end) {
        int mid = (start + end) / 2;

        if (start < end) {
            merge_sort(start, mid);
            merge_sort(mid + 1, end);
            merge(start, end, mid);
        }
    }

    static void merge(int start, int end, int mid) {
        //merge대상 index를 순회할 2개의 포인터와 start부터 차례대로 넣어줄 index 생성
        int p = start;
        int q = mid +1;
        int index = start;

        while (p <= mid || q <= end) {
            if (p <= mid && q > end)
                dummy_array[index++] = array[p++];
            else if(p > mid && q <= end)
                dummy_array[index++] = array[q++];
            else{
                if(array[p] > array[q])
                    dummy_array[index++] = array[q++];
                else
                    dummy_array[index++] = array[p++];
            }
        }

        for (int i = start; i <= end; i++) {
            array[i] = dummy_array[i];
        }
    }
}
```

재귀를 사용하여 Top-Down 방식을 사용했다.

대부분의 정렬의 경우 재귀는 피하여 구현하는게 일반적이다.

___
## 정렬 알고리즘별 시간복잡도
![정렬 알고리즘 table](https://blog.kakaocdn.net/dn/c7BCcK/btq2XiZbCaC/bLhp5sS7MZiFj1dZNB6OGk/img.png)
