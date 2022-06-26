# 프로그래머스 실패율

람다식 활용 풀이

```java
import java.util.ArrayList;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] cnt = new int[N +1];
        double[] arr = new double[N + 1];

        int[] answer = new int[N];

        for(int stage : stages) {
            if(stage > N) {
                stage = N;
                cnt[stage]--;
            }
            cnt[stage]++;
            for (int i = 1; i <= stage; i++)
                arr[i]++;
        }

        ArrayList<double[]> rate_array = new ArrayList<>();


        for(int i = 1; i<= N; i++) {
            if (cnt[i] == 0) {
                rate_array.add(new double[]{(double) i, (double) 0});
                continue;
            }

            rate_array.add(new double[]{(double) i, (double) cnt[i] / arr[i]});
        }

        rate_array.sort((a, b) -> Double.compare(b[1], a[1]));

        for(int i=0;i<rate_array.size();i++){
            answer[i]=(int)rate_array.get(i)[0];
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
        return answer;
    }
}
```

___


클래스 정의, Comparable 구현
```java
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    static int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
    static int N = 5;

    public static void main(String[] args) {

        int[] cnt = new int[N +1];
        double[] arr = new double[N + 1];

        int[] answer = new int[N];

        for(int stage : stages) {
            if(stage > N) {
                stage = N;
                cnt[stage]--;
            }
            cnt[stage]++;
            for (int i = 1; i <= stage; i++)
                arr[i]++;
        }

        ArrayList<Node> rate_array = new ArrayList<>();

        for(int i = 1; i<= N; i++) {
            if (cnt[i] == 0) {
                rate_array.add(new Node(i, (double)0));
                continue;
            }

            rate_array.add(new Node(i, (double)cnt[i]/arr[i]));
        }

        Collections.sort(rate_array);

        for(int i=0;i<rate_array.size();i++){
            answer[i] = rate_array.get(i).index;
            System.out.print(rate_array.get(i).fail_rate + " ");

        }

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}

class Node implements Comparable<Node>{
    int index;
    double fail_rate;

    public Node(int index, double fail_rate) {
        this.index = index;
        this.fail_rate = fail_rate;
    }

    public int compareTo(Node a) {
        if(a.fail_rate < this.fail_rate)
            return -1;
        else if(a.fail_rate > this.fail_rate)
            return 1;
        return  this.index - a.index;
    }
}
```

>public int compareTo(Node a) {
if(a.fail_rate != this.fail_rate)
return (int)a.fail_rate - this.fail_rate;
return  this.index - a.index;
}
>> --> a.fail_rate - this.fail_rate 값이 0~1 사이의 실수값이 나오기 때문에 형변환하면 무조건 0이나온다