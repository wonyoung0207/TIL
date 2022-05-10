package day11_ws;

import java.util.ArrayList;
import java.util.HashMap;

import day11.DuplicatedIDException;

public class FruitDAO implements Ifruit, ISelect{

	HashMap<String, fruit> map = new HashMap<String, fruit>();
	
	public boolean findException(fruit f) throws NotNumberException {// �Էµ� �����Ͱ� ���ε� ����, �������� �Ǻ� false�� �����ִ� ��
		boolean notString = false;
		boolean notNumber = false;
		
		
		for(int i=0; i< f.getName().length(); i++) {
			if(Character.isDigit(f.getName().charAt(i))) {
				notString = true;//���ڰ� ������ true�� �ٲ� 
			}
		}
		
		for (int i = 0; i < f.getCondition().length(); i++) {
			if (Character.isDigit(f.getCondition().charAt(i))) {//���� �̸��� ����ǿ��� ���ڸ� �־���Ѵ�. 
				notString = true;//���ڰ� ������ true�� �ٲ� 
			}

		}
		for (int i = 0; i < f.getPrice().length(); i++) {
			if(!Character.isDigit((f.getPrice() + "").charAt(i))) {//���ݿ��� ���ڰ� ������ �ȵȴ�. -> getPrice()�� �������̶� "" �߰��ؼ� String������ ��ȯ 
				notNumber = true;//���ڰ� ��������� true�� ���Ұ��� 
			}

		}
		
		if(notString || notNumber) {//���� �̸�, ������� ������ ���, ���� ���ݿ� ���ڰ� �ִ� ��� ����
			return false;//false ���ϵǸ� ���� �߻��ϵ��� 
		}
		
		return true;//���� ������� true ���� 
		
		
	}

	//���ڳ� ���ڰ� �ƴҰ�� ���� �߻��ϵ��� 
	@Override
	public void create(fruit f)  throws NotNumberException {
		
		boolean fidexception = true;// �Էµ� �����Ͱ� ���ε� ����, �������� �Ǻ� false�� �����ִ� ��
		fidexception = findException(f);
		if(!fidexception) {//������ ������ ����� 
			throw new NotNumberException("�Էµ� �����Ϳ� ������ �ֽ��ϴ�. �����̸�,���´� ����, ������ ���ڷ� �Է����ּ���.");
		}
		

		map.put(f.getName(), f);
		System.out.println(f.getName() + "���� �ڳʰ� �����Ǿ����ϴ�.");
	}

	//�����Ͱ� ������� ���� �߻� 
	@Override
	public fruit read(String id) throws NotFindException{
		String key = null;
		
		if(!map.containsKey(id)){//�ش� ������ �ִ��� Ȯ�� 
			throw new NotFindException("������ �������� �ʽ��ϴ�. ");
		}

		for(String f : map.keySet()) {
			if(f.equals(id)) {//�Ѿ�� id�� ������ ���� 
				key = f;//��ü�� �ѱ� 
			}

		}
		
		return map.get(key);

	}

	//ã�� �����Ͱ� ������� ���� �߻� 
	//���ڰ� �ƴҰ�� ���� �߻�
	@Override
	public void update(fruit f) throws NotNumberException,NotFindException{
		
		boolean fidexception = true;// �Էµ� �����Ͱ� ���ε� ����, �������� �Ǻ� false�� �����ִ� ��
		fidexception = findException(f);
		if(!fidexception) {//������ ������ ����� 
			throw new NotNumberException();
		}
		
		if(!map.containsKey(f.getName())){//�ش� ������ �ִ��� Ȯ�� 
			throw new NotFindException("������ �������� �ʽ��ϴ�. ");
		}
		
		

		for(String a : map.keySet()) {
			if(a.equals(f.getName())) {//���� ������ �������� ������Ʈ ���� 
				map.put(f.getName(),f);

			}
		}

	}


	//ã�� �����Ͱ� ������� ���� �߻� 
	//���ڰ� �ƴҰ�� ���� �߻�
	@Override
	public void delete(String id) throws NotNumberException,NotFindException{
		
		if(map.containsKey(id)){//�ش� ������ �ִ��� Ȯ�� 
			throw new NotFindException("������ �������� �ʽ��ϴ�. ");
		}

		for(int i = 0; i < id.length(); i++) {//�Է¹��� ���� �̸��� ���ڰ� ����ִ��� �Ǻ� 
			if(Character.isDigit(id.charAt(i))){//���ڰ� ������ ���� 
				throw new NotNumberException();
			}
		}
		
		
		for(String a : map.keySet()) {
			if(a.equals(id)) {//���� ������ �������� ������Ʈ ���� 
				map.remove(a);
			}
		}

	}


	//�����Ͱ� null �ΰ�� ������� ���� �߻� 
	public ArrayList<fruit> All_select() throws MyNullPointException{
		if(map.isEmpty()) {//map�� �ƹ��͵� ������ ���� 
			throw new MyNullPointException("map�� ���� �����ϴ�. ");
		}
		
		ArrayList<fruit> list = new ArrayList<fruit>();
		for(String keys : map.keySet()) {
			list.add(map.get(keys));
		}
		return list;

	}



}
