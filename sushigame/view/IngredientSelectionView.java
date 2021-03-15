package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComponent;

import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;

public class IngredientSelectionView extends JFrame implements ActionListener {
	
	 private String sushi_type;
	    private static String[] single_ings = {"Tuna", "Yellowtail", "Eel", "Crab", "Shrimp"};
	    private static String[] roll_ings = {"Avocado", "Crab", "Eel", "Rice", "Seaweed", "Shrimp", "Tuna", "Yellowtail"};
	    private JPanel[] ing_panels = new JPanel[roll_ings.length];
	    private String roll_name = "Sam's roll";
	    private JTextField roll_name_field;
	    private ArrayList<IngredientPortion> roll_contents = new ArrayList<IngredientPortion>(0);
	    private PlayerChefView sushi_destination;
	    Sashimi.SashimiType sashimi_type = Sashimi.SashimiType.TUNA;
	    Nigiri.NigiriType nigiri_type = Nigiri.NigiriType.TUNA;
	   
	 
	    public IngredientSelectionView(String sushi_type) {
	        this.sushi_type = sushi_type;
	        setName("Ingredient Selector");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        if(sushi_type.equals("Roll")) {
	            add(RollContentPane());
	        } else {
	            add(SingleIngsContentPane());
	        }
	    }
	 
