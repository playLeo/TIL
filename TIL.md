# 소수구하기

1. N 보다 작은 자연수들로 모두 나눠본다. O(N²)
2. √N 이하의 자연수들로 모두 나눠본다. O(N√N)
> 자연수 N은 √N보다 작거나 같은 두 수 A,B의 곱으로 표현할수 있기 때문에

3. 에라토스테네스의 체  O(Nlog(log N))
> k=2 ~ √N 이하까지 반복하여 k 를 제외한 k의 배수를 제외시킨다
![에라토스테네스의 체 이미지](https://blog.kakaocdn.net/dn/vcP21/btqDp03wCZB/2zaIgivKDhL6PQp7AQpIb1/img.gif)

50 ~ 100까지의 소수의 합을 구하여라
```java
public class Main {

    static int A = 50;
    static int B = 100;
    static boolean[] prime;

    public static void main(String[] args) {

        int answer = 0;

        prime_check(B);

        for (int i = A; i <= B; i++)
            if(prime[i] == false)
                answer += i;

        System.out.print(answer);
    }

    //소수라면 false를 나타는 prime 배열 만들기
    static void prime_check(int num) {

        prime = new boolean[num + 1];
        // 소수가 아닌 0,1에 true
        prime[0] = prime[1] = true;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            //중복해서 접근하는 경우 pass
            if(prime[i] == true)
                continue;

            //i값을 제외한 i값의 배수들을 ture로 만들어주기
            //i*2 가아닌 i*i로 가는 이유는 i보다 작은 자연수는 앞선 상황에서 처리되었다
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}
```