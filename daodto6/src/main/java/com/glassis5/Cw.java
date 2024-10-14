package com.glassis5;

public class Cw {

	// DOT : 라인을 그릴때 사용할 기호
	private static final String DOT = "★";

	// LINE_LENGTH : 선을 그릴때 사용할 길이
	private static final int LINE_LENGTH = 32;

	public static void w(String s) { // 문자열을 개행없이 출력하는 메서드
		System.out.print(s); // s = 출력할 문자열
	}

	public static void wn(String s) { // 문자열을 출력한 후 자동으로 개행하는 메서드
		System.out.println(s); // s = 출력할 문자열
	}

	public static void wn() {
		System.out.println(); // 개행만 출력하는 오버로딩 된 메서드
	}

	// DOT 기호로 구성된 라인을 그리는 메서드, LINE_LENGTH만큼 DOT기호를 출력하고 개행
	public static void line() {
		for (int i = 0; i < LINE_LENGTH; i++) {
			w(DOT); // DOT기호를 출력
		}
		wn(); // 개행
	}

	// 단일 DOT기호를 출력하는 메서드
	public static void dot() {
		w(DOT); // DOT기호 출력
	}

	// 주어진 숫자만큼 공백을 출력하는 메서드
	// c = 출력할 공백의 수
	public static void space(int c) {
		for (int i = 0; i < c; i++) {
			w(" "); // 공백 출력
		}
	}
}
