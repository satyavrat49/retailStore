package com.retailStore.invoice.dao.test;

import org.joda.time.DateTime;

public class TimestampTest {

	public static void main(String[] args) {
		String time = "2019-06-24T01:09:51Z";
		DateTime date = new DateTime(time);
		System.out.println(date);
		System.out.println(date.getZone());
		DateTime dt = new DateTime("2019-06-23 21:09:50.445");
		System.out.println(dt);
	}

}
