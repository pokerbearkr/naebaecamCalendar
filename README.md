# naebaecamCalendar
내일배움캠프 캘린더 백엔드 개인과제(2025.03.19~2025.03.26)

##  주요 기능

- 일정 등록
- 전체 일정 조회
- 개별 일정 상세 조회
- 일정 수정
- 일정 삭제

---

## API 명세서

>  [API 명세서 보기](./api.png)

---

## ERD

>  [ERD 보기](./ERD.png)

---

##  Postman 요청 예시

#### 일정 생성 (POST `/api/schedules`)
```json
{
  "title": "과제 제출",
  "startDate": "2025-03-19T10:00:00",
  "endDate": "2025-03-26T14:00:00",
  "description": "캘린더 과제 제출",
  "locate": "Online",
  "password": "1234",
  "authorId": 1
}
