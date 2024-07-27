-- Create user table
CREATE TABLE IF NOT EXISTS user (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL,
    usertype TEXT NOT NULL CHECK (usertype IN ('teacher', 'student')),
    password TEXT NOT NULL
);

-- Create test table
CREATE TABLE IF NOT EXISTS test (
    test_id INTEGER PRIMARY KEY AUTOINCREMENT,
    test_name TEXT NOT NULL,
    timer TEXT NOT NULL -- Store timer as hh:mm:ss
);

-- Create question_set table
CREATE TABLE IF NOT EXISTS question_set (
    question_set_id INTEGER NOT NULL,
    question_no INTEGER NOT NULL,
    question TEXT NOT NULL,
    options TEXT, -- Store options as a comma-separated string
    correct_option TEXT,
    question_type TEXT NOT NULL CHECK (question_type IN ('SingleCorrect', 'MultipleChoice', 'Descriptive', 'Numerical')),
    marks INTEGER NOT NULL,
    PRIMARY KEY (question_set_id, question_no)
);
