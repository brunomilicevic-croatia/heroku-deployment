-- ************************************** polls

CREATE TABLE polls
(
 id          uuid NOT NULL,
 name        text NOT NULL,
 description text NOT NULL,
 CONSTRAINT polls_pk PRIMARY KEY ( id )
);

-- ************************************** questions

CREATE TABLE questions
(
 id      uuid NOT NULL,
 text    text NOT NULL,
 type    character(10) NOT NULL,
 poll_id uuid NOT NULL,
 CONSTRAINT questions_pk PRIMARY KEY ( id ),
 CONSTRAINT questions_polls_fk FOREIGN KEY ( poll_id ) REFERENCES polls ( id )
);

CREATE INDEX questions_poll_id_idx ON questions
(
 poll_id
);

-- ************************************** options

CREATE TABLE options
(
 id      uuid NOT NULL,
 text    text NOT NULL,
 question_id uuid NOT NULL,
 CONSTRAINT options_pk PRIMARY KEY ( id ),
 CONSTRAINT options_questions_fk FOREIGN KEY ( question_id ) REFERENCES questions ( id )
);

CREATE INDEX options_questions_id_idx ON options
(
 question_id
);


-- ************************************** votes

CREATE TABLE votes
(
 vote_instance_id      uuid NOT NULL,
 option_id    uuid NOT NULL,
 question_id uuid NOT NULL,
 CONSTRAINT votes_pk PRIMARY KEY ( vote_instance_id, choosen_option_id, question_id ),
 CONSTRAINT votes_questions_fk FOREIGN KEY ( question_id ) REFERENCES questions ( id ),
 CONSTRAINT votes_options_fk FOREIGN KEY ( option_id ) REFERENCES options ( id )
);

