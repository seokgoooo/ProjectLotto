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
//	private Map<String, Set<Integer>> map;
	
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

	public Set<Integer> LottoRandom () { // 1등  메소드
		set = new HashSet<>();
		bonus = (random.nextInt(45) + 1);
		while (set.size() < 6) { // 세트사이즈만큼
			set.add(random.nextInt(45) + 1);// 세트에 랜덤 추가 // 이제 객체생성해서 출력하면 바로 첫번째 로또번호 추가.		
		}

		return set;
		
	}
	
	public Integer bonusNumber(Set<Integer> set) { // 보너스넘버 메소드 // 역대번호 메소드에 보너스 번호넣기위해
		while (true) {
			bonus = (random.nextInt(45) + 1);
			if (!set.contains(bonus)) {
				break;
			}
		}
		return bonus;
	}
	public void printLottoRandom(Set<Integer> set) {
		System.out.println("당첨번호" + set + "보너스 번호" + bonus);
	}
	
	public Map<String, Set<Integer>> LottoRound() { // 역대번호 메소드
		Map<String, Set<Integer>>map = new HashMap<>();
		int[] bonus = new int[100];
		// Map배열<왼쪽 String 오른쪽 ??) //Set 배열 담기.
		for(int i = 0; i < 100; i++) { // 반복문 키도 됨.. value값만.제대로..
			Set<Integer> set = LottoRandom(); // 반복될떄마다 새로운 1등당첨메소드 생성.
			bonus[i] = bonusNumber(set);
			map.put(String.valueOf(i), set); // 아이가 추가될떄마다 set도 새로운 랜덤번호가 추가되야함.i,
		}
		
//		for(int i = 0;i < map.size();i++) { // 출력 맵사이즈만큼 출력 반복문 100 개겟죠? //반복 현재됨.
//			System.out.println((i + 1) + "회차" + map.get(String.valueOf(i)) + "보너스 번호" + bonus[i]); // i는 o이니 +1시키고 + 회차 그리고  map.get.i키값을 불러와라 불러오면 value도따라온다.
//		}
		
		return map;
		
	}
	
	public void printMap(Map<String, Set<Integer>> map) { // 역대번호 출력 메소드?
		
		for(int i = 0; i < map.size(); i++) {
			System.out.println((i + 1) + "회차" + map.get(String.valueOf(i)) + "보너스번호" + bonusNumber(set));
		}
	}


	public static void main(String[] args) {

//		LottoNumberDrawing lotto1 = new LottoNumberDrawing(); // LottoNumberDrawing 첫번째 로또객체생성.

//		System.out.println(lotto1);

		
		LottoNumberDrawing ld = new LottoNumberDrawing();

		ld.printLottoRandom(ld.LottoRandom());// 1등메소드출력
		ld.printMap(ld.LottoRound());
		
		



		
	}

}
