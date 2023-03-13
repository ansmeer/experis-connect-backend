-- User Test Data
INSERT INTO Users (created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('2022-01-01', '2022-02-02', 'Eivind', 'random.jpg', 'single', 'From Oslo', 'Has a lazy eye');
INSERT INTO Users (created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('2022-01-01', '2022-02-02', 'Martin', 'random.jpg', 'single', 'From Oslo', 'Has a lazy eye');
INSERT INTO Users (created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('2022-01-01', '2022-02-02', 'William', 'random.jpg', 'single', 'From Oslo', 'Has a lazy eye');

-- Topic Test Data
INSERT INTO Topic (created_at, updated_at, name, description, color) VALUES ('2022-01-01', '2022-02-02', 'World of Warcraft', 'Place to flex on Nick', 'brown');

-- Group Test Data
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private) VALUES ('2022-01-01', '2022-02-02', 'Key Pushers', 'The WoW Dungeon Divers', 'pink', false);

-- Post Test Data
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, parent_id, users_id, group_id, topic_id) VALUES ('2022-01-01', '2022-02-02', 'Pushing Keys', 'Going for 22s! I got an SBG key, FREE!', 'TOPIC', 1, 1, 1, 1, 1);
