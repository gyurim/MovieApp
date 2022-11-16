# MovieApp

## Project 설명
> naver search api를 사용해 영화 검색을 할 수 있는 어플리케이션입니다.
> 
> 영화 검색 및 결과 목록, 영화 상세 화면, 즐겨찾기 모음 화면이 구현되어있습니다. 

## Architecture
<img width="1754" alt="스크린샷 2022-11-16 오후 11 46 41" src="https://user-images.githubusercontent.com/31344894/202212066-5e0dc6b1-b47f-49a0-9e31-5c4ddc930f7c.png">

- Clean Architecture와 MVVM & DataBinding 패턴을 사용하였습니다. 

## Tech Stack
- 언어
  - Kotlin
- Tools
  - Android Stuido
- Database
  - Room DB
- DI
  - Hilt
- OkHttp & Retrofit
- Coroutine & Flow
- 이미지 로드
  - Glide
- Paging3

## Feature & Screen
### 1. 영화 검색 및 결과 목록 화면
https://user-images.githubusercontent.com/31344894/202204197-e91ab1d8-1037-4dfe-9e45-9bd3202894d0.mp4
- 검색 키워드를 입력하면 키워드가 일치하는 영화의 목록을 출력해줍니다. 
- Paging과 RecyclerView를 통해 영화 목록을 출력하였습니다.
- 각 Row를 터치하면 각 Row에 맞는 영화 내용 상세 화면으로 이동합니다. 

### 2. 영화 내용 상세 화면
https://user-images.githubusercontent.com/31344894/202203400-f66a4a3c-7244-4bc6-beb4-a36a42b23fa5.mp4
- WebView를 통해 link를 출력해줍니다.
- WebView는 상하 스크롤, Zoom, Zomm & Scroll이 가능합니다.
- 해당 영화가 이미 즐겨찾기가 되어있는 상태인 경우, 노란 별 표식이 출력됩니다.
- 즐겨찾기가 이미 되어있는 영화의 경우, 즐겨찾기 버튼을 누르면 즐겨찾기가 해제됩니다.
- 반대로 즐겨찾기가 되어있지 않은 경우, 해당 영화는 즐겨찾기가 됩니다. 

### 3. 즐겨찾기 모음 화면
https://user-images.githubusercontent.com/31344894/202202992-1af0aa6c-78ae-43dd-babd-acb0975d375d.mp4
- Paging과 RecyclerView를 통해 즐겨찾기 목록을 출력하였습니다.
- 각 Row를 터치하면 각 Row에 맞는 영화 내용 상세 화면으로 이동합니다. 

### 미완성된 기능
- <영화 검색 및 결과 목록 화면>에서 즐겨찾기를 하지 못합니다.
- <영화 검색 및 결과 목록 화면>과 <즐겨찾기 모음 화면>에서 즐겨찾기 된 영화의 경우, 노란 별이 표시되지 않습니다. 
