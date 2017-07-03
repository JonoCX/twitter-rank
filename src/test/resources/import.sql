INSERT INTO twitter.tweet_classification (id,description,key,label)  VALUES (1,NULL,'Receiver','Receiver');
INSERT INTO twitter.tweet_classification (id,description,key,label)  VALUES (2,NULL,'Provider','Provider');
INSERT INTO twitter.tweet_classification (id,description,key,label)  VALUES (3,NULL,'Noise','Noise');
INSERT INTO twitter.tweet_classification (id,description,key,label)  VALUES (4,NULL,'News','News');

INSERT INTO twitter.twitter_user VALUES (1,'2006-07-26 14:58:51','Always think on the bright side of life',413,4020,634,FALSE,FALSE,TRUE,FALSE,FALSE,'en',141,'Brazil','Eduardo Nasi','FFB700','http://pbs.twimg.com/profile_background_images/189203097/SNC15766-2.jpg','https://pbs.twimg.com/profile_background_images/189203097/SNC15766-2.jpg',FALSE,'https://pbs.twimg.com/profile_banners/3144/1431976001','http://pbs.twimg.com/profile_images/564416538984599553/Eozje7QE_normal.jpeg','https://pbs.twimg.com/profile_images/564416538984599553/Eozje7QE_normal.jpeg','3D3D3D','000000','FFFFFF','1F1F1F',TRUE,'eduardonasi',FALSE,53106,'Brasilia',FALSE,'http://eduardonasi.blogspot.com/',-10800);
INSERT INTO twitter.twitter_user VALUES (2,'2006-11-26 07:49:15','i am not serious. and you can call me shirley',80290,1094,1596,FALSE,FALSE,TRUE,FALSE,FALSE,'en',65,'Hyderabad, India','Aditya M ','E1E7CB','http://pbs.twimg.com/profile_background_images/378800000181599186/l51pxeTD.jpeg','https://pbs.twimg.com/profile_background_images/378800000181599186/l51pxeTD.jpeg',TRUE,'https://pbs.twimg.com/profile_banners/22713/1435335825','http://pbs.twimg.com/profile_images/614473102655815681/I3jH6acj_normal.jpg','https://pbs.twimg.com/profile_images/614473102655815681/I3jH6acj_normal.jpg','B1EDF5','3F3647','4A4968','3E2D33',TRUE,'almostinfamous',FALSE,52553,'Chennai',FALSE,'http://fotogenesis.in/',19800);
INSERT INTO twitter.twitter_user VALUES (3,'2006-12-18 09:40:57','Runner, husband, geek, @brands2life board director, CIO, mediocre guitarist, sci-fan, soup-fan, blogger, dude, dad, abiding.',211,1092,984,FALSE,FALSE,TRUE,FALSE,FALSE,'en',73,'London','Armand David','ACDED6','http://abs.twimg.com/images/themes/theme6/bg.gif','https://abs.twimg.com/images/themes/theme6/bg.gif',FALSE,'https://pbs.twimg.com/profile_banners/77503/1354008727','http://pbs.twimg.com/profile_images/2658528301/adcc7fbbe735a0359141872dacf9fab4_normal.jpeg','https://pbs.twimg.com/profile_images/2658528301/adcc7fbbe735a0359141872dacf9fab4_normal.jpeg','038543','EEEEEE','F6F6F6','333333',TRUE,'division6',FALSE,8993,'London',FALSE,'http://www.division6.co.uk/wp/',3600);
INSERT INTO twitter.twitter_user VALUES (4,'2007-01-06 16:21:50','Egyptian Blogger / Journalist / Photographer. Just another talkative Egyptian \nRT is not Endorsement',9904,194944,5169,FALSE,FALSE,TRUE,FALSE,FALSE,'en',2104,'Egypt','Zeinobia','5E0101','http://pbs.twimg.com/profile_background_images/378800000180507070/cuIALumb.png','https://pbs.twimg.com/profile_background_images/378800000180507070/cuIALumb.png',TRUE,'https://pbs.twimg.com/profile_banners/608583/1348065610','http://pbs.twimg.com/profile_images/1422143030/4346a80a-5333-4514-afde-55d68ce1e918_bigger_normal.png','https://pbs.twimg.com/profile_images/1422143030/4346a80a-5333-4514-afde-55d68ce1e918_bigger_normal.png','DD2E44','F8F1C7','020202','2B4749',TRUE,'Zeinobia',FALSE,355463,'Cairo',FALSE,'http://egyptianchronicles.blogspot.com',7200);
INSERT INTO twitter.twitter_user VALUES (5,'2007-01-07 10:43:32','Smalltalker, Domain-Specific Languages for Complex System: Epidemiology, Environmental Monitoring, Rescue Robotics, UMMISCO research team (IRD/UPMC)',5297,1223,1324,FALSE,FALSE,FALSE,FALSE,FALSE,'en',111,'Paris/Hanoi','Serge Stinckwich','352726','http://abs.twimg.com/images/themes/theme5/bg.gif','https://abs.twimg.com/images/themes/theme5/bg.gif',FALSE,NULL,'http://pbs.twimg.com/profile_images/33081542/ml1kYf66RMwkLfvEhQ1cimGLSnn0xJU1hxwEyovsJXTI-GgI3yC4cmRDZ-1uFc0y_normal.jpeg','https://pbs.twimg.com/profile_images/33081542/ml1kYf66RMwkLfvEhQ1cimGLSnn0xJU1hxwEyovsJXTI-GgI3yC4cmRDZ-1uFc0y_normal.jpeg','D02B55','829D5E','99CC33','3E4415',TRUE,'SergeStinckwich',FALSE,11370,'Hanoi',FALSE,'http://www.doesnotunderstand.org',25200);


