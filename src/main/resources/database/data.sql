-- Insert initial data into user table
INSERT INTO user (username, usertype, password) VALUES ('teacher1', 'teacher', 'password123');
INSERT INTO user (username, usertype, password) VALUES ('student1', 'student', 'password456');

-- Insert initial data into test table
INSERT INTO test (test_name, timer) VALUES ('Comprehensive Test', '01:00:00');

-- Insert initial data into question_set table for Comprehensive Test (test_id = 1)

-- SingleCorrect Questions
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 1, 'What is the capital of France?', '{Paris},{London},{Berlin},{Madrid}', 'Paris', 'SingleCorrect', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 2, 'Which planet is known as the Red Planet?', '{Venus},{Mars},{Jupiter},{Saturn}', 'Mars', 'SingleCorrect', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 3, 'What is the largest ocean on Earth?', '{Atlantic Ocean},{Indian Ocean},{Arctic Ocean},{Pacific Ocean}', 'Pacific Ocean', 'SingleCorrect', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 4, 'What is the smallest prime number?', '{1},{2},{3},{5}', '2', 'SingleCorrect', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 5, 'Which element has the chemical symbol H?', '{Helium},{Hydrogen},{Oxygen},{Nitrogen}', 'Hydrogen', 'SingleCorrect', 1);

-- MultipleChoice Questions
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 11, 'Select all prime numbers.', '{2},{3},{4},{5},{6}', '2,3,5', 'MultipleChoice', 2);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 12, 'Which of the following are fruits?', '{Apple},{Carrot},{Tomato},{Potato}', 'Apple,Tomato', 'MultipleChoice', 2);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 13, 'Select all planets with rings.', '{Earth},{Mars},{Saturn},{Jupiter}', 'Saturn,Jupiter', 'MultipleChoice', 2);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 14, 'Which of these are programming languages?', '{Python},{JavaScript},{HTML},{CSS}', 'Python,JavaScript', 'MultipleChoice', 2);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 15, 'Select all continents.', '{Africa},{Oceania},{Mars},{Antarctica}', 'Africa,Oceania,Antarctica', 'MultipleChoice', 2);

-- Descriptive Questions
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 21, 'Describe the process of photosynthesis.', NULL, 'Photosynthesis is the process by which green plants use sunlight to synthesize food.', 'Descriptive', 3);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 22, 'Explain the theory of relativity.', NULL, 'The theory of relativity encompasses two theories by Einstein: Special relativity and General relativity, describing the relationship between space and time.', 'Descriptive', 3);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 23, 'Describe the role of the mitochondria in a cell.', NULL, 'Mitochondria are the powerhouses of the cell, generating most of the cells supply of adenosine triphosphate (ATP).', 'Descriptive', 3);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 24, 'What is climate change and its impacts?', NULL, 'Climate change refers to significant changes in global temperatures and weather patterns over time, affecting ecosystems, sea levels, and weather events.', 'Descriptive', 3);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 25, 'Explain how the human digestive system works.', NULL, 'The digestive system processes food, absorbs nutrients, and removes waste. It includes organs such as the mouth, esophagus, stomach, intestines, and more.', 'Descriptive', 3);

-- Numerical Questions
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 31, 'What is the value of Pi (up to 2 decimal places)?', NULL, '3.14', 'Numerical', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 32, 'What is 25 + 37?', NULL, '62', 'Numerical', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 33, 'What is 7 * 8?', NULL, '56', 'Numerical', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 34, 'How many days are there in a leap year?', NULL, '366', 'Numerical', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 35, 'What is the square root of 144?', NULL, '12', 'Numerical', 1);

-- Additional SingleCorrect Questions
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 6, 'What is the chemical symbol for Gold?', '{Au},{Ag},{Fe},{Pb}', 'Au', 'SingleCorrect', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 7, 'Which planet is closest to the Sun?', '{Earth},{Venus},{Mercury},{Mars}', 'Mercury', 'SingleCorrect', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 8, 'What is the hardest natural substance on Earth?', '{Gold},{Iron},{Diamond},{Platinum}', 'Diamond', 'SingleCorrect', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 9, 'Who wrote "To Kill a Mockingbird"?', '{Harper Lee},{Mark Twain},{J.K. Rowling},{Jane Austen}', 'Harper Lee', 'SingleCorrect', 1);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 10, 'What is the largest organ in the human body?', '{Heart},{Liver},{Skin},{Lung}', 'Skin', 'SingleCorrect', 1);

-- Additional MultipleChoice Questions
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 16, 'Select all Nobel Prize winners.', '{Einstein},{Newton},{Hawking},{Curie}', 'Einstein,Curie', 'MultipleChoice', 2);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 17, 'Which of these are programming languages?', '{Java},{C++},{Python},{HTML}', 'Java,C++,Python', 'MultipleChoice', 2);
-- Additional MultipleChoice Questions (continued)
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 18, 'Select all books by George Orwell.', '{1984},{Animal Farm},{Brave New World},{Fahrenheit 451}', '1984,Animal Farm', 'MultipleChoice', 2);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 19, 'Which of these are programming languages?', '{Java},{Python},{Swift},{HTML}', 'Java,Python,Swift', 'MultipleChoice', 2);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 20, 'Select all living things.', '{Bacteria},{Rocks},{Viruses},{Plants}', 'Bacteria,Plants', 'MultipleChoice', 2);

-- More Descriptive Questions
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 26, 'Discuss the significance of the Internet in modern communication.', NULL, 'The Internet has revolutionized communication by providing instant access to information and enabling global connectivity.', 'Descriptive', 3);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 27, 'Describe the impact of climate change on polar ice caps.', NULL, 'Climate change has led to the melting of polar ice caps, causing sea levels to rise and impacting ecosystems.', 'Descriptive', 3);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 28, 'Explain the role of DNA in genetics.', NULL, 'DNA contains genetic instructions used in the growth, development, functioning, and reproduction of all living organisms.', 'Descriptive', 3);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 29, 'How does renewable energy benefit the environment?', NULL, 'Renewable energy sources reduce greenhouse gas emissions and dependency on fossil fuels, mitigating environmental impact.', 'Descriptive', 3);
INSERT INTO question_set (question_set_id, question_no, question, options, correct_option, question_type, marks)
VALUES (1, 30, 'Explain the concept of supply and demand in economics.', NULL, 'Supply and demand are fundamental economic concepts describing the relationship between the quantity of a product and its price.', 'Descriptive', 3);
