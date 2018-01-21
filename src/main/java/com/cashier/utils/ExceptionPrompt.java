package com.cashier.utils;

public class ExceptionPrompt {
	public static int compare(int num) throws MyException { // 定义方法，抛出异常
		if (num == 1) {
				throw new MyException("余额不足,请到用户中心进行充值"); // 错误信息
		}
		if (num == 2) {
				throw new MyException("商品库存不足！"); // 错误信息
		}
		if (num == 3) {
			throw new MyException("减库失败！");
		}
		if (num == 4) {
			throw new MyException("添加消费记录失败");
		}
		return 0;

	}
}