-- (id,interest,participation,tweetscount,twitterrank,classification_id,user_id)
INSERT INTO twitter.tweet_metadata VALUES (1,NULL,NULL,3,NULL,1,1);
INSERT INTO twitter.tweet_metadata VALUES (2,NULL,NULL,20,NULL,2,1);
INSERT INTO twitter.tweet_metadata VALUES (3,NULL,NULL,5,NULL,3,1);

INSERT INTO twitter.tweet_metadata VALUES (4,NULL,NULL,20,NULL,1,2);
INSERT INTO twitter.tweet_metadata VALUES (5,NULL,NULL,1,NULL,2,2);
INSERT INTO twitter.tweet_metadata VALUES (6,NULL,NULL,1,NULL,3,2);

INSERT INTO twitter.tweet_metadata VALUES (7,NULL,NULL,3,NULL,1,3);
INSERT INTO twitter.tweet_metadata VALUES (8,NULL,NULL,2,NULL,2,3);
INSERT INTO twitter.tweet_metadata VALUES (9,NULL,NULL,50,NULL,3,3);
INSERT INTO twitter.tweet_metadata VALUES (10,NULL,NULL,10,NULL,4,3);

INSERT INTO twitter.tweet_metadata VALUES (11,NULL,NULL,3,NULL,1,4);
INSERT INTO twitter.tweet_metadata VALUES (12,NULL,NULL,10,NULL,2,4);
INSERT INTO twitter.tweet_metadata VALUES (13,NULL,NULL,5,NULL,3,4);

INSERT INTO twitter.tweet_metadata VALUES (14,NULL,NULL,30,NULL,1,5);
INSERT INTO twitter.tweet_metadata VALUES (15,NULL,NULL,10,NULL,2,5);
INSERT INTO twitter.tweet_metadata VALUES (16,NULL,NULL,2,NULL,3,5);
INSERT INTO twitter.tweet_metadata VALUES (17,NULL,NULL,20,NULL,4,5);

-- (follower_id, following_id)
INSERT INTO twitter.follow VALUES (1, 2);
INSERT INTO twitter.follow VALUES (1, 4);
INSERT INTO twitter.follow VALUES (1, 3);

INSERT INTO twitter.follow VALUES (2, 1);
INSERT INTO twitter.follow VALUES (2, 5);

INSERT INTO twitter.follow VALUES (3, 1);
INSERT INTO twitter.follow VALUES (3, 2);
INSERT INTO twitter.follow VALUES (3, 5);

INSERT INTO twitter.follow VALUES (4, 2);
INSERT INTO twitter.follow VALUES (4, 3);
INSERT INTO twitter.follow VALUES (4, 5);

INSERT INTO twitter.follow VALUES (5, 2);