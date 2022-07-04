import java.util.ArrayList;
import java.util.List;

public class Consumer {
	private List<List<Integer>> lottoList = new ArrayList<>();
	private Integer price = 0;

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price += price;
	}

	public List<List<Integer>> getLottoList() {
		return lottoList;
	}

	public void setLottoList(List<List<Integer>> lottoList) {
		this.lottoList.addAll(lottoList);
	}

}
