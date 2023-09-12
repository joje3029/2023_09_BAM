import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		ArrayList<String> articleListTitle = new ArrayList<>();
		ArrayList<String> articleListbody = new ArrayList<>();
//		저장할 arrayLIst 이용
		
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
				if(articleListbody.isEmpty() || articleListTitle.isEmpty()) {
					System.out.println("게시글이 없습니다");
				}else {
					System.out.println(" 번호 / 제목 ");
					for(int i = lastArticleId-1; i>=0;i--) {
						int num = i+1;
						System.out.println(num+"/"+articleListTitle.get(i));
						
					}
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				articleListTitle.add(title);
				articleListbody.add(body);
				

				System.out.printf("%d번글이 생성되었습니다.\n", id);
				lastArticleId++;
			} else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}