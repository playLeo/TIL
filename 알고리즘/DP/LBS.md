![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FSYvlN%2FbtqHNvYVabt%2F5uaqJPCtLzn7UXDKrMoX4K%2Fimg.png)

___

**! 주의할점**
1. 모든 원소의 LIS, LDS를 구하고 LIS + LDS -1 

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp = new int[1001][2];
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 0은 정방향 / 1은 역방향
        dp[1][0] = 1;
        dp[n][1] = 1;

        for (int i = 1; i <= n; i++) {
            find(i,0);
            find(n-i+1,1);
        }

        int max = 0;

        for (int i = 1; i <= n; i++) {
            max = Math.max(find(i, 0) + find(i, 1), max);
        }

        System.out.println(max-1);
    }

    static int find(int n, int d) {

        if (dp[n][d] == 0) {

            if (d == 0) {
                int max = 0;
                for (int i = 1; i < n; i++) {
                    if(arr[i] < arr[n])
                        max = Math.max(max, find(i, d));
                }
                return dp[n][d] = max + 1;
            } else {
                int max = 0;
                for (int i = arr.length-1; i >n; i--) {
                    if(arr[i] < arr[n])
                        max = Math.max(max, find(i, d));
                }
                return dp[n][d] = max + 1;
            }
        }

        return dp[n][d];
    }

}
```
