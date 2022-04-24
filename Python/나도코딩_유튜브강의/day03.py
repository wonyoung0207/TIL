import sys
from random import *
from time import time
from tkinter import commondialog
from tkinter.tix import Balloon

# 퀴즈
# 조건에 맞는 승객 구하기 . 총 50명의 승객 중 소요시간이 맞는 승객을 뽑아서 알려주기
# 조건1. 승객별 운행 소요 시간은 5 ~ 50 분 사이의 난수로 정한다.
# 조건 2. 소요시간 5~15분 사이의 승객만 매칭해야 한다.
cnt = 0  # 총 탑승 승객
for i in range(1, 51):  # 1~50 명의 승객
    time = randrange(5, 51)  # 5분~ 50분 소요시간
    if 5 <= time <= 15:  # 매칭 성공한 경우
        print("[0] {0}번째 손님 (소요시간 : {1}분) ".format(i, time))
        cnt += 1
    # else:  # 매칭 실패한 경우
    #     # print("[x] {0}번째 손님 (소요시간 : {1}분) ".format(i, time))

print("총 탑승 승객: {0}명".format(cnt))


# 함수
# 전달값과 반환값
# 함수 정의 def
def open_account():
    print("새로운 계좌가 생성되었습니다. ")


def deposit(balance, money):
    print("입금이 완료되었습니다. 잔핵은 {0} 원입니다. ".format(balance+money))
    return balance + money


def withdraw(balance, money):
    if balance >= money:
        print("출금이 완료되었습니다. 잔액은 {0} 원 입니다. ".format(balance-money))
    else:
        print("출금이 완료되지 않았습니다. 잔액은 {0} 원 입니다. ".format(balance))
    return balance


def withdraww_night(balance, money):  # 저녁에 출금
    commission = 100  # 수수료
    return commission, balance - money - commission  # 총 2개의 값을 반환한다.


open_account()
balance = 0
balance = deposit(balance, 1000)
withdraw(balance, 500)
commission, balance = withdraww_night(balance, 500)  # 리턴값이 2개이기 때문에 가능
print("수수료 {0} 원이며, 잔액은 {1}원 입니다. ".format(commission, balance))


# 함수 기본값
# 함수의 매개변수에 기본값을 설정하여 출력할 수 있다.
def profile(name, age=16, main_lang="파이썬"):
    print("이름 : {0} \t나이 : {1} \t 주로 사용하는 언어: {2}"
          .format(name, age, main_lang))


profile("유재석", 20, "파이썬")
profile("김태호", 24, "자바")
profile("박명수")


# 키워드 값
# 함수의 매개변수로 넘길때 이름을 지정하면 순서에 상관없이 넘길 수 있다.
def profile(name, age=16, main_lang="파이썬"):
    print("이름 : {0} \t나이 : {1} \t 주로 사용하는 언어: {2}"
          .format(name, age, main_lang))


profile(name="유재석", main_lang="파이썬")
profile(age="13", name="유재석", main_lang="파이썬")


# 가변인자 (매개변수의 갯수가 가변적일 때 , * 을 매개변수에 붙여 가면으로 사용할 수 있다. )

# def profile(name, age, lang1, lang2, lang3, lang4, lang5):
#     print("이름 {0} \t 나이: {1}".format(name, age), end=" ")
#     print(lang1, lang2, lang3, lang4, lang5)

def profile(name, age, *lang):
    print("이름 {0} \t 나이: {1}".format(name, age), end=" ")
    # end의 의미는 print문이 끝났을 경우 뒤에 줄바꿈이 일어나는데, 그것을 end 부분으로 대체한다.
    # 따라서 줄바꿈대신 띄어쓰기가 쓰인다.

    for lang in lang:
        print(lang, end=" ")
    print()


profile("유재석", 20, "Python", "Java", "C++", "C#", "C", "JavaScript")
profile("김태호", 25, "Python", "JavaScript")


# 지역변수와 전역변수
gun = 10


def checkpoint(soldiers):  # 경계근무
    # gun = 20
    global gun  # 전역 공간의 변수를 함수 내에서 사용함
    gun = gun - soldiers
    print("[함수 내] 남은 총 : {0} ".format(gun))


def checkpoint_ret(gun, soldiers):  # 경계근무
    gun = gun - soldiers
    print("[함수 내] 남은 총 : {0} ".format(gun))
    return gun


print("전체 총 : {0} ".format(gun))
checkpoint(2)  # 2명이 총을 가지고 나감
print("남은 총  : {0}".format(gun))

print("전체 총 : {0} ".format(gun))
gun = checkpoint_ret(gun, 2)  # 2
print("남은 총  : {0}".format(gun))


# 퀴즈
# 표준 체중을 구하는 프로그램을 작성하시오\
# 남자 : 키 x 키 x 22
# duwk : 키 x 키 x 21
# 조건1. 표준 체중은 별도의 함수 내에서 계산
# 조건2. 표준 체중은 소수점 둘째자리까지 표시
def std_weight(h, g):  # 키는 m 단위로 받는다.
    if g == "남자":
        return h * h * 22
    else:
        return h * h * 21


height = 175
gender = "남자"
weight = round(std_weight(height/100, gender), 2)
print("키 {0} {1} 의 표준 체중은 {2} 입니다. ".format(height, gender, weight))


# 표준 입출력
print("python", "Java")
print("python", "Java", sep=" vs ")  # seq를 이용하여 print 출력을 변경할 수 있다.
# end를 이용하여 print 끝의 기본설정 줄바꿈을 ?로 바꿀 수 있다.
print("python", "Java", sep=" vs ", end="?")
print("python", "Java", file=sys.stdout)  # 표준 출력으로 출력
print("python", "Java", file=sys.stderr)  # 표준 에러로 처리해서 출력-> 에러가 났을때 구분하기 위해

scores = {"수학": 0, "영어": 50, "코딩": 100}
for subject, score in scores.items():  # items()는 디렉토리의 key와 value를 가져온다
    # 총 8개의 공간을 확보하고 왼쪽정렬한다.
    print(subject.ljust(8), str(score).rjust(4), sep=":")


# 은행 대기 순번표
for num in range(1, 15):
    # 3개의 공간을 확보하고 남는 공간을 0으로 채워라
    print("대기번호 : " + str(num).zfill(3), sep=", ")

answer = input("아무값이나 입력하세요 : ")
print("입력하신 값은 " + answer + "입니다. ")


# 3 : 10 : 14
