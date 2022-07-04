import java.util.ArrayList;
import java.util.List;

public class Consumer {
//	private String name;
	private List<List<Integer>> lottoList = new ArrayList<>();
	private List<List<List<Integer>>> list = new ArrayList<>();
	private Integer count = 0;
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
		list.add(lottoList);
		this.lottoList = lottoList;
		count++;
	}

	public List<List<List<Integer>>> getList() {
		return list;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}