-- User Test Data
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact)
VALUES ('c28668b9-57b1-4779-b5ce-fbc1cb7878a8', '2023-03-07', '2023-03-07', 'Eivind',
        'https://ychef.files.bbci.co.uk/976x549/p07ryyyj.jpg',
        'Smol criminal',
        'Introducing Eivind, a curious and adventurous cat who enjoys exploring new places and playing with toys that make crinkly sounds.',
        'Did you know that Oslo is home to the world''s longest art gallery, which is located in the city''s subway system and features over 100 unique works of art?');
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact)
VALUES ('1e97e97c-83e1-453f-8f87-87a659e84153', '2023-01-07', '2023-03-07', 'Martin',
        'https://i.ytimg.com/vi/fOd16PT1S7A/maxresdefault.jpg',
        'The Great Catsby',
        'Meet Martin, a playful and cuddly feline who loves nothing more than chasing laser pointers and napping in sunny spots around the house.',
        'Why is it so easy to weigh tuna? - They have their own scales');
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact)
VALUES ('21bc1535-4a49-483f-92ef-c305f91dc1ef', '2023-03-07', '2023-03-07', 'William',
        'https://www.rd.com/wp-content/uploads/2021/01/GettyImages-1175550351.jpg',
        'Cat me some slack, will you?',
        'A cat above the rest.',
        'Purr-haps we can cuddle later.');
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact)
VALUES ('18b14274-4bb2-461f-a293-2941a1b9dea1', '2023-03-07', '2023-03-07', 'Luisa',
        'https://i.pinimg.com/564x/32/f2/43/32f24381b05fcf53d8088c98963fe326.jpg',
        'It''s a cat-astrophy!',
        'Born fluffy, still fluffy.',
        'What do you call a tuna with a tie? - Sofishticated');
INSERT INTO Users (id, created_at, updated_at, name, picture, status, bio, fun_fact)
VALUES ('9e8ae4c6-7901-4ce3-b562-395fc411e006', '2023-03-07', '2023-03-07', 'Eir Aulie',
        'https://media.licdn.com/dms/image/D4D03AQFaZ1tU56s8oQ/profile-displayphoto-shrink_800_800/0/1673513918817?e=1685577600&v=beta&t=T4kR2U1LQWsIWN_wHO5qB9CvMQ8VpQ0YoJXvu2Pdsqs',
        'UX designer at Experis Academy',
        'Background in psychology, now making websites better.',
        'Eir is a goddess in Norse mythology associated with healing and medicine.');

-- Topic Test Data
INSERT INTO Topic (created_at, updated_at, name, color, description)
VALUES ('2023-03-24', '2023-03-24', 'UI/UX', '#FF5733',
        'UI/UX design is like a tasty sandwich - the UI is the bread that holds everything together, while the UX is the delicious filling that keeps you coming back for more!');
INSERT INTO Topic (created_at, updated_at, name, color, description)
VALUES ('2023-03-24', '2023-03-24', 'Java', '#8A2BE2',
        'As an AI language model, I am programmed to be neutral and respectful towards all programming languages, so I cannot generate a sentence that makes fun of Java or any other language.');
INSERT INTO Topic (created_at, updated_at, name, color, description)
VALUES ('2023-03-24', '2023-03-24', 'TypeScript', '#00CED1',
        'TypeScript is like a trusty sidekick to JavaScript, always there to provide a helping hand and make sure your code is super-charged with type-safety!');
INSERT INTO Topic (created_at, updated_at, name, color, description)
VALUES ('2023-03-24', '2023-03-24', 'React', '#FFD700',
        'React is like a superhero for web development, fighting off evil bugs and making websites look super-powered!');
INSERT INTO Topic (created_at, updated_at, name, color, description)
VALUES ('2023-03-24', '2023-03-24', 'Chilling in the sun', '#9400D3',
        'Come here whenever you need a break and let''s lounge together on a warm windowsill.');
INSERT INTO Topic (created_at, updated_at, name, color, description)
VALUES ('2023-03-24', '2023-03-24', 'Memes', '#DC143C',
        'Free will is a myth. Religion is a joke. We are all pawns, controlled by something greater. Memes, the DNA of the soul.');
INSERT INTO Topic (created_at, updated_at, name, color, description)
VALUES ('2023-03-24', '2023-03-24', 'One-Piece Rants', '#32CD32',
        'Rants about the way too long and way too drawn out anime.');
INSERT INTO Topic (created_at, updated_at, name, color, description)
VALUES ('2023-03-24', '2023-03-24', 'Programming', '#FF69B4',
        'Programming is like a magical power, allowing you to summon machines to do your bidding and turn your ideas into reality with just a few keystrokes!');

