package sushigame.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Plate;
import comp401sushi.RedPlate;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
    private String sushi_type = "Sashimi";
    private Sushi plate_contents;
    double gold_plate_price = 5.00;
    private String plate_color = "Red";
    private int add_position;
    private static String[] sushi_types = {"Sashimi", "Nigiri", "Roll"};
    private static String[] plate_colors = {"Red", "Green", "Blue", "Gold"};
    private JLabel gpp_field_label;
    private JTextField gold_plate_price_field;
   
    JButton make_button;
    JButton ingredients_button;
   
    private IngredientSelectionView ingredient_selector;
   
    public PlayerChefView(int belt_size) {
        add_position= 0;
        listeners = new ArrayList<ChefViewListener>();
 
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
       
        JLabel sushi_cb_label = new JLabel("Sushi Type:");
        sushi_cb_label.setAlignmentX(CENTER_ALIGNMENT);
        add(sushi_cb_label);
        JComboBox<String> sushi_cb = new JComboBox<String>(sushi_types);
        sushi_cb.setName("Sushi_CB");
        sushi_cb.addActionListener(this);
        sushi_cb.setMaximumSize(new Dimension(300, 20));
        add(sushi_cb);
       
        add(Box.createRigidArea(new Dimension(0, 10)));
       
        ingredients_button = new JButton("Add Ingredient(s)");
        ingredients_button.setName("Ing_Button");
        ingredients_button.addActionListener(this);
        ingredients_button.setAlignmentX(CENTER_ALIGNMENT);
        add(ingredients_button);
       
        add(Box.createRigidArea(new Dimension(0, 10)));
       
        JLabel plate_cb_label = new  JLabel("Plate Color: ");
        plate_cb_label.setAlignmentX(CENTER_ALIGNMENT);
        add(plate_cb_label);
        JComboBox<String> plate_cb = new JComboBox<String>(plate_colors);
        plate_cb.setName("Plate_CB");
        plate_cb.addActionListener(this);
        plate_cb.setMaximumSize(new Dimension(300, 20));
        add(plate_cb);
       
        gpp_field_label = new JLabel("Set gold plate price:");
        gpp_field_label.setAlignmentX(CENTER_ALIGNMENT);
        gpp_field_label.setVisible(false);
        add(gpp_field_label);
        gold_plate_price_field = new JTextField(String.format("%.2f", gold_plate_price));
        gold_plate_price_field.setName("GPP_Field");
        gold_plate_price_field.setMaximumSize(new Dimension(100, 20));
        gold_plate_price_field.setVisible(false);
        gold_plate_price_field.addActionListener(this);
        add(gold_plate_price_field);
       
        add(Box.createRigidArea(new Dimension(0, 10)));
       
        JPanel make_button_panel = new JPanel();
        make_button_panel.setLayout(new FlowLayout());
       
        make_button = new JButton("Make");
        make_button.setName("Make_Button");
        make_button.addActionListener(this);
        make_button.setEnabled(false);
        make_button_panel.add(make_button);
       
        make_button_panel.add(new JLabel("at or near postion: "));
       
        Integer[] indices = new Integer[belt_size];
        for(int i = 0; i < belt_size; i++) {
            indices[i] = i;
        }
        JComboBox<Integer> position_cb = new JComboBox<Integer>(indices);
        position_cb.setName("Pos_CB");
        position_cb.addActionListener(this);
        make_button_panel.add(position_cb);
        add(make_button_panel);
       
    }
 
    public void registerChefListener(ChefViewListener cl) {
        listeners.add(cl);
    }
   
   
 
    private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
        for (ChefViewListener l : listeners) {
            l.handleRedPlateRequest(plate_sushi, plate_position);
        }
    }
 
    private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
        for (ChefViewListener l : listeners) {
            l.handleGreenPlateRequest(plate_sushi, plate_position);
        }
    }
 
    private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
        for (ChefViewListener l : listeners) {
            l.handleBluePlateRequest(plate_sushi, plate_position);
        }
    }
   
    private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
        for (ChefViewListener l : listeners) {
            l.handleGoldPlateRequest(plate_sushi, plate_position, price);
        }
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((Component) e.getSource()).getName()) {
        case "Sushi_CB":
            JComboBox<String> scb = (JComboBox<String>) e.getSource();
            sushi_type = (String) scb.getSelectedItem();
            break;
        case "Ing_Button":
            ingredient_selector = new IngredientSelectionView(sushi_type);
            ingredient_selector.pack();
            ingredient_selector.setSushiDestination(this);
            ingredient_selector.setVisible(true);
            make_button.setEnabled(true);
            break;
        case "Plate_CB":
            JComboBox<String> pcb = (JComboBox<String>) e.getSource();
            plate_color = (String) pcb.getSelectedItem();
            if(plate_color == "Gold") {
                gold_plate_price_field.setVisible(true);
                gpp_field_label.setVisible(true);
            } else {
                gold_plate_price_field.setVisible(false);
                gpp_field_label.setVisible(false);
            }
            break;
        case "GPP_Field":
            gold_plate_price = Double.parseDouble(gold_plate_price_field.getText());
            gold_plate_price_field.setBorder(null);
            if (gold_plate_price < 5.0 || gold_plate_price > 10.0) {
                gold_plate_price_field.setBorder(BorderFactory.createLineBorder(Color.RED));
                gold_plate_price = 5.00;
                gold_plate_price_field.setText(String.format("%.2f", gold_plate_price));
                JOptionPane.showMessageDialog(this, "Gold plate price must be between $5.00 and $10.00", "Price error",  JOptionPane.ERROR_MESSAGE);
            }
            break;
        case "Make_Button":
            switch(plate_color) {
            case "Red":
                makeRedPlateRequest(plate_contents, add_position);
                break;
            case "Blue":
                makeBluePlateRequest(plate_contents, add_position);
                break;
            case "Green":
                makeGreenPlateRequest(plate_contents, add_position);
                break;
            case "Gold":
                makeGoldPlateRequest(plate_contents, add_position, gold_plate_price);
                break;
            }
            break;
        case "Pos_CB":
            JComboBox<Integer> pos_cb = (JComboBox<Integer>) e.getSource();
            add_position = (Integer) pos_cb.getSelectedItem();
            break;
        }
    }
    
    public void handleSushi(Sushi sushi) {
        plate_contents = sushi;
    }

 
}

