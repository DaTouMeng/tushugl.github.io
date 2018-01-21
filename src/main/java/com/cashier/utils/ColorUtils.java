package com.cashier.utils;

import java.awt.Color;

public class ColorUtils {

	public static final Color getColor(final String hexColor) {
		String hexc = hexColor;
		Color result = Color.WHITE;
		if (hexc != null && !"".equals(hexc)) {
			if (hexc.startsWith("#")) {
				hexc = hexc.replaceFirst("#", "");
			}
			result = new Color(Integer.parseInt(hexc, 16));
		}
		return result;
	}

}
