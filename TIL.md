# DFS

* 그래프 순회
<details>
<summary>백준 24480</summary>
<div mardown="1">

[백준 24480](https://www.acmicpc.net/problem/24480)


오늘도 서준이는 깊이 우선 탐색(DFS) 수업 조교를 하고 있다. 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.

N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다. 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다. 정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.

깊이 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 **내림차순**으로 방문한다.
_____
입력

첫째 줄에 정점의 수 N (5 ≤ N ≤ 100,000), 간선의 수 M (1 ≤ M ≤ 200,000), 시작 정점 R (1 ≤ R ≤ N)이 주어진다.

다음 M개 줄에 간선 정보 u v가 주어지며 정점 u와 정점 v의 가중치 1인 양방향 간선을 나타낸다. (1 ≤ u < v ≤ N, u ≠ v) 모든 간선의 (u, v) 쌍의 값은 서로 다르다.
___
출력

첫째 줄부터 N개의 줄에 정수를 한 개씩 출력한다. i번째 줄에는 정점 i의 방문 순서를 출력한다. 시작 정점의 방문 순서는 1이다. 시작 정점에서 방문할 수 없는 경우 0을 출력한다.
___

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] answer;
    static boolean[] check;
    static ArrayList<Integer>[] arrayLists;
    static int cnt = 0;


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        arrayLists = new ArrayList[n+1];
        answer = new int[n+1];
        check = new boolean[n+1];

        //ArrayList를 만들어 무방향 그래프의 정보를 저장한다.
        for (int i = 1; i <= n; i++)
            arrayLists[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //방향이 없기때문에 양방향정보를 저장해야한다.
            arrayLists[a].add(b);
            arrayLists[b].add(a);
        }


        //내림차순으로  방문하기위해 Collections.reverseOrder()
        for(int i = 1; i <= n; i++) {
            Collections.sort(arrayLists[i], Collections.reverseOrder());
        }

        check[r] = true;
        dfs(r);

        for (int i = 1; i < answer.length; i++) {
            sb.append(answer[i] + "\n");
        }

        System.out.print(sb);
    }

    static void dfs(int r) {
        answer[r]  = ++cnt;
        for (int i = 0; i < arrayLists[r].size(); i++) {
            if(check[arrayLists[r].get(i)])
                continue;
            check[arrayLists[r].get(i)] = true;
            dfs(arrayLists[r].get(i));
        }

    }

}

```
</div>
</details>

* 조합 모두 탐색
<details>
<summary>프로그래머스 단체사진</summary>
<div mardown="1">

[프로그래머스 단체사진    ](https://school.programmers.co.kr/learn/courses/30/lessons/1835)




</div>
</details>

* 좌표순회
<details>
<summary>백준 2667</summary>
<div mardown="1">

[백준 2267](https://www.acmicpc.net/problem/2267)

<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.


![그림](https://www.acmicpc.net/upload/images/ITVH9w1Gf6eCRdThfkegBUSOKd.png)
___
입력

첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
___
출력

첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
___
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static int n;
    static int[][] arr;
    static int cnt = 0;
    static ArrayList<Integer> arrayList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n][n];
        arrayList = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {     
                
                if(arr[i][j] == 1 && !visited[i][j])
                    dfs(i,j);
                
                if (cnt != 0) {
                    arrayList.add(cnt);
                    cnt = 0;
                }
            }
        }

        Collections.sort(arrayList);

        sb.append(arrayList.size() + "\n");
        for (int i = 0; i < arrayList.size(); i++) {
            sb.append(arrayList.get(i) + "\n");
        }

        System.out.print(sb);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        ++cnt;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && arr[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }

}

```

유사한문제

* [카카오 컬러링북](https://school.programmers.co.kr/learn/courses/30/lessons/1829)
</div>
</details>