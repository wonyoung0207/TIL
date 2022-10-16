

#  Scanner

> 사용 개념 간소화 

---

## 풀이 

1. Scanner

   ```java
   //구현
   Scanner input = new Scanner(System.in);
   int num = input.nextInt();
   DrivingCar car = new DrivingCar();
   
   switch (num){
       case 1:
           car.getDrive(1);
           break;
       case 2:
           car.gearNetural();
           break;
       case 3:
           car.gearReverse();
           break;
       case 4:
           car.gearParking();
           break;
   
       default:
           System.out.println(num + " : 알 수 없는 입력값입니다.");
           break;
   }//구현
   Scanner input = new Scanner(System.in);
   int num = input.nextInt();
   DrivingCar car = new DrivingCar();
   
   switch (num){
       case 1:
           car.getDrive(1);
           break;
       case 2:
           car.gearNetural();
           break;
       case 3:
           car.gearReverse();
           break;
       case 4:
           car.gearParking();
           break;
   
       default:
           System.out.println(num + " : 알 수 없는 입력값입니다.");
           break;
   }
   ```
