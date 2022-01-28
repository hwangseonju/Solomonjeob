# JQL

용도 :  조건에 맞는 이슈를 찾고자 할 떄

- Jira Query Language
- Jira Issue를 구조적으로 검색 하기위해 제공하는 언어
- sql과 비슷한 문법
- jira의 각 필드에 맞는 특수한 예약어 제공
- 쌓인 issue들을 재가공해 유의미한 데이터를 도출해 내는데 활용(gadget, agile board등)
- basic query
    
    버튼으로 클릭 advanced누르면 advanced쿼리로 바꿔줌
    
- advanced query
    
    project = DP and issuetype=Story
    
- jql operators
    - =,≠,>, ≤
    - in not in
    - ~, !~
    - is empty, is not empty, is null, is not null
- jql keywords
    
    and, ot, not, empty, null , orderby
    
- jql dates
    - relative dates 상대 날짜 개념
        - Current(today)
        - -1d, 1d 전날 다음날 -2d, 2d 이틀전 이틀후....
        - -1w, 1w 일주일전 일주일 후 -2w, 2w 이주일전 이주일후...
- jql function
    - endOfDay(),startOfDay() : 하루끝 24시, 00시
    - endOfWeek() : 토요일 , startOfWeek() : 일요일
    - endOfMonth, endOfYear() ,startOfMonth(), startOfYear()
    - currentLogin() : 로그인한 시간 기준
    - currentUser() : 현재 로그인한 사용자

- 사용법
    
    isseu  > search for issues 이슈검색 기능
    
    프로젝트 선택 > 원하는 타입 선택 > advanced선택  쿼리 변경됨
    
    ex ) project = DP AND updated > -2d :2일전 수정된 업데이트를 볼 수 있음 
    
    project = DP AND updated < endOfWeek() : 일요일 이후 수정된 걸 보여줘..
    
    project = DP AND updated > startOfWeek(1d) : 월요일 이후 수정된 이슈
    
    project = DP AND updated > startOfWeek(1d)  AND updated <endOfWeek(-1d): 월~금 사이의 수정된 이슈
    
    project = DP AND assignee = currentUser() : 내가 담당자인 이슈
    
    - project = DP and updated > startOfWeek(1d) and updated < endOfWeek(-1d) 입력 후 save as로 저장하면 필터로 저장가능 왼쪽보면 탭이 생기고 details 링크가 생김 그링크를 클릭하면 edit current filter >  addviewr로 공유, add를 넣어야 저장이됨
    - find fileter 로 찾을 수 있는데 별을 클릭시 필터처럼 뜸
    - resolution : issue가 시스템에서 끝났는가 Done이 끝난거
    
    ### Dash Board
    
    - filter results
        
        save filter : 이슈선택
        
        nuber of :한페이지에 몇개
        
        column : 어떤 컬럼을 보여줄 것인가
        
        auto refresh : 상태 업데이트
        
    - assigned to me 나에게 할당된 이슈
    - heat map : 어떤 이슈가 가장많은가
    - pie chart : heatmap과 같은 것을 차트로보여줌