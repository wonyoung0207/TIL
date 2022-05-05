# 빈 자리는 빈공간으로 두고, 오른쪽 정렬을 하되, 총 10자리 공간을 확보
import pickle
from http.client import LineTooLong


print("{0: > 10}".format(500))

# 양수일 댄 + 로 표시, 음수일땐 - 로 표시
print("{0: >+10}".format(500))
print("{0: >+10}".format(-500))

# 왼쪽 정렬을 하고, 빈칸으로 _로 채움
print("{0:_<+10}".format(500))

# 3자리마다 콤마를 찍어주기
print("{0:,}".format(1000000))
print("{0:+,}".format(1000000))

# 3자리마다 콤마를 찍어주기, 부호도 붙이고, 자릿수 확보하기
# 돈이 많으면 행복하니까 빈 자리는 ^로 채워주기
print("{0:^<+30,}".format(10000000000))

# 소수점 출력
print("{0:f}".format(5/3))

# 소수점 특정 자리수 까지만 표시
print("{0:.2f}".format(5/3))


# 파일 입출력
# w 는 쓰기용도, 따라서 덮어씌우기가 된다.
score_file = open("score.txt", "w", encoding="utf8")
print("수학 : 0", file=score_file)
print("영어 : 50", file=score_file)
score_file.close()  # 파일을 열었으면 반드시 닫아야함


# a 는 이미 존재하는 파일에다가 이어서 사용한다는 이야기다.
score_file = open("score.txt", "a", encoding="utf8")
score_file.write("과학: 70")
score_file.write("\n코딩: 90")
score_file.close()  # 파일을 열었으면 반드시 닫아야함

# r은 파일을 읽어오는 용도로 사용된다.
score_file = open("score.txt", "r", encoding="utf8")
print(score_file.read())
score_file.close()

# 줄별로 읽기. 한 줄을 읽고 커서는 다음줄로 이동
score_file = open("score.txt", "r", encoding="utf8")
print(score_file.readline())
print(score_file.readline(), end="")  # end를 이용해서 print의 디폴트인 줄바꿈 막기
print(score_file.readline(), end="")
print(score_file.readline())
score_file.close()

# 파일에 몇줄이 있는지 모를경우 사용
score_file = open("score.txt", "r", encoding="utf8")
while True:
    line = score_file.readline()
    if not line:  # 더이상 출력할게 없는경우 탈출
        break
    print(line, end="")
score_file.close()

# 리스트에 값을 넣어서 출력
score_file = open("score.txt", "r", encoding="utf8")
lines = score_file.readlines()  # 모든 라인을 가져와서 list 형태로 저장
for line in lines:
    print(line, end="")

score_file.close()


# pickle
# 프로그램 상에서 사용하는 것을 파일 형태로 저장하는 기능

# 파일 쓰기 dump() 이용
# pickle을 사용하기 위해서는 바이트(b) 형태로 다뤄야 한다.
profile_file = open("profile.pickle", "wb")  # 바이트 형식으로 쓴다.
profile = {"이름": "빅명수", "나이": 30, "취미": ["축구", "골프", "코딩"]}
print(profile)
pickle.dump(profile, profile_file)  # profile변수의 내용을 file에 저장한다.
profile_file.close()

# 파일 불러오기 load() 이용
profile_file = open("profile.pickle", "rb")  # 바이트 형식으로 쓴다.
profile = pickle.load(profile_file)  # file에 있는 정보를 profile에 불러오기
print(profile)
profile_file.close()


# with : 파일 입출력을 간단히 할 수 있게 해준다.
# 위의 close 하는 부분을 with가 자동으로 진행시켜줌
with open("profile.pickle", "rb") as profile_file:
    # profile을 pickle을 이용해 오픈하는데, 그 정보를 profile_file에 저장한다.
    print(pickle.load(profile_file))  # 저장된 정보를 이용해서 load()한다.

with open("study.txt", "w", encoding="utf8") as study_file:
    study_file.write("파이썬을 공부하고 있어요")

with open("study.txt", "r", encoding="utf8") as study_file:
    print(study_file.read())


# 퀴즈 7
# 회사에서는 매주 1회 작성해야 하는 보고서가 있습니다.
# 보고서는 항상 아래와 같은 형태로 출력되어야 한다.
# - X 주차 보고서 -
# 부서:
# 이름 :
# 업무 요약 :
# 1주차부터 50주차까지의 보고서 파일을 만드는 프로그램을 작성
# 조건 : 파일명은 '1주차.txt', '2주차.txt'
for i in range(1, 3):
    with open(str(i) + "주차.txt", "w", encording="utf8") as report_file:
        report_file.write("- {0} 주차 보고서 - ".fomat(i))
        report_file.write("\ n부서:")
        report_file.write("\n 이름:")
        report_file.write("\n 업무요약:")


# 클래스 3: 38: 31
