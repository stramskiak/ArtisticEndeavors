INSERT INTO GENRE (GENRE_TYPE) values (1); --'paper'
INSERT INTO GENRE (GENRE_TYPE) values (2); --'glass'
INSERT INTO GENRE (GENRE_TYPE) values (3); --'clay'
INSERT INTO GENRE (GENRE_TYPE) values (4); --'logo development'
INSERT INTO GENRE (GENRE_TYPE) values (5); --'other'
INSERT INTO GENRE (GENRE_TYPE) values (6); --'other'
INSERT INTO GENRE (GENRE_TYPE) values (7); --'other'
INSERT INTO GENRE (GENRE_TYPE) values (8); --'other'
INSERT INTO GENRE (GENRE_TYPE) values (9); --'other'
INSERT INTO GENRE (GENRE_TYPE) values (10); --'logo item'
INSERT INTO GENRE (GENRE_TYPE) values (11); --'paper item'
INSERT INTO GENRE (GENRE_TYPE) values (12); --'glass item'
INSERT INTO GENRE (GENRE_TYPE) values (13); --'clay item'


INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('a01b01', 'angel01blue01.gif', 1, '3D Paper Angel' , 24.99, 0.0
	, 'Original papercraft design for your enjoyment. '	); --1
INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('gbCollection', 'GlassBeadCollection.jpg', 4, 'Glass Bead Jewelry' , 9.99, 0.0
	, 'Lampworked glass beads in handcrafted jewelry.'	); --2
INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('a01KitVar', 'AngelKitVariation.jpg', 1, 'Angel 01 Variations' , 12.25, 0.0
	, 'A range of color options to suit your special occation. '	); --3
INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('a01b02', 'angel01blue02.jpg', 1, 'Angel 01 Kit' , 19.99, 0.0
	, 'This angel kit is available now!'	); --4
INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('a01parts', 'angel01parts.jpg', 1, 'Angel 01 Kit Parts' , 39.99, 0.0
	, 'An example of the paper cutouts included in every Angel 01 Kit'
	); --5 

INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('a01video', 'angel01video.jpg', 1, 'Angel 01 Kit Construction Video' , 59.99, 0.0
	, 'follow the link www.leatherswan.com/video/video.mp3 to see construction'
	); --6

INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('b01', 'Babies01.jpg', 3, 'Sleeping Baby', 8.99, 0.0
	, 'A special baby reminding me to relax!'
	); --7

INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('bMoD', 'make_n_babies.jpg', 3, 'MakeNbabies for the March of Dimes', 14.50, 0.1
	, 'A grass roots effort to spread the word of healthy babies thru crafty connections.'
	); --8

INSERT INTO ITEM (ITEMID, IMAGE, GENRE_ID, TITLE, PRICE, DISCOUNT, DESCRIPTION)
	values('LSLogo', 'My Leather Swan.gif', 4, 'Leather Swan Original', 12.99, 0.0
	, 'A suprise find in an antique store'
	); --9


INSERT INTO REVIEW (ITEMID, USERNAME, TEXT, DATE_OF_REVIEW)
	values('b01', 'fflintstone', 'Making babies is great fun!'
	, 'Sun Feb 23 08:32:10 pst 2015'
	);

INSERT INTO REVIEW (ITEMID, USERNAME, TEXT, DATE_OF_REVIEW)
	values('a01b01', 'fflintstone', 'A glorious angel and fun project. I put mine in a wedding scrapbook.', 'Sun Feb 23 08:32:10 pst 2015'
	);

INSERT INTO REVIEW (ITEMID, USERNAME, TEXT, DATE_OF_REVIEW)
	values('gbCollection', 'fflintstone', 'Cant wait to see what comes next!', 'Sun Feb 23 08:32:10 pst 2015'
	);

INSERT INTO REVIEW (ITEMID, USERNAME, TEXT, DATE_OF_REVIEW)
	values('gbCollection', 'fflintstone', 'Fun and playful.', 'Sun Feb 23 08:32:10 pst 2015'
	);

INSERT INTO REVIEW (ITEMID, USERNAME, TEXT, DATE_OF_REVIEW)
	values('gbCollection', 'fflintstone', 'A riot of color and form.', 'Sun Feb 23 08:32:10 pst 2015'
	);

INSERT INTO REVIEW (ITEMID, USERNAME, TEXT, DATE_OF_REVIEW)
	values('LSLogo', 'fflintstone', 'Such a fun logo, it really has a history!', 'Sun Feb 23 08:32:10 pst 2015'
	);

INSERT INTO REVIEW (ITEMID, USERNAME, TEXT, DATE_OF_REVIEW)
	values('a01parts', 'fflintstone', 'Complex pattern with step-by-step construction help.', 'Sun Feb 23 08:32:10 pst 2015'
	);

INSERT INTO REVIEW (ITEMID, USERNAME, TEXT, DATE_OF_REVIEW)
	values('a01b01', 'fflintstone', 'Love my angel. A flight of fancy for the young at heart.', 'Sun Feb 23 08:32:10 pst 2015'
	);


INSERT INTO ARTIST (FIRSTNAME, LASTNAME, IMAGE_URL, BIO) 
    values ('Anita','Stramski','astramski'
    , 'Always curious and extending my reach, I am a diverse artistic talent!'
    ); --1

INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (1,1);
INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (2,1);
INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (3,1);
INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (4,1);
INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (5,1); 
INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (6,1); 
INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (7,1);
INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (8,1);
INSERT INTO ITEM_ARTIST (ITEM_ID, ARTIST_ID) values (9,1);

