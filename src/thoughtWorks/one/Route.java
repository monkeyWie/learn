package thoughtWorks.one;

import thoughtWorks.one.Trains.Town;

public class Route {
	private Town from;
	private Town to;
	private int distance;
	
	public Route() {
	}

	public Route(Town from, Town to, int distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}
	
	public Town getFrom() {
		return from;
	}
	public void setFrom(Town from) {
		this.from = from;
	}
	public Town getTo() {
		return to;
	}
	public void setTo(Town to) {
		this.to = to;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	/**
	 * 检查是否为参数中的方向
	 * @return
	 * */
	public boolean checkDirection(Town from,Town to){
		if(this.from==from&&this.to==to){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.from.toString()+this.to.toString();
	}
}
