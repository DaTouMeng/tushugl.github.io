package com.cashier.utils;


	public class MyException extends Exception // 创建自定义异常类，继承Exception类

	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7781306370943367986L;

		public MyException(String ErrorMessagr) // 构造方法

		{
			super(ErrorMessagr);
		} // 父类构造方法

	}

