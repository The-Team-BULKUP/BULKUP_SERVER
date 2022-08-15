네이밍 규칙
- 클래스/인터페이스 이름
    - 파스칼표기법을 사용합니다.
    - 예시
    - ```JAVA
      public class reservation
      public class Reservation
      ```
- 테스트 클래스는 'Tests’로 끝남
    - 예시
    - ```JAVA
        public class WatcherTests {
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


예외처리
- 모든 예외는 CustomException으로 처리합니다.
  - 예시
  - ```JAVA
    public void signup(ReqestDto req) {
        // 회원 중복 검사 ( 아이디, 핸드폰 )
        accountRepository.findByUsername(req.getUsername())
                .ifPresent(m -> {
                    throw new CustomException(ErrorCode.USERNAME_DUPLICATION);
                });
        .....
  ```  
  
Transaction 처리
- 클래스 레벨에서 ReadOnly 속성을 추가합니다.
    - 예시
    - ```JAVA
        @Transaction(readOnly = true)
        public class Reservation {
      
            public void reserve() {
                // ...
            }
        }
      ```
- 수정이 필요한 메서드에선 ReadOnly = false로 오버라이트합니다.
  - 예시
  - ```JAVA
    @Transactional
    public void signup(ReqestDto req) {
        // 회원 중복 검사 ( 아이디, 핸드폰 )
        accountRepository.findByUsername(req.getUsername())
                .ifPresent(m -> {
                    throw new CustomException(ErrorCode.USERNAME_DUPLICATION);
                });
        .....
  ```
  

...