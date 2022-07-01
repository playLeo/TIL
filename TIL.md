# 최대공약수(GCD), 최소공배수

#### 최대공약수(GCD) - Greatest Common Divisor

[유클리드 호제법](https://st-lab.tistory.com/154)(Euclidean algorithm)

두 수 a, b ∈ ℤ 이고, r = a mod b 이라고 한다. (r 은 a에 b를 나눈 나머지를 의미)

이 때 r은 (0 ≤ r < b) 이고, a ≥ b 이다.



이 때 a 와 b의 최대공약수를 (a, b)라고 할 때 (a, b)의 최대공약수는 (b, r)의 최대공약수와 같다.

즉, 아래와 같다는 의미다.

GCD(a, b) = GCD(b, r)

r 이 0 이 되는 순간 b 의값이 최대공약수가 된다.
