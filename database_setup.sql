-- Student Quiz System - Database Setup
-- Run this file in MySQL before launching the application

CREATE DATABASE IF NOT EXISTS student_quiz;
USE student_quiz;

-- Drop existing tables
DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS questions;

-- Students table
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    dob VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Questions table
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(100) NOT NULL,
    question_text TEXT NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_answer CHAR(1) NOT NULL
);

-- Results table
CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    student_name VARCHAR(100),
    subject VARCHAR(100),
    total_questions INT,
    correct_answers INT,
    wrong_answers INT,
    percentage DECIMAL(5,2),
    attempt_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id)
);

-- Insert Questions

-- Java Questions
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is the default value of an int variable in Java?','0','null','-1','undefined','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which keyword is used to inherit a class in Java?','implements','extends','inherits','super','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is the size of int in Java?','2 bytes','4 bytes','8 bytes','depends on OS','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which method is the entry point of a Java program?','start()','run()','main()','init()','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What does JVM stand for?','Java Variable Machine','Java Virtual Machine','Java Visual Module','Java Verified Method','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which of these is not a primitive type?','int','char','String','float','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is the parent class of all Java classes?','Base','Object','Super','Root','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which keyword prevents method overriding?','static','private','final','abstract','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What does \'this\' keyword refer to?','Superclass object','Current class object','Child class object','Static context','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which collection maintains insertion order?','HashSet','TreeSet','LinkedList','HashMap','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is method overloading?','Same name different return type','Same name different parameters','Override in subclass','Dynamic dispatch','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which access modifier restricts to same class only?','protected','default','public','private','D');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is a constructor?','A method with return type','Special method to initialize object','Static block','Interface method','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which exception is thrown when array index is out of range?','NullPointerException','ClassCastException','ArrayIndexOutOfBoundsException','IOException','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is an interface in Java?','A class with only abstract methods','A blueprint with no concrete methods','Both A and B','A type of variable','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What does \'static\' keyword mean?','Object-level access','Class-level access','Protected access','Thread-safe access','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which loop is guaranteed to execute at least once?','for','while','do-while','foreach','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is autoboxing?','Casting objects','Auto conversion primitive to wrapper','Garbage collection','Type checking','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which of these is a checked exception?','NullPointerException','IOException','ArithmeticException','ClassCastException','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is the use of \'super\' keyword?','Refer to current object','Refer to parent class','Create object','Call interface method','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which data structure uses LIFO?','Queue','Stack','LinkedList','Tree','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is polymorphism?','One class many objects','One name many forms','Many classes one object','Static binding only','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What does \'instanceof\' operator check?','Type of variable','Whether object belongs to class','Null check','Method exists','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which is not an OOP principle?','Encapsulation','Inheritance','Compilation','Polymorphism','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is the use of \'finally\' block?','Runs only on success','Runs only on exception','Always runs','Optional execution','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which class is used for immutable strings?','StringBuilder','StringBuffer','String','CharSequence','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is garbage collection in Java?','Deleting arrays','Auto memory management','Clearing console','Removing methods','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','Which is a marker interface?','Runnable','Comparable','Serializable','Iterable','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What is the return type of hashCode()?','String','boolean','int','Object','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Java','What does \'synchronized\' keyword do?','Speeds up execution','Thread safety','Enables parallelism','Memory optimization','B');

-- Mathematics Questions
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the value of sin(90°)?','0','1','-1','0.5','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is 15% of 200?','20','25','30','35','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','Solve: 2x + 3 = 11. Find x.','3','4','5','6','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the area of a circle with radius 7?','154','144','164','174','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is log(100) base 10?','1','2','10','100','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the derivative of x^2?','x','2x','2','x^3/3','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the sum of angles in a triangle?','90','180','270','360','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is sqrt(144)?','11','12','13','14','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is 7!?','2520','5040','720','40320','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the HCF of 12 and 18?','3','6','9','12','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the LCM of 4 and 6?','12','24','8','6','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is 2^10?','512','1024','256','2048','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the median of 3,7,9,11,15?','7','9','11','10','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','A triangle has base 10 and height 6. Area?','30','60','36','24','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the cube of 3?','9','27','18','36','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','Simplify: (a+b)^2 = ?','a^2+b^2','a^2+2ab+b^2','2a+2b','a^2-b^2','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the slope of a horizontal line?','1','-1','0','undefined','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the Pythagorean theorem?','a+b=c','a^2+b^2=c^2','a*b=c','2a+2b=2c','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','A coin is tossed. Probability of heads?','1/4','1/3','1/2','3/4','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the volume of a cube with side 3?','9','18','27','36','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the perimeter of a square with side 5?','20','25','10','15','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the mode of 2,3,3,4,5?','2','3','4','5','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','Integral of 2x is?','x^2+C','2x^2+C','x+C','2+C','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is cos(0°)?','0','1','-1','0.5','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the arithmetic mean of 10,20,30?','15','20','25','30','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','4 * 0 = ?','4','0','1','undefined','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the GCD of 24 and 36?','6','12','8','4','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','Solve: x^2 = 25. x = ?','5 only','-5 only','both 5 and -5','25','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is 3/4 as percentage?','60%','70%','75%','80%','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Mathematics','What is the next prime after 7?','9','10','11','13','C');

