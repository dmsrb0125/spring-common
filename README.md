# 스프링 프로젝트 컨벤션

## 사용 기술
- Java
- Spring boot
- JPA
- MySQL

## 통합적 컨벤션
- 도메인별 패키직관리(그 안에서 컨트롤러,서비스,레퍼지토리,dto별 관리)
- 메세지는 한국말로 작성
- 주석 사용하여 기능별 설명
- application.yml 으로 관리 (각 로컬별관리가 필요한 부분이나 노출시키면안되는정보는 환경변수로 관리)

## 인증인가
- `@Authentication`를 사용하여 로그인정보가 저장된 UserDetailsImpl를 가져올수있게한다
  - 게시물 등을 생성할때 로그인된 정보를 주입하여 데이터 넣어주기
  - 수정삭제같은 로직을 수행할때 로그인정보와 데이터의 정보 확인하여 권한 확인
  - 프로필업로드과 같은 로그인상태에서만 가능한  api요청 간소화및 보안화   user/1/profile(x)  user/profile(o)
- 인증인가를 하지않고 열어두어야하는 요청은  `WebSecurityConfig(시큐리티 설정)`, `JwtAuthorizationFilter(인가 필터)` 두곳에서  설정해준다

## Http 응답
- ResponseUtils : 요청에 따른 사용해야할 응답 메서드 정의
- HttpResponseDto : 요청에 따른 보내줘야할 응답DTO 정의(빌더를 사용하여 상태코드 ,메세지, 데이터를 선택적으로 응답)
- 기본적으로 요청에따른 성공은 상태코드만 반환
- 조회같이 데이터가필요한경우 상태코드와 데이터 반환
- 예상된 에러가 발생해여 실패한경우  `ResponseCodeEnum`을 사용해서 상태코드와,메세지를 이넘으로 정의하고 이것을 반환

## 예외처리
- `GlobalExceptionAdvice`에 글로벌 관리 ex) UserException,PostException 과 같이 도메인를로 정의하여 관리
  - `MethodArgumentNotValidException`를 통해 dto, 엔티티 데이터 유효성 검사 관리
- 도메인별 예외처리 관리 ex) user/UserException
- 상세하게 예상되는 커스텀 예외 UserException 을 상속받아  정의하여 관리  user/ UserNotFoundException -> UserException은 직접적으로 사용 x

## 파일별 관리

### 엔티티
- 모든 엔티티를 `Timestamped` 를 상속받아 자동으로 생성 시간과 수정 시간을 관리
- 세터는 엔티티 단위사용 아닌 필드 단위로 필요한 부분만 맞게 사용
- 일관성을 위해 모든 칼럼에 `@Column` 사용
- 필수적으로 가져야할 데이터 `@NotNull` 사용

### DTO
- 필수적으로 보내야할 데이터 `@NotBlank` 사용

### 레퍼지토리
- null값의 가능성이있는 조회는 `Optional` 클래스 사용하여 조회
- `Adapter` 사용
  - 유저 레퍼지토리 처리 중복코드를 줄이기위해 중간의 어댑터 연결 클래스를 사용
  - 간단한 코드도 레퍼지토리를 직접사용하지말고 어댑터를 통해사용(어댑터로 다른 처리를 했는대 save 처리를위해  불필요한 레퍼지토리의 생성을 하지않고 어댑터만으로 처리하고, 코드의 일관성을 위해)

### 컨트롤러
- 엔티티가 달라도 논리적으로 상속 관계이면 api에 명시  comment/1  (x)  post/commnet/1 (o)

### 서비스
- DB 변경 비지니스로직은 `@Transactional` 사용
- 조회 로직도 `@Transactional(readOnly = true)`를 사용해서 성능 최적화
