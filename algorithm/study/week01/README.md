### [코딩인터뷰완전분석 스터디 과제] 1주차 
[최대공약수와 최소공배수](https://github.com/brainbackdoor/bbd-study-algorithm/blob/algorithm/programmers/week01/src/task01/TryHelloWorld.java)

[행렬의 덧셈](https://github.com/brainbackdoor/bbd-study-algorithm/blob/algorithm/programmers/week01/src/task01/SumMatrix.java)

[약수의 합](https://github.com/brainbackdoor/bbd-study-algorithm/blob/algorithm/programmers/week01/src/task01/SumDivisor.java)

---
### 코드 복잡성확인을 위한 방법  
[[sonarqube]](https://www.sonarqube.org/) - cyclomatic complexity  
[[codacy]](https://app.codacy.com/app/brainbackdoor/bbd-study-algorithm/dashboard?bid=6803008) - churn/complexity  
[[pmd]](http://www.baeldung.com/java-static-analysis-tools)  
[[pythontutor]](http://www.pythontutor.com/visualize.html#mode=edit) - traversal  
[[codility]](https://app.codility.com/programmers/lessons/1-iterations/) - big-O  

#### 코드실행 시간 계산
```
	private static final Logger log = LoggerFactory.getLogger(SumDivisorTest.class);
	private int limit = 100000000;

	@Test
	public void 약수의합_테스트() {
		SumDivisor sum = new SumDivisor();
		new Thread(() -> {
			long start = System.currentTimeMillis();
			for (int i = limit; i-- > 0;) {
				sum.sumDivisor(12);
			}
			log.debug("elapsed time for task01 : {}ms", System.currentTimeMillis() - start);
		}).run();
	}
```