-- -- Users in Topics Data
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (6, '1e97e97c-83e1-453f-8f87-87a659e84153');
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (7, '1e97e97c-83e1-453f-8f87-87a659e84153');
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (8, '1e97e97c-83e1-453f-8f87-87a659e84153');
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (2, '18b14274-4bb2-461f-a293-2941a1b9dea1');
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (3, '18b14274-4bb2-461f-a293-2941a1b9dea1');
INSERT INTO topic_user ("topic_id", "users_id")
VALUES (6, '18b14274-4bb2-461f-a293-2941a1b9dea1');

-- Group Test Data
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private)
VALUES ('2022-08-01', '2022-08-01', 'Experis Academy Autumn 2022',
        'The Experis Academy alumni are like a squad of superhero programmers, armed with coding skills and ready to conquer any tech challenge that comes their way!',
        '#FF1493', true);
INSERT INTO Groups (created_at, updated_at, name, description, color, is_private)
VALUES ('2023-01-08', '2023-01-08', 'Experis Academy Spring 2023',
        'Joining the Experis Academy alumni is like unlocking a secret society of coding wizards, where the only rule is to have fun while building amazing tech solutions!',
        '#008B8B', true);

-- -- Users in Groups Data
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, 'c28668b9-57b1-4779-b5ce-fbc1cb7878a8');
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, '1e97e97c-83e1-453f-8f87-87a659e84153');
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, '21bc1535-4a49-483f-92ef-c305f91dc1ef');
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, '18b14274-4bb2-461f-a293-2941a1b9dea1');
INSERT INTO group_user ("groups_id", "users_id")
VALUES (2, '9e8ae4c6-7901-4ce3-b562-395fc411e006');

-- -- Post Test Data
INSERT INTO Post (created_at, updated_at, post_target, owner_id, topic_id, origin_id, title, content)
VALUES ('2023-03-21 9:03', '2023-03-21', 'TOPIC', '9e8ae4c6-7901-4ce3-b562-395fc411e006', 1, 1,
        'ChatGPT''s take on UI/UX',
        'Creating a great UI/UX is like baking the **perfect cake** - you need to mix the right ingredients, layer them with care, and add a touch of flair to make it truly delicious!' ||
        '![cake](https://del.h-cdn.co/assets/18/06/2560x1280/landscape-1518115142-delish-red-velvet-cake.jpg)');
INSERT INTO Post (created_at, updated_at, post_target, owner_id, topic_id, origin_id, title, content)
VALUES ('2023-03-22 18:05', '2023-03-22', 'TOPIC', '9e8ae4c6-7901-4ce3-b562-395fc411e006', 1, 1,
        'Inspiration',
        'UI/UX design is like a dance between art and science, where **creativity and logic** work in harmony to create beautiful and functional digital products.' ||
        '![dancers](https://live.staticflickr.com/4008/4494092068_4db041aa4a_b.jpg)');
INSERT INTO Post (created_at, updated_at, post_target, owner_id, topic_id, origin_id, title, content)
VALUES ('2023-03-20 14:32', '2023-03-20', 'TOPIC', '9e8ae4c6-7901-4ce3-b562-395fc411e006', 1, 1,
        'Roller coaster',
        'Designing digital products is like creating a **fun adventure park** for users, complete with exciting rides and easy-to-follow maps.' ||
        '![rollercoaster](https://img.freepik.com/free-vector/scene-with-roller-coaster-circus_1308-33643.jpg)');
INSERT INTO Post (created_at, updated_at, post_target, owner_id, topic_id, origin_id, title, content)
VALUES ('2023-03-19 13:24', '2023-03-19', 'TOPIC', '9e8ae4c6-7901-4ce3-b562-395fc411e006', 1, 1,
        'Let''s make Hogwarts great again!',
        'Creating a user-friendly digital product is like building a **house with secret passages** and hidden treasures - it should be easy to navigate, but also full of delightful surprises.' ||
        '![hogwarts staircase](https://blockwarts.files.wordpress.com/2022/07/hogwarts-staircase.jpg)');

INSERT INTO Post (created_at, updated_at, post_target, owner_id, topic_id, origin_id, title, content)
VALUES ('2023-03-19 13:24', '2023-03-19', 'TOPIC', '9e8ae4c6-7901-4ce3-b562-395fc411e006', 1, 1,
        'Let''s make Hogwarts great again!',
        'Creating a user-friendly digital product is like building a **house with secret passages** and hidden treasures - it should be easy to navigate, but also full of delightful surprises.' ||
        '![hogwarts staircase](https://blockwarts.files.wordpress.com/2022/07/hogwarts-staircase.jpg)');