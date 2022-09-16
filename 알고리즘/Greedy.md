![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FsLqVl%2FbtqJ6cJnvUL%2FnlUKEgmTZksgpiu3r19dVK%2Fimg.png)


**! 시작과 끝 시간이 같을 수 있는 경우를 생각해야한다.**

**종료시점으로 정렬하면 쉽게 풀수 있다**
<details>
<summary>시작시점으로 정렬</summary>

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int cnt = 1;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                   return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        int[] pre_time = {0, Integer.MAX_VALUE};

        for (int i = 0; i < n; i++) {
            if (pre_time[1] > arr[i][1]) {
                pre_time[0] = arr[i][0];
                pre_time[1] = arr[i][1];
            }
            else{
                if (pre_time[1] <= arr[i][0]) {
                    cnt++;
                    pre_time[0] = arr[i][0];
                    pre_time[1] = arr[i][1];
                }
            }

        }

        System.out.println(cnt);

    }

}






```
</details>

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int cnt = 1;

        int end = arr[0][1];

        for (int i = 1; i < n; i++) {
            if(arr[i][0] >= end) {
                cnt ++;
                end = arr[i][1];
            }
        }

    System.out.print(cnt);
    }

}
```
