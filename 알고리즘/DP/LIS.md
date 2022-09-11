최장 증가하는 부분수열(LIS) - 순서를 유지한체 몇개의 수를 뽑았을 때, 가장 많이 뽑을 수 있는 부분수열(이전 원소보다 커야한다. 같은거 허용 X )

![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb8dw3b%2FbtqHMvEIzlv%2FLK5wlOyvKbIVuTewTjNr40%2Fimg.png)

___

! 주의할점
1. n이 LIS의 마지막 원소라면 n의 인덱스 i 부터 인덱스 0 까지 n 보다 작은 수들 중, 가장 긴 부분수열을 갖는 수열 + n

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int[] dp = new int[1001];
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dp[1] = 1;

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++)
            max = Math.max(find(i), max);

        System.out.println(max);


    }

    static int find(int i) {
        int max = 0;
        if (dp[i] == 0) {
            for (int j = 1; j < i; j++) {
                if(arr[j] < arr[i])
                    max = Math.max(max, find(j));
            }
            return dp[i] = max +1;
        }

        return dp[i];
    }
}
```

