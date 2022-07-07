-- genre insert
INSERT INTO genre (id, name, tid) VALUES(10,'로맨스',null);
INSERT INTO genre (id, name, tid) VALUES(11,'로맨스/코미디',10);
INSERT INTO genre (id, name, tid) VALUES(12,'로맨스/전쟁',10);
INSERT INTO genre (id, name, tid) VALUES(20,'판타지',null);
INSERT INTO genre (id, name, tid) VALUES(21,'판타지/모험',20);
INSERT INTO genre (id, name, tid) VALUES(22,'판타지/SF',20);
INSERT INTO genre (id, name, tid) VALUES(30,'공포',null);
INSERT INTO genre (id, name, tid) VALUES(31,'공포/스릴러',null);

SELECT * FROM genre;



-- movie insert
INSERT INTO movie VALUES (null, 12,'바람과 함께 사라지다','victor fleming','bibian lee,frank mogan',19391215,'windimg1','windimg2','usa','221m','내일은 내일의 태양이 뜨는 법이니까
남북전쟁 발발 직전, 오하라 가문의 장녀 스칼렛은 도도한 매력으로 뭇 남성들의 우상이다.
그녀가 짝사랑하던 애슐리가 친구 멜라니와 결혼한다는 이야기를 듣고 뒤늦게 고백하지만 그 자리에서 거절당하고, 이 모습을 새로 이사 온 레트에게 들키고 만다.
당황해 어쩔 줄을 모르는 스칼렛과는 반대로 이미 레트는 거침없는 매력의 스칼렛에게 빠져들고 있었다.
전쟁은 남부에 불리해지고 스칼렛은 레트의 마차를 타고 죽을 고비를 넘기며 고향으로 돌아간다. 파란만장한 미국 현대사를 온몸으로 관통하는 가운데, 두 사람은 결혼하게 된다.
하지만, 행복한 결혼생활도 잠시, 레트는 여전히 스칼렛의 마음에는 애슐리가 있다는 사실을 깨닫게 되는데...
삶의 모든 것을 뜨겁게 사랑했던 여자 스칼렛
그런 그녀를 운명처럼 사랑했던 남자 레트
생애 가장 가슴 벅찬 클래식 로맨스가 찾아온다!');
INSERT INTO movie VALUES (null, 21,'오즈의 마법사','victor fleming','frank mogan,judi gland',19390805,'ozimg1.jpg','ozimg2.jpg','usa','201m','회오리 바람에 휩쓸려 오즈의 나라로 내던져진 도로시는 집으로 되돌아갈 수 있는 유일한 길이 위대한 오즈의 마법사를 만나는 것임을 알고 그를 찾아 긴 여정이 시작된다. 도로시는 애견 토토와 함께 노란 길을 따라 오즈의 마법사가 사는 에메랄드 시티로 향한다.');
INSERT INTO movie VALUES (null, 31,'싸이코','Alfred Joseph Hitchcock','Anthony Perkins, Janet Leigh',19600908,'psyimg1.jpg','psyimg2.jpg','usa','109m','회사원인 마리온(재닛 리 분)은 그녀의 애인 샘(존 게빈 분)과 결혼하길 원하지만 샘은 빚을 갚을 때까지 기다리라고 말한다. 그래서 그녀는 자신이 다니고 있는 회사 사장이 은행에 입금하라고 맡긴 돈을 들고 도망친다. 돈을 가지고 샘을 만나러 떠난 그녀는 도주 첫날 밤 도로변에 있는 낡은 모텔에 묵게 된다. 모텔의 주인인 노먼 베이츠(안소니 퍼킨스 분)는 그녀에게 친절하게 대해주며 자신은 모텔 바로 뒤쪽 빅토리아풍의 큰 저택에서 몸이 불편한 어머니와 함께 살고 있다고 말한다. 잠자리에 들기 전, 마리온이 샤워를 하는 도중, 난데없이 검은 형상이 욕실에 나타나고 마리온은 실종된다. 실종된 마리온을 찾기 위해 그녀의 언니 라일라와 샘, 그리고 보험회사 측에서 고용한 탐정인 아보가스트 등 세 사람이 추적에 나선다. 아보가스트는 조사 끝에 그녀가 머물렀던 모텔을 찾게 되는데...');
INSERT INTO movie VALUES (null, 22,'2001: 스페이스 오디세이','Stanley Kubrick ','Gary Lockwood, William Sylvester',19680402,'2001img1.jpg','2001img2.jpg','usa','239m','인류에게 문명의 지혜를 가르쳐 준 검은 돌기둥의 정체를 밝히기 위해서 목성으로 향하는 디스커버리호 안에는 선장 보우만과 승무원 풀, 전반적인 시스템을 관장하는 인공지능 컴퓨터 할이 타고 있다.평화롭던 우주선은 할이 스스로 생각하기 시작하면서부터 위기를 맞는다. 특히나 이 영화는 60년대 작품으로 인간이 아직 달에 가기 전에 만들어진, 기념비적인 SF 우주 영화.');
INSERT INTO movie VALUES (null, 31,'하녀','김기영 ','김진규, 이은심',19601103,'houseimg1.jpg','houseimg2.jpg','kor','111m','주인공인 그(김진규 분)는 아내(주증녀 분)와 다리가 불편한 딸, 그리고 아들(안성기 분)과 행복하게 살면서 방직공장에서 음악을 가르치는 음악선생이자 작곡가이다.
또한 그는 방직공장의 여공들 사이에서 인기가 높다.
그러던 어느 날 그의 집에 가정부(이은심 분)가 들어오고 집에는 이상한 분위기가 감돈다.
그는 아내 몰래 가정부와 불의의 관계를 맺는다. 그런데 가정부는 이상성격의 소유자로 그를 협박한다.
이렇게 한 지붕 아래서 남편과 아내, 그리고 가정부 이들의 기묘한 동거가 시작되는데...');



-- cust Insert
INSERT INTO cust VALUES ('kms', 'pwd01', '김민식', 19900805, '1000', 'man');
INSERT INTO cust VALUES ('awy', 'pwd02', '안원영', 19600908, '1500', 'man');
INSERT INTO cust VALUES ('jhj', 'pwd03', '장효준', 19680402, '2000', 'man');
INSERT INTO cust VALUES ('jsy', 'pwd04', '정세연', 19601103, '3000', 'woman');
INSERT INTO cust VALUES ('ljm', 'pwd05', '이진만', 19390805, '4500', 'man');
SELECT * FROM cust;


-- review Insert
INSERT INTO reviews VALUES (6000, 'kms', 1000, 1, '좋다', '2022-07-08');
INSERT INTO reviews VALUES (6001, 'awy', 1001, 2, '재밌다', '2022-07-02' );
INSERT INTO reviews VALUES (6002, 'jhj', 1002, 3, '지겹다', '2022-04-08');
INSERT INTO reviews VALUES (6003, 'jsy', 1003, 4, '잠이 온다', '2022-04-04');
INSERT INTO reviews VALUES (6004, 'ljm', 1004, 5, '즐겁다', '2022-01-08');
SELECT * FROM reviews;



-- pointlist Insert
INSERT INTO pointlist VALUES (3000, 'kms', 7000, '2020-07-08', '쿠폰1');
INSERT INTO pointlist VALUES (3001, 'awy', 8000, '2021-06-13', '쿠폰2');
INSERT INTO pointlist VALUES (3002, 'jhj', 9000, '2021-01-03', '쿠폰3');
INSERT INTO pointlist VALUES (3003, 'jsy', 10000, '2019-12-7', '쿠폰4');
INSERT INTO pointlist VALUES (3004, 'ljm', 11000, '2019-04-6', '쿠폰5');
SELECT * FROM pointlist;


-- SEAT TABLE DDL
INSERT INTO seat VALUES ('A01','A',01);
INSERT INTO seat VALUES ('A02','A',02);
INSERT INTO seat VALUES ('A03','A',03);
INSERT INTO seat VALUES ('A04','A',04);
INSERT INTO seat VALUES ('A05','A',05);
INSERT INTO seat VALUES ('A06','A',06);
INSERT INTO seat VALUES ('A07','A',07);
INSERT INTO seat VALUES ('A08','A',08);
INSERT INTO seat VALUES ('A09','A',09);
INSERT INTO seat VALUES ('A10','A',10);
INSERT INTO seat VALUES ('A11','A',11);
INSERT INTO seat VALUES ('A12','A',12);
INSERT INTO seat VALUES ('A13','A',13);
INSERT INTO seat VALUES ('A14','A',14);
INSERT INTO seat VALUES ('A15','A',15);
INSERT INTO seat VALUES ('A16','A',16);
INSERT INTO seat VALUES ('A17','A',17);
INSERT INTO seat VALUES ('A18','A',18);

INSERT INTO seat VALUES ('B01','B',01);
INSERT INTO seat VALUES ('B02','B',02);
INSERT INTO seat VALUES ('B03','B',03);
INSERT INTO seat VALUES ('B04','B',04);
INSERT INTO seat VALUES ('B05','B',05);
INSERT INTO seat VALUES ('B06','B',06);
INSERT INTO seat VALUES ('B07','B',07);
INSERT INTO seat VALUES ('B08','B',08);
INSERT INTO seat VALUES ('B09','B',09);
INSERT INTO seat VALUES ('B10','B',10);
INSERT INTO seat VALUES ('B11','B',11);
INSERT INTO seat VALUES ('B12','B',12);
INSERT INTO seat VALUES ('B13','B',13);
INSERT INTO seat VALUES ('B14','B',14);
INSERT INTO seat VALUES ('B15','B',15);
INSERT INTO seat VALUES ('B16','B',16);
INSERT INTO seat VALUES ('B17','B',17);
INSERT INTO seat VALUES ('B18','B',18);

INSERT INTO seat VALUES ('C01','C',01);
INSERT INTO seat VALUES ('C02','C',02);
INSERT INTO seat VALUES ('C03','C',03);
INSERT INTO seat VALUES ('C04','C',04);
INSERT INTO seat VALUES ('C05','C',05);
INSERT INTO seat VALUES ('C06','C',06);
INSERT INTO seat VALUES ('C07','C',07);
INSERT INTO seat VALUES ('C08','C',08);
INSERT INTO seat VALUES ('C09','C',09);
INSERT INTO seat VALUES ('C10','C',10);
INSERT INTO seat VALUES ('C11','C',11);
INSERT INTO seat VALUES ('C12','C',12);
INSERT INTO seat VALUES ('C13','C',13);
INSERT INTO seat VALUES ('C14','C',14);
INSERT INTO seat VALUES ('C15','C',15);
INSERT INTO seat VALUES ('C16','C',16);
INSERT INTO seat VALUES ('C17','C',17);
INSERT INTO seat VALUES ('C18','C',18);

INSERT INTO seat VALUES ('D01','D',01);
INSERT INTO seat VALUES ('D02','D',02);
INSERT INTO seat VALUES ('D03','D',03);
INSERT INTO seat VALUES ('D04','D',04);
INSERT INTO seat VALUES ('D05','D',05);
INSERT INTO seat VALUES ('D06','D',06);
INSERT INTO seat VALUES ('D07','D',07);
INSERT INTO seat VALUES ('D08','D',08);
INSERT INTO seat VALUES ('D09','D',09);
INSERT INTO seat VALUES ('D10','D',10);
INSERT INTO seat VALUES ('D11','D',11);
INSERT INTO seat VALUES ('D12','D',12);
INSERT INTO seat VALUES ('D13','D',13);
INSERT INTO seat VALUES ('D14','D',14);
INSERT INTO seat VALUES ('D15','D',15);
INSERT INTO seat VALUES ('D16','D',16);
INSERT INTO seat VALUES ('D17','D',17);
INSERT INTO seat VALUES ('D18','D',18);

INSERT INTO seat VALUES ('E01','E',01);
INSERT INTO seat VALUES ('E02','E',02);
INSERT INTO seat VALUES ('E03','E',03);
INSERT INTO seat VALUES ('E04','E',04);
INSERT INTO seat VALUES ('E05','E',05);
INSERT INTO seat VALUES ('E06','E',06);
INSERT INTO seat VALUES ('E07','E',07);
INSERT INTO seat VALUES ('E08','E',08);
INSERT INTO seat VALUES ('E09','E',09);
INSERT INTO seat VALUES ('E10','E',10);
INSERT INTO seat VALUES ('E11','E',11);
INSERT INTO seat VALUES ('E12','E',12);
INSERT INTO seat VALUES ('E13','E',13);
INSERT INTO seat VALUES ('E14','E',14);
INSERT INTO seat VALUES ('E15','E',15);
INSERT INTO seat VALUES ('E16','E',16);
INSERT INTO seat VALUES ('E17','E',17);
INSERT INTO seat VALUES ('E18','E',18);

INSERT INTO seat VALUES ('F01','F',01);
INSERT INTO seat VALUES ('F02','F',02);
INSERT INTO seat VALUES ('F03','F',03);
INSERT INTO seat VALUES ('F04','F',04);
INSERT INTO seat VALUES ('F05','F',05);
INSERT INTO seat VALUES ('F06','F',06);
INSERT INTO seat VALUES ('F07','F',07);
INSERT INTO seat VALUES ('F08','F',08);
INSERT INTO seat VALUES ('F09','F',09);
INSERT INTO seat VALUES ('F10','F',10);
INSERT INTO seat VALUES ('F11','F',11);
INSERT INTO seat VALUES ('F12','F',12);
INSERT INTO seat VALUES ('F13','F',13);
INSERT INTO seat VALUES ('F14','F',14);
INSERT INTO seat VALUES ('F15','F',15);
INSERT INTO seat VALUES ('F16','F',16);
INSERT INTO seat VALUES ('F17','F',17);
INSERT INTO seat VALUES ('F18','F',18);

INSERT INTO seat VALUES ('G01','G',01);
INSERT INTO seat VALUES ('G02','G',02);
INSERT INTO seat VALUES ('G03','G',03);
INSERT INTO seat VALUES ('G04','G',04);
INSERT INTO seat VALUES ('G05','G',05);
INSERT INTO seat VALUES ('G06','G',06);
INSERT INTO seat VALUES ('G07','G',07);
INSERT INTO seat VALUES ('G08','G',08);
INSERT INTO seat VALUES ('G09','G',09);
INSERT INTO seat VALUES ('G10','G',10);
INSERT INTO seat VALUES ('G11','G',11);
INSERT INTO seat VALUES ('G12','G',12);
INSERT INTO seat VALUES ('G13','G',13);
INSERT INTO seat VALUES ('G14','G',14);
INSERT INTO seat VALUES ('G15','G',15);
INSERT INTO seat VALUES ('G16','G',16);
INSERT INTO seat VALUES ('G17','G',17);
INSERT INTO seat VALUES ('G18','G',18);

INSERT INTO seat VALUES ('I01','I',01);
INSERT INTO seat VALUES ('I02','I',02);
INSERT INTO seat VALUES ('I03','I',03);
INSERT INTO seat VALUES ('I04','I',04);
INSERT INTO seat VALUES ('I05','I',05);
INSERT INTO seat VALUES ('I06','I',06);
INSERT INTO seat VALUES ('I07','I',07);
INSERT INTO seat VALUES ('I08','I',08);
INSERT INTO seat VALUES ('I09','I',09);
INSERT INTO seat VALUES ('I10','I',10);
INSERT INTO seat VALUES ('I11','I',11);
INSERT INTO seat VALUES ('I12','I',12);
INSERT INTO seat VALUES ('I13','I',13);
INSERT INTO seat VALUES ('I14','I',14);
INSERT INTO seat VALUES ('I15','I',15);
INSERT INTO seat VALUES ('I16','I',16);
INSERT INTO seat VALUES ('I17','I',17);
INSERT INTO seat VALUES ('I18','I',18);

INSERT INTO seat VALUES ('J01','J',01);
INSERT INTO seat VALUES ('J02','J',02);
INSERT INTO seat VALUES ('J03','J',03);
INSERT INTO seat VALUES ('J04','J',04);
INSERT INTO seat VALUES ('J05','J',05);
INSERT INTO seat VALUES ('J06','J',06);
INSERT INTO seat VALUES ('J07','J',07);
INSERT INTO seat VALUES ('J08','J',08);
INSERT INTO seat VALUES ('J09','J',09);
INSERT INTO seat VALUES ('J10','J',10);
INSERT INTO seat VALUES ('J11','J',11);
INSERT INTO seat VALUES ('J12','J',12);
INSERT INTO seat VALUES ('J13','J',13);
INSERT INTO seat VALUES ('J14','J',14);
INSERT INTO seat VALUES ('J15','J',15);
INSERT INTO seat VALUES ('J16','J',16);
INSERT INTO seat VALUES ('J17','J',17);
INSERT INTO seat VALUES ('J18','J',18);

INSERT INTO seat VALUES ('K01','K',01);
INSERT INTO seat VALUES ('K02','K',02);
INSERT INTO seat VALUES ('K03','K',03);
INSERT INTO seat VALUES ('K04','K',04);
INSERT INTO seat VALUES ('K05','K',05);
INSERT INTO seat VALUES ('K06','K',06);
INSERT INTO seat VALUES ('K07','K',07);
INSERT INTO seat VALUES ('K08','K',08);
INSERT INTO seat VALUES ('K09','K',09);
INSERT INTO seat VALUES ('K10','K',10);
INSERT INTO seat VALUES ('K11','K',11);
INSERT INTO seat VALUES ('K12','K',12);
INSERT INTO seat VALUES ('K13','K',13);
INSERT INTO seat VALUES ('K14','K',14);
INSERT INTO seat VALUES ('K15','K',15);
INSERT INTO seat VALUES ('K16','K',16);
INSERT INTO seat VALUES ('K17','K',17);
INSERT INTO seat VALUES ('K18','K',18);

INSERT INTO seat VALUES ('L01','L',01);
INSERT INTO seat VALUES ('L02','L',02);
INSERT INTO seat VALUES ('L03','L',03);
INSERT INTO seat VALUES ('L04','L',04);
INSERT INTO seat VALUES ('L05','L',05);
INSERT INTO seat VALUES ('L06','L',06);
INSERT INTO seat VALUES ('L07','L',07);
INSERT INTO seat VALUES ('L08','L',08);
INSERT INTO seat VALUES ('L09','L',09);
INSERT INTO seat VALUES ('L10','L',10);
INSERT INTO seat VALUES ('L11','L',11);
INSERT INTO seat VALUES ('L12','L',12);
INSERT INTO seat VALUES ('L13','L',13);
INSERT INTO seat VALUES ('L14','L',14);
INSERT INTO seat VALUES ('L15','L',15);
INSERT INTO seat VALUES ('L16','L',16);
INSERT INTO seat VALUES ('L17','L',17);
INSERT INTO seat VALUES ('L18','L',18);

SELECT * FROM seat;



-- THEATER TABLE DDL
INSERT INTO theater VALUES (1,'A01',168);
INSERT INTO theater VALUES (2,'A01',168);
INSERT INTO theater VALUES (3,'A01',168);

SELECT * FROM theater;



-- SCHEDULES TALBE DDL
INSERT INTO schedules VALUES (null,1,1000,'2022-07-07');
SELECT * FROM schedules;




-- DETAIL_SCHEDULES TALBE DDL
INSERT INTO detail_schedules VALUES (2000,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2000,2,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2000,3,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2000,4,'19:00:00','21:00:00');
SELECT * FROM detail_schedules;




-- detail_reservation Insert
INSERT INTO detail_reservation VALUES(NULL,2000,sysdate(),2,'A11');
INSERT INTO detail_reservation VALUES(NULL,2000,sysdate(),1,'A12');
INSERT INTO detail_reservation VALUES(NULL,2000,sysdate(),4,'C21');
INSERT INTO detail_reservation VALUES(NULL,2000,sysdate(),1,'A17');
INSERT INTO detail_reservation VALUES(NULL,2000,sysdate(),3,'D22');

select * from detail_reservation;



-- reservation Insert
INSERT INTO reservation VALUES (NULL, 4000, 'kms', 2, 20000, 19000);
INSERT INTO reservation VALUES (NULL, 4001, 'kms', 2, 20000, 9000);
INSERT INTO reservation VALUES (NULL, 4002, 'kms', 2, 20000, 10000);
INSERT INTO reservation VALUES (NULL, 4003, 'kms', 2, 20000, 10000);
INSERT INTO reservation VALUES (NULL, 4004, 'kms', 1, 10000, 9000);

SELECT * FROM reservation;



-- coupon Insert
INSERT INTO coupon VALUES ('event01', 1000);
INSERT INTO coupon VALUES ('event02', 2000);
INSERT INTO coupon VALUES ('event03', 3000);
INSERT INTO coupon VALUES ('event04', 4000);
INSERT INTO coupon VALUES ('event05', 5000);
INSERT INTO coupon VALUES ('event06', 6000);
SELECT * FROM coupon;



-- mycoupon Insert
INSERT INTO mycoupon VALUES (NULL,'kms', 'event01',true, NULL, sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 'event02',true, NULL, sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 'event03',false, sysdate(), sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 'event04',false, sysdate(), sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 'event05',true, NULL, sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 'event06',true, NULL, sysdate());
SELECT * FROM mycoupon;










