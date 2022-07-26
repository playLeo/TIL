# Heap

완전 이진 트리로서 아래조건을 만족한다. (최소힙 기준)
* 각 노드의 값은 자기 자식의 값보다 작다(중복 원소가 있는경우 작거나 같다.)
* 리프 노드는 자식이 없으므로 자동 만족.

트리에서 부모자식 간의 관계를 나타내기 위해 보통의 트리를 표현할 때 쓰는 링크나 포인터 같은 것을 사용해서 구현할 수 있으나,
힙은 완전 이진트리이기 때문에 **배열**을 사용하여 간단하게 구현할 수 있다.

- 부모노드 A[K], 자식노드 A[2K], A[2K+1] 로 표현가능

[백준 최대힙](https://www.acmicpc.net/problem/11279)
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] arr = new int[100001];
    static int size = 0;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());

            if (v == 0) {
                pop();
            }
            else
                add(v);

        }


        System.out.print(sb);
    }

    static void pop() {
        if(size == 0)
            sb.append("0\n");
        else {
            sb.append(arr[1] + "\n");
            swap(1,size);
            size--;
            for (int i = size / 2; i > 0; i--) {
                heapify(i);
            }
        }
    }

    static void add(int v) {
        arr[++size] = v;
        for (int i = size / 2; i > 0; i--) {
            heapify(i);
        }
    }

    static void heapify(int i) {
        int max;
        if(i*2+1 <= size){
            max = Math.max(arr[i*2], arr[i*2 +1]);
            if(arr[i] < max){
                if(arr[i*2] == max) {
                    swap(i, i * 2);
                    heapify(i*2);
                }
                else{
                    swap(i, i*2 +1);
                    heapify(i * 2 + 1);
                }
            }
        } else if (i * 2 <= size) {
            if (arr[i] < arr[i * 2]) {
                swap(i, i * 2);
                heapify(i*2);
            }
        }

    }

    static void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
```

1. 배열을 heapify
2. 원소 추가시 배열에 삽입후 heapify
3. 원소 삭제시 root 노드 삭제후 마지막 노드 root로 올리고 heapify
4. 부모와 자식노드 교환시 자식노드로 교환된 노드 heap성질 만족 여부 확인후 heapify 진행

! 완전이진트리기 때문에 O(log N)

! Heap 구현시 index는 '1'부터 시작해야 편하다.

! default는 최소힙

! peek하면 pop되는 값이나온다. -> index 첫 번째 값

! Max Heap 구현시 new PriorityQueue<>(Comparator.reverseOrder()) 

! PriorityQueue는 추상자료형 / Heap 은 자료구죠

! q.isEmpty 가 true 인 상황에서 poll()해도 Exception 발생안하고 null이 반환된다.
