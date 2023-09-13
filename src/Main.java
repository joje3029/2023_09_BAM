import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {

      System.out.println("== 프로그램 시작 ==");

      Scanner sc = new Scanner(System.in);
      int lastArticleId = 0; //글 번호
      

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
            int view = 0;
            System.out.printf("제목 : ");
            String title = sc.nextLine();
            System.out.printf("내용 : ");
            String body = sc.nextLine();
         
         // 현재 날짜 구하기
            LocalDate now = LocalDate.now();
     
            // 포맷 정의
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
     
            // 포맷 적용
            String formatedNow = now.format(formatter);
            
            Article article = new Article(id, title, body, formatedNow, view);
            
            articles.add(article);
            

            System.out.printf("%d번글이 생성되었습니다.\n", id);
            lastArticleId++;
            
            
         } else if(command.startsWith("article detail")){ //detail일 때
        	 //2.조회수 기능은 클릭되었을때 즉 detail을 입력하면 증가하고 증가한게 보여야 함.
        	 //조회수가 보이게도 해야하고 클릭했을때 그 변수가 증가해야겠네. 0으로 생성은 글 쓰고 저장할때 되야겠다.
        	 //그럼 같이 객체로 넣는게 낫겠다.
        	 //조회수 변수명 : views
        	 
        	 String[] str= command.split(" "); 
             String num =str[2];
             int id =Integer.parseInt(num);
             
             boolean found = false;
             
            for(int i=0; i<articles.size(); i++) {
            	Article article = articles.get(i);
            		if(article.id == id) {
            			found = true;
            			article.view++;
                        System.out.printf("번호 : %d\n", article.id );
                        System.out.printf("날짜 : %s\n", article.formatedNow); 
                        System.out.printf("제목 : %s\n", article.title);
                        System.out.printf("내용 : %s\n", article.body);
                        System.out.printf("조회수 : %d\n", article.view);
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
             			
             			articles.remove(i);
             			
                    	System.out.printf("%d번 게시글이 없어졌습니다.\n",id);
                         break;

             		}
             		
        		}
             if (found == false) {
            	 System.out.printf("%d번 게시물은 없어\n", id);
             }
           
         }else if(command.startsWith("article modify")){// 1. article modify로 명령문 : 게시글 수정
//        	 게시글 수정은 몇번 글인지 선택을 해야하니 detail/ delete랑 비슷
        	 String[] str= command.split(" "); 
             String num =str[2];
             int id =Integer.parseInt(num);
             //여기까지 modify 뒤의 번호를 id라는 변수에 저장한것
             boolean found = false; //일단 못찾았을때
             
             for(int i=0; i<articles.size(); i++) { // 순회
             	Article article = articles.get(i); 
             		if(article.id == id) { // 찾으려는 게시물 있는 경우
             			found = true; //있으니까 true로 변경
             			//새로 받을 제목이랑 내용
             			System.out.printf("제목 : "); 
                        String title = sc.nextLine();
                        System.out.printf("내용 : ");
                        String body = sc.nextLine();
                     
                        //여기서 부터 수정되야겠네
                        //아이디가 맞는 걸 찾았으니 그 인덱스에 들어있는 title이랑 body에 담긴걸 새로 입력한 title과 body로 변경하면 되는거잖아.
                        article.title = title;
                        article.body = body;
                        
                        System.out.printf("%d번글이 수정되었습니다.\n", id);
                        
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
	int view;
	String formatedNow;
    int id;
    String title;
    String body;
   
   Article(int id, String title, String body, String formatedNow, int view){
	  this.view = view;
      this.id = id;
      this.title = title;
      this.body = body;
      this.formatedNow = formatedNow;
   }   
}