![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFVOby%2FbtqH49oKKnX%2FbKDqwO3atspxS6SHkjHlTk%2Fimg.png)

**! 주의할점**

1. 문자 n이 LCS의 마지막 원소라면으로 접근하면 n이 중복으로 나올 수 있기 때문에 subsequence를 잡기 힘들다.
2. 부분 수열의 순서가 지켜지기 때문에 표를만들어 해결하자
3. 재귀에서 r,c 의 -1 에 접근하기 때문에 범위를 벗어난 경우 0을 리턴하자

| |A|C|A|Y|K|P|
|---|---|---|---|---|---|---|
|C|0|1|1|1|1|1|
|A|1|1|2|2|2|2|
|P|1|1|2|2|2|3|
|C|0|2|2|2|2|3|
|A|1|2|3|3|3|3|
|K|1|2|3|3|4|4|

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static Integer[][] dp;
    static char[] s1, s2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();

        dp = new Integer[s2.length][s1.length];

        System.out.println(find(s2.length - 1, s1.length - 1));
    }

    static int find(int r, int c) {

        if(r == -1 || c == -1)
            return 0;

        if (dp[r][c] == null) {
            if(s2[r] == s1[c])
                return dp[r][c] = find(r - 1, c - 1) + 1;

            return dp[r][c] = Math.max(find(r - 1, c), find(r, c - 1));
        }
        return dp[r][c];
    }
}
```
