# 브라우저에 데이터를 저장하는법 3가지

1. 비회원인데 장바구니에 상품을 어떻게 담아놓는 걸까?
- F12 눌러 보세요 > Storage 영역, Cache 영역
- 대부분 Storage영역

1. IndexedDB : 브라우저상의 DB
    - 브라우저에 데이터를 영구적으로 저장할 수 있는 방법 중 하나( 영구적은 아님 브라우저 초기화시 사라짐)
    - IndexDB를 사용하여 네트워크 상태와 상관없이 풍부한 쿼리 기능을 이용할 수 있는 웹애플리케이션을 만들 수 있다.
    - IndexDB를 사용한 웹 애플리케이션은 온라인과 오프라인 환경에서 모두 동작
    - transaction db사용해 key-value로 데이터를 관리
    - transaction내에서 문제 발생시 모든 변경사항 폐기 되고 이전상태
    - CORS때문에 http에서만든 indexDB는 https도메인 접근 불가
    - 데이터가 영속성으로 유지
    - 50mb정도의 용량이 허용, 대용량 데이터 사용 시에 성능 이슈 있음
    - indexDB를 스토어로 사용하려면 안됨(vue로 렌더링 한다던지..)
2.  Storage
    - Local Storage vs session storage
        - 큰 차이는 영구성 local storage는 기기와 플랫폼이 허락하는 한 데이터 저장(jwt)
        - session storage은 브라우저를 닫으면 데이터 삭제됨
        - 5~10mb사용가능
        - store을 보고 아무것도 없으면 local storage를 볼 것 (Jwt유무...)
3. 쿠키
    - 저장 가능한 쿠기 매우작고 텍스트 형태
    - 만료시간 설정이 가능
    - 요청에 실어서 서버로 전송하는 일이 잦음
    - 인증 정보 등을 보관할 때 사용함
    - 4kb의 크기를 갖는다