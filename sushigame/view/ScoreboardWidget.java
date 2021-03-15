package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private JComboBox dropdown;
	private Sort sort;
	
	private enum Sort {BALANCE, CONSUMED, SPOILED};
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		sort = Sort.BALANCE;
		
		String[] options = {"Sort by balance", "sort by consumed", "sort by spoiled"};
		dropdown = new JComboBox(options);
		dropdown.addActionListener(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		add(dropdown, BorderLayout.NORTH);
		display.setText(makeScoreboardHTML());
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

//		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[0];
		
		switch(sort) {
		case BALANCE:
			chefs = sort(new HighToLowBalanceComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
			}
			break;
			
		case CONSUMED:
			chefs = sort(new ConsumedComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " (" + Math.round(c.getConsumed()*100.0)/100.0 + " oz.) <br>";
			}
			break;
		case SPOILED:
			chefs = sort(new SpoiledComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " (" + Math.round(c.getSpoiled()*100.0)/100.0 + " oz.) <br>";
			}
			break;
		}
		
		return sb_html;

	}
	
	public Chef[] sort(Comparator<Chef> comp) {
		Chef[] opponent_chefs = game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i = 1; i < chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.parallelSort(chefs, comp);
		return chefs;
	}

	public void refresh() {
		display.setText(makeScoreboardHTML());		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
