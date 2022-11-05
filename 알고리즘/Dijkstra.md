## 다익스트라

### 최단 경로 + 시작 V로부터 모든 V까지의 계산 + 음의 가중치 여부 -> 다익스트라 사용

### ArrayList로 V, E, W를 정의하고 시작점을 넣어 다익스트라 알고리즘을 시작하자.

### Dijkstra 사용하기
* 시작점 부터 가장 최소인 경로를 확정경로로 편입시켜며 경로를 탐색한다.(나머지 V는 INF 로 정의)
* PriorityQueue를 사용해서 INF가 아닌 경로중 최솟값을 확정 경로로 포함시킨다. -> while문으로 PriorityQueue가 empty가 될때까지 돌린다.
* 확정경로로 포함된 경로를 다시 방문하지 않기 위해 visited를 사용해서 방문 여부를 체크해야한다.(방문 여부를 쉽게 체크할 수 있도록 문제에서 V는 순서대로 숫자로 주어질듯)
* PriorityQueue를 관리 하기 위해 poll된 V에서 갈수있는 V를 탐색(visited 되지 않은 V만) 후 이미 offer된 V와 W를 비교해서 최소 경로를 찾아야 한다.
* 이미 offer된 V보다 더 최소의 경로가 발견된다면 여지없이 offer한다 -> 왜냐하면 PriorityQueue 이기 때문에 최솟값이 먼저 처리되어서 먼저 poll 되고 visited 처리 되기 때문에 괜찮다.
* 최단 경로의 결과를 가지고 있는 단순 배열을 만들어 활용한다.
**결과를 저장하는 배열은 더 빠른 경로가 생긴다면 계속 갱신되는 매커니즘을 가지고 있어야한다.**

**이유는 PriorityQueue에만 해당노드의 도착 결과값을 저장한다면 노드의 최소값을 비교 갱신하기 어렵다.**

**PriorityQueue는 현재 상태의 최선의 값을 저장하고 result에는 최종적으로 최선의 값을 저장한다.**

### 백준 1753
![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2F1nlpl%2Fbtrozwo3C8J%2F9PoHfnBoL8LUOk7kOwLz5K%2Fimg.png)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int id;
    int weight;

    public Node(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

public class Main {

    static List<Node>[] list;
    static int[] result;
    static boolean[] visited;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        list = new ArrayList[v + 1];
        visited = new boolean[v + 1];
        result = new int[v + 1];

        Arrays.fill(result, INF);

        for (int i = 1; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }


        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, weight));
        }

        dijkstra(k);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < result.length; i++) {
            if (result[i] == INF)
                sb.append("INF" + "\n");
            else
                sb.append(result[i] + "\n");
        }

        System.out.println(sb);

    }

    static void dijkstra(int k) {

        //시작시 시작지점 0으로 초기화
        result[k] = 0;
        
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(k, 0));

        while (!q.isEmpty()) {
            Node pollNode = q.poll();

            int id = pollNode.id;
            int weight = pollNode.weight;

            if (visited[id])
                continue;

            //visited를 ture를 주면서 result에 값을 weight를 할당하는 것보다 밑에 로직에서 유동적을 변하게 구성해야 문제가 풀린다.
            visited[id] = true;

            for (int i = 0; i < list[id].size(); i++) {
                Node nextNode = list[id].get(i);

                if (visited[nextNode.id])
                    continue;

                if (result[nextNode.id] > weight + nextNode.weight)
                    result[nextNode.id] = weight + nextNode.weight;

                q.offer(new Node(nextNode.id, result[nextNode.id]));

            }

        }
    }
}

```
