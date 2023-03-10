-- User Test Data
INSERT INTO Users (created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('2022-01-01', '2022-02-02', 'Eivind', 'random.jpg', 'single', 'From Oslo', 'Has a lazy eye');
INSERT INTO Users (created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('2022-01-01', '2022-02-02', 'Martin', 'random.jpg', 'single', 'From Oslo', 'Has a lazy eye');
INSERT INTO Users (created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('2022-01-01', '2022-02-02', 'William', 'random.jpg', 'single', 'From Oslo', 'Has a lazy eye');

-- Topic Test Data
INSERT INTO Topic (created_at, updated_at, name, description, color) VALUES ('2022-01-01', '2022-02-02', 'World of Warcraft', 'Place to flex on Nick', 'brown');

-- Post Test Data
INSERT INTO Post (created_at, updated_at, title, content, post_target, sender_id, reply_parent_id, target_user, target_group, target_topic) VALUES ('2022-01-01', '2022-02-02', 'Pushing Keys', 'Going for 22s! I got an SBG key, FREE!', 'TOPIC', '1', 1, '1', 1, 1)