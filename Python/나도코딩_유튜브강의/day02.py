# 리스트 : 자바의 배열과 흡사. 리스트는 대괄호 [] 를 사용하며 순서가 존재한다.
# 튜플 : 순서는 있지만 리스트와 다르게 내용을 바꿀수 없다. 튜플은 () 를 사용한다.
# dictionary : key와 value 값으로 이루어져있다. 순서가 없고 key 값을 통해 value를 추출할 수 있다. {key : value} 의 구조를 가져 key값이 중복될 수 없다.
# set : 순서가 없고 중복이 불가하다. set([1,2])또는 {1,2}  의 구조를 가진다.

# 리스트 1:22:45
from random import sample, shuffle


num_list = [10, 20, 30]
print(num_list)
person_list = ["유재석", "조세호", "박명수"]
print(person_list.index("조세호"))  # 조세호가 몇번째에 있는지 알려줌

# 리스트 추가
person_list.append("하하")  # 리스트의 맨 뒤에 하하를 추가한다.
print(person_list)
person_list.insert(1, "정형돈")
print(person_list)

# 리스트에서 뒤에서 한명씩 빼기
print(person_list.pop())  # 맨 뒤에 한명이 출력됨
print("맨 뒤에 있는 하하 제거 : ", person_list)

# 리스트에 해당값이 몇개 있는지 확인
person_list.append("유재석")
print(person_list.count("유재석"))  # 같은 값이 몇번 나오는지 확인

# 리스트는 정렬할 수 있다.
num_list = [5, 2, 3, 1, 4]
num_list.sort()
print(num_list)
# 거꾸로 정렬
num_list.reverse()
print(num_list)

# 리스트 안의 내용을 모두 제거
num_list.clear()
print(num_list)

# 리스트의 장점 :
# 1. 다양한 자료형 사용 가능
num_list = [5, 2, 3, 1, 4]
mix_list = ["조세호", 10, True]
print("다양한 자료형 사용한 리스트 : ", mix_list)
# 2. 여러 리스트를 병합
num_list.extend(mix_list)
print("두 개의 리스트 병합 : ", num_list)


# key 와 value이용
cabinet = {1: "유재석", 100: "김태호"}  # key : value 구조로 이루어져있다.
print("cabinet %s" % cabinet[1])
print("cabinet " + cabinet.get(100))
print("cabinet is %s" % cabinet.get(5, "사용불가"))  # 5가 없으면 뒤의 문장을 출력함.
print(3 in cabinet)  # in 키워드를 이용하면 값이 있는지 없는지 확인할 수 있다.
print(1 in cabinet)  # in 키워드를 이용하면 값이 있는지 없는지 확인할 수 있다.
cabinet[200] = "조세호"
print(cabinet)

# key값 삭제
del cabinet[200]  # key 200 , 조세호 삭제
print(cabinet)

# 어떤 키가 있는지 알려주는 함수
print(cabinet.keys())
# 어떤 value 가 있는지 알려주는 함수
print(cabinet.values())
# 키와 value를 동시에 출력
print(cabinet.items())

cabinet.clear()  # 모든 내용 삭제

# 튜플 -> 리스트보다 빠르지만 리스트와는 다르게 변경 불가
menu = ("돈가쓰", "치즈까스")
# menu.add("생선까스")  # 튜플은 내용을 변경할 수 없음

(name, age, birth) = "김종국", 24, "3/23"
print(name, age, birth)


# 집합( set)
# 중복이 안되고, 순서가 없음
my_set = {1, 2, 3, 3, 3}  # set 나타내는 방법
print(my_set)
java = {"유재석", "김태호", "양세형"}
python = set(["유재석", "박명수"])

# 교집합 ( java와 pyrhon 둘다 할수있는 사람 출력)
print(java & python)
print(java.intersection(python))
# 합집합
print(java | python)
print(java.union(python))
# 차집합
print(java-python)
print(java.difference(python))

