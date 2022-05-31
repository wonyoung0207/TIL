package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ItemService;
import com.vo.ItemVO;

public class ItemSearchPrice {

	public static void main(String[] args) {
		Map<String,Integer> map = setSearchPrice();
		List<ItemVO> list = null;

		
		System.out.println("Spring Start ..");
		//Spring ȯ���� ���������. 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml");
		
		ItemService service = (ItemService) factory.getBean("itemservice");

		try {
			
			list = service.searchPrice(map); 
			for(ItemVO item : list) {
				System.out.println(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Spring End ..");
		}
	}
	
	public static Map<String,Integer> setSearchPrice() {
		Map<String,Integer> map = new HashMap<String,Integer>();
		Scanner input = new Scanner(System.in);
		int price1=0 ,price2=0, priceSwitch=0;
		
		System.out.println("ã�� ������ ������ �������ּ���.(�ּҿ� �ִ밪�� �Է����ּ���)");
		price1 = input.nextInt();
		price2 = input.nextInt();
		if(price1 > price2) {// ���� �����ֱ� 
			priceSwitch = price1;
			price1 = price2;
			price2 = priceSwitch;
		}
		
		map.put("price1", price1);
		map.put("price2", price2);
		
		return map;
	}

}
