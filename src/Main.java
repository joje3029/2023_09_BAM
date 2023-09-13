import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {

      System.out.println("== 프로그램 시작 ==");

      Scanner sc = new Scanner(System.in);
      int lastArticleId = 0;

      ArrayList<Article> articles = new ArrayList<>();
//      ArrayList<String> articleListTitle = new ArrayList<>();
//      ArrayList<String> articleListbody = new ArrayList<>();
//      근데 굳이 array list를 두개 쓰고 싶지 않음. -> 객체화를 안해서 그럼 그래서 두개가 한번에 append되지 않은거고.
      
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
        		if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
				} else {
					System.out.println("번호   /    제목   ");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf(" %d     /   %s  \n", article.id, article.title);
					}
				}

         } else if (command.equals("article write")) {
            int id = lastArticleId + 1;
            System.out.printf("제목 : ");
            String title = sc.nextLine();
            System.out.printf("내용 : ");
            String body = sc.nextLine();
            
            //날짜만 데꼬오면 됨! 그럼 완성이야!!
//            LocalDateTime deteTime=LocalDateTime.now();
//            DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         // 현재 날짜 구하기
            LocalDate now = LocalDate.now();
     
            // 포맷 정의
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
     
            // 포맷 적용
            String formatedNow = now.format(formatter);
            
            Article article = new Article(id, title, body, formatedNow);
            
            articles.add(article);
            

            System.out.printf("%d번글이 생성되었습니다.\n", id);
            lastArticleId++;
            
            
         } else if(command.startsWith("article detail")){ //detail일 때
        	 String[] str= command.split(" "); 
             String num =str[2];
             int id =Integer.parseInt(num);
             
             boolean found = false;
             
            for(int i=0; i<articles.size(); i++) {
            	Article article = articles.get(i);
            		if(article.id == id) {
            			found = true;
                        System.out.printf("번호 : %d\n", article.id );
                        System.out.printf("날짜 : %s\n", article.formatedNow); 
                        System.out.printf("제목 : %s\n", article.title);
                        System.out.printf("내용 : %s\n", article.body);
                        break;

            		}
            	}
           
            if (found == false) {
				System.out.printf("%d번 게시물은 없어\n", id);
			}

      
         }else if(command.startsWith("article delete")) { //delete 삭제일때
        	 String[] str= command.split(" "); 
             String num =str[2];
             int id =Integer.parseInt(num);
             boolean found = false;
             
             for(int i=0; i<articles.size(); i++) {
             	Article article = articles.get(i);
             		if(article.id == id) {
             			found = true;
             			//remove에서 문제군!!
//             			어떤 문제인가! : article id로 지우면 java.lang.IndexOutOfBoundsException이 발생.
//             			그렇다고 무조건 인덱스로는 못해. 왜냐하면 1,2번글 쓰고 둘다 지우고 3번 글을 쓰면 얘가 인덱스 1이거든. 
//             			그럼 우선 id로 유무를 확인하고 나서 그 article.id의 인덱스 위치를 확인하는게 가능하면 그걸 지울수 있겠네! try!
//             			1. 일단 if에서 맞은 id의 i값(인덱스)가 출력되는 코드 만들기
             			articles.remove(i);
             			
                    	System.out.printf("%d번 게시글이 없어졌습니다.\n",id);
                         break;

             		}
             		
        		}
             if (found == false) {
            	 System.out.printf("%d번 게시물은 없어\n", id);
             }
             
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
	String formatedNow;
    int id;
    String title;
    String body;
   
   Article(int id, String title, String body, String formatedNow){
      this.id = id;
      this.title = title;
      this.body = body;
      this.formatedNow = formatedNow;
   }   
}