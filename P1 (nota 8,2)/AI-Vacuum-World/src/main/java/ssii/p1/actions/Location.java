/*Custom Vacuum World Example 
Copyright (C) 2015  Jorge J. Gomez-Sanz

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/	

package ssii.p1.actions;

public class Location {

	private int x;
	private int y;	
	
	public Location(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public Location clone(){
		return new Location(x,y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString(){
		return "("+x+","+y+")";
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Location && obj!=null){
			return ((Location)obj).getX()==x && ((Location)obj).getY()==y;
		}
		return false;
	}
	
}
