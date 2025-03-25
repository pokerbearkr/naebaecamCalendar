-- Author 테스트용 데이터
INSERT INTO author (id, name, email, created_at, updated_at)
VALUES (1, '오동원', 'ogdongwon@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Calendar 테스트용 데이터
INSERT INTO calendar (id, title, start_date, end_date, description, locate, password, author_id, created_at, updated_at)
VALUES (
           1,
           '테스트용 일정',
           CURRENT_TIMESTAMP,
           DATEADD('HOUR', 3, CURRENT_TIMESTAMP),
           '테스트 캘린더 일정입니다.',
           '서울',
           '1234',
           1,
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP
       );
