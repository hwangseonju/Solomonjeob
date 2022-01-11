# Git Flow

> Git flow 란?

Git-flow : Git으로 소프트웨어 개발을 관리 할때 표준으로 사용되는 방법

​                 기능의 역활이 아니라 서로간의 약속적인 방법론

5가지 종류의 브랜치를 이용하여 개발을 진행한다.

- (Master branch) : 기준이 되는 브랜치로 제품을 배포하는 브랜치
- (Develop branch) : 개발 브랜치로 개발자들이 이 브랜치를 기준으로 각자 작업한 기능들을 합침
- (Feature branch) : 단위 기능을 개발하는 브랜치로 기능 개발이 완료되면 develop 브랜치에 합침
- (Release branch) : 배포를 위해 master 브랜치로 보내기전에 품질검사를 위한 브랜치
- (Hotfix branch) : 배포중에 버그가 생겼을때 긴급 수정하는 브랜치





![git-flow](./document/git-flow.png)



> 개발순서

1. master 브랜치와 develop 브랜치는 메인 브랜치로 사용한다
2. 개발이나 수정할 내용이 있으면 develop 브랜치에서 feature 브랜치를 생성한다.
3. 개발이나 수정이 완료되면 feature 브랜치는 develop 브랜치로 merge 한다.
4.  develop 브랜치에서 release 브랜치를 생성하여 성능검사를 실시한다.
5.  성능검사를 통과하면 realease 브랜치를 develop 브랜치와 master 브랜치로 merge한다.
6.  배포중에 버그가 생겼을 시에는 master 브랜치에서 hotfix 브랜치를 생성하여 수정후 master에 merge 한다.