-- Computer Networks Questions
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What does OSI stand for?','Open System Interconnection','Open Software Interface','Optical Signal Integration','Operating System Interface','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','How many layers does the OSI model have?','5','6','7','8','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','Which layer handles routing?','Transport','Network','Data Link','Session','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What does IP stand for?','Internet Protocol','Internal Process','Interface Port','Input Packet','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','Which protocol is used for email sending?','HTTP','FTP','SMTP','POP3','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is the default port for HTTP?','21','25','80','443','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What does DNS stand for?','Data Network Service','Domain Name System','Digital Node Server','Dynamic Network Setup','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is the maximum length of an Ethernet frame?','512 bytes','1024 bytes','1518 bytes','2048 bytes','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','Which topology connects all nodes to a central hub?','Bus','Ring','Star','Mesh','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What does TCP stand for?','Transfer Control Protocol','Transmission Control Protocol','Transport Connection Protocol','Terminal Command Protocol','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','Which class of IP address starts with 10.x.x.x?','Class A','Class B','Class C','Class D','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is the purpose of ARP?','Assign IP to hostname','Map IP to MAC address','Route packets','Encrypt data','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','Which protocol is connectionless?','TCP','HTTP','UDP','FTP','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is the default subnet mask for Class C?','255.0.0.0','255.255.0.0','255.255.255.0','255.255.255.255','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What does FTP stand for?','Fast Transfer Protocol','File Transfer Protocol','File Type Protocol','Forward Transfer Port','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','Which device connects two different networks?','Hub','Switch','Router','Repeater','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is bandwidth?','Number of packets','Data transfer rate','Network delay','Packet size','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What does HTTPS use for security?','SSH','SSL/TLS','FTP','IPSec','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is a MAC address?','IP-based address','Hardware address of network card','Domain name','Port number','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','How many bits in an IPv4 address?','16','32','64','128','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What does DHCP do?','Encrypts data','Assigns IP addresses dynamically','Routes packets','Filters traffic','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','Which layer does a switch operate at?','Layer 1','Layer 2','Layer 3','Layer 4','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is latency?','Bandwidth of network','Time delay in data transmission','Packet loss rate','Data compression','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What does NAT stand for?','Network Address Translation','Network Access Terminal','Node Assignment Table','Null Address Type','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What port does SSH use?','21','22','23','25','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is the loopback address?','192.168.0.1','10.0.0.1','127.0.0.1','172.16.0.1','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is a broadcast address used for?','Send to one host','Send to all hosts in subnet','Encrypt traffic','Reserve for routers','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','Which protocol resolves hostnames to IP?','ARP','DNS','DHCP','NAT','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is a firewall?','A type of router','Security system filtering traffic','A network switch','An encryption protocol','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Computer Networks','What is the full duplex communication?','Send only','Receive only','Send and receive simultaneously','Alternating send/receive','C');

