## Moida (글또 플랫폼)

#### Plan 

```
1. Java + sql mapper
2. Java + jpa
3. Kotlin + jpa

순서로 동일한 요구사항을 해결할 계획이다.
이 후 수집할 수 있는 데이터에 대한 고민을 추가적으로 하게 될 것이며, 
그 결과에 따라 Datastore에 대한 학습도 추가할 수 있을지 판단하려 한다.
```

#### Step 1. 데이터 수집

```
RSS를 통해 멤버의 글을 받아온다.

- Member
-- idx
-- name
-- fbLink
-- blogLink

- Feed
-- idx
-- memberIdx
-- title
-- link
-- version
-- List<FeedHistory>

- FeedHistory
-- idx
-- createdDate
-- feedIdx
-- contents
```

#### Step 2. Contents 작성

```
공유된 글에 피드백을 달고, 체크리스트를 작성한다.

- Feedback
-- idx
-- feedIdx
-- memberIdx
-- contents
-- createdDate
-- updatedDate

- CheckList
-- idx
-- Form
-- memberIdx
-- point
-- List<CheckListHistory>

- Form
-- idx
-- List<Item>
-- createDate
-- updateDate
-- authorIdx

- Item 
-- idx
-- content
-- check

- CheckListHistory
-- idx
-- createdDate
-- checkListIdx
-- contents
```

#### Step 3. 글 제출 주기 관리기능

```
글 제출 주기를 설정할 수 있으며 이 일정은 구글 캘린더로 Export할 수 있다.
설정된 시점 내에 Feed가 작성되지 않을 시 알람을 보내게 할 수 있다.

- Period
-- idx
-- List<Date>

- Alert
-- idx
-- name
-- contents
-- schedule

- AlertHistory
-- idx
-- createdDate
-- receiver
-- alertIdx
-- contents
```

#### Step 4. 댓글 피드백 작성여부 체크 && Deposit 정산 기능

#### FB Login 기능

#### 대시보드 기능