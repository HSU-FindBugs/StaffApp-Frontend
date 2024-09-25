## 찾아벌레
<p align="center">
  <img src="https://github.com/user-attachments/assets/a3f1bf93-2e05-4f9f-a4f0-8d2dcb813488" alt="찾아벌레"/>
</p> 
<div align="center">
 해충 방제 사업자를 위한 AI 기반 벌레 탐색 및 관제 앱 찾아벌레 안드로이드 파트
</div>

## 목차
  - [개요](#개요)
  - [내용](#내용)
  - [화면](#화면)
  - [서비스 구조](#서비스구조)
  - [시스템 구현도](#시스템구현도)
  - [기술 스택](#기술스택)
  - [저작권](#저작권)

## 개요
- 프로젝트 이름: 찾아벌레 (2024 한성대학교 )
- 프로젝트 지속기간: 2024.09.09 ~ 09.25
- 개발 엔진 및 언어: Android Studio, Kotlin
- 멤버: 김성민

## 내용
- **`메인 홈`**
    - 직원 기본 정보 조회
        - 이름, 관리 지역, 관리 고객 수, 출퇴근 여부
    - 오늘의 정보 조회 : 최근 가장 많이 발견된 벌레
    - 공지 사항 조회
        - 공지 사항 목록 볼 수 있음 (클릭시 다이얼로그로 조회)
- **`고객 관리`**
    - **고객 찾기**
        - 고객 목록
            - 목록에서 고객 누르면 해당 고객의 정보 확인 화면으로 이동
        - 고객 검색
            - 검색한 고객의 정보 화면으로 이동
        - 최근 검색 기록 조회
    - **고객 등록**
        - 관리할 고객 등록 (이름, 전화번호, 주소, 멤버십 정보)
    - **고객 정보 수정**
        - 등록된 고객의 정보 수정  (이름, 전화번호, 주소, 멤버십 정보)
    - **고객 정보 확인**
        - 고객 정보 조회 (이름, 전화번호, 주소, 남은 멤버십 기간)
        - 해당 고객의 최근 방문 정보 조회
        - 고객 특이사항 조회 및 수정
    - **해충 기록 목록**
        - **해충 기록 보기**
            - 목록에서 선택한 해충 이름, 정보, 발견 카메라, 시간, 날짜 조회 및 방제 솔루션 제공
    - **감지 사진 기록**
        - 목록에서 감지된 해충의 기록 누르면 해당 해충이 발견된 시점의 사진, 시간 조회
    - **실시간 영상 보기**
        - 해충이 감지되고 있는 카메라의 실제 영상을 볼 수 있음 (웹뷰 사용)
    - **카메라 등록/삭제**
- **`알림`**
    - 카메라로 벌레가 감지되면 SSE(Server-Sent Events)로 해당 고객의 이름과 주소가 푸시 알림으로 생성됨
    - 알림 목록에 해당 고객 추가 됨
- **`프로필`**
    - 직원 정보 수정

## 화면
<p align="center">
  <img src="https://github.com/user-attachments/assets/23410c1e-bbab-4146-b703-8d7f3f2f28e5" alt="찾아벌레"/>
  <img src="https://github.com/user-attachments/assets/fdab9304-f8c8-434e-9cde-8f7a7846e9e7" alt="찾아벌레"/>
  <img src="https://github.com/user-attachments/assets/c126f1c5-abbd-4905-9bae-bf4f16581e3b" alt="찾아벌레"/>
</p>

## 서비스 구조
<p align="center">
  <img src="https://github.com/user-attachments/assets/bf079918-3c95-4a8b-bcb9-4b62eb8b3f26" alt="찾아벌레"/>
</p>

## 시스템 구현도
<p align="center">
  <img src="https://github.com/user-attachments/assets/c7e146d9-fd39-407a-a33b-589f126786c5" alt="찾아벌레"/>
</p>

## 기술스택

### **🤖** 안드로이드
| **Category** | **TechStack** |
| --- | --- |
| Architecture | Clean Architecture, MVVM |
| DI | Hilt |
| Network | Retrofit, OkHttp, Gson, SSE(Server-Sent Events)|
| Asynchronous | Coroutines, Flow |
| Jetpack | DataBinding, Navigation | 
| Image | Glide |

## 기술스택
Copyright 2024. 전세원 All rights reserved.<br>
ⓒ 2024. 전세원 All rights reserved.<br>
(c) 2024. 전세원 All rights reserved.