	    private JPanel SingleIngsContentPane() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	        JLabel ingredient_prompt =new JLabel("Select one ingredient for your " + sushi_type + ":");
	        ingredient_prompt.setAlignmentX(CENTER_ALIGNMENT);
	        panel.add(ingredient_prompt);
	        JComboBox<String> single_ings_cb = new JComboBox<String>(single_ings);
	        single_ings_cb.setName("Single_Ings_CB");
	        single_ings_cb.addActionListener(this);
	        panel.add(single_ings_cb);
	        JButton single_ing_button = new JButton("Select");
	        single_ing_button.setName("Single_Ing_Button");
	        single_ing_button.addActionListener(this);
	        panel.add(single_ing_button);
	        return panel;
	    }
	 
	    private JPanel RollContentPane() {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	        roll_name_field = new JTextField(roll_name);
	        roll_name_field.setName("Roll_Name_Field");
	        roll_name_field.setMaximumSize(new Dimension(300, 20));
	        roll_name_field.addActionListener(this);
	        panel.add(new JLabel("Roll Name:"));
	        panel.add(roll_name_field);
	        JPanel titles_panel = new JPanel();
	        titles_panel.setLayout(new GridLayout(1, 2));
	        titles_panel.add(new JLabel("Ingredient"));
	        titles_panel.add(new JLabel("Amount"));
	        panel.add(titles_panel);
	        for(int i = 0; i < roll_ings.length; i++) {
	            JCheckBox selectable_ing = new JCheckBox(roll_ings[i]);
	            selectable_ing.setName(roll_ings[i]);
	            JTextField ing_amount_field = new JTextField(String.format("%.2f", 0.0));
	            ing_amount_field.setMaximumSize(new Dimension(100, 20));
	            ing_panels[i] = new JPanel();
	            ing_panels[i].setLayout(new GridLayout(1, 2));
	            ing_panels[i].add(selectable_ing);
	            ing_panels[i].add(ing_amount_field);
	            panel.add(ing_panels[i]);
	        }
	        JButton make_roll_button = new JButton("Make Roll");
	        make_roll_button.setName("Make_Roll_Button");
	        make_roll_button.addActionListener(this);
	        panel.add(make_roll_button);
	        return panel;      
	    }
	 
	    @SuppressWarnings("unchecked")
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String component_name = ((JComponent) e.getSource()).getName();
	        switch(component_name) {
	        case "Single_Ings_CB":
	            JComboBox<String> sicb = (JComboBox<String>) e.getSource();
	            String ing = single_ings[sicb.getSelectedIndex()];
	            switch(sushi_type) {
	            case "Sashimi":
	                switch(ing) {
	                case "Tuna":
	                    sashimi_type = Sashimi.SashimiType.TUNA;
	                    break;
	                case "Yellowtail":
	                    sashimi_type = Sashimi.SashimiType.YELLOWTAIL;
	                    break;
	                case "Eel":
	                    sashimi_type = Sashimi.SashimiType.EEL;
	                    break; 
	                case "Crab":
	                    sashimi_type = Sashimi.SashimiType.CRAB;
	                    break;
	                case "Shrimp":
	                    sashimi_type = Sashimi.SashimiType.SHRIMP;
	                    break;
	                }
	                break;
	            case "Nigiri":
	                switch(ing) {
	                case "Tuna":
	                    nigiri_type = Nigiri.NigiriType.TUNA;
	                    break;
	                case "Yellowtail":
	                    nigiri_type = Nigiri.NigiriType.YELLOWTAIL;
	                    break;
	                case "Eel":
	                    nigiri_type = Nigiri.NigiriType.EEL;
	                    break; 
	                case "Crab":
	                    nigiri_type = Nigiri.NigiriType.CRAB;
	                    break;
	                case "Shrimp":
	                    nigiri_type = Nigiri.NigiriType.SHRIMP;
	                    break;
	                }
	                break;
	            }
	            break;
	        case "Single_Ing_Button":
	            switch(sushi_type) {
	            case "Sashimi":
	                deliverSushi(new Sashimi(sashimi_type));
	                break;
	            case "Nigiri":
	                deliverSushi(new Nigiri(nigiri_type));
	                break;
	            }
	            break;
	        case "Roll_Name_Field":
	            roll_name = roll_name_field.getText();
	            break;
	        case "Make_Roll_Button":
	            boolean makeable = true;
	            for(int i = 0; i < ing_panels.length; i++) {
	                JCheckBox ing_cb = (JCheckBox) ing_panels[i].getComponents()[0];
	                if(ing_cb.isSelected()) {
	                    JTextField amount_tf = (JTextField) ing_panels[i].getComponents()[1];
	                    amount_tf.setBorder(null);
	                    String ing_amount_string = amount_tf.getText();
	                    double ing_amount = Double.parseDouble(ing_amount_string);
	                    if (ing_amount > 0 && ing_amount <= 1.5) {
	                        switch(ing_cb.getName()) {
	                        case "Avocado":
	                            roll_contents.add(new AvocadoPortion(ing_amount));
	                            break;
	                        case "Crab":
	                            roll_contents.add(new CrabPortion(ing_amount));
	                            break;
	                        case "Eel":
	                            roll_contents.add(new EelPortion(ing_amount));
	                            break;
	                        case "Rice":
	                            roll_contents.add(new RicePortion(ing_amount));
	                            break;
	                        case "Seaweed":
	                            roll_contents.add(new SeaweedPortion(ing_amount));
	                            break;
	                        case "Shrimp":
	                            roll_contents.add(new ShrimpPortion(ing_amount));
	                            break;
	                        case "Tuna":
	                            roll_contents.add(new TunaPortion(ing_amount));
	                            break;
	                        case "Yellowtail":
	                            roll_contents.add(new YellowtailPortion(ing_amount));
	                            break;
	                        }
	                    } else {
	                        amount_tf.setBorder(BorderFactory.createLineBorder(Color.RED));
	                        JOptionPane.showMessageDialog(this, "Ingredient amount must be greater than 0 oz but less than or equal to 1.5 oz", "Amount error",  JOptionPane.ERROR_MESSAGE);
	                        makeable = false;
	                    }
	                }
	            }
	            if (roll_contents.size() > 0 && makeable) {
	                IngredientPortion[] roll_contents_arr = new IngredientPortion[roll_contents.size()];
	                roll_contents_arr = roll_contents.toArray(roll_contents_arr);
	                deliverSushi(new Roll(roll_name, roll_contents_arr));
	            }
	            break;
	        }
	    }
	 
	    public void setSushiDestination(PlayerChefView sushi_destination) {
	        this.sushi_destination = sushi_destination;
	    }
	 
	    private void deliverSushi(Sushi sushi) {
	        sushi_destination.handleSushi(sushi);
	        this.dispose();
	    }
	 
	}

