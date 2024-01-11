글쓰기, 댓글, 파일 첨부 기능 게시판


**기간** 2023.12.15~

  

**기능**
1. `Spring Data JPA` ,`MySQL` 활용해 CRUD 구현
2. `BindingResult` ,`@Validated`  활용해 로그인, 회원가입 구현
3. `Interceptor` , `@SessionAttribute`  활용해 로그인 검증
4. `Pageable` 활용해 게시판 페이징 구현
5. `Thymeleaf` 활용해 HTML 동적 렌더링
6. 관리자 페이지 구현(타 회원, 게시물, 댓글 삭제 가능)

  

**예정기능 **
1. 채팅기능

  
**배운것**
1. `BindingResult` 활용
2. `Interceptor` 활용
3. `Thymeleaf`  활용
4. `Junit`를 활용해 Test코드 작성

  
**느낀점**
1. 코드를 작성하는 데 2시간이 소요되었지만, 오류를 디버깅하고 해결하는 데 8시간이 걸렸다. 이 경험을 통해 테스트 코드 작성의 중요성을 더욱 강하게 느끼게 되었다.
2. 내가 경험한 대부분의 오류는 대소문자를 잘못 사용하거나 알파벳 한 글자를 잘못 쓰는 등 매우 단순한 실수들이었다. 이러한 실수를 방지하기 위해 코드를 작성할 때 세부적인 부분에도 주의를 기울여야겠다는 생각이 들었다.

  
231215
로그인 세션 구현

  
231216
인터셉터 구현
게시판 추가, 수정 구현
회원 관리 구현(수정)
게시판 삭제 구현
UserController에서  return "main" 하면 css 적용 안됨 - css경로에 static 없애서 해결

  

231218
관리자 페이지 - 회원 삭제 구현
게시글 삭제(관리자일때 모두, 아니면 자기것만)
게시글 수정(자기것만)
게시판 첨부 구현

  
231219
게시판 등록, 조회, 삭제 구현, 댓글 개수

  
231220
게시판 추가정보 "${#dates.format(post.time, 'yyyy-MM-dd HH:mm')}" 해결

  

231221
JPA 에서 Spring Data jpa로 변경
페이징구현

  
231221
JPA 에서 Spring Data jpa로 변경
페이징구현
게시판 조회수 기능

  
231222
ID로 친구추가 구현
친구조회 구현
친구삭제 구현

  
231224
게시판에서 친구 추가 구현

  
로그인
![1login](https://github.com/heoeuntaek/project-post/assets/80875005/1c4ea3e5-9878-466d-80ed-4fdd235864d1)

  
회원가입
![회원가입2](https://github.com/heoeuntaek/project-post/assets/80875005/ba312ce5-b0b5-42ce-b1ae-d2125a8cf15f)

  
메인화면
![메인3](https://github.com/heoeuntaek/project-post/assets/80875005/83c9d753-c03b-4ebd-92d5-94d36310da5e)

  
게시판 글쓰기
![게시판 글쓰기4](https://github.com/heoeuntaek/project-post/assets/80875005/e0bdd44e-5558-4dad-93aa-abc0c62d29df)

  
게시판 리스트
![게시판리스트](https://github.com/heoeuntaek/project-post/assets/80875005/4a4a96ed-8ddf-4b35-b3a8-f60da6497ce5)

  
게시판 조회
![게시판조회6](https://github.com/heoeuntaek/project-post/assets/80875005/2cf53274-63b4-47a0-a576-5be341e6fe5a)

  
회원 수정
![회원수정7](https://github.com/heoeuntaek/project-post/assets/80875005/87ba1c69-7184-44fb-b35d-3c5ebc7a83ef)

  
관리자 페이지
![관리자 페이지8](https://github.com/heoeuntaek/project-post/assets/80875005/fecf4842-62f1-442d-b205-592035d39d61)


