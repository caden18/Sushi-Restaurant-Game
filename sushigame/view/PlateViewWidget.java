package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import comp401sushi.Plate;
import sushigame.model.Belt;

public class PlateViewWidget {
	
	private JButton button;
	private Plate plate;
	private Belt belt;
	private int position;
	
	public PlateViewWidget (Plate plate, Belt belt, int position) {
		this.plate = plate;
		this.belt = belt;
		this.position = position;
		
		button = new JButton();
		button.setMinimumSize(new Dimension(300, 20));
		button.setPreferredSize(new Dimension(300, 20));
		button.setOpaque(true);
		button.setBackground(Color.GRAY);
		button.setBorderPainted(false);
		
		
	}
	
	public Plate getPlate() {
		return this.plate;
	}
	
	public Belt getBelt() {
		return this.belt;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public JButton getButton() {
		return this.button;
	}
	
	public void update(Plate plate, int position) {
		this.plate = plate;
		this.position = position;
		
		String s = "";
		
		if (this.plate != null) {
			s = this.getPlate().getContents().getName() + " by " + this.getPlate().getChef().getName() + " | Age: " + this.getBelt().getAgeOfPlateAtPosition(this.position);
			
			switch (this.getPlate().getColor()) {
			case RED:
				button.setBackground(Color.RED); break;
			case GREEN:
				button.setBackground(Color.GREEN); break;
			case BLUE:
				button.setBackground(Color.BLUE); break;
			case GOLD:
				button.setBackground(Color.YELLOW); break;
			}
			 } else {
				button.setBackground(Color.GRAY);
		}
		button.setText(s);
	}
	

}
