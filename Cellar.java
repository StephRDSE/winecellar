package winecellar;

public class Cellar {

	private Long cellarId;
	private Long wineId;
	private int bottleNb;

	public Cellar(Long cellarId, Long wineId, int bottleNb) {
		this.cellarId = cellarId;
		this.wineId = wineId;
		this.bottleNb = bottleNb;
	}

	public Cellar(Long wineId, int bottleNb) {
		this.wineId = wineId;
		this.bottleNb = bottleNb;
	}

	public Long getCellarId() {
		return cellarId;
	}

	public void setCellarId(Long cellarId) {
		this.cellarId = cellarId;
	}

	public Long getWineId() {
		return wineId;
	}

	public void setWineId(Long wineId) {
		this.wineId = wineId;
	}

	public int getBottleNb() {
		return bottleNb;
	}

	public void setBottleNb(int bottleNb) {
		this.bottleNb = bottleNb;
	}

	@Override
	public String toString() {
		return "Cellar [cellarId=" + cellarId + ", wineId=" + wineId + ", bottleNb=" + bottleNb + "]";
	}
	
	
}
