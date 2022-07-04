import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Lotto {
	private Set<Integer> set;// Set 배열 필드
	private Integer bonus; // 보너스 필드
	private Random random = new Random(); // 랜덤객체 필드

	public Lotto() { // 생성자 : 필드를 재설정할수있음.
		this.set = new HashSet<>();
	}

	public Lotto(Set<Integer> set, Integer bonus) {
		super();
		this.set = set;
		this.bonus = bonus;
	}

	public Set<Integer> getSet() { // Set배열 게터
		return set;
	}

	public void setSet() {
		while (set.size() < 6) { // 세트사이즈만큼
			set.add(random.nextInt(45) + 1);// 세트에 랜덤 추가 // 이제 객체생성해서 출력하면 바로 첫번째 로또번호 추가.
		}
	}

	public List<Integer> SetNum(Set<Integer> set) {
		List<Integer> num = new ArrayList<>();
		Iterator<Integer> next = set.iterator();
		int i = 0;
		while (next.hasNext()) {
			num.add(next.next());
			i++;
		}
		return num;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus() {
		while (true) {
			bonus = (random.nextInt(45) + 1);
			if (!set.contains(bonus)) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		Lotto js = new Lotto();
		js.setSet();
		List<Integer> sort = js.SetNum(js.getSet());
		Collections.sort(sort);
		System.out.println(sort.get(0));
		System.out.println(sort.get(1));
		System.out.println(sort.get(2));
		System.out.println(sort.get(3));
		System.out.println(sort.get(4));
		System.out.println(sort.get(5));

		js.setBonus();
		System.out.println("보너스 : " + js.getBonus());
	}
}