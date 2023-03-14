-- User Test Data
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('fda39db5-429a-4d95-8a39-1aa1fb9f3249', '2022-01-01', '2022-02-02', 'Eivind', 'https://ychef.files.bbci.co.uk/976x549/p07ryyyj.jpg', 'single', 'From Oslo', 'Has a lazy eye');
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('78ee330e-f8b3-48b8-8443-06583a0742fa', '2022-01-01', '2022-02-02', 'Martin', 'https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png', 'single', 'From Oslo', 'Has a lazy eye');
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact) VALUES ('b1af30bd-0f25-4bf1-b001-cc6a8babe84d', '2022-01-01', '2022-02-02', 'William', 'https://www.rd.com/wp-content/uploads/2021/01/GettyImages-1175550351.jpg', 'single', 'From Oslo', 'Has a lazy eye');

-- Topic Test Data
INSERT INTO Topic (created_at, updated_at, name, description, color) VALUES ('2022-01-01', '2022-02-02', 'World of Warcraft', 'Place to flex on Nick', 'brown');

-- Group Test Data
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private) VALUES ('2022-01-01', '2022-02-02', 'Key Pushers', 'The WoW Dungeon Divers', 'pink', false);

-- Post Test Data
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id) VALUES ('2022-01-01', '2022-02-02', 'Pushing Keys', 'Going for 22s! I got an SBG key, FREE!', 'TOPIC', 'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1);

-- Users in Groups Data
INSERT INTO group_user("groups_id", "users_id") VALUES (1,'78ee330e-f8b3-48b8-8443-06583a0742fa');

-- Users in Topics Data
INSERT INTO topic_user("topic_id", "users_id") VALUES (1, 'fda39db5-429a-4d95-8a39-1aa1fb9f3249');

