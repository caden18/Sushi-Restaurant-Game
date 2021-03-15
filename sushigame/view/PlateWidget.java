package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import comp401sushi.IngredientPortion;
import comp401sushi.Plate;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class PlateWidget extends JButton implements ActionListener, BeltObserver {
	
	private Plate plate;
	JOptionPane box;
	int age;
	
	public PlateWidget(Plate plate, int age) {
		
		this.plate = plate;
		this.age = age;
		setLayout(new BorderLayout());
		this.addActionListener(this);
		box = new JOptionPane();
		box.setSize(new Dimension(100, 100));
	}
	
	public void setPlate(Plate plate, int age) {
		this.plate = plate;
		this.age = age;
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (plate != null) {
			String name = "Name: " + plate.getContents().getName();
			String type = "Type: ";
			if (name.contains("nigiri")) {
				type += "nigiri";
			} else if (name.contains("Sashimi")) {
				type += "Sashimi";
			} else {
				type += "Roll";
			}
			String ageString = "Age: " + age;
			String chef = "Chef: " + plate.getChef().getName();
			String color = "Color: " + plate.getColor().name();
			IngredientPortion[] ings = plate.getContents().getIngredients();
			String ingString = "Ingredients: " + "\n";
			for (int j = 0; j < ings.length; j++) {
				ingString += " - " + ings[j].getName() + ": " + ings[j].getAmount() + " oz" + "\n";
				
			}
			JOptionPane.showMessageDialog(this, name + "\n" + type + "\n" + chef + "\n" + ageString + "\n" + color + "\n" + ingString);
			
			
		}
	}
}
