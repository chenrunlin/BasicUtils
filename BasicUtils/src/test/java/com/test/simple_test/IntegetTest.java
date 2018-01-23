package com.test.simple_test;

public class IntegetTest {

	/**
	 * 结论: Integer 类型的值在[-128,127] 期间,Integer 用 “==”是可以的。Integer与 int 类型比较（==）比较的是值。
	 */
	public static void main(String[] args) throws Exception {
		
		Integer a = -129;
		Integer a1 = -129;
		Integer aaa = new Integer(-129);
		
		Integer aa = -128;
		Integer aa1 = -128;
		
		System.out.println("a==a1:" + (a == a1)); //   a==a1:false
		System.out.println("aa==aa1:" + (aa == aa1)); //   aa==aa1:true
		System.out.println("aaa==a:" + (aaa == a));        // aaa==a:false
		System.out.println("aaa==a1:" + (aaa == a1));        // aaa==a1:false
		System.out.println("a.equals(a1):" + a.equals(a1));   //  a.equals(a1):true
		
		Integer b = 128;
		Integer b1 = 128;
		System.out.println("b==b1:" + (b == b1));    // b==b1:false
		System.out.println("b.equals(b1):" + b.equals(b1));  //  b.equals(b1):true
		
		Integer c = 127;
		Integer cc = 127;
		Integer d = 1;
		Integer dd = 1;
		System.out.println("c==cc:" + (c == cc));  // c==cc:true
		System.out.println("d==dd:" + (d == dd));  // d==dd:true
		
		Integer e = 128;
		int e1 = 128;
		System.out.println("e == e1:" + (e == e1));  // e == e1:true
		
	}

}
