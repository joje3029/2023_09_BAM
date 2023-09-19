import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Article> articles = new ArrayList<Article>();
	static List<Member> memebers = new ArrayList<Member>();

	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		makeTestData();
		makeTestMember();
		

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 3;
		int memberId =3; //테스트데이터로 3명이 있으니까.

		while (true) {

			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("너 명령어 입력 안했어");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}
			
			if (command.startsWith("article list")) {// contains로 했을때 기존처럼 일 잘함.
				if (articles.size() == 0) {// 저장소에 아무것도 없을때
					System.out.println("게시글이 없습니다");
				} else {// 있을 때
					//검색어 모양이 article list ~ 이니까 잘라야겠지
					String[] commandDiv = command.split(" ");
					
					//1. 그냥 보여줄래? -> string index가 1까지
					//2. 포함된거 보여줄래? -> string index가 2까지
					
					if(commandDiv.length !=2) {
						String num=commandDiv[2];
						//저장소를 순회해서 num이 들어가있는 제목은 출력
						for(int i = articles.size() - 1; i >= 0; i--) {
							Article article = articles.get(i);
							String title=article.title;
							if(title.contains(num)){
								System.out.printf(" %4d     /   %5s    /      %4d  \n", article.id, article.title, article.hit);
							}
						
						}
					continue;	
					}
					
					
					System.out.println("번호      /    제목     /    조회   ");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf(" %4d     /   %5s    /      %4d  \n", article.id, article.title, article.hit);
					}
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				String regDate = Util.getNow();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, regDate, title, body);
				articles.add(article);

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastArticleId++;
			} else if (command.startsWith("article detail")) {

				String[] commandDiv = command.split(" "); // article detail 1

				int id = Integer.parseInt(commandDiv[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 없어\n", id);
					continue;
				}

				foundArticle.hit++;

				System.out.println("번호 : " + foundArticle.id);
				System.out.println("작성날짜 : " + foundArticle.regDate);
				System.out.println("수정날짜 : " + foundArticle.updateDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("조회수 : " + foundArticle.hit);

			} else if (command.startsWith("article modify")) {

				String[] commandDiv = command.split(" ");

				int id = Integer.parseInt(commandDiv[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 없어\n", id);
					continue;
				}

				System.out.printf("제목 : ");
				String newTitle = sc.nextLine();
				System.out.printf("내용 : ");
				String newBody = sc.nextLine();

				String updateDate = Util.getNow();
				foundArticle.title = newTitle;
				foundArticle.body = newBody;
				foundArticle.updateDate = updateDate;

			} else if (command.startsWith("article delete")) {

				String[] commandDiv = command.split(" "); // article delete 1

				int id = Integer.parseInt(commandDiv[2]);

				int foundIndex = getArticleIndexById(id);

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 없어\n", id);
					continue;
				}

				articles.remove(foundIndex);
				System.out.println(id + "번 글을 삭제했어");
				
//			여기까지 글 관련
//			여기서부터 회원가입
			}else if(command.equals("member join")) {
				
//				우선 입력 받는데가 있어야겠지? -> write 응용
				int id=memberId+1;
				String memberRegDate = Util.getNow();
				String loginId =null;
				
				while(true) {
					System.out.printf("아이디 : ");
					loginId = sc.nextLine();
					
//				아이디가 같은지 체크 = members.loginId가 같아야하는거 => 있는지 없는지 유무만 확인
					if(loginId.length()==0) {
						System.out.println("아이디 입력해");
						continue;
					}
					
					int foundLoginId=getMemberById(loginId);
					
					if(foundLoginId == 1) {
						System.out.println("이미 사용중인 아이디입니다.");
						continue;
					}
					break;
				}
				
				String name =null;
				String lginPw =null;
				while(true) {
					System.out.printf("비밀번호: ");
					lginPw = sc.nextLine();
					System.out.printf("비밀번호 확인 : ");
					String checkLoginPw = sc.nextLine();
					
					//비밀번호랑 비밀번호 확인이 같으면 넘기고 아니면 안같다고 하고 명령어로 올리기
					if(!lginPw.equals(checkLoginPw)){
						System.out.println("비밀번호가 다릅니다.");
						continue;
					}
					System.out.printf("회원 이름: ");
					name = sc.nextLine();
					if(name.length()==0) {
						System.out.println("아이디 입력해");
						continue;
					}
					
					
					break;
				}
				
				
				
				
				//하나로 조립해서 member 저장소에 저장 
				//1.저장소 만들기
				//2.조립할 class(설계도 만들기)
				//3.조립할 부분 수정하기
				Member member = new Member(id, memberRegDate, loginId, lginPw,name);
				memebers.add(member);

				System.out.println("회원가입되었습니다.");
				memberId++;
			} else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}

// member 순회
	private static int getMemberById(String loginId) {
		for (int i = 0; i < memebers.size(); i++) {// 저장소 순회
			Member member = memebers.get(i); //하나씩 까서 article에 담음
			if (member.loginId.equals(loginId)) { // article 에 담았는데 그게 id랑 같으면
				return 1; //인덱스를 리턴 //아래와의 유일한 차이
			}
		}
		return -1;
	}
	


	
// article 순회
	private static int getArticleIndexById(int id) {// 아티클의 인덱스를 구하는 함수 //위치

		for (int i = 0; i < articles.size(); i++) {// 저장소 순회
			Article article = articles.get(i); //하나씩 까서 article에 담음
			if (article.id == id) { // article 에 담았는데 그게 id랑 같으면
				return i; //인덱스를 리턴 //아래와의 유일한 차이
			}
		}
		
		return -1; //못찾으면 return -1
	}

	private static Article getArticleById(int id) {// 아티클의 아이디 번호를 구하는 함수 //번호
		
//		주석처리한 부분을 getArticleIndexById함수로 외주를 주자.
		int index = getArticleIndexById(id); // getArticleIndexById로부터 인덱스를 얻습니다.

	    if (index != -1) {
	        return articles.get(index); // 아티클을 반환합니다.
	    }
		
		

		return null;// 못찾으면 retrun null
	}

	
	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터 3개 생성 완료");
		articles.add(new Article(1, Util.getNow(), Util.getNow(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNow(), Util.getNow(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNow(), Util.getNow(), "제목3", "내용3", 33));
	}
	
	private static void makeTestMember() {
		System.out.println("테스트를 위한 회원 3개 생성 완료");
		memebers.add(new Member(1, Util.getNow(), "test1", "test1", "test1"));
		memebers.add(new Member(2, Util.getNow(), "test2", "test2", "test2"));
		memebers.add(new Member(3, Util.getNow(), "test3", "test3", "test3"));
		
	}
}

class Article {
	int id;
	String regDate;
	String updateDate;
	String title;
	String body;
	int hit;

	Article(int id, String regDate, String updateDate, String title, String body) {
		this(id, regDate, updateDate, title, body, 0);
	}

	Article(int id, String regDate, String updateDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}
}

class Member{
	int id;
	String memberRegDate;
	String loginId;
	String lginPw;
	String name;
	
	public Member(int id, String memberRegDate, String loginId, String lginPw, String name) {
		this.id = id;
		this.memberRegDate = memberRegDate;
		this.loginId = loginId;
		this.lginPw = loginId;
		this.name = name;
	}
	
}
