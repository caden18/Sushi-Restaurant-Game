package sushigame.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements ActionListener, BeltObserver {

	private Belt belt;
	private PlateViewWidget[] belt_labels;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		
		belt_labels = new PlateViewWidget[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			PlateViewWidget label = new PlateViewWidget(null, belt, i);
			label.getButton().addActionListener(this);
			add(label.getButton());
			belt_labels[i] = label;
		}
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			belt_labels[i].update(belt.getPlateAtPosition(i), i);

		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < belt.getSize(); i++) {
			if (e.getSource() == belt_labels[i].getButton()) {
				if (belt_labels[i].getButton().getText().contains("Ingredients")) {
					belt_labels[i].update(belt_labels[i].getPlate(), belt_labels[i].getPosition());
				} else {
					String s = "Ingredients";
					
					for (int j = 0; j < belt_labels[i].getPlate().getContents().getIngredients().length; j++) {
						s += String.format("%.2f", belt_labels[i].getPlate().getContents().getIngredients()[j].getAmount());
					}
					belt_labels[i].getButton().setText(s);
				}
			}
		}
		
	}
	
}
