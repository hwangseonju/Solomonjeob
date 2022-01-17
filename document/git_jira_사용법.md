### Jira
---

- 한글보다는 영어 사용 추천
- sprint : 주로 1~2주 기준으로 계획하는 것을 추천(일정을 모두 백로그에 넣어놓고, 시간 계획 후 시작)
- backlog에서 sprint로 올리면 그 주에 해당하는 목표로 설정된다는 의미
- Estimate 의미 : 일정을 수행하는데 소요되는 예상 시간(1h = 1point)
- epic, story, task 제대로 된 분리가 중요!
- sprint 시작전에 backlog, storypoint, 총 소요시간 지정하고 sprint 시작하기
- Burndown 차트 : 계획 처리 흐름(감소하는 식의 차트 모양이 가장 Best)

-> 백로그에 잔뜩 쌓아놓고 스프린트에 맞게 올려서 개발을 진행하자!!!

 

### Commit
---

- type은 핵심내용 위주로만 하되, 너무 많지는 않게 하도록 지정(너무 많으면 커밋 메시지 확인할 때마다 확인하러가야되는 불편함이 있음)
- 프로젝트가 대괄호로 생성되서 표기되기 때문에 type은 대괄호를 사용하지않는것이 좋을 듯 함..
- README.md는 프로젝트를 모르는 사람이 봐도 알 수 있을 정도로 전반적인 내용을 간략히 작성해두기(필수는 아님)
- contributor를 통해 팀원들의 활동 정도를 확인 가능
- git-lab > wiki - 노션외에 따로 정리를 해주는게 좋음, 노션도 좋지만 쉽게 접할 수 있도록 wiki도 잊지않도록..
- issues > list : jira와 gitlab 연결
- feature 안에서 나누는 방식은 각 팀의 프로젝트에 따라 다르기 때문에 초기에 계획하고 진행하는게 좋음
- jira에서 comment를 잘이용하면 내용 정리에 좋음



### Notion
---

- 가장 중요한 것은 일정!
  

#### Git
---
- clone -> branch 생성 -> git add -> git commit -> git push
- merge할 경우 적어도 하나의 comment가 달릴 경우 진행하도록 정함(각 팀마다 규칙 정하기)
- 브랜치 전략 종류 : git flow, **github flow**, gitlab flow
- LGTM(looks good to me) : 코드 리뷰시 comment할게 없을 경우 작성하는 댓글
- 팀별로 comment나 좋아요가 몇개 이상일 경우에 merge하는 방식으로 컨벤션 정하기
- Jenkins 외에 git-lab에서 제공하는 CI/CD 기능도 이용해보기 


#### Jira
---
- jira 담당자를 한명 지정하여 사용하면 좋을듯(주로 스토리 포인트 관리)
- 월~금까지 시간별로 이슈를 분리해서 모두 등록해놓고 나중에 이슈 이름을 변경해서 사용하는 방식으로 진행(정확한 방법은 아님..)