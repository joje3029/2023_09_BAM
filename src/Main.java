import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Article> articles = new ArrayList<Article>();

	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 3;

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

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
				} else {
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

			} else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}

	// TODO : 어떻게 통합하지??? : 해봄 됨.
	//1. 함수를 새로 만든다
	//2.한놈한테 반복되는 구간일을 몰아준다. : 난 이게 좋아 이걸로 시도함.
	
	//
	
	private static int getArticleIndexById(int id) {// 아티클의 인덱스를 구하는 함수

		for (int i = 0; i < articles.size(); i++) {// 저장소 순회
			Article article = articles.get(i); //하나씩 까서 article에 담음
			if (article.id == id) { // article 에 담았는데 그게 id랑 같으면
				return i; //인덱스를 리턴 //아래와의 유일한 차이
			}
		}
		
		
		
		return -1; //못찾으면 return -1
	}

	private static Article getArticleById(int id) {// 아티클의 아이디 번호를 구하는 함수

//		for (int i = 0; i < articles.size(); i++) {// 저장소 순회
//			Article article = articles.get(i);//하나씩 까서 article에 담음
//			if (article.id == id) {// article 에 담았는데 그게 id랑 같으면
//				return article; // 아티클을 반환
//			}
//		}
		
//		주석처리한 부분을 getArticleIndexById함수로 외주를 주자.
		int index = getArticleIndexById(id); // getArticleIndexById로부터 인덱스를 얻습니다.

	    if (index != -1) {
	        return articles.get(index); // 아티클을 반환합니다.
	    }
		
//		if(getArticleIndexById(id)!=-1) {
//			return i.id;
//		}
		

		return null;// 못찾으면 retrun null
	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터 3개 생성 완료");
		articles.add(new Article(1, Util.getNow(), Util.getNow(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNow(), Util.getNow(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNow(), Util.getNow(), "제목3", "내용3", 33));
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