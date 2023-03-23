-- User Test Data
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact)
VALUES ('fda39db5-429a-4d95-8a39-1aa1fb9f3249', '2022-01-01', '2022-02-02', 'Eivind',
        'https://ychef.files.bbci.co.uk/976x549/p07ryyyj.jpg', 'single', 'From Oslo', 'Has a lazy eye');
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact)
VALUES ('78ee330e-f8b3-48b8-8443-06583a0742fa', '2022-01-01', '2022-02-02', 'Martin',
        'https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png', 'single', 'From Oslo',
        'Has a lazy eye');
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact)
VALUES ('b1af30bd-0f25-4bf1-b001-cc6a8babe84d', '2022-01-01', '2022-02-02', 'William',
        'https://www.rd.com/wp-content/uploads/2021/01/GettyImages-1175550351.jpg', 'single', 'From Oslo',
        'Has a lazy eye');

-- Topic Test Data
INSERT INTO Topic (created_at, updated_at, name, description, color)
VALUES ('2022-01-01', '2022-02-02', 'World of Warcraft', 'Place to flex on Nick', 'brown');
INSERT INTO Topic (created_at, updated_at, name, description, color)
VALUES ('2022-01-01', '2022-02-02', 'World of Warcraft', 'Place to flex on Nick', 'brown');
INSERT INTO Topic (created_at, updated_at, name, description, color)
VALUES ('2022-01-02', '2022-02-03', 'One-Piece Rants', 'Rants about the way too long and way too drawn out anime',
        'blue');
INSERT INTO Topic (created_at, updated_at, name, description, color)
VALUES ('2022-01-02', '2022-02-03', 'Programming', 'NEEEEEEEEEERD!', 'black');

-- Group Test Data
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private)
VALUES ('2022-01-01', '2022-02-02', 'Key Pushers', 'The WoW Dungeon Divers', 'pink', true);
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private)
VALUES ('2022-01-01', '2022-02-02', 'Key Pushers 2', 'The WoW Dungeon Divers', 'pink', false);
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private)
VALUES ('2022-01-01', '2022-02-02', 'Key Pushers 3', 'The WoW Dungeon Divers', 'pink', true);
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private)
VALUES ('2022-01-01', '2022-02-02', 'Key Pushers', 'The WoW Dungeon Divers', 'pink', false);
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private)
VALUES ('2022-01-01', '2022-02-02', 'Weebs United', 'You will not regret joining this group! UwU', 'white', false);
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private)
VALUES ('2022-01-01', '2022-02-02', 'The Giga Chads', 'Dont join unless you can at least do 200kg bench!!!', 'darkblue',
        true);

