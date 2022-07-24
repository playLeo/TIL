package practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static List<Integer> [] list;
    static boolean[] visited;
    static List<Integer> ll;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        while(tc-- >0) {

            int flag = 1;
            int n = Integer.parseInt(br.readLine()); // 팀수 n
            int[] rank = new int[n]; // 등수를 저장할 배열
            st = new StringTokenizer(br.readLine()); // 1등부터 n등 까지의 등수
            for(int i =0; i<n; i++)
                rank[i] = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(br.readLine()); // 변화한 등수 갯수 m

            ll = new LinkedList<>();
            list = new ArrayList[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i < list.length; i++)
                list[i] = new ArrayList<>();

            for(int i =0; i<n-1; i++)
                for(int j = i+1; j<n; j++)
                    list[rank[i]].add(rank[j]);

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(list[a].contains(b)) {
                    flag =0;
                    break;
                }

                list[a].add(b);
                list[b].remove((Object)a);
            }

            if(flag == 0) {
                sb.append("IMPOSSIBLE" + "\n");
                continue;
            }

            for (int i = 1; i < list.length; i++) {
                if (!visited[i])
                    dfs(i);
            }

            for (int i = ll.size() - 1; i >= 0; i--)
                sb.append(ll.get(i) + " ");

            sb.append("\n");
        }

        System.out.print(sb);

    }

    static void dfs(int i) {

        visited[i] = true;

        if (!list[i].isEmpty())
            for(int v : list[i])
                if(!visited[v])
                    dfs(v);

        ll.add(i);
    }
}
