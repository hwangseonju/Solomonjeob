## Git

| Git 사용 이유

- 협업  Workflow
- 커밋 컨벤션 통일
- 배포 자동화
- 코드 리뷰



| Git  구조

1. Repository
2. Command



| Git Flow

- Feature -> **Develop** -> Bugfix -> Release -> Hotfix -> **Master**
- 약 10년 전에 나온 전략(오래된 전략)
- 권장 : Feature -> **Develop** -> Hotfix -> **Master**
- Feature : 기능을 개발하는 브랜치(Commit, Merge Request, Code Review)
- Develop : 다음 출시 버전을 개발하는 브랜치(Feature가 통과한 상태)
- Release : 이번 출시 버전을 준비하는 브랜치
- Hotfix : 출시 버전에서 발생한 버그를 수정하는 브랜치
- Master : 제품으로 출시될 수 있는 브랜치



| Git flow 시작

1. git flow init
2. git branch
3. git flow feature start sign_up

=> 단, 해당 방법은 코드리뷰가 불가능하기 때문에 수동으로 branch 생성하여 merge request해야함.



| .gitignore 관리

- https://gitignore.io



| Git 사용법

- clone : git clone {git repo 주소}

- 브랜치 변경 : git checkout -b {새로운 브랜치 이름}
- staging area에 올리기 : git add .
- commit : git commit -m "커밋 메시지 작성"





-----





## Jira

| Jira 기능

- 지속적인 스프린트 관리 : 다양한 변화에 빠르고 효과적으로 대응할 수 있는 업무 환경
- 대시보드 활용한 데이터 시각화
- 이슈 기반의 프로젝트 관리



| Jira default 이슈 종류

- Epic : 이야기를 묶는 단위, 큰틀 (ex. 로그인)
- Story : 요구사항, 개발 기능, 이야기 (ex. 로그인 페이지 구현)
- Task : 구현과는 직접적인 관련 없는 업무, 작업 (ex. 문서작성)
- Sub-Task : Task를 수행하기 위한 작은 업무
- Bug : 버그 리포팅, 버그



| 백로그와 스프린트

- 백로그(backlog) : 해야할 일들의 목록. 스토리나 태스크 등이 백로그에 들어감
- 스프린트(spirnt) : 업무(백로그)를 진행할 기간을 정의. 주 단위로 관리하면 주차별로 만들 수 있고, 특정 기간별로도 지정 가능
