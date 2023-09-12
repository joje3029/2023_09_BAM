import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		ArrayList<Article> articles = new ArrayList<>();
//		ArrayList<String> articleListTitle = new ArrayList<>();
//		ArrayList<String> articleListbody = new ArrayList<>();
//		근데 굳이 array list를 두개 쓰고 싶지 않음. -> 객체화를 안해서 그럼 그래서 두개가 한번에 append되지 않은거고.
		
		while (true) {
			
			System.out.printf("명령어 ) ");
			String command = sc.nextLine();

			if (command.length() == 0) {
				System.out.println("너 명령어 입력 안했어");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			if (command.equals("article list")) {
				if(articles.isEmpty()) {
					System.out.println("게시글이 없습니다");
				}else {
					System.out.println(" 번호 / 제목 ");
					for(int i = lastArticleId-1; i>=0;i--) {
						int num = i+1;
						Article article = articles.get(i);
						System.out.printf(" %d /  %s \n",article.id, article.title);
						
					}
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				
				articles.add(article);
				

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastArticleId++;
				
			} else if(command.startsWith("article detail")){ //detail일 때
//				게시물이 있냐 없냐
				if(articles.isEmpty()){ //이건 그냥 게시물 자체가 없는 경우
					System.out.println("게시글이 없습니다");
					continue;
				}else { //게시글이 있을때
//					해당 번호 게시글이 있느냐 없느냐
				 String[] str= command.split(" "); //
				 String num =str[2];
				 int strnum =Integer.parseInt(num);
				 for(int i = 0; i <articles.size(); i++) {
					 if(i == strnum) {
						 Article artice= articles.get(i);
						 System.out.printf("번호 : %d", artice.id );
//						 System.out.printf("날짜 : "); 일단 날짜 빼 햇갈려
						 System.out.printf("제목 : %s", artice.title);
						 System.out.printf("내용 : %s", artice.body);
						 
						 break;
					 }
					 System.out.printf("%d번 게시글은 없음\n",strnum);
					 break;
				 }
				}
		
			}else if(command.startsWith("article delete")) { //delete 삭제일때
//				1. 게시물이 있냐 없냐
				
//				2. 있으면 게시물 삭제하고 
//				3. 출력 %d번 게시글을 삭제 했습니다.
				
				
			}else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
	
}

class Article{
	int id;
	String title;
	String body;
	
	Article(int id, String title, String body){
		this.id = id;
		this.title = title;
		this.body = body;
	}	
}