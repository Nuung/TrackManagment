package controller;

import java.util.ArrayList;

import controller.db.DBconnection;

public class ChangeLecture {
	private int[] changeLecture; // 변환 기이수 강의 담아줄 ArrayList
	private DBconnection dbcon;
	
	// 생성자에서 바로 subject를 int형으로 변환 실시
	public ChangeLecture(int stnum, ArrayList<String> subject){
		this.dbcon = new DBconnection();
		this.changeLecture = new int[subject.size()];
		
		for(int i = 0 ; i < subject.size() ; i++) {
			if(subject.get(i).equals("선형대수및프로그래밍")) {
				changeLecture[i] = 1;
			}else if(subject.get(i).equals("컴퓨터그래픽스")) {
				changeLecture[i] = 2;
			}else if(subject.get(i).equals("웹프로그래밍")) {
				changeLecture[i] = 3;
			}else if(subject.get(i).equals("영상처리")) {
				changeLecture[i] = 4;
			}else if(subject.get(i).equals("HCI개론")) {
				changeLecture[i] = 5;
			}else if(subject.get(i).equals("웹프로그래밍설계")) {
				changeLecture[i] = 6;
			}else if(subject.get(i).equals("웹기반시스템")) {
				changeLecture[i] = 7;
			}else if(subject.get(i).equals("윈도우즈프로그래밍")) {
				changeLecture[i] = 8;
			}else if(subject.get(i).equals("XML프로그래밍")) {
				changeLecture[i] = 9;
			}else if(subject.get(i).equals("데이터컴퓨팅")) {
				changeLecture[i] = 10;
			}else if(subject.get(i).equals("정보검색")) {
				changeLecture[i] = 11;
			}else if(subject.get(i).equals("가상현실")) {
				changeLecture[i] = 12;
			}else if(subject.get(i).equals("멅티미디어프로그래밍")) {
				changeLecture[i] = 13;
			}else if(subject.get(i).equals("고급실시간그래픽스")) {
				changeLecture[i] = 14;
			}else if(subject.get(i).equals("오픈소스SW개론")) {
				changeLecture[i] = 15;
			}else if(subject.get(i).equals("멀티미디어")) {
				changeLecture[i] = 16;
			}else if(subject.get(i).equals("통계학개론")) {
				changeLecture[i] = 17;
			}else if(subject.get(i).equals("신호및시스템")) {
				changeLecture[i] = 18;
			}else if(subject.get(i).equals("디지털신호처리")) {
				changeLecture[i] = 19;
			}else if(subject.get(i).equals("멀티미디어데이터베이스")) {
				changeLecture[i] = 20;
			}else if(subject.get(i).equals("패턴인식")) {
				changeLecture[i] = 21;
			}else if(subject.get(i).equals("컴퓨터비젼시스템")) {
				changeLecture[i] = 22;
			}else if(subject.get(i).equals("영상처리프로그래밍")) {
				changeLecture[i] = 23;
			}else if(subject.get(i).equals("모바일프로그래밍")) {
				changeLecture[i] = 24;
			}else if(subject.get(i).equals("컴퓨터네트워크")) {
				changeLecture[i] = 25;
			}else if(subject.get(i).equals("확률통계및프로그래밍")) {
				changeLecture[i] = 26;
			}else if(subject.get(i).equals("통신시스템")) {
				changeLecture[i] = 27;
			}else if(subject.get(i).equals("임베디드시스템")) {
				changeLecture[i] = 28;
			}else if(subject.get(i).equals("네트워크프로그래밍")) {
				changeLecture[i] = 29;
			}else if(subject.get(i).equals("정보보호개론")) {
				changeLecture[i] = 30;
			}else if(subject.get(i).equals("데이터통신")) {
				changeLecture[i] = 31;
			}else if(subject.get(i).equals("무선통신")) {
				changeLecture[i] = 32;
			}else if(subject.get(i).equals("스마트그리드")) {
				changeLecture[i] = 33;
			}else if(subject.get(i).equals("인터넷보안")) {
				changeLecture[i] = 34;
			}else if(subject.get(i).equals("지능형시스템")) {
				changeLecture[i] = 35;
			}else if(subject.get(i).equals("멀티코어프로그래밍")) {
				changeLecture[i] = 36;
			}else if(subject.get(i).equals("디지털시스템")) {
				changeLecture[i] = 37;
			}else if(subject.get(i).equals("마이크로컴퓨터")) {
				changeLecture[i] = 38;
			}else if(subject.get(i).equals("VHDL프로그래밍")) {
				changeLecture[i] = 39;
			}else if(subject.get(i).equals("데이터베이스")) {
				changeLecture[i] = 40;
			}else if(subject.get(i).equals("프로그래밍언어의개념")) {
				changeLecture[i] = 41;
			}else if(subject.get(i).equals("소프트웨어공학")) {
				changeLecture[i] = 42;
			}else if(subject.get(i).equals("컴파일러")) {
				changeLecture[i] = 43;
			}else if(subject.get(i).equals("시스템모델링")) {
				changeLecture[i] = 44;
			}else if(subject.get(i).equals("분산시스템")) {
				changeLecture[i] = 45;
			}else if(subject.get(i).equals("UNIX프로그래밍")) {
				changeLecture[i] = 46;
			}else if(subject.get(i).equals("문제해결기법")) {
				changeLecture[i] = 47;
			}else if(subject.get(i).equals("인공지능")) {
				changeLecture[i] = 48;
			}else if(subject.get(i).equals("데이터베이스프로그래밍")) {
				changeLecture[i] = 49;
			}else if(subject.get(i).equals("데이터분석개론")) {
				changeLecture[i] = 50;
			}else if(subject.get(i).equals("기계학습")) {
				changeLecture[i] = 51;
			}else if(subject.get(i).equals("컴퓨터비젼시스템")) {
				changeLecture[i] = 52;
			}else if(subject.get(i).equals("게임프로그래밍")) {
				changeLecture[i] = 54;
			}else if(subject.get(i).equals("디지털사운드")) {
				changeLecture[i] = 55;
			}else if(subject.get(i).equals("컴퓨터애니메이션")) {
				changeLecture[i] = 56;
			}else if(subject.get(i).equals("증강현실")) {
				changeLecture[i] = 57;
			}else if(subject.get(i).equals("어셈블리어")) {
				changeLecture[i] = 58;
			}else if(subject.get(i).equals("보안프로그래밍")) {
				changeLecture[i] = 59;
			}else if(subject.get(i).equals("대칭키암호론")) {
				changeLecture[i] = 60;
			}else if(subject.get(i).equals("공개키암호론")) {
				changeLecture[i] = 61;
			}else if(subject.get(i).equals("시스템해킹과보안")) {
				changeLecture[i] = 62;
			}else if(subject.get(i).equals("인터넷보안")) {
				changeLecture[i] = 63;
			}else if(subject.get(i).equals("악성코드분석")) {
				changeLecture[i] = 64;
			}else if(subject.get(i).equals("네트워크해킹과보안")) {
				changeLecture[i] = 65;
			}else if(subject.get(i).equals("디지털포렌식")) {
				changeLecture[i] = 66;
			}else if(subject.get(i).equals("정보보호와보안의기초")) {
				changeLecture[i] = 67;
			}else if(subject.get(i).equals("데이터베이스및보안")) {
				changeLecture[i] = 68;
			}else if(subject.get(i).equals("사이버전개론")) {
				changeLecture[i] = 69;
			}else if(subject.get(i).equals("데이터기반인공지능")) {
				changeLecture[i] = 70;
			}else if(subject.get(i).equals("경영과학")) {
				changeLecture[i] = 71;
			}else if(subject.get(i).equals("데이터시각화")) {
				changeLecture[i] = 72;
			}else if(subject.get(i).equals("대용량데이터처리개론")) {
				changeLecture[i] = 73;
			}else if(subject.get(i).equals("텍스트마이닝")) {
				changeLecture[i] = 74;
			}else if(subject.get(i).equals("의사결정")) {
				changeLecture[i] = 75;
			}else if(subject.get(i).equals("문제해결및실습:C++")) {
				changeLecture[i] = 76;
			}else if(subject.get(i).equals("문제해결및실습:JAVA")) {
				changeLecture[i] = 77;
			}else if(subject.get(i).equals("오픈소스SW")) {
				changeLecture[i] = 78;
			}else if(subject.get(i).equals("객체지향설계기술")) {
				changeLecture[i] = 79;
			}else if(subject.get(i).equals("SW교육특강1")) {
				changeLecture[i] = 80;
			}else if(subject.get(i).equals("SW교육특강2")) {
				changeLecture[i] = 81;
			}else if(subject.get(i).equals("오픈소스SW공학")) {
				changeLecture[i] = 82;
			}else if(subject.get(i).equals("오픈소스SW설계")) {
				changeLecture[i] = 83;
			}else {
				changeLecture[i] = 0;
			}
		}//for
		
		for (int j = 0; j < changeLecture.length; j++) {
			if(dbcon.registUserSubject(stnum, changeLecture[j])) {
				System.out.println("getting in [ " + changeLecture[j] + " ] ");
			} // if
			else {
				System.out.println("Changing Error!");
			}
		} // for
	}
}