-- Post Test Data
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, origin_id)
VALUES ('2022-01-01', '2022-02-02', 'First post', 'Going for 22s! I got an SBG key, LETT!!', 'GROUP',
        'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1, 1);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, parent_id, origin_id)
VALUES ('2022-01-02', '2022-02-02', 'Reply to first post', 'That sounds good to me! I am in! It will be so free.', 'GROUP',
        'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1, 1, 1);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, parent_id, origin_id)
VALUES ('2022-01-03', '2022-02-02', 'Another reply to first post', 'I dont know if I would call it "free", but it is definitely easier than some of the other dungeons.', 'GROUP',
        'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1, 1, 1);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, parent_id, origin_id)
VALUES ('2022-01-04', '2022-02-02', 'Wow, first post is popular', 'My highest key done is just a +5 so I think I will pass but if you dont mind then I would like to join :)', 'GROUP',
        'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1, 1, 1);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, parent_id, origin_id)
VALUES ('2022-01-07', '2022-02-02', 'The reply got a reply :)', 'How free are we talking? I have a +18 as my best, but I would like a 20 vault if you dont mind.', 'GROUP',
        'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1, 2, 1);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, parent_id, origin_id)
VALUES ('2022-01-01', '2022-02-02', 'Reply inception', 'I am just here to say it is free, because that is what this thread is about isnt it?', 'GROUP',
        'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1, 5, 1);

INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, origin_id)
VALUES ('2022-01-06', '2022-02-02', 'WoW nerds', 'WoW is so lame, who can you guys enjoy a 20 year old game... Grow up!', 'GROUP',
        'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1, 7);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, origin_id)
VALUES ('2022-01-05', '2022-02-02', 'Did a +22 the other day', 'GET ON MY LEVEL!! WTS +20 Boosts, dm for more info ;)',
        'GROUP', 'b1af30bd-0f25-4bf1-b001-cc6a8babe84d', 1, 8);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, origin_id)
VALUES ('2022-01-01', '2022-02-02', 'I watched A Sister is All You Need. first the first time',
        'It was so good, what a beautiful love story and really cool to get some insight in the author world. ',
        'GROUP', '78ee330e-f8b3-48b8-8443-06583a0742fa', 2, 9);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, group_id, origin_id)
VALUES ('2022-01-01', '2022-02-02', 'Season 2 dungeons are kinda mid arent they?',
        'Freehold is SICK! But the new Dragonflight dungeons are all kind of wack. We obviously dont know before we try, but I dont have a good feeling about this. ',
        'GROUP', '78ee330e-f8b3-48b8-8443-06583a0742fa', 1, 10);
INSERT INTO post (created_at, updated_at, title, content, post_target, owner_id, topic_id, origin_id)
VALUES ('2022-01-09', '2022-02-02', 'LF Cutting Edge Guild',
        'I have completed all keys to +30s and now this is too easy for me so I want to join a raiding guild to maybe get to do some hard content for once in this game. ',
        'TOPIC', 'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 1, 11);
INSERT INTO post (created_at, updated_at, title, content, post_target, owner_id, topic_id, origin_id)
VALUES ('2022-01-22', '2022-02-02', 'I like M+, it is so exciting when you get to a level with a new affix :)',
        'I got to a +5 key yesterday and it was so cool how my key now suddenly had Necrotic on it!! It was so much fun and challenging, you really had to think on your toes. I cant wait till I get to +7 to get another one :D',
        'TOPIC', '78ee330e-f8b3-48b8-8443-06583a0742fa', 1, 12);
INSERT INTO post (created_at, updated_at, title, content, post_target, owner_id, topic_id, origin_id)
VALUES ('2022-11-01', '2022-02-02', 'What is going on?!',
        'It has been 3 weeks since last episode! The quality is subpar anyways and that will not change so how dare they delay it..,',
        'TOPIC', '78ee330e-f8b3-48b8-8443-06583a0742fa', 2, 13);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, users_id, origin_id)
VALUES ('2022-03-01', '2022-02-02', 'Join a dungeon?', 'Hey! I am about to log on WoW. Want to join for a few runs?', 'USER',
        'b1af30bd-0f25-4bf1-b001-cc6a8babe84d', '78ee330e-f8b3-48b8-8443-06583a0742fa', 14);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, users_id, origin_id)
VALUES ('2022-01-02', '2022-02-03', 'You there?', 'Hey! Have you stopped using this app? You havent responded in a while so a bit worried :(', 'USER',
        'b1af30bd-0f25-4bf1-b001-cc6a8babe84d', '78ee330e-f8b3-48b8-8443-06583a0742fa', 15);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, users_id, origin_id)
VALUES ('2022-01-02', '2022-02-03', 'How are you doing?', 'What is up? I just joined this app and noticed you were here too!', 'USER',
        'fda39db5-429a-4d95-8a39-1aa1fb9f3249', '78ee330e-f8b3-48b8-8443-06583a0742fa', 16);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, users_id, parent_id, origin_id)
VALUES ('2022-01-02', '2022-02-03', 'This is a reply from Martin to William', 'But who know, maybe I am doing this wrong :(', 'USER',
        '78ee330e-f8b3-48b8-8443-06583a0742fa', 'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 16, 16);
INSERT INTO Post (created_at, updated_at, title, content, post_target, owner_id, users_id, origin_id)
VALUES ('2022-01-02', '2022-02-03', 'This should be a parent post from William to Martin', 'Do you see this or is it still bugged?', 'USER',
        '78ee330e-f8b3-48b8-8443-06583a0742fa', 'fda39db5-429a-4d95-8a39-1aa1fb9f3249', 18);

-- Users in Groups Data
INSERT INTO group_user("groups_id", "users_id")
VALUES (1, '78ee330e-f8b3-48b8-8443-06583a0742fa');
INSERT INTO group_user("groups_id", "users_id")
VALUES (1, 'fda39db5-429a-4d95-8a39-1aa1fb9f3249');
INSERT INTO group_user("groups_id", "users_id")
VALUES (2, '78ee330e-f8b3-48b8-8443-06583a0742fa');
INSERT INTO group_user("groups_id", "users_id")
VALUES (3, 'b1af30bd-0f25-4bf1-b001-cc6a8babe84d');

-- Users in Topics Data
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (1, 'fda39db5-429a-4d95-8a39-1aa1fb9f3249');
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (2, '78ee330e-f8b3-48b8-8443-06583a0742fa');
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (3, '78ee330e-f8b3-48b8-8443-06583a0742fa');

