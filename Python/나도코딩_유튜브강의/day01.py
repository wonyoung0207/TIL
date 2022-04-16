
# 자료형
from ipaddress import NetmaskValueError
from pickletools import string1
from platform import java_ver
from random import *
from math import *
print(5)
print(-10)
print(3.14)
print(1000)
print(5+3)
print(2*8)


# 문자열
print('풍선')
print('안녕'*8)


# 참/ 거짓
print(5 > 10)
print(True)
print(not (5 > 10))


# 변수
name = "쟈니 "
animal = "강아지"
age = 4
hobby = "신첵"
is_adult = age >= 3
print("우리집 " + animal + "의 이름은 " + name + "예요")
print(name + "는 " + str(age) + "살이며, " + hobby + "을 좋아해요")
print(name, "는 ", age, "살이며, ", hobby, "을 좋아해요")
print(name + "는 노견일까요? " + str(is_adult))

# 변수를 이용하여 문장을 출력
station = "사당"
print(station + "행 열차가 들어오고 있습니다. ")


# 연산자
print(2**3)  # 2^3 = 8
print(5 % 3)  # 나머지 구하기 2
print(10 % 3)  # 나눈 후 나머지를 출력 1
print(5 // 3)  # 나눈 후 몫을 출력 = 1
print(10 // 3)  # 3
print(3 == 3)
print(4 + 3 == 7)
print(1 != 3)  # true

print(not(1 != 3))  # false
print((3 > 0) and (3 < 5))  # true => 2개의 조건이 다 참일경우만 참
print((3 > 0) & (3 < 5))  # true
print((3 > 0) | (3 > 5))  # true => 둘다 거짓이어야 false가 나옴
print((3 > 0) or (3 < 5))  # true


# 수식
number = 12
print(number)
number += 2
print(number)
number *= 2
print(number)
number /= 2
print(number)
number %= 2
print(number)


# 숫자처리 함수
print(abs(5))  # 절대값
print(pow(4, 2))  # 제곱
print(max(5, 12))  # 둘중 큰값을 출력
print(min(5, 12))  # 작은값을 출력
print(round(3.14))  # 반올림 3
print(round(4.99))  # 반올림 5

print(floor(4.99))  # 내림 4
print(ceil(3.14))  # 올림 4
print(sqrt(16))  # 제곱근 4


# random 함수
print(random())  # 0.0 ~ 1.0 미만의 임의의 값 생성
print(random() * 10)  # 0.0 ~ 10.0 미만의 임의의 값 생성
# 0 ~ 10 미만의 임의의 값 생성 => 1을 더하지 않으면 10 미만, 1을 더하면 이하로 바뀐다.
print(int(random() * 10))
print(int(random() * 10) + 1)  # 1 ~ 10 이하의 임의의 값 생성
print(int(random() * 45) + 1)  # 1 ~ 45 이하의 임의의 값 생성

print(randrange(1, 45))  # 1~ 45 미만의 임의의 값 생성
print(randrange(1, 46))  # 1~ 46 미만의 임의의 값 생성
print(randint(1, 45))  # 1 ~ 45 이하의 임의의 값 생성


# # 퀴즈
# 조건1. 랜덤으로 날짜를 뽑는다.
# 조건2. 월별 날짜는 다름을 감안하여 최소 일수인 28 이내로 정한다.
# 조건3. 매월 1~3일은 스터디 준비를 해야 하므로 제외
date = randint(4, 28)
print("오프라인 스터디 모임 날짜는 매월 " + str(date) + " 일로 선정되었습니다. ")


# 여러가지 문자열 출력
sentence = '나는 소년입니다. '
print(sentence)
sentence2 = "나는 소년입니다."
print(sentence2)
sentence3 = """
나는 소년이고,
파이썬은 쉬워요
"""
print(sentence3)  # 여러줄을 출력할 수 있다.


# 슬라이싱
jumin = "960207-1234567"
print("성별 : " + jumin[7])
print("연 : " + jumin[0:2])  # 0~ 2 직전까지 가져옴 ( 0 ,1 만 가져옴 )
print("월 : " + jumin[2:4])  # 2~ 4 직전까지 가져옴 ( 2 ,3 만 가져옴 )
print("월 : " + jumin[4:6])  # 0~ 2 직전까지 가져옴 ( 4 ,5 만 가져옴 )
print("생년월일 : " + jumin[:6])  # 처음부터 6 직전까지
print("뒤 7자리 : " + jumin[7:])  # 7부터 끝까지
print("뒤 7자리 (뒤에서부터 ): " + jumin[-7:])  # 7부터 끝까지


# 문자열 처리 함수
s = "python is Amazing"
print(s.lower())
print(s.upper())
print(s[0].isupper())  # 대문자인지 아닌지 출력
print(len(s))  # 문자의 길이를 출력
print(s.replace("python", "java"))  # 글자를 찾아서 그다음 문자로 대체한다.

index = s.index("n")  # n 이라는 글자를 처음부터 찾는다.
print(index)
index2 = s.index("n", index + 1)  # 첫 n 글자 다음부터 n 을 찾음
print(index2)
print(s.count("n"))  # 문자열에서 n 이 몇번 나오는지 알려줌
print(s.find("n"))  # n문자를 찾을 수 있다.

# find와 index의 차이점
# print(str.find("java"))  # 해당 문자열에서 java가 없으면 -1를 리턴
# index = str.index("java")  # 해당 문자열에서 java가 없으면 오류남,
# 따라서 find 쓰는것이 좋음

# 문자열 포맷
# 방법 1
print("나는 %d 살입니다. " % 20)
print("나는 %s 을 종아해요. " % "파이썬")
print("나는 %s , %s을 종아해요. " % ("파이썬", "java"))
print("apple %c 로 시작해요. " % "A")

# 방법2
print("나는 {} 색과 {} 색을 좋아해요".format("파란", "빨간"))
print("나는 {1} 색과 {0} 색을 좋아해요".format("파란", "빨간"))  # 순서를 바꿀수 있다는 장점이 있다.

# 방법 3
print("나는 {age}살이고, {color} 색을 좋아해요".format(age=20, color="초록"))

# 방법 4 ( v3.6이상)
age = 14
color = "파랑"
print(f"나는 {age}살이며, {color}색을 좋아해요")


# 탈출문자 : 문자열을 탈출하는 것
# \n : (줄바꿈)
print("백문이 불여일견  \n백견이 불여일타 ")

# \" or \' : 문장 내에서 따옴표를 표시할 수 있게 한다.
print("저는 \"나도코딩\" 입니다.")
print("저는 \'나도코딩\' 입니다.")

# \\ : 문장 내에서 \ 로 표시된다.
print("\\Desktop\\python workspace")  # \과 뒤의 한글자가 결합해 오류가나는것을 방지한다.

# \r : 커서를 맨 앞으로 이동
# \r 부분에서 커서가 맨 앞으로 이동해 덮어씌운다. => "red " 부분을 pine 으로 덮는다.
print("red Apple\rPine")

# \b : 백스페이스 (한글자 삭제 )
print("Redd\bApple")

# /t : 탭
print("Red \tApple")


# 퀴즈
# 예) http://naver.com
# 조건1. http:// 부분은 제외한다. -> naver.com
# 조건2. 처음 만나는 점(.) 이후 부분은 제외 -> naver
# 조건3. 남은 글자 중 처음 세자리 + 글자 갯수 + 글자 내 'e' 갯수 + "!" 로 구성
# 예) 생성된 비밀번호 : nav51!

url = "http://naver.com"
# url = "http://gmail.com"
my_url = url[7:]  # 다른 방법 : url.replace("http://", "")
my_url = my_url[:my_url.find(".")]  # 다른방법: url[:url.index(".")]
print(my_url)
pw = my_url[:3] + str(len(my_url)) + str(my_url.count("e")) + "!"
print("{0} 의 비밀번호는 {1} 입니다. ".format(url, pw))


# 리스트 1:22:45
