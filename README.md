글쓰기, 댓글, 파일 첨부 기능 게시판


**기간** 2023.12.15~


**기능**
1. `BindingResult` ,`@Validated` 를 활용해 로그인, 회원가입 구현
2. `Interceptor` , `@SessionAttribute` 를 활용해 로그인 검증
3. `JPA` ,`MySQL`를 활용해 데이터 CRUD 구현
4. `Thymeleaf` 를 활용해 HTML 동적 렌더링
5. 관리자 페이지 구현(타 회원, 게시물, 댓글 삭제 가능)

**예정기능 **
1. 게시판 조회수 기능 구현
2. 구글 로그인 구현

**배운점**
1. `indingResult` 활용
2. `Interceptor` 활용
3. `Thymeleaf`  활용
4. `AssertJ` 를 활용해 Test코드 작성

**느낀점**
1. 코드를 구현하는 시간이 2라면, 오류를 디버깅하고 해결하는 데 8이 걸렸다. 이를 통해 테스트 코드를 작성의 필요성을 느꼈다.
2. 내가 겪은 대부분의 오류는 대소문자, 알파벳 1글자 차이 등 매우 단순한 오류들이었습니다. 이러한 실수를 방지하기 위해 코드를 작성할 때 세부적으로, 주의 깊게 하나하나 생각하면서 짜면 좋겠다는 생각이 들었다.

![1login](https://github.com/heoeuntaek/project-post/assets/80875005/1c4ea3e5-9878-466d-80ed-4fdd235864d1)
![회원가입2](https://github.com/heoeuntaek/project-post/assets/80875005/ba312ce5-b0b5-42ce-b1ae-d2125a8cf15f)
![메인3](https://github.com/heoeuntaek/project-post/assets/80875005/83c9d753-c03b-4ebd-92d5-94d36310da5e)
![게시판 글쓰기4](https://github.com/heoeuntaek/project-post/assets/80875005/e0bdd44e-5558-4dad-93aa-abc0c62d29df)
![게시판리스트](https://github.com/heoeuntaek/project-post/assets/80875005/4a4a96ed-8ddf-4b35-b3a8-f60da6497ce5)
![게시판조회6](https://github.com/heoeuntaek/project-post/assets/80875005/2cf53274-63b4-47a0-a576-5be341e6fe5a)
![회원수정7](https://github.com/heoeuntaek/project-post/assets/80875005/87ba1c69-7184-44fb-b35d-3c5ebc7a83ef)
![관리자 페이지8](https://github.com/heoeuntaek/project-post/assets/80875005/fecf4842-62f1-442d-b205-592035d39d61)

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



