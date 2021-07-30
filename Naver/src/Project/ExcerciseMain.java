package Project;

import java.util.Scanner;

public class ExcerciseMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EMember mem = new EMember();
		ExcerciseSQL esql = new ExcerciseSQL();
		boolean run = true; // 첫번째 do while문 값
		boolean run2 = true; // 두번째 do while문 값
		boolean run3 = true; // 세번째 do while문 값
		boolean a = true;

		int menu = 0; // 첫번째 switch문 메뉴값
		int menu1 = 0; // 두번째 switch문 메뉴값
		int menu2; // 세번째 switch문 메뉴값

		do {
			System.out.println("                        ▶     환영합니다! ◀\n");
			System.out.println("*** Daily Exercise Trainner ***");
			
			System.out.println("    1. 회원가입  2. 로그인  3.종료        ");
			System.out.println("                             ");
			System.out.print  ("       메뉴 선택 : ");
			menu = sc.nextInt();
			System.out.println();
			System.out.println("*******************************");
			System.out.println();System.out.println();

			switch (menu) {

			case 1: // 회원가입

				esql.connect();
				System.out.println("**회원 가입을 시작합니다**\n");
				boolean run1 = true; // id가 중복됐는지 여부 판단하기 위한 boolean 값
				String ID = null;
				while (run1) {
					System.out.print(" ID를 입력해주세요 : ");
					ID = sc.next();
					run1 = esql.Check(ID);
				}
				System.out.print(" 패스워드를 입력해주세요 : ");
				String PW = sc.next();

				System.out.print(" 이름을 입력해주세요 : ");
				String name = sc.next();

				System.out.print(" 나이를 입력해주세요 : ");
				int age = sc.nextInt();

				System.out.print(" 키를 입력해주세요 : ");
				int hight = sc.nextInt();

				System.out.print(" 몸무게를 입력해주세요 : ");
				int weight = sc.nextInt();

				mem.setId(ID);
				mem.setPw(PW);
				mem.setName(name);
				mem.setAge(age);
				mem.setHight(hight);
				mem.setWeight(weight);

				esql.memberJoin(mem);
				System.out.println();
				System.out.println(mem);
				System.out.println();
				
				break;

			case 2: // 로그인

				esql.connect();
				System.out.print("아이디를 입력하세요 : ");
				ID = sc.next();
				System.out.print("패스워드를 입력하세요 : ");
				PW = sc.next();
				System.out.println();
				System.out.println("확인중");
				System.out.println(".");
				System.out.println(".");
				System.out.println(".");

				boolean check1 = esql.checkInfo(ID, PW);
				if (check1) {
					System.out.println(ID+"님 반갑습니다!\n");
					do {
						
						System.out.println("***********Personal Trainner************");
						System.out.println("   1. 운동종목 조회  2. 운동선택    3. 운동종목 추가   ");
						System.out.println("   4. 운동기록 조회  5. 정보 수정   6. 로그아웃         ");
						System.out.println("                                       ");
						System.out.println("                                       ");
						System.out.print  ("          메뉴 선택 : ");
						menu1 = sc.nextInt();
						System.out.println();
						System.out.println("****************************************\n");
						
						sc.nextLine();
						switch (menu1) {
						case 1: // 운동종목 조회

							esql.choice();
							break;

						case 2: // 운동선택
							
							esql.onlyName();
							System.out.println("┏                            ┓");
							System.out.print  ("  운동을 선택하세요 : ");
							String ex = sc.nextLine();
							System.out.println("┖                            ┛ ");
							
							esql.input(ex, ID);
							break;

						case 3: // 운동종목 추가

							esql.addr();
							break;

						case 4: // 운동기록 조회
							do {
								
								System.out.println("********** Search ***********");
								System.out.println("  1. 전체 조회  2. 부분 조회  3. 종료    ");
								System.out.print  ("    선택 : ");
								menu2 = sc.nextInt();
								System.out.println("*****************************\n");
								
								sc.nextLine();

								switch (menu2) {
								case 1:
									esql.allSearch(ID);
									break;
								case 2:
									do {
										System.out.println("=======================================");
										System.out.println();
										System.out.print ("조회하실 운동을 선택해 주세요!  ");
										System.out.println();
										System.out.println();
										esql.onlyName();
										System.out.println();
										System.out.println("※ 숫자가 아닌 운동 이름을 적어주세요");
										System.out.print("(나가시려면 종료를 입력해주세요.) : ");
										String exname = sc.nextLine();
										System.out.println();

										
										System.out.println();

										

										boolean b = esql.finalSearch(exname);

										if (b) {
											esql.partSearch(exname);
										} else {
											if (exname.equals("종료")) {
												System.out.println("부분조회를 종료합니다.\n");
												a = false;
											} else {
												System.out.println("운동이름이 없습니다.\n");
												a = true;
											}
										}
									} while (a);
									break;

								case 3:
									System.out.println();
									System.out.println("조회를 종료합니다.");
									System.out.println();
									run3 = false;
								}
							} while (run3);

							break;

						case 5: // 회원 수정

							System.out.println("아이디 비밀번호 입력");

							System.out.print(" 아이디 >> ");
							String id = sc.next();

							System.out.print(" 비밀번호 >> ");
							String pw = sc.next();

							boolean check2 = esql.checkInfo(id, pw);

							if (check2) {
								System.out.println("  "+id + "님의 회원 정보 수정");

								System.out.print("   수정할 비밀번호 >> ");
								String Pw = sc.next();

								System.out.print("   수정할 이름 >> ");
								String Name = sc.next();

								System.out.print("   수정할 나이 >> ");
								int Age = sc.nextInt();

								System.out.print("   수정할 키(cm) >> ");
								int Hight = sc.nextInt();

								System.out.print("   수정할 몸무게(kg) >> ");
								int Weight = sc.nextInt();

								mem.setId(id);
								mem.setPw(Pw);
								mem.setName(Name);
								mem.setAge(Age);
								mem.setHight(Hight);
								mem.setWeight(Weight);

								esql.modify(mem);

								System.out.println("수정한 회원 정보 확인\n");
								System.out.println(mem);
							} else {
								System.out.println("아이디와 비빌번호가 일치하지 않습니다.\n");
							}
							break;

						case 6: // 로그아웃

							System.out.println("======로그아웃======");
							System.out.println("  수고하셨습니다!");
							
							System.out.println();System.out.println();
							run2 = false;
							break;

						default:
							System.out.println("다시 입력해주세요.");
							break;
						}

					} while (run2);
				}
				break;

			case 3: // 종료
				esql.conClose();
				System.out.println();
				System.out.println("종료!");
				run = false;
				break;
			default:
				System.out.println("다시 입력해주세요.");
				break;

			}

		} while (run);
	}
}

