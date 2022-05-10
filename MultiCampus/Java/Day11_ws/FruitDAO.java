package day11_ws;

import java.util.ArrayList;
import java.util.HashMap;

import day11.DuplicatedIDException;

public class FruitDAO implements Ifruit, ISelect{

	HashMap<String, fruit> map = new HashMap<String, fruit>();
	
	public boolean findException(fruit f) throws NotNumberException {// 입력된 데이터가 재대로된 문자, 숫자인지 판별 false면 문제있는 것
		boolean notString = false;
		boolean notNumber = false;
		
		
		for(int i=0; i< f.getName().length(); i++) {
			if(Character.isDigit(f.getName().charAt(i))) {
				notString = true;//숫자가 있으면 true로 바꿈 
			}
		}
		
		for (int i = 0; i < f.getCondition().length(); i++) {
			if (Character.isDigit(f.getCondition().charAt(i))) {//과일 이름과 컨디션에는 문자만 있어야한다. 
				notString = true;//숫자가 있으면 true로 바꿈 
			}

		}
		for (int i = 0; i < f.getPrice().length(); i++) {
			if(!Character.isDigit((f.getPrice() + "").charAt(i))) {//가격에는 문자가 있으면 안된다. -> getPrice()가 숫자형이라 "" 추가해서 String형으로 변환 
				notNumber = true;//문자가 들어있으면 true로 변할것임 
			}

		}
		
		if(notString || notNumber) {//과일 이름, 컨디션이 숫자일 경우, 과일 가격에 문자가 있는 경우 실행
			return false;//false 리턴되면 에러 발생하도록 
		}
		
		return true;//문제 없을경우 true 리턴 
		
		
	}

	//문자나 숫자가 아닐경우 에러 발생하도록 
	@Override
	public void create(fruit f)  throws NotNumberException {
		
		boolean fidexception = true;// 입력된 데이터가 재대로된 문자, 숫자인지 판별 false면 문제있는 것
		fidexception = findException(f);
		if(!fidexception) {//문제가 있으면 실행됨 
			throw new NotNumberException("입력된 데이터에 문제가 있습니다. 과일이름,상태는 문자, 가격은 숫자로 입력해주세요.");
		}
		

		map.put(f.getName(), f);
		System.out.println(f.getName() + "과일 코너가 생성되었습니다.");
	}

	//데이터가 없을경우 에러 발생 
	@Override
	public fruit read(String id) throws NotFindException{
		String key = null;
		
		if(!map.containsKey(id)){//해당 과일이 있는지 확인 
			throw new NotFindException("과일이 존재하지 않습니다. ");
		}

		for(String f : map.keySet()) {
			if(f.equals(id)) {//넘어온 id와 같은걸 리턴 
				key = f;//객체를 넘김 
			}

		}
		
		return map.get(key);

	}

	//찾는 데이터가 없을경우 에러 발생 
	//문자가 아닐경우 에러 발생
	@Override
	public void update(fruit f) throws NotNumberException,NotFindException{
		
		boolean fidexception = true;// 입력된 데이터가 재대로된 문자, 숫자인지 판별 false면 문제있는 것
		fidexception = findException(f);
		if(!fidexception) {//문제가 있으면 실행됨 
			throw new NotNumberException();
		}
		
		if(!map.containsKey(f.getName())){//해당 과일이 있는지 확인 
			throw new NotFindException("과일이 존재하지 않습니다. ");
		}
		
		

		for(String a : map.keySet()) {
			if(a.equals(f.getName())) {//같은 과일이 있을때만 업데이트 가능 
				map.put(f.getName(),f);

			}
		}

	}


	//찾는 데이터가 없을경우 에러 발생 
	//문자가 아닐경우 에러 발생
	@Override
	public void delete(String id) throws NotNumberException,NotFindException{
		
		if(map.containsKey(id)){//해당 과일이 있는지 확인 
			throw new NotFindException("과일이 존재하지 않습니다. ");
		}

		for(int i = 0; i < id.length(); i++) {//입력받은 과일 이름에 숫자가 들어있는지 판별 
			if(Character.isDigit(id.charAt(i))){//숫자가 있으면 실행 
				throw new NotNumberException();
			}
		}
		
		
		for(String a : map.keySet()) {
			if(a.equals(id)) {//같은 과일이 있을때만 업데이트 가능 
				map.remove(a);
			}
		}

	}


	//데이터가 null 인경우 없을경우 에러 발생 
	public ArrayList<fruit> All_select() throws MyNullPointException{
		if(map.isEmpty()) {//map에 아무것도 없으면 실행 
			throw new MyNullPointException("map에 값이 없습니다. ");
		}
		
		ArrayList<fruit> list = new ArrayList<fruit>();
		for(String keys : map.keySet()) {
			list.add(map.get(keys));
		}
		return list;

	}



}
