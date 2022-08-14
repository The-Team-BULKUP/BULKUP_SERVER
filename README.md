네이밍 규칙
- 클래스/인터페이스 이름
    - 파스칼표기법을 사용합니다.
    - 예시
    - ```JAVA
      public class reservation
      public class Reservation
      ```
- 테스트 클래스는 'Test’로 끝남
    - 예시
    - ```JAVA
        public class WatcherTest {
      ```
- 메서드 이름에 소문자 카멜표기법 적용
    - 예시
    - ```JAVA
        public void testReservation() {
      ```
- 메서드 이름은 동사/전치사로 시작
    - 예시
    - ```JAVA
      
        public void renderHtml() {
            // 동사 사용예
      }
      
        private void toString() {
            // 전치사 사용예
        }
      ```
      
- 상수는 대문자와 언더스코어로 구성
    - 예시
    - ```JAVA
        public static final String RESERVATION_URL = "http://localhost:8080/reservation";
      ```
- 임시 변수 외에는 1 글자 이름 사용 금지
    - 예시
    - ```JAVA
        HtmlParser p = new HtmlParser(); // bad
        HtmlParser parser = new HtmlParser(); // good
      ```



중괄호
- K&R 스타일로 중괄호 선언
    - 예시
    - ```JAVA
        public class Watcher {
            public void watch() {
                // ...
            }
        }
      ```
