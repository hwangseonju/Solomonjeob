# Git 공부

### 메인 브랜치

master : 제품으로 출시될 수 있는 브랜치

develop : 다음 출시 버전을 개발하는 브랜치

### 보조 브랜치

feature : 기능을 개발하는 브랜치

release : 이번 출시 버전을 준비하는 브랜치

hotfix : 출시 버전에서 발생한 버그를 수정하는 브랜치



일반적인 개발 흐름

feature			develop		release		hotfixes		master



처음에는 master와 develop 브랜치가 존재한다.

develop 브랜치는 master에서부터 시작된 브랜치이다.

develop 브랜치에서는 상시로 버그를 수정한 커밋들이 추가된다. 

새로운 기능 추가 작업이 있는 경우 develop 브랜치에서 feature 브랜치를 생성한다.

feature 브랜치는 언제나 develop 브랜치에서부터 시작하게 된다.

기능 추가 작업이 완료되었다면 feature 브랜치는 develop 브랜치로 merge된다.

develop에 이번 버전에 포함되는 모든 기능이 merge되었다면 QA를 하기 위해 develop 브랜치에서부터 release 브랜치를 생성한다.

QA를 진행하면서 발생한 버그들은 release브랜치에 수정된다.

QA를 무사히 통과했다면 release 브랜치를 master와 develop브랜치로 merge한다.

마지막으로 출시된 master 브랜치에서 버전 태그를 추가한다.



# Jira 공부

큰 틀(Epic) : 이야기를 묶는 단위(ex: 로그인)

이야기(Story) : 요구사항, 개발 기능(ex: 로그인페이지구현)

작업(Task) : 구현과는 직접적인 관련없는 업무(문서작성)

버그(Bug) : 버그 리포팅