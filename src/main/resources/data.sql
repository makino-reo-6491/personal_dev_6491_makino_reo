-- categories テーブルにデータを挿入するクエリ
INSERT INTO categories (name)
VALUES
('仕事'),
('個人'),
('その他');

-- users テーブルにデータを挿入するクエリ
INSERT INTO users (email, name, password)
VALUES
('tanaka@aaa.com', '田中太郎', 'test123'),
('suzuki@aaa.com', '鈴木一郎', 'test456'),
('makino@aaa.com', '牧野', 'test123');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (category_id, user_id, title, closing_date, progress, memo)
VALUES
(1, 1, '見積もり', '2025-12-31', 0, '案件に適した見積もりを取る'),
(1, 3, '個人開発演習', '2024-05-28', 1, '試行錯誤中'),
(2, 3, '病院', '2024-05-25', 0, '準備段階'),
(3, 3, '家族の病院', '2024-06-08', 0, '無し');