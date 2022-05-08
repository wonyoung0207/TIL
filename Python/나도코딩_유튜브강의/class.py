# 클래스
# 마린 : 공격 유닛, 군인, 총 가능
from operator import truediv


name = "마린"  # 유닛 이름
hp = 40  # 유닛 체력
damage = 5  # 유닛 공격력

print("{} 유닛이 생성되었습니다. ".format(name))
print("채력 {0}, 공격력 {1} \n".format(hp, damage))

# 탱크 : 공격 유닛, 탱크, 포를 사용, 일반/시즈 모드로 나뉨
tank_name = '탱크'
tank_hp = 150
tank_damage = 35
print("{} 유닛이 생성되었습니다. ".format(tank_name))
print("채력 {0}, 공격력 {1} \n".format(tank_hp, tank_damage))


def attack(name, location, damage):  # 함수 이용
    print("{0} : {1} 방향으로 적군을 공격합니다. [공격력 {2}]".format(
        name, location, damage
    ))


attack(name, "1시", damage)
attack(tank_name, "1시", tank_damage)


class Unit:  # 클래스 이용
    def __init__(self, name, hp, damage):
        self.name = name  # self는 자기 자신을 의미한다.
        self.hp = hp
        self.damage = damage

        print("{} 유닛이 생성되었습니다. ".format(self.name))
        print("채력 {0}, 공격력 {1} \n".format(self.hp, self.damage))


marine1 = Unit("마린1", 40, 5)
marine2 = Unit("마린2", 40, 5)
tank = Unit("탱크", 150, 35)

# init
# 객체의 생성자를 의미한다.
# 마린과 탱크는 Uit의 인스턴스 라고 한다.


# 멤버 변수
# 클래스 내에서 선언된 변수로, 클래스에 국한된다.
# 레이스 : 공중 유닛, 비행기 , 클로킹( 상대방에게 보이지 않음 )
wraith = Unit("레이스", 80, 5)
# 클래스 이름으로 나타낸다.
print("유닛 이름 : {0}, 공격력 : {1}".format(wraith.name, wraith.damage))


# 마인드 컨트롤 : 상대방 유닛을 내것으로 만드는 것
wraith2 = Unit("레이스", 80, 5)
# 현재 Unit 맴버 변수에는 에는 clocking이 존재하지 않는다.
wraith2.clocking = True  # 파이썬의 특징으로, 외부에서 객체 변수선언 후 값을 넣어줄 수 있다.
# 따라서 wraith2 에만 clocking이 존재하게 된다.
if wraith2.clocking == True:
    print("{0} 는 현재 클로킹 상태입니다. ".format(wraith2.name))


# 메소드
class AttackUnit:
    def __init__(self, name, hp, damage):
        self.name = name
        self.hp = hp
        self.damage = damage

    def attack(self, location):  # 공격했을 경우 실행
        print("{0} : {1} 방향으로 적군을 공격합니다. [공격력 {2}]"
              .format(self.name, location, self.attack)
              )

    def damaged(self, damage):  # 피해를 입었을 경우 실행
        print("{0} : {1} 데미지를 입었습니다. ".format(self.name, damage))
        self.hp -= damage
        print("{0} : 현재 체력은 {1} 입니다. ".format(self.name, self.hp))
        if self.hp <= 0:
            print("{0} : 파괴되었습니다. ".format(self.name))


# 파이어뱃: 공격유닛, 화염방사기,
firebat1 = AttackUnit("파이어뱃", 50, 16)
firebat1.attack("5시")

firebat1.damaged(25)
firebat1.damaged(25)


# 3: 59: 29