# 집합에 값을 추가 또는 삭제
java.add("김태호")
print(java)
java.remove("김태호")
print(java)


# 자료구조의 변경
menu = {"커피", "우유", "주스"}  # set(집합 ) 형태
print(menu, type(menu))

menu = list(menu)  # [] 형태
print(menu, type(menu))

menu = tuple(menu)  # () 형태
print(menu, type(menu))


# shuffle 과 sample
# shuffle : 무작위로 섞는다.
# sample : 무작위로 뽑는다.
lst = [1, 2, 3, 4, 5]
shuffle(lst)  # lst를 무작위로 섞는다.
print(lst)
# lst.sort()
print(sample(lst, 1))  # lst에서 1개만큼 랜덤으로 샘플을 뽑는다.


# 퀴즈
# 추첨을 통해 1명은 치킨, 3명은 커피 쿠폰을 받습니다. 이때 중복되지 않게 하시오.
# 조건1. 편의상 댓글은 20명이 작성하고, 1~20 이라고 가정
# 조건2. 댓글 내용과 상관 없이 무작위로 추첨하되 중복 불가
# 조건3. random 모듈의 shuffle 과 sample을 활용
# range(시작 , 끝 ) => range(1,11) -> 1 ~10 까지 반복
# ex) zero = [0 for i in range(101)] => 100개를 0으로 모두 초기화
users = range(1, 21)
print(type(users))  # range 로 만들면 타입이 range라 shuffle과 sample를 사용 못함
users = list(users)
shuffle(users)
winners = sample(users, 4)
print("--당첨자 발표--")
print("치킨 당첨자 : {0}".format(winners[0]))
print("치킨 당첨자 : {0}".format(winners[1:]))


# if 조건문 (분기)
weather = "비"
# 사용자 입력문 input -> 항상 문자열로 받는다.
# weather = input("오늘 날씨는 어때요?")

if(weather == "비" or "눈"):
    print("우산을 챙기세요.")
elif(weather == "미세먼지"):
    print("마스크가 필요해요 ")
else:
    print("준비물 필요 없어요")

# temp = int(input("기온은 어때요?"))
# if 30 <= temp:
#     print("너무 더워요. 나가지 마세요")
# elif 10 <= temp and temp < 30:
#     print("괜찮은 날씨에요")
# else:
#     print("너무 추워요. 나가지 마세요")


# for 문 (반복문)
for waiting_no in [0, 1, 2, 3, 4]:
    print("대기번호 : {0} ".format(waiting_no))
for waiting_no in range(1, 6):
    print("대기번호 : {0} ".format(waiting_no))

starbucks = ["아이언맨", "토르", "그루트"]
for i in starbucks:
    print("{0} 커피가 준비되었습니다. ".format(i))


# while 문
customer = "토르"
index = 5
while index >= 0:
    print("{0} 커피가 준비되었습니다. {1} 번 남았어요.".format(customer, index))
    index -= 1
    if index == 0:
        print("커피는 폐기되었어요")

customer = "토르"
person = "Unknown"
# while person != customer:
#     print("{0} , 커피가 준비되었습니다. ".format(customer))
#     person = input("이름이 어떻게 되시나요?")


# continue 와 break
stu_sick = [2, 5]  # 아파서 결석한 학생
no_book = [7]  # 책이 없는 학생
for student in range(1, 11):
    if student in stu_sick:  # 2,5번일경우 실행된다.
        continue
    elif student in no_book:
        print("오늘 수업 여기까지. {0}은 교무실로 따라와 ".format(student))
        break
    print("{0} , 책을 읽게 시킵니다. ".format(student))

# 한줄 for문
students = [1, 2, 3, 4, 5]
students = [i+100 for i in students]
print(students)

# for문 이용 학생이름 길이로 변환
animals = ["dog", "cat", "bear"]
animals = [len(i) for i in animals]
print(animals)

# 2: 22: 45ddd
