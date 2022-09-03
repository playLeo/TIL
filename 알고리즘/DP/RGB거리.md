![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcEDffW%2FbtqGtekG8P5%2FJB00I17K0Fi0GbjGjTTQSK%2Fimg.png)

___

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp = new int[1001][3];
    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n+1][3];

        for (int i = 1; i <= n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        dp[1][0] = arr[1][0];
        dp[1][1] = arr[1][1];
        dp[1][2] = arr[1][2];

        int min = Integer.MAX_VALUE;

        min = Math.min(min, find(n, 0));
        min = Math.min(min, find(n, 1));
        min = Math.min(min, find(n, 2));

        System.out.println(min);


    }

    static int find(int n, int m) {
        if(dp[n][m] == 0) {

            if (m == 0)
                return dp[n][m] = Math.min(find(n - 1, 1), find(n - 1, 2)) + arr[n][m];

             else if (m == 1)
                return dp[n][m] = Math.min(find(n - 1, 0), find(n - 1, 2)) + arr[n][m];

             else
                return dp[n][m] = Math.min(find(n - 1, 0), find(n - 1, 1)) + arr[n][m];
        }
        return dp[n][m];
    }


}


```
