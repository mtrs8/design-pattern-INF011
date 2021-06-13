package application;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DebugGraphics;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class ViewMenu extends JFrame {
	
    @SuppressWarnings("rawtypes")
	private DefaultListModel modelChoosen;
    @SuppressWarnings("rawtypes")
	private DefaultListModel modelList;
    private JButton addIngredientes;
    private JComboBox<String> comboBoxTypePizzas;
    private JList<String> panelEscolhido;
    private JList<String> panelDisponiveis;
    private JButton downButton;
    private JLabel ingredientesDisponiveis;
    private JLabel ingredientesEscolhidos;
    private JButton prepararPizza;
    private JButton removeIngredients;
    private JLabel typePizza;
    private JButton upButton;
    private JScrollPane scrollIngredientesDisponiveis;
    private JScrollPane scrollIngredientesEscolhidos; 

	private static final long serialVersionUID = 1L;
	
	public ViewMenu() {
        initComponents();
    }
    
    @SuppressWarnings("rawtypes")
	private void initComponents() {

        modelChoosen = new DefaultListModel();
        modelList = new DefaultListModel();
        scrollIngredientesDisponiveis = new JScrollPane();
        panelDisponiveis = new JList<>();
        scrollIngredientesEscolhidos = new JScrollPane();
        panelEscolhido = new JList<>();
        upButton = new JButton();
        downButton = new JButton();
        addIngredientes = new JButton();
        removeIngredients = new JButton();
        comboBoxTypePizzas = new JComboBox<>();
        typePizza = new JLabel();
        ingredientesDisponiveis = new JLabel();
        ingredientesEscolhidos = new JLabel();
        prepararPizza = new JButton();
        
        prepararPizza.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		// TODO Auto-generated method stub
        		selectionButtonPressed();
        	}
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pizza Decorator");
        setBackground(new Color(204, 204, 255));

        panelDisponiveis.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        panelDisponiveis.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        scrollIngredientesDisponiveis.setViewportView(panelDisponiveis);

        scrollIngredientesEscolhidos.setViewportView(panelEscolhido);

        upButton.setFont(new Font("Tahoma", 0, 14));
        upButton.setText("Subir");

        downButton.setFont(new Font("Tahoma", 0, 14));
        downButton.setText("Descer");

        addIngredientes.setFont(new Font("Tahoma", 0, 18));
        addIngredientes.setText("<");

        removeIngredients.setFont(new Font("Tahoma", 0, 18));
        removeIngredients.setText(">");

        prepararPizza.setFont(new Font("Tahoma", 0, 14)); 
        prepararPizza.setText("Preparar");

        comboBoxTypePizzas.setFont(new Font("Tahoma", 0, 14));
        comboBoxTypePizzas.setToolTipText("");

        typePizza.setFont(new Font("Tahoma", 1, 14)); 
        typePizza.setText("Tipos de Pizzas:");

        ingredientesDisponiveis.setFont(new Font("Tahoma", 1, 14)); 
        ingredientesDisponiveis.setText("Ingredientes Disponíveis");

        ingredientesEscolhidos.setFont(new Font("Tahoma", 1, 14));
        ingredientesEscolhidos.setText("Ingredientes Escolhidos");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prepararPizza, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollIngredientesDisponiveis, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(addIngredientes, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(removeIngredients, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(typePizza)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxTypePizzas, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                            .addComponent(ingredientesDisponiveis))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollIngredientesEscolhidos, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(downButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(upButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(36, 36, 36))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ingredientesEscolhidos)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(upButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(downButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxTypePizzas, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addComponent(typePizza, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ingredientesDisponiveis, GroupLayout.Alignment.TRAILING)
                            .addComponent(ingredientesEscolhidos, GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(scrollIngredientesDisponiveis, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollIngredientesEscolhidos, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(removeIngredients, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(addIngredientes, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)))))
                .addGap(18, 18, 18)
                .addComponent(prepararPizza, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }                                                                                                                                                                                    

    public JButton getUpIngredients() {return upButton;}

    public JButton getDownIngredients() {return downButton;}

    public JButton getRemoveIngredients() {return removeIngredients;}

    public JButton getAddIngredients() {return addIngredientes;}

    public JButton getPreparePizza() {return prepararPizza;}
    
    public JList<String> getDecoratorAvailable() {return panelDisponiveis;}
    
    public JList<String> getDecoratorChosen() {return panelEscolhido;}

    @SuppressWarnings("rawtypes")
	public DefaultListModel getModelChoosen() {return modelChoosen;}

    @SuppressWarnings("rawtypes")
	public DefaultListModel getModelList() {return modelList;}

    public void setModelChoosen(@SuppressWarnings("rawtypes") DefaultListModel modelChoosen) {this.modelChoosen = modelChoosen;}

    public void setModelList(@SuppressWarnings("rawtypes") DefaultListModel modelList) {this.modelList = modelList;}

    public JComboBox<String> getComboBoxTypePizzas() {return comboBoxTypePizzas;}
    
    public void selectionButtonPressed() {
    	
    }
    
}
