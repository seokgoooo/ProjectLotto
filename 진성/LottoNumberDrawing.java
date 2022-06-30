package 진성;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.omg.PortableInterceptor.INACTIVE;

public class LottoNumberDrawing {
	private Set<Integer> set;// Set 배열 필드
	private Integer bonus; // 보너스 필드
	private Random random = new Random(); // 랜덤객체 필드
	
	public LottoNumberDrawing() { // 생성자 : 필드를 재설정할수있음.
		this.set = new HashSet<>();

	}
	@Override
	public String toString() { // 출력모양 toString
		return set + " 추가번호  " + bonus + "]";
	}

	public Set<Integer> getSet() { //Set배열 게터
		return set;
	}

	public void setSet(Set<Integer> set) { //Set배열 세터
		this.set = set;
	}

	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public LottoNumberDrawing(Set<Integer> set, Integer bonus) {
		super();
		this.set = set;
		this.bonus = bonus;
	}

	public Set<Integer> LottoRandom () {
		set = new HashSet<>();
		while (set.size() < 6) { // 세트사이즈만큼
			set.add(random.nextInt(45) + 1);// 세트에 랜덤 추가 // 이제 객체생성해서 출력하면 바로 첫번째 로또번호 추가.

		}

//		while (true) {
//			bonus = (random.nextInt(45) + 1);
//			if (!set.contains(bonus)) {
//				break;
//			}
//		}
		return set;
		
	}
	
	public Integer bonusNumber(Set<Integer> set) {
		int bonus = 0;
		while (true) {
			bonus = (random.nextInt(45) + 1);
			if (!set.contains(bonus)) {
				break;
			}
		}
		return bonus;
	}
	
	public void LottoRound() {
		Map<String, Set<Integer>> map = new HashMap<>(); // lotto 번호를 담을 map 배열생성. 스트링으로바꾸면된다? // 링크드해쉬맵이순서대로 -> 해쉬맵으로 key는 스트링타입  value는 LottoNumberDrawing타입
		int[] bonus = new int[100];
		// Map배열<왼쪽 String 오른쪽 ??) //Set 배열 담기.
		for(int i = 0; i < 100; i++) { // 반복문 키도 됨.. value값만.제대로..
//			LottoNumberDrawing lnd = new LottoNumberDrawing();// 객체생성 
			Set<Integer> set = LottoRandom();
			bonus[i] = bonusNumber(set);
			map.put(String.valueOf(i), set); // 아이가 추가될떄마다 set도 새로운 랜덤번호가 추가되야함.i,
		}
		
		for(int i = 0;i < map.size();i++) { // 출력 맵사이즈만큼 출력 반복문 100 개겟죠? //반복 현재됨.
			System.out.println((i + 1) + "회차" + map.get(String.valueOf(i)) + "보너스 번호" + bonus[i]); // i는 o이니 +1시키고 + 회차 그리고  map.get.i키값을 불러와라 불러오면 value도따라온다.
		}
		return;
	}


	public static void main(String[] args) {

//		LottoNumberDrawing lotto1 = new LottoNumberDrawing(); // LottoNumberDrawing 첫번째 로또객체생성.

//		System.out.println(lotto1);

		
		LottoNumberDrawing ld = new LottoNumberDrawing();
		ld.LottoRandom();
		ld.LottoRound();
//		System.out.println(ld);
		
		



		
	}

}
