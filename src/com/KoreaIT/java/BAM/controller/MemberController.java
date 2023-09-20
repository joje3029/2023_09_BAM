package com.KoreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private String actionMethodName;
	private String command;

	int lastMemberId = 0;

	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}

	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			dologin();
			break;
		default:
			System.out.println("그런 세부기능은 없어");
			break;
		}
	}

	

	

	public void doJoin() {
		int id = lastMemberId + 1;
		String regDate = Util.getNow();
		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;
		String name = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (loginId.length() == 0) {
				System.out.println("아이디 입력해라");
				continue;
			} else if (isJoinableLoginId(loginId) == false) {
				System.out.println("이미 쓰는 아이디야");
				continue;
			}

			break;
		}

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			if (loginPw.length() == 0) {
				System.out.println("비밀번호 입력해라");
				continue;
			}
			while (true) {
				System.out.printf("로그인 비밀번호 확인 : ");
				loginPwConfirm = sc.nextLine();

				if (loginPwConfirm.length() == 0) {
					System.out.println("비밀번호 확인 입력해라");
					continue;
				}
				break;
			}

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호 확인해");
				continue;
			}
			break;
		}

		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine();

			if (name.length() == 0) {
				System.out.println("이름 입력해라");
				continue;
			}
			break;
		}

		Member member = new Member(id, regDate, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 가입되었습니다.\n", id);
		lastMemberId++;

	}
	
	private void dologin() {
//		int id = lastMemberId + 1;
		String regDate = Util.getNow();
		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;
		String name = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (loginId.length() == 0) {
				System.out.println("아이디 입력해라");
				continue;
			} else if (isJoinableLoginId(loginId) == true) {
				System.out.println("가입하지 않은 아이디");
				continue;
			}

			break;
		}
		

		while(true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			if(isfindLoginPw(loginPw)==-1) {
				System.out.println("비밀번호 일치 안함");
				continue;
			}
			break;
		}
		System.out.println("로그인되었습니다.");
			

	}
	

	private int isfindLoginPw(String loginPw) {	
		for(int i = 0; i<members.size();i++) {
			Member member = members.get(i);
			if(member.loginPw.equals(loginPw)){
				return i; 
				}
			}
		return -1;
		}
	
	private boolean isJoinableLoginId(String loginId) { // members의 logId를 찾으려는 것
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true; // 기존에 없어. 
		}

		return false; //기존에 있어
	}
	
	

	private int getMemberIndexByLoginId(String loginId) { // 순회는 얘가 함.
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
		}
		return -1;
	}
	
	public void makeMemeberTestData() {
		System.out.println("테스트를 위한 게시글 데이터 5개 생성 완료");
		members.add(new Member(1, Util.getNow(), Util.getNow(), "test1", "test1", "test1"));
		members.add(new Member(1, Util.getNow(), Util.getNow(), "test1", "test1", "test1"));
		members.add(new Member(1, Util.getNow(), Util.getNow(), "test1", "test1", "test1"));
	}

}