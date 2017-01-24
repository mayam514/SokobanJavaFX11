package model.data;

import java.io.Serializable;

public class Position implements Serializable{

	private static final long serialVersionUID = 1L;
	//Data members
	private int p_x;
	private int p_y;
	
	//Constructors
	public Position() {
		this.setP_x(0);
		this.setP_y(0);
	}
	
	public Position(int x, int y){
		this.setP_x(x);
		this.setP_y(y);
	}

	public Position(Position p){
		this.setP_x(p.getP_x());
		this.setP_y(p.getP_y());
	}
	
	//Get and set method
	public int getP_y() {
		return p_y;
	}

	public void setP_y(int p_y) {
		this.p_y = p_y;
	}

	public int getP_x() {
		return p_x;
	}

	public void setP_x(int p_x) {
		this.p_x = p_x;
	}
	
	//Override method
	@Override
	public boolean equals(Object other){
		if(other instanceof Position){
			if((this.getP_x()==((Position)other).getP_x())&&(this.getP_y()==((Position)other).getP_y()))
				return true;
		}
		return false;
	}
	
}