-- DBMS Questions
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What does DBMS stand for?','Data Base Management System','Data Binary Modification System','Database Module Storage','Data Block Memory System','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','Which language is used to query databases?','HTML','Python','SQL','XML','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What does SQL stand for?','Structured Query Language','Simple Query Logic','Sequential Query Lookup','Standard Question Language','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','Which SQL command retrieves data?','INSERT','UPDATE','SELECT','DELETE','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is a primary key?','Foreign reference key','Unique identifier for a record','Index of a column','Duplicate allowed key','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What does ACID stand for in transactions?','Atomicity Consistency Isolation Durability','All Checks Integrity Data','Automatic Control Index Decode','Access Control Input Data','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is normalization?','Removing redundant data','Adding more tables','Encrypting data','Backup process','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','Which SQL command removes all rows?','DROP','DELETE','TRUNCATE','REMOVE','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is a foreign key?','Primary key of same table','Reference to primary key of another table','Unique index','Default constraint','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is a view in SQL?','Stored procedure','Virtual table from a query','Index structure','Transaction log','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What does JOIN do in SQL?','Deletes rows','Combines rows from two tables','Creates new table','Sorts results','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is an index used for?','Encrypting data','Faster data retrieval','Creating backups','Data normalization','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','Which normal form removes partial dependencies?','1NF','2NF','3NF','BCNF','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What does GROUP BY do?','Joins tables','Groups rows with same values','Filters conditions','Sorts data','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is a trigger?','Auto-executed procedure on event','Constraint on column','Type of join','Backup strategy','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','Which SQL clause filters aggregate results?','WHERE','HAVING','GROUP BY','ORDER BY','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is a stored procedure?','Saved SQL query block','Type of index','Table constraint','View type','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is referential integrity?','Column is unique','Foreign key must match primary key','Data is encrypted','Table is normalized','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','Which command adds a new row?','UPDATE','ALTER','INSERT','MODIFY','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is a deadlock?','Server crash','Two transactions wait for each other','Slow query','Broken index','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What does DDL stand for?','Data Define Language','Data Definition Language','Direct Data Logic','Dynamic Data Layer','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','Which SQL clause sorts results?','WHERE','GROUP BY','ORDER BY','HAVING','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is an ER diagram used for?','Schema visualization','Writing queries','Index design','Backup planning','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is a transaction?','Single SQL query','Logical unit of work','Index scan','Column type','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What does DML stand for?','Data Manipulation Language','Direct Machine Logic','Data Module Layer','Dynamic Memory Logic','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','Which is not a type of JOIN?','INNER JOIN','OUTER JOIN','CROSS JOIN','SINGLE JOIN','D');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What does ROLLBACK do?','Commits transaction','Undoes uncommitted changes','Drops table','Creates backup','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is schema?','Logical structure of database','Physical storage file','Query result set','Backup file','A');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is a candidate key?','Primary key only','Minimal superkey','Foreign key','Composite key','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('DBMS','What is sharding?','Encrypting rows','Horizontal database partitioning','Vertical indexing','Join optimization','B');

-- Advanced Data Structures and Algorithms Questions
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the time complexity of binary search?','O(n)','O(n log n)','O(log n)','O(1)','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','Which data structure uses FIFO?','Stack','Queue','Tree','Graph','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the worst case of QuickSort?','O(n log n)','O(n)','O(n^2)','O(log n)','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a Heap?','LIFO structure','Complete binary tree with heap property','Linked list variant','Hash structure','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','Which traversal visits left, root, right?','Pre-order','Post-order','In-order','Level-order','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is dynamic programming?','Runtime code generation','Breaking problem into overlapping subproblems','Memory allocation','Graph traversal','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the height of a balanced BST with n nodes?','O(n)','O(log n)','O(n log n)','O(1)','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a trie?','Binary tree','Tree for string storage','Hash table','Circular queue','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','Which algorithm finds shortest path in unweighted graph?','Dijkstra','Bellman-Ford','BFS','DFS','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the amortized cost of push in dynamic array?','O(n)','O(n log n)','O(1)','O(log n)','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a Red-Black tree?','AVL variant','Self-balancing BST with color property','Heap type','B-Tree variant','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What does DFS use internally?','Queue','Stack','Heap','Array','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is Dijkstra\'s algorithm used for?','Minimum spanning tree','Shortest path in weighted graph','Sorting','Pattern matching','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a B-Tree used for?','In-memory sorting','Disk-based indexing','String search','Graph traversal','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the space complexity of merge sort?','O(1)','O(log n)','O(n)','O(n^2)','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a hash collision?','Hash function error','Two keys map to same slot','Array overflow','Null pointer','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is Kruskal\'s algorithm?','Shortest path','Minimum spanning tree using edges','Graph coloring','Topological sort','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is memoization?','Memory allocation','Caching subproblem results','Stack overflow prevention','Dynamic memory','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','Which sorting has best average case O(n log n)?','Bubble Sort','Selection Sort','Insertion Sort','Merge Sort','D');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a segment tree used for?','String matching','Range queries and updates','Graph traversal','Hash storage','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the time complexity of Heap Sort?','O(n^2)','O(n log n)','O(n)','O(log n)','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is topological sorting?','Sorting numbers','Linear ordering of DAG nodes','Tree traversal','Cycle detection','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a Fenwick tree?','Self-balancing BST','Prefix sum tree (BIT)','Trie variant','Segment tree variant','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is Floyd-Warshall algorithm used for?','MST','All pairs shortest paths','Sorting','String matching','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is an AVL tree?','Any binary tree','Self-balancing BST by height','Heap type','Trie variant','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the worst case of insertion sort?','O(n log n)','O(n)','O(n^2)','O(log n)','C');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a disjoint set used for?','Path compression','Union-Find operations','Sorting','Graph coloring','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the Knapsack problem classified as?','Graph problem','Dynamic programming problem','Greedy problem only','Sorting problem','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is a sparse table?','Empty hash table','Static RMQ in O(1) after O(n log n) preprocessing','B-Tree with empty nodes','Compressed trie','B');
INSERT INTO questions(subject,question_text,option_a,option_b,option_c,option_d,correct_answer) VALUES('Advanced Data Structures and Algorithms','What is the time complexity of building a heap?','O(n log n)','O(n)','O(log n)','O(n^2)','B');