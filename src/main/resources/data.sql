INSERT INTO MATURITY (ID, MATURITY_LEVEL, MATURITY_DESCRIPTION) VALUES (1, 1, 'TODOPUBLICO');
INSERT INTO MATURITY (ID, MATURITY_LEVEL, MATURITY_DESCRIPTION) VALUES (2, 2, 'SUPERVISION');
INSERT INTO MATURITY (ID, MATURITY_LEVEL, MATURITY_DESCRIPTION) VALUES (3, 3, 'ADULTOS');

INSERT INTO IMAGE (ID, VIDEO_ID, URL, TYPE, WIDTH, HEIGHT, EXTENSION, SIZE, IMAGE_KEY) VALUES (1,1, 'HTTP://IMAGE1', 'IMG', 100, 100, 'JPG', '100', '1');
INSERT INTO IMAGE (ID, VIDEO_ID, URL, TYPE, WIDTH, HEIGHT, EXTENSION, SIZE, IMAGE_KEY) VALUES (2,2, 'HTTP://IMAGE2', 'IMG', 200, 200, 'JPG', '200', '2');

INSERT INTO GENDER (ID, NAME_GENDER) VALUES (1, 'KIDS');
INSERT INTO GENDER (ID, NAME_GENDER) VALUES (2, 'TERROR');
INSERT INTO GENDER (ID, NAME_GENDER) VALUES (3, 'DRAMA');

INSERT INTO MOVIES (ID, RELEASE_YEAR, TITLE, SYNOPSIS, NUM_SEASONS_LABEL, ID_GENDER, ID_IMAGE, ID_MATURITY ) VALUES (1, 2020, 'TEST1', 'TESTDES1', 1, 1, 1, 1);
INSERT INTO MOVIES (ID, RELEASE_YEAR, TITLE, SYNOPSIS, NUM_SEASONS_LABEL, ID_GENDER, ID_IMAGE, ID_MATURITY ) VALUES (2, 2021, 'TEST2', 'TESTDES2', 1, 2, 2, 2);
INSERT INTO MOVIES (ID, RELEASE_YEAR, TITLE, SYNOPSIS, NUM_SEASONS_LABEL, ID_GENDER, ID_IMAGE, ID_MATURITY ) VALUES (3, 2022, 'TEST3', 'TESTDES3', 1, 3, 1, 3);
