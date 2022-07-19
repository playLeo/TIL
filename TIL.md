# [백준 1167 트리의 지름](https://www.acmicpc.net/problem/1167)

* [트리와 그래프]()


### 풀이방법
 1. 트리의 정보를 인접행렬로 만들고, 임의의 V1에서 DFS로 탐색하여 최댓값이 되는 마지막 V2 찾는다.
 2. V2는 트리 전체의 최댓값을 갖는 경로의 마지막 V2와 일치한다.
 3. V2에서 시작하는 최댓값을 DFS로 탐색.

실수한점

i+1 행에 vertex i 의 정보가 주어지는 조건이 아니지만 주어지는 테스트 케이스만 보고 안일하게 생각했다.

```java
package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;
    static ArrayList<int[]>[] arr;
    static int[] answer;
    static int node;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        arr = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++)
            arr[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int index = Integer.parseInt(st.nextToken());

            while (true) {
                int v = Integer.parseInt(st.nextToken());

                if (v == -1)
                    break;

                int w = Integer.parseInt(st.nextToken());

                arr[index].add(new int[]{v, w});
            }
        }

        dfs(1 , 0);

        visited = new boolean[n + 1];

        dfs(node, 0);

        System.out.print(max);

    }

    static void dfs(int v , int sum) {
        visited[v] = true;
        for (int[] vv : arr[v]) {
            if (!visited[vv[0]]) {
                int nsum = sum + vv[1];
                if (nsum > max) {
                    max = nsum;
                    node = vv[0];
                }
                dfs(vv[0], nsum);
            }
        }

    }

}



```

