package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

class IncomeNodeTest {
	
	private IncomeNode firstIncomeNode;
	
	public void setupScenary1() {
		Calendar c = new GregorianCalendar();
		Income in = new Income("test", 1000, c);
		firstIncomeNode = new IncomeNode("213",in);
	}
	
	public void setupScenary2() {
		setupScenary1();
		Calendar c = new GregorianCalendar();
		Calendar cc = new GregorianCalendar();
		IrregularIncome inOne = new IrregularIncome("123", 10000, c, "Burguer");
		IrregularIncome inTwo = new IrregularIncome("132", 1000000, cc, "HotDog");
		firstIncomeNode.addNode(new IncomeNode("4234",inOne));
		firstIncomeNode.addNode(new IncomeNode("43534",inTwo));
	}

	public void setupScenary3() {
		
	}
	@Test
	public void test1() {
		setupScenary1();
		Calendar c = new GregorianCalendar();
		IrregularIncome newIncome = new IrregularIncome("Burguer", 100000, c, "Burguer");
		IncomeNode node = new IncomeNode("1", newIncome);
		firstIncomeNode.addNode(node);
		assertTrue(firstIncomeNode.checkNode());
	}
	
	@Test
	public void test2() {
		setupScenary2();
		ArrayList<Income> list = firstIncomeNode.realIncomes();
		assertTrue(list.size() != 0);
	}
	
	@Test
	public void test3() {
		setupScenary1();
		Calendar d = new GregorianCalendar();
		Income search = new Income("test",1000,d);
		Income found = firstIncomeNode.searchNode(search);
		assertTrue(found != null);
	}

}
