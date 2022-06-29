import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LottoNumberDrawing {
	private static Set<Integer> set;// 필드
	private int bonus; // 보너스 필드
	static Random random = new Random(); // 랜덤객체 필드

	@Override
	public String toString() { // 출력모양 toString
		return set + ", bonus=" + bonus + "]";
	}

	public Set<Integer> getSet() { //
		return set;
	}

	public void setSet(Set<Integer> set) {
		LottoNumberDrawing.set = set;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public LottoNumberDrawing() { // 생성자
		this.set = new HashSet<>();
		while (set.size() < 6) { // 세트사이즈만큼
			set.add(random.nextInt(45) + 1);// 세트에 랜덤 추가 // 이제 객체생성해서 출력하면 바로 첫번째 로또번호 추가.
		}
	}

	public static void main(String[] args) {
		LottoNumberDrawing lotto1 = new LottoNumberDrawing(); // LottoNumberDrawing 첫번째 로또객체생성.
		LottoNumberDrawing lotto2 = new LottoNumberDrawing(); // ? 왜안됨 메소드에서 배열생성 반복문을 써야함....
		LottoNumberDrawing lotto3 = new LottoNumberDrawing();
		LottoNumberDrawing lotto4 = new LottoNumberDrawing();
		LottoNumberDrawing lotto5 = new LottoNumberDrawing();

		System.out.println(lotto1);
		System.out.println(lotto2);
		System.out.println(lotto3);
		System.out.println(lotto4);
		System.out.println(lotto5);
		

	}
}
