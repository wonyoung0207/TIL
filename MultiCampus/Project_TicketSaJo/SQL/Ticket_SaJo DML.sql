-- genre insert
INSERT INTO genre (id, name, tid) VALUES(10,'로맨스',null);
INSERT INTO genre (id, name, tid) VALUES(11,'로맨스/코미디',10);
INSERT INTO genre (id, name, tid) VALUES(12,'로맨스/전쟁',10);
INSERT INTO genre (id, name, tid) VALUES(13,'로맨스/드라마',10);
INSERT INTO genre (id, name, tid) VALUES(20,'판타지',null);
INSERT INTO genre (id, name, tid) VALUES(21,'판타지/모험',20);
INSERT INTO genre (id, name, tid) VALUES(22,'판타지/SF',20);
INSERT INTO genre (id, name, tid) VALUES(30,'공포',null);
INSERT INTO genre (id, name, tid) VALUES(31,'공포/스릴러',30);
INSERT INTO genre (id, name, tid) VALUES(40,'드라마',null);
INSERT INTO genre (id, name, tid) VALUES(41,'드라마/범죄',40);
INSERT INTO genre (id, name, tid) VALUES(42,'드라마/모험',40);

SELECT * FROM genre;



-- movie insert
INSERT INTO movie VALUES (null, 12,'바람과 함께 사라지다','victor fleming','bibian lee,frank mogan',19391215,'windimg1.jpg','windimg2.jpg','미국','221m','내일은 내일의 태양이 뜨는 법이니까
남북전쟁 발발 직전, 오하라 가문의 장녀 스칼렛은 도도한 매력으로 뭇 남성들의 우상이다.
그녀가 짝사랑하던 애슐리가 친구 멜라니와 결혼한다는 이야기를 듣고 뒤늦게 고백하지만 그 자리에서 거절당하고, 이 모습을 새로 이사 온 레트에게 들키고 만다.
당황해 어쩔 줄을 모르는 스칼렛과는 반대로 이미 레트는 거침없는 매력의 스칼렛에게 빠져들고 있었다.
전쟁은 남부에 불리해지고 스칼렛은 레트의 마차를 타고 죽을 고비를 넘기며 고향으로 돌아간다. 파란만장한 미국 현대사를 온몸으로 관통하는 가운데, 두 사람은 결혼하게 된다.
하지만, 행복한 결혼생활도 잠시, 레트는 여전히 스칼렛의 마음에는 애슐리가 있다는 사실을 깨닫게 되는데...
삶의 모든 것을 뜨겁게 사랑했던 여자 스칼렛
그런 그녀를 운명처럼 사랑했던 남자 레트
생애 가장 가슴 벅찬 클래식 로맨스가 찾아온다!');
INSERT INTO movie VALUES (null, 21,'오즈의 마법사','victor fleming','frank mogan,judi gland',19390805,'ozimg1.jpg','ozimg2.jpg','미국','201m','회오리 바람에 휩쓸려 오즈의 나라로 내던져진 도로시는 집으로 되돌아갈 수 있는 유일한 길이 위대한 오즈의 마법사를 만나는 것임을 알고 그를 찾아 긴 여정이 시작된다. 도로시는 애견 토토와 함께 노란 길을 따라 오즈의 마법사가 사는 에메랄드 시티로 향한다.');
INSERT INTO movie VALUES (null, 31,'싸이코','Alfred Joseph Hitchcock','Anthony Perkins, Janet Leigh',19600908,'psyimg1.jpg','psyimg2.jpg','미국','109m','회사원인 마리온(재닛 리 분)은 그녀의 애인 샘(존 게빈 분)과 결혼하길 원하지만 샘은 빚을 갚을 때까지 기다리라고 말한다. 그래서 그녀는 자신이 다니고 있는 회사 사장이 은행에 입금하라고 맡긴 돈을 들고 도망친다. 돈을 가지고 샘을 만나러 떠난 그녀는 도주 첫날 밤 도로변에 있는 낡은 모텔에 묵게 된다. 모텔의 주인인 노먼 베이츠(안소니 퍼킨스 분)는 그녀에게 친절하게 대해주며 자신은 모텔 바로 뒤쪽 빅토리아풍의 큰 저택에서 몸이 불편한 어머니와 함께 살고 있다고 말한다. 잠자리에 들기 전, 마리온이 샤워를 하는 도중, 난데없이 검은 형상이 욕실에 나타나고 마리온은 실종된다. 실종된 마리온을 찾기 위해 그녀의 언니 라일라와 샘, 그리고 보험회사 측에서 고용한 탐정인 아보가스트 등 세 사람이 추적에 나선다. 아보가스트는 조사 끝에 그녀가 머물렀던 모텔을 찾게 되는데...');
INSERT INTO movie VALUES (null, 22,'2001: 스페이스 오디세이','Stanley Kubrick ','Gary Lockwood, William Sylvester',19680402,'2001img1.jpg','2001img2.jpg','미국','239m','인류에게 문명의 지혜를 가르쳐 준 검은 돌기둥의 정체를 밝히기 위해서 목성으로 향하는 디스커버리호 안에는 선장 보우만과 승무원 풀, 전반적인 시스템을 관장하는 인공지능 컴퓨터 할이 타고 있다.평화롭던 우주선은 할이 스스로 생각하기 시작하면서부터 위기를 맞는다. 특히나 이 영화는 60년대 작품으로 인간이 아직 달에 가기 전에 만들어진, 기념비적인 SF 우주 영화.');
INSERT INTO movie VALUES (null, 31,'하녀','김기영 ','김진규, 이은심',19601103,'houseimg1.jpg','houseimg2.jpg','한국','111m','주인공인 그(김진규 분)는 아내(주증녀 분)와 다리가 불편한 딸, 그리고 아들(안성기 분)과 행복하게 살면서 방직공장에서 음악을 가르치는 음악선생이자 작곡가이다.
또한 그는 방직공장의 여공들 사이에서 인기가 높다.
그러던 어느 날 그의 집에 가정부(이은심 분)가 들어오고 집에는 이상한 분위기가 감돈다.
그는 아내 몰래 가정부와 불의의 관계를 맺는다. 그런데 가정부는 이상성격의 소유자로 그를 협박한다.
이렇게 한 지붕 아래서 남편과 아내, 그리고 가정부 이들의 기묘한 동거가 시작되는데...');

-- 안원영 
INSERT INTO movie VALUES (null, 31,'쇼생크 탈출','프랭크 다라본트','팀 로빈스,모건 프리먼',19930910,'shawimg1.jpg','shawimg2.jpg','미국','142m','주인공 앤디 듀프레인(Andy Dufresne)이 아내와 그녀의 정부를 살해했다는 누명을 뒤집어쓰고 메인 주 주립 교도소 쇼생크(Shawshank)에 갇히면서 이야기가 시작된다. 앤디는 자신이 결백하니 곧 풀려나리라 생각했던 것과는 달리 2번의 종신형을 선고받아 쇼생크 교도소에 투옥당한다.');
INSERT INTO movie VALUES (null, 31,'Breakfast At Tiffany','블레이크 에드워즈','오드리 햅번, 조지 페파드',19621001,'tiffanyimg1.jpg','tiffanyimg2.jpg','미국','162m','뉴욕 맨하탄의 새벽 거리, 한 여인이 택시에서 내린다.
소매가 치렁한 이브닝 드레스, 얼굴을 반이나 가린 검은 안경. 그녀는 티파니 보석상을 활보하며 흥미로운 눈빛으로 보석을 바라본다. 한 손에 빵을 들고, 우아한 몸짓으로 새벽거리를 리드미컬하게 걸어가는 그녀의 이름은 홀리(Holly Golightly: 오드리 헵번 분), 사실 그녀는 택사스 농부의 아내로 어떻게 그녀가 맨하탄에 정착했는지 알 수 없다.');
INSERT INTO movie VALUES (null, 11,'로마의 휴일','윌리엄 와일러','그레고리 펙, 오드리 햅번',19550930,'romaimg1.jpg','romaimg2.jpg','미국','118m','왕실의 딱딱한 제약과 정해진 스케줄에 피곤해 지고 싫증난 앤 공주(Princess Ann: 오드리 헵번 분)는 거리로 뛰쳐나가 잠들었다가 어느 신사(Joe Bradley: 그레고리 펙 분)를 만난다. 그와 함께 아이스크림도 맛나게 먹고 신나게 스쿠터를 타고 다니면서 서민의 즐거운 생활을 맛 본 앤 공주는 신사와의 고별식이 다가오자 무척이나 아쉬워한다. 한편 거리에서 벤치에 잠든 여인을 만난 그 신사는 특종을 찾는 신문기자였다. 그저 불쌍한 여인인 줄 알았던 아가씨가 앤 공주임을 알아챈 기자는 굴러들어온 특종감을 만났으니 신나기 그지없었다.');
INSERT INTO movie VALUES (null, 11,'사랑은 비를 타고','진 켈리,스탠리','진 켈리, 도널드 오코너',19541021,'rainimg1.jpg','rainimg2.jpg','미국','103m','아마츄어 쇼 코미디언인 돈 록우드(Don Lockwood: 진 켈리 분)와 코스모(Cosmo Brown: 도날드 오코너 분)는 공연을 하며 이곳저곳을 떠돌아다니다 뜻대로 되지않자 새 일자리를 얻기위해 헐리우드로 온다. 그런데 우연찮게 돈 록우드는 마뉴멘탈 영화사의 스턴트맨역을 따내게 되고 당시 최고의 인기를 누리고 있던 여배우인 리나 레이먼트(Lina Lamont: 쟌 하겐 분)와 함께 다수의 영화에 출연함으로써 단연 스타로 급부상하게 된다.
  그러나 화려한 영광도 잠시, 헐리웃 영화계가 무성영화에서 유성영화 체제로 전환됨으로써 목소리 연기가 너무나 형편없는 리나 레이먼트 때문에 영화를 완전히 망치게 된다. 그 때문에 돈 록우드와 그의 영화는 완전히 인기를 잃게 된다.');
  
-- 정세연
INSERT INTO movie VALUES (null, 11,'건축학개론','이용주','엄태웅, 한가인',20120322,'architecture1.jpg','architecture2.jpg','한국','118m','생기 넘치지만 숫기 없던 스무 살, 건축학과 승민은 건축학개론 수업에서 처음 만난 음대생 서연에게 반한다. 함께 숙제를 하게 되면서 차츰 마음을 열고 친해지지만, 자신의 마음을 표현하는 데 서툰 순진한 승민은 입 밖에 낼 수 없었던 고백을 마음 속에 품은 채 작은 오해로 인해 서연과 멀어지게 된다. 어쩌면 다시…사랑할 수 있을까? 15년 만에 그녀를 다시 만났다 서른 다섯의 건축가가 된 승민 앞에 15년 만에 불쑥 나타난 서연. 당황스러움을 감추지 못하는 승민에게 서연은 자신을 위한 집을 설계해달라고 한다. 자신의 이름을 건 첫 작품으로 서연의 집을 짓게 된 승민, 함께 집을 완성해 가는 동안 어쩌면 사랑이었을지 모를 그때의 기억이 되살아나 두 사람 사이에 새로운 감정이 쌓이기 시작하는데…');
INSERT INTO movie VALUES (null, 31,'숨바꼭질','허정','손현주,문정희,전미선',20130814,'hideandseek1.jpg','hideandseek1.jpg','한국','107m','고급 아파트에서 완벽한 가정을 꾸리고 사는 성공한 사업가 ‘백성수’(손현주)는 하나뿐인 형 백성철에 대한 비밀과 지독한 결벽증을 가지고 있다. 어느 날 성수는 형의 실종 소식을 듣고 수십 년 만에 찾아간 형의 아파트에서 집집마다 새겨진 이상한 암호와 형을 알고 있는 ‘주희’(문정희) 가족을 만난다. “제발 그 사람한테 제 딸 좀 그만 훔쳐보라고 하세요” 어린 딸과 단 둘이 살고 있는 ‘주희’는 자신의 집을 훔쳐보는 누군가의 존재를 느끼며 두려움에 떨고 있다.');
INSERT INTO movie VALUES (null, 21,'아바타','James Francis Cameron','Samuel Henry John Worthington, Zoe Saldaña',20091217,'avatar1.jpg','avatar2.jpg','미국','162m','지구 에네지 고갈 문제를 해결하기 위해 판도라 행성으로 향한 인류는 원주민 나비족과 대립하게 된다. 이 과정에서, 전직 해병대원 제이크 설리(샘 워싱턴)가 아바타 프로그램을 통해 나비족의 중심부에 투입되는데... 피할 수 없는 전쟁! 이 모든 운명을 손에 쥔 제이크! 그 누구도 넘보지 못한 역대급 세계가 열린다!');
INSERT INTO movie VALUES (null, 31,'범죄도시1','강윤성','마동석, 윤계상',20171003,'crime1.jpg','crime2.jpg','한국','121m','2004년 서울… 하얼빈에서 넘어와 단숨에 기존 조직들을 장악하고 가장 강력한 세력인 춘식이파 보스 ‘황사장(조재윤 분)’까지 위협하며 도시 일대의 최강자로 급부상한 신흥범죄조직의 악랄한 보스 ‘장첸(윤계상 분)’. 대한민국을 뒤흔든 ‘장첸(윤계상 분)’ 일당을 잡기 위해 오직 주먹 한방으로 도시의 평화를 유지해 온 괴물형사 ‘마석도(마동석 분)’와 인간미 넘치는 든든한 리더 ‘전일만(최귀화 분)’ 반장이 이끄는 강력반은 나쁜 놈들을 한방에 쓸어버릴 끝.짱.나.는. 작전을 세우는데… 통쾌하고! 화끈하고! 살벌하게! 나쁜 놈들 때려잡는 강력반 형사들의 ‘조폭소탕작전’이 시작된다!');


-- 김민식 
INSERT INTO movie VALUES (null, 41,'라쇼몽','Kurosawa Akira','Toshiro Mifune, Machiko Kyō',19500825,'rashomonimg1.jpg','rashomonimg2.jpg','미국','212m','전란이 난무하는 헤이안 시대, 억수같은 폭우가 쏟아지는 라생문의 처마 밑에서 나무꾼과 스님이 모르겠어. 아무래도 모르겠어 라며 심각한 표정으로 생각에 잠겨 있다. 잠시 비를 피하러 그곳에 들른 한 남자가 그 소리를 듣고 궁금해 한다. 이들은 이 남자를 상대로 최근에 그 마을에 있었던 기묘한 사건을 들려준다.');
INSERT INTO movie VALUES (null, 31,'벤허','William Wyler','Charlton Heston',19591118,'benhurimg1.jpg','benhurimg2.jpg','일본','88m','복수와 용서, 구원에 관한 스펙터클 클래식 <벤허> 역사상 가장 품격 있는 명작의 귀환! 서기 26년, 로마 제국 시대 ‘유다 벤허’(찰톤 헤스톤)는 예루살렘의 제일 가는 유태 귀족이다. 어느날, 로마의 지배하에 있던 이스라엘에 새로운 총독이 부임해오고, 그를 보좌하는 주둔 사령관으로 벤허의 옛 친구인 멧살라(스티븐 보이드)가 임명된다. ');
INSERT INTO movie VALUES (null, 13,'서울의 지붕 밑','이형표','김승호, 김진규, 최은희',19611215,'seoulimg1.jpg','seoulimg2.jpg','한국','115m','서울의 한 동네에서 한의원을 운영하는 김학규(김승호)는 건너편에 생긴 산부인과로 손님을 많이 빼앗겨 골이 나 있다. 그의 딸인 미장원을 운영하는 젊은 미망인인 현옥(최은희)은 산부인과 의사 최두열(김진규)과 좋은 감정을 나누고 있지만, 김학규는 최두열을 시기하고 질투하여 그 둘의 사이를 사사건건 훼방 놓으려 한다. 대학을 나왔으나 취직을 못하고 있는 아들 현구(신영균)는 점례를 임신시켜 결혼하려고 하나, 김학규는 점례가 동네 주막 여주인(황정순)의 딸이라며 반대한다. 어느 날 최두열이 시의원 선거에 나간다는 소식을 접한 김학규는 입후보하고 전 재산을 투자해 선거에 몰두하지만 결국 낙선의 고배를 마신다. 쓸데없는 아집으로 가사를 탕진한 김학규는 자신의 잘못을 뉘우치고 현구와 점례를 받아들이고 현옥과 두열의 관계도 승낙한다.');
INSERT INTO movie VALUES (null, 41,'시계 태엽 오렌지','Stanley Kubrick','Malcolm McDowell,Patrick Magee',19711219,'clockimg1.jpg','clockimg2.jpg','영국','136m','노숙자 폭행, 집단 싸움, 차량 절도, 주택 침입… 10대 소년 ‘알렉스’는 친구들과 어울려 극악한 비행을 저지르고 다닌다.어느 때와 다름없이 한 저택에 침입해 주인과 싸우고 달아나려던 순간 경찰에 검거된다. 살인죄가 적용되어 14년 형을 살게 된 ‘알렉스’.');



Select * from movie;

-- cust Insert
INSERT INTO cust VALUES ('kms', 'pwD1', '김민식', 19900805, '0', 'man',true);
INSERT INTO cust VALUES ('awy', 'pwD2', '안원영', 19600908, '0', 'man',true);
INSERT INTO cust VALUES ('jhj', 'jhj', '장효준', 19680402, '1000', 'man',true);
INSERT INTO cust VALUES ('jsy', 'pwD4', '정세연', 19601103, '0', 'woman',true);
INSERT INTO cust VALUES ('ljm', 'pwD5', '이진만', 19390805, '0', 'man',true);
INSERT INTO cust VALUES ('id01', '11111', '방말숙', 19700504, '0', 'woman',true);
INSERT INTO cust VALUES ('id02', '22222', '이말숙', 19620503, '0', 'man',true);
INSERT INTO cust VALUES ('id03', '33333', '태말숙', 20030214, '0', 'woman',true);
INSERT INTO cust VALUES ('id04', '44444', '남말숙', 19970729, '0', 'man',true);
INSERT INTO cust VALUES ('id05', '55555', '홍말숙', 19371219, '0', 'woman',true);

SELECT * FROM 	cust;


-- review Insert
INSERT INTO reviews VALUES (null, 'kms', 1000, 1, '좋다', '2022-07-08');
INSERT INTO reviews VALUES (null, 'awy', 1001, 2, '재밌다', '2022-07-02' );
INSERT INTO reviews VALUES (null, 'jhj', 1002, 3, '지겹다', '2022-04-08');
INSERT INTO reviews VALUES (null, 'jsy', 1003, 4, '잠이 온다', '2022-04-04');
INSERT INTO reviews VALUES (null, 'ljm', 1004, 5, '즐겁다', '2022-01-08');
INSERT INTO reviews VALUES (null, 'kms', 1000, 4, '명작입니다.', '2022-07-08');
INSERT INTO reviews VALUES (null, 'awy', 1005, 1, '너무 졸려요', '2022-07-08');
INSERT INTO reviews VALUES (null, 'ljm', 1004, 2, '생각보다는 별로', '2022-07-02' );
INSERT INTO reviews VALUES (null, 'jsy', 1001, 3, '지겹다', '2022-04-08');
INSERT INTO reviews VALUES (null, 'kms', 1002, 4, '기대보다 훨씬 재밌어요', '2022-04-04');
INSERT INTO reviews VALUES (null, 'jhj', 1003, 5, '최고의 명작', '2022-01-08');
INSERT INTO reviews VALUES (null, 'jhj', 1000, 4, '그럭저럭 재밌습니다.', '2022-07-08');

INSERT INTO reviews VALUES (null, 'kms', 1006, 1, '좋다', '2022-07-08');
INSERT INTO reviews VALUES (null, 'awy', 1007, 2, '재밌다', '2022-07-02' );
INSERT INTO reviews VALUES (null, 'jhj', 1008, 3, '지겹다', '2022-04-08');
INSERT INTO reviews VALUES (null, 'jsy', 1009, 4, '잠이 온다', '2022-04-04');
INSERT INTO reviews VALUES (null, 'ljm', 1010, 5, '즐겁다', '2022-01-08');

INSERT INTO reviews VALUES (null, 'kms', 1011, 1, '좋다', '2022-07-08');
INSERT INTO reviews VALUES (null, 'awy', 1012, 2, '재밌다', '2022-07-02' );
INSERT INTO reviews VALUES (null, 'jhj', 1013, 3, '지겹다', '2022-04-08');
INSERT INTO reviews VALUES (null, 'jsy', 1014, 4, '잠이 온다', '2022-04-04');
INSERT INTO reviews VALUES (null, 'ljm', 1015, 5, '즐겁다', '2022-01-08');
INSERT INTO reviews VALUES (null, 'ljm', 1016, 5, '즐겁다', '2022-01-08');

INSERT INTO reviews VALUES (null, 'kms', 1006, 3, '좋다', '2022-07-08');
INSERT INTO reviews VALUES (null, 'awy', 1007, 1, '잠이 온다', '2022-07-02' );
INSERT INTO reviews VALUES (null, 'jhj', 1008, 2, '지겹다', '2022-04-08');
INSERT INTO reviews VALUES (null, 'jsy', 1009, 5, '꼭 보세요', '2022-04-04');
INSERT INTO reviews VALUES (null, 'ljm', 1010, 2, '지루해요', '2022-01-08');

INSERT INTO reviews VALUES (null, 'kms', 1011, 2, '지겹다', '2022-07-08');
INSERT INTO reviews VALUES (null, 'awy', 1012, 2, '잠이 온다', '2022-07-02' );
INSERT INTO reviews VALUES (null, 'jhj', 1013, 4, '즐겁다', '2022-04-08');
INSERT INTO reviews VALUES (null, 'jsy', 1014, 5, '재밌다', '2022-04-04');
INSERT INTO reviews VALUES (null, 'ljm', 1015, 2, '즐겁다', '2022-01-08');
INSERT INTO reviews VALUES (null, 'ljm', 1016, 3, '그저 그렇다', '2022-01-08');

SELECT * FROM reviews;


-- pointlist Insert
INSERT INTO pointlist VALUES (3000, 'kms', 7000, '2020-07-08', '쿠폰1');
INSERT INTO pointlist VALUES (3001, 'awy', 8000, '2021-06-13', '쿠폰2');
INSERT INTO pointlist VALUES (3002, 'jhj', 9000, '2021-01-03', '쿠폰3');
INSERT INTO pointlist VALUES (3003, 'jsy', 10000, '2019-12-7', '쿠폰4');
INSERT INTO pointlist VALUES (3004, 'ljm', 11000, '2019-04-6', '쿠폰5');
SELECT * FROM pointlist;






-- theater TABLE DDL

-- 1관
INSERT INTO theater VALUES (1,'A','01','A01');
INSERT INTO theater VALUES (1,'A','02','A02');
INSERT INTO theater VALUES (1,'A','03','A03');
INSERT INTO theater VALUES (1,'A','04','A04');
INSERT INTO theater VALUES (1,'A','05','A05');
INSERT INTO theater VALUES (1,'A','06','A06');
INSERT INTO theater VALUES (1,'A','07','A07');
INSERT INTO theater VALUES (1,'A','08','A08');
INSERT INTO theater VALUES (1,'A','09','A09');
INSERT INTO theater VALUES (1,'A',10,'A10');
INSERT INTO theater VALUES (1,'A',11,'A11');
INSERT INTO theater VALUES (1,'A',12,'A12');
INSERT INTO theater VALUES (1,'A',13,'A13');
INSERT INTO theater VALUES (1,'A',14,'A14');
INSERT INTO theater VALUES (1,'A',15,'A15');
INSERT INTO theater VALUES (1,'A',16,'A16');
INSERT INTO theater VALUES (1,'A',17,'A17');
INSERT INTO theater VALUES (1,'A',18,'A18');

INSERT INTO theater VALUES (1,'B','01','B01');
INSERT INTO theater VALUES (1,'B','02','B02');
INSERT INTO theater VALUES (1,'B','03','B03');
INSERT INTO theater VALUES (1,'B','04','B04');
INSERT INTO theater VALUES (1,'B','05','B05');
INSERT INTO theater VALUES (1,'B','06','B06');
INSERT INTO theater VALUES (1,'B','07','B07');
INSERT INTO theater VALUES (1,'B','08','B08');
INSERT INTO theater VALUES (1,'B','09','B09');
INSERT INTO theater VALUES (1,'B',10,'B10');
INSERT INTO theater VALUES (1,'B',11,'B11');
INSERT INTO theater VALUES (1,'B',12,'B12');
INSERT INTO theater VALUES (1,'B',13,'B13');
INSERT INTO theater VALUES (1,'B',14,'B14');
INSERT INTO theater VALUES (1,'B',15,'B15');
INSERT INTO theater VALUES (1,'B',16,'B16');
INSERT INTO theater VALUES (1,'B',17,'B17');
INSERT INTO theater VALUES (1,'B',18,'B18');

INSERT INTO theater VALUES (1,'C','01','C01');
INSERT INTO theater VALUES (1,'C','02','C02');
INSERT INTO theater VALUES (1,'C','03','C03');
INSERT INTO theater VALUES (1,'C','04','C04');
INSERT INTO theater VALUES (1,'C','05','C05');
INSERT INTO theater VALUES (1,'C','06','C06');
INSERT INTO theater VALUES (1,'C','07','C07');
INSERT INTO theater VALUES (1,'C','08','C08');
INSERT INTO theater VALUES (1,'C','09','C09');
INSERT INTO theater VALUES (1,'C',10,'C10');
INSERT INTO theater VALUES (1,'C',11,'C11');
INSERT INTO theater VALUES (1,'C',12,'C12');
INSERT INTO theater VALUES (1,'C',13,'C13');
INSERT INTO theater VALUES (1,'C',14,'C14');
INSERT INTO theater VALUES (1,'C',15,'C15');
INSERT INTO theater VALUES (1,'C',16,'C16');
INSERT INTO theater VALUES (1,'C',17,'C17');
INSERT INTO theater VALUES (1,'C',18,'C18');

INSERT INTO theater VALUES (1,'D','01','D01');
INSERT INTO theater VALUES (1,'D','02','D02');
INSERT INTO theater VALUES (1,'D','03','D03');
INSERT INTO theater VALUES (1,'D','04','D04');
INSERT INTO theater VALUES (1,'D','05','D05');
INSERT INTO theater VALUES (1,'D','06','D06');
INSERT INTO theater VALUES (1,'D','07','D07');
INSERT INTO theater VALUES (1,'D','08','D08');
INSERT INTO theater VALUES (1,'D','09','D09');
INSERT INTO theater VALUES (1,'D',10,'D10');
INSERT INTO theater VALUES (1,'D',11,'D11');
INSERT INTO theater VALUES (1,'D',12,'D12');
INSERT INTO theater VALUES (1,'D',13,'D13');
INSERT INTO theater VALUES (1,'D',14,'D14');
INSERT INTO theater VALUES (1,'D',15,'D15');
INSERT INTO theater VALUES (1,'D',16,'D16');
INSERT INTO theater VALUES (1,'D',17,'D17');
INSERT INTO theater VALUES (1,'D',18,'D18');

INSERT INTO theater VALUES (1,'E','01','E01');
INSERT INTO theater VALUES (1,'E','02','E02');
INSERT INTO theater VALUES (1,'E','03','E03');
INSERT INTO theater VALUES (1,'E','04','E04');
INSERT INTO theater VALUES (1,'E','05','E05');
INSERT INTO theater VALUES (1,'E','06','E06');
INSERT INTO theater VALUES (1,'E','07','E07');
INSERT INTO theater VALUES (1,'E','08','E08');
INSERT INTO theater VALUES (1,'E','09','E09');
INSERT INTO theater VALUES (1,'E',10,'E10');
INSERT INTO theater VALUES (1,'E',11,'E11');
INSERT INTO theater VALUES (1,'E',12,'E12');
INSERT INTO theater VALUES (1,'E',13,'E13');
INSERT INTO theater VALUES (1,'E',14,'E14');
INSERT INTO theater VALUES (1,'E',15,'E15');
INSERT INTO theater VALUES (1,'E',16,'E16');
INSERT INTO theater VALUES (1,'E',17,'E17');
INSERT INTO theater VALUES (1,'E',18,'E18');

INSERT INTO theater VALUES (1,'F','01','F01');
INSERT INTO theater VALUES (1,'F','02','F02');
INSERT INTO theater VALUES (1,'F','03','F03');
INSERT INTO theater VALUES (1,'F','04','F04');
INSERT INTO theater VALUES (1,'F','05','F05');
INSERT INTO theater VALUES (1,'F','06','F06');
INSERT INTO theater VALUES (1,'F','07','F07');
INSERT INTO theater VALUES (1,'F','08','F08');
INSERT INTO theater VALUES (1,'F','09','F09');
INSERT INTO theater VALUES (1,'F',10,'F10');
INSERT INTO theater VALUES (1,'F',11,'F11');
INSERT INTO theater VALUES (1,'F',12,'F12');
INSERT INTO theater VALUES (1,'F',13,'F13');
INSERT INTO theater VALUES (1,'F',14,'F14');
INSERT INTO theater VALUES (1,'F',15,'F15');
INSERT INTO theater VALUES (1,'F',16,'F16');
INSERT INTO theater VALUES (1,'F',17,'F17');
INSERT INTO theater VALUES (1,'F',18,'F18');

INSERT INTO theater VALUES (1,'G','01','G01');
INSERT INTO theater VALUES (1,'G','02','G02');
INSERT INTO theater VALUES (1,'G','03','G03');
INSERT INTO theater VALUES (1,'G','04','G04');
INSERT INTO theater VALUES (1,'G','05','G05');
INSERT INTO theater VALUES (1,'G','06','G06');
INSERT INTO theater VALUES (1,'G','07','G07');
INSERT INTO theater VALUES (1,'G','08','G08');
INSERT INTO theater VALUES (1,'G','09','G09');
INSERT INTO theater VALUES (1,'G',10,'G10');
INSERT INTO theater VALUES (1,'G',11,'G11');
INSERT INTO theater VALUES (1,'G',12,'G12');
INSERT INTO theater VALUES (1,'G',13,'G13');
INSERT INTO theater VALUES (1,'G',14,'G14');
INSERT INTO theater VALUES (1,'G',15,'G15');
INSERT INTO theater VALUES (1,'G',16,'G16');
INSERT INTO theater VALUES (1,'G',17,'G17');
INSERT INTO theater VALUES (1,'G',18,'G18');

INSERT INTO theater VALUES (1,'H','01','H01');
INSERT INTO theater VALUES (1,'H','02','H02');
INSERT INTO theater VALUES (1,'H','03','H03');
INSERT INTO theater VALUES (1,'H','04','H04');
INSERT INTO theater VALUES (1,'H','05','H05');
INSERT INTO theater VALUES (1,'H','06','H06');
INSERT INTO theater VALUES (1,'H','07','H07');
INSERT INTO theater VALUES (1,'H','08','H08');
INSERT INTO theater VALUES (1,'H','09','H09');
INSERT INTO theater VALUES (1,'H',10,'H10');
INSERT INTO theater VALUES (1,'H',11,'H11');
INSERT INTO theater VALUES (1,'H',12,'H12');
INSERT INTO theater VALUES (1,'H',13,'H13');
INSERT INTO theater VALUES (1,'H',14,'H14');
INSERT INTO theater VALUES (1,'H',15,'H15');
INSERT INTO theater VALUES (1,'H',16,'H16');
INSERT INTO theater VALUES (1,'H',17,'H17');
INSERT INTO theater VALUES (1,'H',18,'H18');

INSERT INTO theater VALUES (1,'I','01','I01');
INSERT INTO theater VALUES (1,'I','02','I02');
INSERT INTO theater VALUES (1,'I','03','I03');
INSERT INTO theater VALUES (1,'I','04','I04');
INSERT INTO theater VALUES (1,'I','05','I05');
INSERT INTO theater VALUES (1,'I','06','I06');
INSERT INTO theater VALUES (1,'I','07','I07');
INSERT INTO theater VALUES (1,'I','08','I08');
INSERT INTO theater VALUES (1,'I','09','I09');
INSERT INTO theater VALUES (1,'I',10,'I10');
INSERT INTO theater VALUES (1,'I',11,'I11');
INSERT INTO theater VALUES (1,'I',12,'I12');
INSERT INTO theater VALUES (1,'I',13,'I13');
INSERT INTO theater VALUES (1,'I',14,'I14');
INSERT INTO theater VALUES (1,'I',15,'I15');
INSERT INTO theater VALUES (1,'I',16,'I16');
INSERT INTO theater VALUES (1,'I',17,'I17');
INSERT INTO theater VALUES (1,'I',18,'I18');

INSERT INTO theater VALUES (1,'J','01','J01');
INSERT INTO theater VALUES (1,'J','02','J02');
INSERT INTO theater VALUES (1,'J','03','J03');
INSERT INTO theater VALUES (1,'J','04','J04');
INSERT INTO theater VALUES (1,'J','05','J05');
INSERT INTO theater VALUES (1,'J','06','J06');
INSERT INTO theater VALUES (1,'J','07','J07');
INSERT INTO theater VALUES (1,'J','08','J08');
INSERT INTO theater VALUES (1,'J','09','J09');
INSERT INTO theater VALUES (1,'J',10,'J10');
INSERT INTO theater VALUES (1,'J',11,'J11');
INSERT INTO theater VALUES (1,'J',12,'J12');
INSERT INTO theater VALUES (1,'J',13,'J13');
INSERT INTO theater VALUES (1,'J',14,'J14');
INSERT INTO theater VALUES (1,'J',15,'J15');
INSERT INTO theater VALUES (1,'J',16,'J16');
INSERT INTO theater VALUES (1,'J',17,'J17');
INSERT INTO theater VALUES (1,'J',18,'J18');

INSERT INTO theater VALUES (1,'K','01','K01');
INSERT INTO theater VALUES (1,'K','02','K02');
INSERT INTO theater VALUES (1,'K','03','K03');
INSERT INTO theater VALUES (1,'K','04','K04');
INSERT INTO theater VALUES (1,'K','05','K05');
INSERT INTO theater VALUES (1,'K','06','K06');
INSERT INTO theater VALUES (1,'K','07','K07');
INSERT INTO theater VALUES (1,'K','08','K08');
INSERT INTO theater VALUES (1,'K','09','K09');
INSERT INTO theater VALUES (1,'K',10,'K10');
INSERT INTO theater VALUES (1,'K',11,'K11');
INSERT INTO theater VALUES (1,'K',12,'K12');
INSERT INTO theater VALUES (1,'K',13,'K13');
INSERT INTO theater VALUES (1,'K',14,'K14');
INSERT INTO theater VALUES (1,'K',15,'K15');
INSERT INTO theater VALUES (1,'K',16,'K16');
INSERT INTO theater VALUES (1,'K',17,'K17');
INSERT INTO theater VALUES (1,'K',18,'K18');

INSERT INTO theater VALUES (1,'L','01','L01');
INSERT INTO theater VALUES (1,'L','02','L02');
INSERT INTO theater VALUES (1,'L','03','L03');
INSERT INTO theater VALUES (1,'L','04','L04');
INSERT INTO theater VALUES (1,'L','05','L05');
INSERT INTO theater VALUES (1,'L','06','L06');
INSERT INTO theater VALUES (1,'L','07','L07');
INSERT INTO theater VALUES (1,'L','08','L08');
INSERT INTO theater VALUES (1,'L','09','L09');
INSERT INTO theater VALUES (1,'L',10,'L10');
INSERT INTO theater VALUES (1,'L',11,'L11');
INSERT INTO theater VALUES (1,'L',12,'L12');
INSERT INTO theater VALUES (1,'L',13,'L13');
INSERT INTO theater VALUES (1,'L',14,'L14');
INSERT INTO theater VALUES (1,'L',15,'L15');
INSERT INTO theater VALUES (1,'L',16,'L16');
INSERT INTO theater VALUES (1,'L',17,'L17');
INSERT INTO theater VALUES (1,'L',18,'L18');

-- 2관
INSERT INTO theater VALUES (2,'A','01','A01');
INSERT INTO theater VALUES (2,'A','02','A02');
INSERT INTO theater VALUES (2,'A','03','A03');
INSERT INTO theater VALUES (2,'A','04','A04');
INSERT INTO theater VALUES (2,'A','05','A05');
INSERT INTO theater VALUES (2,'A','06','A06');
INSERT INTO theater VALUES (2,'A','07','A07');
INSERT INTO theater VALUES (2,'A','08','A08');
INSERT INTO theater VALUES (2,'A','09','A09');
INSERT INTO theater VALUES (2,'A',10,'A10');
INSERT INTO theater VALUES (2,'A',11,'A11');
INSERT INTO theater VALUES (2,'A',12,'A12');
INSERT INTO theater VALUES (2,'A',13,'A13');
INSERT INTO theater VALUES (2,'A',14,'A14');
INSERT INTO theater VALUES (2,'A',15,'A15');
INSERT INTO theater VALUES (2,'A',16,'A16');
INSERT INTO theater VALUES (2,'A',17,'A17');
INSERT INTO theater VALUES (2,'A',18,'A18');

INSERT INTO theater VALUES (2,'B','01','B01');
INSERT INTO theater VALUES (2,'B','02','B02');
INSERT INTO theater VALUES (2,'B','03','B03');
INSERT INTO theater VALUES (2,'B','04','B04');
INSERT INTO theater VALUES (2,'B','05','B05');
INSERT INTO theater VALUES (2,'B','06','B06');
INSERT INTO theater VALUES (2,'B','07','B07');
INSERT INTO theater VALUES (2,'B','08','B08');
INSERT INTO theater VALUES (2,'B','09','B09');
INSERT INTO theater VALUES (2,'B',10,'B10');
INSERT INTO theater VALUES (2,'B',11,'B11');
INSERT INTO theater VALUES (2,'B',12,'B12');
INSERT INTO theater VALUES (2,'B',13,'B13');
INSERT INTO theater VALUES (2,'B',14,'B14');
INSERT INTO theater VALUES (2,'B',15,'B15');
INSERT INTO theater VALUES (2,'B',16,'B16');
INSERT INTO theater VALUES (2,'B',17,'B17');
INSERT INTO theater VALUES (2,'B',18,'B18');

INSERT INTO theater VALUES (2,'C','01','C01');
INSERT INTO theater VALUES (2,'C','02','C02');
INSERT INTO theater VALUES (2,'C','03','C03');
INSERT INTO theater VALUES (2,'C','04','C04');
INSERT INTO theater VALUES (2,'C','05','C05');
INSERT INTO theater VALUES (2,'C','06','C06');
INSERT INTO theater VALUES (2,'C','07','C07');
INSERT INTO theater VALUES (2,'C','08','C08');
INSERT INTO theater VALUES (2,'C','09','C09');
INSERT INTO theater VALUES (2,'C',10,'C10');
INSERT INTO theater VALUES (2,'C',11,'C11');
INSERT INTO theater VALUES (2,'C',12,'C12');
INSERT INTO theater VALUES (2,'C',13,'C13');
INSERT INTO theater VALUES (2,'C',14,'C14');
INSERT INTO theater VALUES (2,'C',15,'C15');
INSERT INTO theater VALUES (2,'C',16,'C16');
INSERT INTO theater VALUES (2,'C',17,'C17');
INSERT INTO theater VALUES (2,'C',18,'C18');

INSERT INTO theater VALUES (2,'D','01','D01');
INSERT INTO theater VALUES (2,'D','02','D02');
INSERT INTO theater VALUES (2,'D','03','D03');
INSERT INTO theater VALUES (2,'D','04','D04');
INSERT INTO theater VALUES (2,'D','05','D05');
INSERT INTO theater VALUES (2,'D','06','D06');
INSERT INTO theater VALUES (2,'D','07','D07');
INSERT INTO theater VALUES (2,'D','08','D08');
INSERT INTO theater VALUES (2,'D','09','D09');
INSERT INTO theater VALUES (2,'D',10,'D10');
INSERT INTO theater VALUES (2,'D',11,'D11');
INSERT INTO theater VALUES (2,'D',12,'D12');
INSERT INTO theater VALUES (2,'D',13,'D13');
INSERT INTO theater VALUES (2,'D',14,'D14');
INSERT INTO theater VALUES (2,'D',15,'D15');
INSERT INTO theater VALUES (2,'D',16,'D16');
INSERT INTO theater VALUES (2,'D',17,'D17');
INSERT INTO theater VALUES (2,'D',18,'D18');

INSERT INTO theater VALUES (2,'E','01','E01');
INSERT INTO theater VALUES (2,'E','02','E02');
INSERT INTO theater VALUES (2,'E','03','E03');
INSERT INTO theater VALUES (2,'E','04','E04');
INSERT INTO theater VALUES (2,'E','05','E05');
INSERT INTO theater VALUES (2,'E','06','E06');
INSERT INTO theater VALUES (2,'E','07','E07');
INSERT INTO theater VALUES (2,'E','08','E08');
INSERT INTO theater VALUES (2,'E','09','E09');
INSERT INTO theater VALUES (2,'E',10,'E10');
INSERT INTO theater VALUES (2,'E',11,'E11');
INSERT INTO theater VALUES (2,'E',12,'E12');
INSERT INTO theater VALUES (2,'E',13,'E13');
INSERT INTO theater VALUES (2,'E',14,'E14');
INSERT INTO theater VALUES (2,'E',15,'E15');
INSERT INTO theater VALUES (2,'E',16,'E16');
INSERT INTO theater VALUES (2,'E',17,'E17');
INSERT INTO theater VALUES (2,'E',18,'E18');

INSERT INTO theater VALUES (2,'F','01','F01');
INSERT INTO theater VALUES (2,'F','02','F02');
INSERT INTO theater VALUES (2,'F','03','F03');
INSERT INTO theater VALUES (2,'F','04','F04');
INSERT INTO theater VALUES (2,'F','05','F05');
INSERT INTO theater VALUES (2,'F','06','F06');
INSERT INTO theater VALUES (2,'F','07','F07');
INSERT INTO theater VALUES (2,'F','08','F08');
INSERT INTO theater VALUES (2,'F','09','F09');
INSERT INTO theater VALUES (2,'F',10,'F10');
INSERT INTO theater VALUES (2,'F',11,'F11');
INSERT INTO theater VALUES (2,'F',12,'F12');
INSERT INTO theater VALUES (2,'F',13,'F13');
INSERT INTO theater VALUES (2,'F',14,'F14');
INSERT INTO theater VALUES (2,'F',15,'F15');
INSERT INTO theater VALUES (2,'F',16,'F16');
INSERT INTO theater VALUES (2,'F',17,'F17');
INSERT INTO theater VALUES (2,'F',18,'F18');

INSERT INTO theater VALUES (2,'G','01','G01');
INSERT INTO theater VALUES (2,'G','02','G02');
INSERT INTO theater VALUES (2,'G','03','G03');
INSERT INTO theater VALUES (2,'G','04','G04');
INSERT INTO theater VALUES (2,'G','05','G05');
INSERT INTO theater VALUES (2,'G','06','G06');
INSERT INTO theater VALUES (2,'G','07','G07');
INSERT INTO theater VALUES (2,'G','08','G08');
INSERT INTO theater VALUES (2,'G','09','G09');
INSERT INTO theater VALUES (2,'G',10,'G10');
INSERT INTO theater VALUES (2,'G',11,'G11');
INSERT INTO theater VALUES (2,'G',12,'G12');
INSERT INTO theater VALUES (2,'G',13,'G13');
INSERT INTO theater VALUES (2,'G',14,'G14');
INSERT INTO theater VALUES (2,'G',15,'G15');
INSERT INTO theater VALUES (2,'G',16,'G16');
INSERT INTO theater VALUES (2,'G',17,'G17');
INSERT INTO theater VALUES (2,'G',18,'G18');

INSERT INTO theater VALUES (2,'H','01','H01');
INSERT INTO theater VALUES (2,'H','02','H02');
INSERT INTO theater VALUES (2,'H','03','H03');
INSERT INTO theater VALUES (2,'H','04','H04');
INSERT INTO theater VALUES (2,'H','05','H05');
INSERT INTO theater VALUES (2,'H','06','H06');
INSERT INTO theater VALUES (2,'H','07','H07');
INSERT INTO theater VALUES (2,'H','08','H08');
INSERT INTO theater VALUES (2,'H','09','H09');
INSERT INTO theater VALUES (2,'H',10,'H10');
INSERT INTO theater VALUES (2,'H',11,'H11');
INSERT INTO theater VALUES (2,'H',12,'H12');
INSERT INTO theater VALUES (2,'H',13,'H13');
INSERT INTO theater VALUES (2,'H',14,'H14');
INSERT INTO theater VALUES (2,'H',15,'H15');
INSERT INTO theater VALUES (2,'H',16,'H16');
INSERT INTO theater VALUES (2,'H',17,'H17');
INSERT INTO theater VALUES (2,'H',18,'H18');

INSERT INTO theater VALUES (2,'I','01','I01');
INSERT INTO theater VALUES (2,'I','02','I02');
INSERT INTO theater VALUES (2,'I','03','I03');
INSERT INTO theater VALUES (2,'I','04','I04');
INSERT INTO theater VALUES (2,'I','05','I05');
INSERT INTO theater VALUES (2,'I','06','I06');
INSERT INTO theater VALUES (2,'I','07','I07');
INSERT INTO theater VALUES (2,'I','08','I08');
INSERT INTO theater VALUES (2,'I','09','I09');
INSERT INTO theater VALUES (2,'I',10,'I10');
INSERT INTO theater VALUES (2,'I',11,'I11');
INSERT INTO theater VALUES (2,'I',12,'I12');
INSERT INTO theater VALUES (2,'I',13,'I13');
INSERT INTO theater VALUES (2,'I',14,'I14');
INSERT INTO theater VALUES (2,'I',15,'I15');
INSERT INTO theater VALUES (2,'I',16,'I16');
INSERT INTO theater VALUES (2,'I',17,'I17');
INSERT INTO theater VALUES (2,'I',18,'I18');

INSERT INTO theater VALUES (2,'J','01','J01');
INSERT INTO theater VALUES (2,'J','02','J02');
INSERT INTO theater VALUES (2,'J','03','J03');
INSERT INTO theater VALUES (2,'J','04','J04');
INSERT INTO theater VALUES (2,'J','05','J05');
INSERT INTO theater VALUES (2,'J','06','J06');
INSERT INTO theater VALUES (2,'J','07','J07');
INSERT INTO theater VALUES (2,'J','08','J08');
INSERT INTO theater VALUES (2,'J','09','J09');
INSERT INTO theater VALUES (2,'J',10,'J10');
INSERT INTO theater VALUES (2,'J',11,'J11');
INSERT INTO theater VALUES (2,'J',12,'J12');
INSERT INTO theater VALUES (2,'J',13,'J13');
INSERT INTO theater VALUES (2,'J',14,'J14');
INSERT INTO theater VALUES (2,'J',15,'J15');
INSERT INTO theater VALUES (2,'J',16,'J16');
INSERT INTO theater VALUES (2,'J',17,'J17');
INSERT INTO theater VALUES (2,'J',18,'J18');

INSERT INTO theater VALUES (2,'K','01','K01');
INSERT INTO theater VALUES (2,'K','02','K02');
INSERT INTO theater VALUES (2,'K','03','K03');
INSERT INTO theater VALUES (2,'K','04','K04');
INSERT INTO theater VALUES (2,'K','05','K05');
INSERT INTO theater VALUES (2,'K','06','K06');
INSERT INTO theater VALUES (2,'K','07','K07');
INSERT INTO theater VALUES (2,'K','08','K08');
INSERT INTO theater VALUES (2,'K','09','K09');
INSERT INTO theater VALUES (2,'K',10,'K10');
INSERT INTO theater VALUES (2,'K',11,'K11');
INSERT INTO theater VALUES (2,'K',12,'K12');
INSERT INTO theater VALUES (2,'K',13,'K13');
INSERT INTO theater VALUES (2,'K',14,'K14');
INSERT INTO theater VALUES (2,'K',15,'K15');
INSERT INTO theater VALUES (2,'K',16,'K16');
INSERT INTO theater VALUES (2,'K',17,'K17');
INSERT INTO theater VALUES (2,'K',18,'K18');

INSERT INTO theater VALUES (2,'L','01','L01');
INSERT INTO theater VALUES (2,'L','02','L02');
INSERT INTO theater VALUES (2,'L','03','L03');
INSERT INTO theater VALUES (2,'L','04','L04');
INSERT INTO theater VALUES (2,'L','05','L05');
INSERT INTO theater VALUES (2,'L','06','L06');
INSERT INTO theater VALUES (2,'L','07','L07');
INSERT INTO theater VALUES (2,'L','08','L08');
INSERT INTO theater VALUES (2,'L','09','L09');
INSERT INTO theater VALUES (2,'L',10,'L10');
INSERT INTO theater VALUES (2,'L',11,'L11');
INSERT INTO theater VALUES (2,'L',12,'L12');
INSERT INTO theater VALUES (2,'L',13,'L13');
INSERT INTO theater VALUES (2,'L',14,'L14');
INSERT INTO theater VALUES (2,'L',15,'L15');
INSERT INTO theater VALUES (2,'L',16,'L16');
INSERT INTO theater VALUES (2,'L',17,'L17');
INSERT INTO theater VALUES (2,'L',18,'L18');


-- 3관
INSERT INTO theater VALUES (3,'A','01','A01');
INSERT INTO theater VALUES (3,'A','02','A02');
INSERT INTO theater VALUES (3,'A','03','A03');
INSERT INTO theater VALUES (3,'A','04','A04');
INSERT INTO theater VALUES (3,'A','05','A05');
INSERT INTO theater VALUES (3,'A','06','A06');
INSERT INTO theater VALUES (3,'A','07','A07');
INSERT INTO theater VALUES (3,'A','08','A08');
INSERT INTO theater VALUES (3,'A','09','A09');
INSERT INTO theater VALUES (3,'A',10,'A10');
INSERT INTO theater VALUES (3,'A',11,'A11');
INSERT INTO theater VALUES (3,'A',12,'A12');
INSERT INTO theater VALUES (3,'A',13,'A13');
INSERT INTO theater VALUES (3,'A',14,'A14');
INSERT INTO theater VALUES (3,'A',15,'A15');
INSERT INTO theater VALUES (3,'A',16,'A16');
INSERT INTO theater VALUES (3,'A',17,'A17');
INSERT INTO theater VALUES (3,'A',18,'A18');

INSERT INTO theater VALUES (3,'B','01','B01');
INSERT INTO theater VALUES (3,'B','02','B02');
INSERT INTO theater VALUES (3,'B','03','B03');
INSERT INTO theater VALUES (3,'B','04','B04');
INSERT INTO theater VALUES (3,'B','05','B05');
INSERT INTO theater VALUES (3,'B','06','B06');
INSERT INTO theater VALUES (3,'B','07','B07');
INSERT INTO theater VALUES (3,'B','08','B08');
INSERT INTO theater VALUES (3,'B','09','B09');
INSERT INTO theater VALUES (3,'B',10,'B10');
INSERT INTO theater VALUES (3,'B',11,'B11');
INSERT INTO theater VALUES (3,'B',12,'B12');
INSERT INTO theater VALUES (3,'B',13,'B13');
INSERT INTO theater VALUES (3,'B',14,'B14');
INSERT INTO theater VALUES (3,'B',15,'B15');
INSERT INTO theater VALUES (3,'B',16,'B16');
INSERT INTO theater VALUES (3,'B',17,'B17');
INSERT INTO theater VALUES (3,'B',18,'B18');

INSERT INTO theater VALUES (3,'C','01','C01');
INSERT INTO theater VALUES (3,'C','02','C02');
INSERT INTO theater VALUES (3,'C','03','C03');
INSERT INTO theater VALUES (3,'C','04','C04');
INSERT INTO theater VALUES (3,'C','05','C05');
INSERT INTO theater VALUES (3,'C','06','C06');
INSERT INTO theater VALUES (3,'C','07','C07');
INSERT INTO theater VALUES (3,'C','08','C08');
INSERT INTO theater VALUES (3,'C','09','C09');
INSERT INTO theater VALUES (3,'C',10,'C10');
INSERT INTO theater VALUES (3,'C',11,'C11');
INSERT INTO theater VALUES (3,'C',12,'C12');
INSERT INTO theater VALUES (3,'C',13,'C13');
INSERT INTO theater VALUES (3,'C',14,'C14');
INSERT INTO theater VALUES (3,'C',15,'C15');
INSERT INTO theater VALUES (3,'C',16,'C16');
INSERT INTO theater VALUES (3,'C',17,'C17');
INSERT INTO theater VALUES (3,'C',18,'C18');

INSERT INTO theater VALUES (3,'D','01','D01');
INSERT INTO theater VALUES (3,'D','02','D02');
INSERT INTO theater VALUES (3,'D','03','D03');
INSERT INTO theater VALUES (3,'D','04','D04');
INSERT INTO theater VALUES (3,'D','05','D05');
INSERT INTO theater VALUES (3,'D','06','D06');
INSERT INTO theater VALUES (3,'D','07','D07');
INSERT INTO theater VALUES (3,'D','08','D08');
INSERT INTO theater VALUES (3,'D','09','D09');
INSERT INTO theater VALUES (3,'D',10,'D10');
INSERT INTO theater VALUES (3,'D',11,'D11');
INSERT INTO theater VALUES (3,'D',12,'D12');
INSERT INTO theater VALUES (3,'D',13,'D13');
INSERT INTO theater VALUES (3,'D',14,'D14');
INSERT INTO theater VALUES (3,'D',15,'D15');
INSERT INTO theater VALUES (3,'D',16,'D16');
INSERT INTO theater VALUES (3,'D',17,'D17');
INSERT INTO theater VALUES (3,'D',18,'D18');

INSERT INTO theater VALUES (3,'E','01','E01');
INSERT INTO theater VALUES (3,'E','02','E02');
INSERT INTO theater VALUES (3,'E','03','E03');
INSERT INTO theater VALUES (3,'E','04','E04');
INSERT INTO theater VALUES (3,'E','05','E05');
INSERT INTO theater VALUES (3,'E','06','E06');
INSERT INTO theater VALUES (3,'E','07','E07');
INSERT INTO theater VALUES (3,'E','08','E08');
INSERT INTO theater VALUES (3,'E','09','E09');
INSERT INTO theater VALUES (3,'E',10,'E10');
INSERT INTO theater VALUES (3,'E',11,'E11');
INSERT INTO theater VALUES (3,'E',12,'E12');
INSERT INTO theater VALUES (3,'E',13,'E13');
INSERT INTO theater VALUES (3,'E',14,'E14');
INSERT INTO theater VALUES (3,'E',15,'E15');
INSERT INTO theater VALUES (3,'E',16,'E16');
INSERT INTO theater VALUES (3,'E',17,'E17');
INSERT INTO theater VALUES (3,'E',18,'E18');

INSERT INTO theater VALUES (3,'F','01','F01');
INSERT INTO theater VALUES (3,'F','02','F02');
INSERT INTO theater VALUES (3,'F','03','F03');
INSERT INTO theater VALUES (3,'F','04','F04');
INSERT INTO theater VALUES (3,'F','05','F05');
INSERT INTO theater VALUES (3,'F','06','F06');
INSERT INTO theater VALUES (3,'F','07','F07');
INSERT INTO theater VALUES (3,'F','08','F08');
INSERT INTO theater VALUES (3,'F','09','F09');
INSERT INTO theater VALUES (3,'F',10,'F10');
INSERT INTO theater VALUES (3,'F',11,'F11');
INSERT INTO theater VALUES (3,'F',12,'F12');
INSERT INTO theater VALUES (3,'F',13,'F13');
INSERT INTO theater VALUES (3,'F',14,'F14');
INSERT INTO theater VALUES (3,'F',15,'F15');
INSERT INTO theater VALUES (3,'F',16,'F16');
INSERT INTO theater VALUES (3,'F',17,'F17');
INSERT INTO theater VALUES (3,'F',18,'F18');

INSERT INTO theater VALUES (3,'G','01','G01');
INSERT INTO theater VALUES (3,'G','02','G02');
INSERT INTO theater VALUES (3,'G','03','G03');
INSERT INTO theater VALUES (3,'G','04','G04');
INSERT INTO theater VALUES (3,'G','05','G05');
INSERT INTO theater VALUES (3,'G','06','G06');
INSERT INTO theater VALUES (3,'G','07','G07');
INSERT INTO theater VALUES (3,'G','08','G08');
INSERT INTO theater VALUES (3,'G','09','G09');
INSERT INTO theater VALUES (3,'G',10,'G10');
INSERT INTO theater VALUES (3,'G',11,'G11');
INSERT INTO theater VALUES (3,'G',12,'G12');
INSERT INTO theater VALUES (3,'G',13,'G13');
INSERT INTO theater VALUES (3,'G',14,'G14');
INSERT INTO theater VALUES (3,'G',15,'G15');
INSERT INTO theater VALUES (3,'G',16,'G16');
INSERT INTO theater VALUES (3,'G',17,'G17');
INSERT INTO theater VALUES (3,'G',18,'G18');

INSERT INTO theater VALUES (3,'H','01','H01');
INSERT INTO theater VALUES (3,'H','02','H02');
INSERT INTO theater VALUES (3,'H','03','H03');
INSERT INTO theater VALUES (3,'H','04','H04');
INSERT INTO theater VALUES (3,'H','05','H05');
INSERT INTO theater VALUES (3,'H','06','H06');
INSERT INTO theater VALUES (3,'H','07','H07');
INSERT INTO theater VALUES (3,'H','08','H08');
INSERT INTO theater VALUES (3,'H','09','H09');
INSERT INTO theater VALUES (3,'H',10,'H10');
INSERT INTO theater VALUES (3,'H',11,'H11');
INSERT INTO theater VALUES (3,'H',12,'H12');
INSERT INTO theater VALUES (3,'H',13,'H13');
INSERT INTO theater VALUES (3,'H',14,'H14');
INSERT INTO theater VALUES (3,'H',15,'H15');
INSERT INTO theater VALUES (3,'H',16,'H16');
INSERT INTO theater VALUES (3,'H',17,'H17');
INSERT INTO theater VALUES (3,'H',18,'H18');

INSERT INTO theater VALUES (3,'I','01','I01');
INSERT INTO theater VALUES (3,'I','02','I02');
INSERT INTO theater VALUES (3,'I','03','I03');
INSERT INTO theater VALUES (3,'I','04','I04');
INSERT INTO theater VALUES (3,'I','05','I05');
INSERT INTO theater VALUES (3,'I','06','I06');
INSERT INTO theater VALUES (3,'I','07','I07');
INSERT INTO theater VALUES (3,'I','08','I08');
INSERT INTO theater VALUES (3,'I','09','I09');
INSERT INTO theater VALUES (3,'I',10,'I10');
INSERT INTO theater VALUES (3,'I',11,'I11');
INSERT INTO theater VALUES (3,'I',12,'I12');
INSERT INTO theater VALUES (3,'I',13,'I13');
INSERT INTO theater VALUES (3,'I',14,'I14');
INSERT INTO theater VALUES (3,'I',15,'I15');
INSERT INTO theater VALUES (3,'I',16,'I16');
INSERT INTO theater VALUES (3,'I',17,'I17');
INSERT INTO theater VALUES (3,'I',18,'I18');

INSERT INTO theater VALUES (3,'J','01','J01');
INSERT INTO theater VALUES (3,'J','02','J02');
INSERT INTO theater VALUES (3,'J','03','J03');
INSERT INTO theater VALUES (3,'J','04','J04');
INSERT INTO theater VALUES (3,'J','05','J05');
INSERT INTO theater VALUES (3,'J','06','J06');
INSERT INTO theater VALUES (3,'J','07','J07');
INSERT INTO theater VALUES (3,'J','08','J08');
INSERT INTO theater VALUES (3,'J','09','J09');
INSERT INTO theater VALUES (3,'J',10,'J10');
INSERT INTO theater VALUES (3,'J',11,'J11');
INSERT INTO theater VALUES (3,'J',12,'J12');
INSERT INTO theater VALUES (3,'J',13,'J13');
INSERT INTO theater VALUES (3,'J',14,'J14');
INSERT INTO theater VALUES (3,'J',15,'J15');
INSERT INTO theater VALUES (3,'J',16,'J16');
INSERT INTO theater VALUES (3,'J',17,'J17');
INSERT INTO theater VALUES (3,'J',18,'J18');

INSERT INTO theater VALUES (3,'K','01','K01');
INSERT INTO theater VALUES (3,'K','02','K02');
INSERT INTO theater VALUES (3,'K','03','K03');
INSERT INTO theater VALUES (3,'K','04','K04');
INSERT INTO theater VALUES (3,'K','05','K05');
INSERT INTO theater VALUES (3,'K','06','K06');
INSERT INTO theater VALUES (3,'K','07','K07');
INSERT INTO theater VALUES (3,'K','08','K08');
INSERT INTO theater VALUES (3,'K','09','K09');
INSERT INTO theater VALUES (3,'K',10,'K10');
INSERT INTO theater VALUES (3,'K',11,'K11');
INSERT INTO theater VALUES (3,'K',12,'K12');
INSERT INTO theater VALUES (3,'K',13,'K13');
INSERT INTO theater VALUES (3,'K',14,'K14');
INSERT INTO theater VALUES (3,'K',15,'K15');
INSERT INTO theater VALUES (3,'K',16,'K16');
INSERT INTO theater VALUES (3,'K',17,'K17');
INSERT INTO theater VALUES (3,'K',18,'K18');

INSERT INTO theater VALUES (3,'L','01','L01');
INSERT INTO theater VALUES (3,'L','02','L02');
INSERT INTO theater VALUES (3,'L','03','L03');
INSERT INTO theater VALUES (3,'L','04','L04');
INSERT INTO theater VALUES (3,'L','05','L05');
INSERT INTO theater VALUES (3,'L','06','L06');
INSERT INTO theater VALUES (3,'L','07','L07');
INSERT INTO theater VALUES (3,'L','08','L08');
INSERT INTO theater VALUES (3,'L','09','L09');
INSERT INTO theater VALUES (3,'L',10,'L10');
INSERT INTO theater VALUES (3,'L',11,'L11');
INSERT INTO theater VALUES (3,'L',12,'L12');
INSERT INTO theater VALUES (3,'L',13,'L13');
INSERT INTO theater VALUES (3,'L',14,'L14');
INSERT INTO theater VALUES (3,'L',15,'L15');
INSERT INTO theater VALUES (3,'L',16,'L16');
INSERT INTO theater VALUES (3,'L',17,'L17');
INSERT INTO theater VALUES (3,'L',18,'L18');

SELECT * FROM theater;



-- SCHEDULES TALBE DDL
INSERT INTO schedules VALUES (null,1,1000,sysdate());
INSERT INTO schedules VALUES (null,2,1001,sysdate());
INSERT INTO schedules VALUES (null,3,1002,sysdate());
INSERT INTO schedules VALUES (null,1,1003,sysdate());
INSERT INTO schedules VALUES (null,2,1004,sysdate());
INSERT INTO schedules VALUES (null,1,1005,DATE_ADD(sysdate(), INTERVAL 1 DAY));
INSERT INTO schedules VALUES (null,2,1006,DATE_ADD(sysdate(), INTERVAL 1 DAY));
INSERT INTO schedules VALUES (null,3,1007,DATE_ADD(sysdate(), INTERVAL 1 DAY));
INSERT INTO schedules VALUES (null,1,1008,DATE_ADD(sysdate(), INTERVAL 1 DAY));
INSERT INTO schedules VALUES (null,2,1009,DATE_ADD(sysdate(), INTERVAL 1 DAY));
INSERT INTO schedules VALUES (null,3,1010,DATE_ADD(sysdate(), INTERVAL 1 DAY));
INSERT INTO schedules VALUES (null,3,1000,DATE_ADD(sysdate(), INTERVAL 1 DAY));
SELECT * FROM schedules;




-- DETAIL_SCHEDULES TALBE DDL
INSERT INTO detail_schedules VALUES (2000,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2000,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2000,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2001,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2001,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2001,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2002,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2002,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2002,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2003,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2003,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2003,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2004,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2004,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2004,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2005,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2005,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2005,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2006,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2006,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2006,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2007,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2007,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2007,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2008,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2008,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2008,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2009,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2009,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2009,3,'19:00:00','21:00:00');
INSERT INTO detail_schedules VALUES (2010,1,'13:00:00','15:00:00');
INSERT INTO detail_schedules VALUES (2010,2,'16:00:00','18:00:00');
INSERT INTO detail_schedules VALUES (2010,3,'19:00:00','21:00:00');

SELECT * FROM detail_schedules;








-- reservation Insert
INSERT INTO reservation VALUES (NULL, 'kms', 2, 20000, 19000);
INSERT INTO reservation VALUES (NULL, 'kms', 2, 20000, 9000);
INSERT INTO reservation VALUES (NULL, 'kms', 2, 20000, 10000);
INSERT INTO reservation VALUES (NULL, 'kms', 2, 20000, 10000);
INSERT INTO reservation VALUES (NULL, 'kms', 1, 10000, 9000);

SELECT * FROM reservation;

-- ticket Insert
INSERT INTO ticket VALUES(NULL,2000,6000,sysdate(),2,'A11');
INSERT INTO ticket VALUES(NULL,2000,6001,sysdate(),1,'A12');
INSERT INTO ticket VALUES(NULL,2001,6002,sysdate(),4,'C21');
INSERT INTO ticket VALUES(NULL,2001,6003,sysdate(),1,'A17');
INSERT INTO ticket VALUES(NULL,2002,6004,sysdate(),3,'D22');

select * from ticket;



-- coupon Insert
INSERT INTO coupon VALUES (null,'event01','2022-06-01','2022-09-30','snow', 1000,'모든 고객에게 제공되는 쿠폰');
INSERT INTO coupon VALUES (null,'event02','2022-06-01','2022-07-30','users', 2000,'여름기념 쿠폰');
INSERT INTO coupon VALUES (null,'event03','2022-07-20','2022-08-20','wizard', 3000,'깜짝 할인 이벤트 쿠폰');
INSERT INTO coupon VALUES (null,'event04','2022-07-20','2022-08-20','smile', 4000,'반짝 할인 이벤트 쿠폰');
INSERT INTO coupon VALUES (null,'event05','2022-06-20','2022-08-20','users', 2000,'봄 기념 쿠폰');
INSERT INTO coupon VALUES (null,'event06','2022-07-25','2022-08-20','wizard', 30000,'코로나 기원 쿠폰');
INSERT INTO coupon VALUES (null,'event07-BIG sale','2022-06-25','2022-07-25','gift', 10000,'Big Sale');
SELECT * FROM coupon;



-- mycoupon Insert
INSERT INTO mycoupon VALUES (NULL,'kms', 8000,true, NULL, sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 8001,true, NULL, sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 8002,false, sysdate(), sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 8003,false, sysdate(), sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 8004,true, NULL, sysdate());
INSERT INTO mycoupon VALUES (NULL,'kms', 8005,true, NULL, sysdate());
SELECT * FROM mycoupon;

-- admin Insert
INSERT INTO admin VALUES('master','111111');
INSERT INTO code VALUES(1,LEFT(UUID(),8));


-- visitList
INSERT INTO visitList VALUES(adddate(sysdate(), interval -1 DAY),55);
INSERT INTO visitList VALUES(adddate(sysdate(), interval -2 DAY),73);
INSERT INTO visitList VALUES(adddate(sysdate(), interval -3 DAY),22);
INSERT INTO visitList VALUES(adddate(sysdate(), interval -4 DAY),104);
INSERT INTO visitList VALUES(adddate(sysdate(), interval -5 DAY),126);
INSERT INTO visitList VALUES(adddate(sysdate(), interval -6 DAY),43);

SELECT * FROM visitList;