//	private List<ChefViewListener> listeners;
//	private Sushi kmp_roll;
//	private Sushi crab_sashimi;
//	private Sushi eel_nigiri;
//	private int belt_size;
//	
//	public PlayerChefView(int belt_size) {
//		this.belt_size = belt_size;
//		listeners = new ArrayList<ChefViewListener>();
//
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//		JButton sashimi_button = new JButton("Make red plate of crab sashimi at position 3");
//		sashimi_button.setActionCommand("red_crab_sashimi_at_3");
//		sashimi_button.addActionListener(this);
//		add(sashimi_button);
//
//		JButton nigiri_button = new JButton("Make blue plate of eel nigiri at position 8");
//		nigiri_button.setActionCommand("blue_eel_nigiri_at_8");
//		nigiri_button.addActionListener(this);
//		add(nigiri_button);
//
//		JButton roll_button = new JButton("Make gold plate of KMP roll at position 5");
//		roll_button.setActionCommand("gold_kmp_roll_at_5");
//		roll_button.addActionListener(this);
//		add(roll_button);
//
//		kmp_roll = new Roll("KMP Roll", new IngredientPortion[] {new EelPortion(1.0), new AvocadoPortion(0.5), new SeaweedPortion(0.2)});
//		crab_sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
//		eel_nigiri = new Nigiri(Nigiri.NigiriType.EEL);
//	}
//
//	public void registerChefListener(ChefViewListener cl) {
//		listeners.add(cl);
//	}
//
//	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
//		for (ChefViewListener l : listeners) {
//			l.handleRedPlateRequest(plate_sushi, plate_position);
//		}
//	}
//
//	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
//		for (ChefViewListener l : listeners) {
//			l.handleGreenPlateRequest(plate_sushi, plate_position);
//		}
//	}
//
//	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
//		for (ChefViewListener l : listeners) {
//			l.handleBluePlateRequest(plate_sushi, plate_position);
//		}
//	}
//	
//	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
//		for (ChefViewListener l : listeners) {
//			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
//		}
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		switch (e.getActionCommand()) {
//		case "red_crab_sashimi_at_3":
//			makeRedPlateRequest(crab_sashimi, 3);
//			break;
//		case "blue_eel_nigiri_at_8":
//			makeBluePlateRequest(eel_nigiri, 8);
//			break;
//		case "gold_kmp_roll_at_5":
//			makeGoldPlateRequest(kmp_roll, 5, 5.00);
//		}
//	}

