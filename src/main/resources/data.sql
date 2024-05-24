-- categories テーブルにデータを挿入するクエリ
INSERT INTO categories (name)
VALUES
('仕事'),
('個人'),
('その他');

-- users テーブルにデータを挿入するクエリ
INSERT INTO users (email, name, password)
VALUES
('isida@aaa.com', '石田 源明', 'ishida123'),
('kobayashi@aaa.com', '小林 憲司 ', 'kobayashi123'),
('nakadome@aaa.com', '中留 大輔 ', 'nakadome123'),
('mitsui@aaa.com', '三井 悠太郎  ', 'mitsui123'),
('iga@aaa.com', '伊賀 直哉 ', 'iga123'),
('makino@aaa.com', '牧野 黎王', 'makino123');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (category_id, user_id, title, closing_date, progress, memo)
VALUES
(1, 1, 'アプリデザイン', '2024-05-14', 0, '新しいデザイン案の作成'),
(1, 2, 'ユーザーストーリー作成', '2024-05-15', 1, '必要な機能の選定'),
(1, 3, '個人開発', '2024-05-16', 2, '画面遷移完了'),
(1, 4, '個人開発その２', '2024-05-18', 1, '試行錯誤中'),
(1, 5, '個人開発その３', '2024-05-23', 0, '進捗だめです'),
(1, 6, '個人開発演習成果発表', '2024-05-28', 0, '準備完了'),
(3, 6, 'CL決勝', '2024-06-02', 0, '後追い視聴リマインド'),
(2, 6, '買い物', '2024-06-03', 0, 'ついでにランチも行きます');
