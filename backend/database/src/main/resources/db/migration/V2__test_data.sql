INSERT INTO USERS VALUES ('dsgsfdgdfgds5dd6','Krwawos', 'passwd');
INSERT INTO USERS VALUES ('fdsfdssgfdd656', 'Blodig', 'passwd123');
INSERT INTO USERS VALUES ('t465hbty5j', 'Undertaker', 'laspos');

INSERT INTO PRODUCER VALUES ('as5fo347bt5og98a','Games Workshop');
INSERT INTO PRODUCER VALUES ('ergmsb5435aw', 'Army Painter');
INSERT INTO PRODUCER VALUES ('es6rr64ex', 'Para Bellum');

INSERT INTO PAINTING_TECHNIQUE VALUES ('sernseb6kz5zw', 'Priming');
INSERT INTO PAINTING_TECHNIQUE VALUES ('s8el5gww54', 'Basing');
INSERT INTO PAINTING_TECHNIQUE VALUES ('r35bov57tywo', 'Shading');

INSERT INTO PAINT VALUES ('dearb6sde7h665', 'Caledor Sky', 'BASE', 'as5fo347bt5og98a');
INSERT INTO PAINT VALUES ('g65esgejmugt', 'Abbadon Black', 'BASE', 'as5fo347bt5og98a');
INSERT INTO PAINT VALUES ('tbdttmvf3w54', 'Dark Black', 'BASE', 'ergmsb5435aw');
INSERT INTO PAINT VALUES ('dtdstwazthtyo78654', 'Nuln Oil', 'WASH', 'as5fo347bt5og98a');

INSERT INTO MODELING_PRODUCT VALUES ('et456457', 'Leaves', 'Base', 'ergmsb5435aw');
INSERT INTO MODELING_PRODUCT VALUES ('srswrqw23145', 'Stones', 'BASE', 'ergmsb5435aw');
INSERT INTO MODELING_PRODUCT VALUES ('ryurt8690', 'Grass', 'BASE', 'ergmsb5435aw');

INSERT INTO GAME VALUES ('y5h765r765', 'Conquest: TLAOK', 'FANTASY', 'es6rr64ex');
INSERT INTO GAME VALUES ('6ej7rdg978', 'Warhammer 40k', 'SCIENCE-FICTION', 'as5fo347bt5og98a');
INSERT INTO GAME VALUES ('a53woikstr', 'Warhammer AOS', 'FANTASY', 'as5fo347bt5og98a');

INSERT INTO FACTION VALUES ('sdzfsttlatae45765', '100 Kingdoms', 'y5h765r765');
INSERT INTO FACTION VALUES ('sCrt5wbtsdnfdte', 'Old Dominion', 'y5h765r765');
INSERT INTO FACTION VALUES ('esnby5r37gvcv', 'Space Marines', '6ej7rdg978');

INSERT INTO MINIATURE VALUES ('asyndf7jr6756', 'Militia', 'Melee infantry', 'sdzfsttlatae45765');
INSERT INTO MINIATURE VALUES ('redjh7urt75r', 'Household Knight', 'Cavalry', 'sdzfsttlatae45765');
INSERT INTO MINIATURE VALUES ('dnytfmiyufktdr23645', 'Intercessor', 'Infantry', 'esnby5r37gvcv');

INSERT INTO COURSE VALUES ('hdynrttd735gfnhf', 'How to paint Militia', 'Quick look how to paint Militia', 'asyndf7jr6756', 'dsgsfdgdfgds5dd6');
INSERT INTO COURSE VALUES ('fdfdshflbeh6455645', 'Best looking Intercessors', 'Blood angels, how to paint', 'dnytfmiyufktdr23645', 'fdsfdssgfdd656');
INSERT INTO COURSE VALUES ('asfranglfptr7569', 'How to base properly', 'Guide how to base properly your miniatures', 'asyndf7jr6756', 't465hbty5j');

INSERT INTO COURSE_STEP VALUES ('dnytf7tr389780',
                                1,
                                'First basing',
                                'After priming with favourite primer. Firstly we have to base our miniature with caledor sky.',
                                'hdynrttd735gfnhf');
INSERT INTO COURSE_STEP VALUES ('dcfxkylutf8sx',
                                2,
                                'Time for shading',
                                'Now we need to do some shading with out nuln oil.',
                                'hdynrttd735gfnhf');
INSERT INTO COURSE_STEP VALUES ('fdaw55486tnghdfx',
                                1,
                                'Preparation',
                                'Firstly we put our base paint on wet palette. Add a bit of water from tip of brush.',
                                'asfranglfptr7569');

INSERT INTO PAINT_TECHNIQUE_MAPPING VALUES ('dnytf7tr389780', 'dearb6sde7h665', 's8el5gww54');
INSERT INTO PAINT_TECHNIQUE_MAPPING VALUES ('dcfxkylutf8sx', 'dtdstwazthtyo78654', 'r35bov57tywo');
INSERT INTO PAINT_TECHNIQUE_MAPPING VALUES ('fdaw55486tnghdfx', 'g65esgejmugt', 's8el5gww54');