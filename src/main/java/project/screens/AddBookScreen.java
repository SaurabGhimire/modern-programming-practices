package project.screens;

import project.business.*;
import project.dataaccess.Auth;
import project.dataaccess.DataAccess;
import project.dataaccess.DataAccessFacade;
import project.project.utils.validation.DialogUtils;
import project.project.utils.validation.ValidationUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AddBookScreen extends Routes implements Component{
    private JPanel contentPane;
    private static AddBookScreen instance;
    private JTextField titleTextField;
    private JTextField isbnNumTextField;
    private JComboBox borrowTimeDropdown;
    private JComboBox authorDropdown;

    private JList selectedAuthorList;
    private JLabel titleLabel;
    private JLabel isbnNumLabel;
    private JLabel borrowTimeLabel;
    private JLabel authorLabel;
    private JButton addAuthorButton;
    private JPanel inner;
    private JButton saveButton;

    private StringBuilder validationMessage;


    private AddBookScreen() {
        validationMessage= new StringBuilder();
        populateAuthors();
        populateBorrowTimeDropdown();
        authorDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Author selectedItem = (Author) authorDropdown.getSelectedItem();
                DefaultListModel<Author> model = (DefaultListModel<Author>) selectedAuthorList.getModel(); // Get the model associated with the JList
                if(model.contains(selectedItem)){
                    model.removeElement(selectedItem);
                } else{
                    model.addElement(selectedItem);
                }
                if(model.isEmpty()){
                    authorDropdown.setSelectedIndex(-1);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validationMessage.setLength(0);
                validateInput();
                if(!validationMessage.isEmpty()){
                    DialogUtils.showValidationMessage(validationMessage.toString());
                    return;
                }
                List<Author> authors = new ArrayList<>();
                BorrowTimeEnum borrowTime = (BorrowTimeEnum) borrowTimeDropdown.getSelectedItem();
                DefaultListModel<Author> model = (DefaultListModel<Author>) selectedAuthorList.getModel();
                Object[] array = model.toArray();
                for(Object item: array){
                    authors.add((Author) item);
                }
                Book book = new Book(
                        isbnNumTextField.getText(),
                        titleTextField.getText(),
                        borrowTime.getValue(),
                        authors
                );
                SystemController systemController = new SystemController();
                systemController.addNewBook(book);
            }
        });
        addAuthorButton.addActionListener(e -> navigateTo(SCREENS.AddAuthor));
    }

    @Override
    public JPanel getMainPanel() {
        return contentPane;
}

    @Override
    public void render() {
        Dashboard dash = Dashboard.getInstance();
        dash.setPageTitle("Add a book");
        dash.setPageButtonVisibility(false);
        dash.repaintButtons(Routes.SCREENS.Books);
        JPanel mainPanel = dash.getInnerPanel();
        mainPanel.removeAll();
        mainPanel.add(getMainPanel());
        thread.setContentPane(dash.getMainPanel());
        refresh();
    }

    public static AddBookScreen getInstance() {
//        if(instance == null){
//            instance = new AddBookScreen();
//        }
        instance = new AddBookScreen();
        return instance;
    }

    public void populateBorrowTimeDropdown(){
        DefaultComboBoxModel<BorrowTimeEnum>  model = (DefaultComboBoxModel<BorrowTimeEnum>) borrowTimeDropdown.getModel();
        int index = 0;
        for(BorrowTimeEnum borrowTime: BorrowTimeEnum.values()){
            model.insertElementAt(borrowTime, index++);
        }
    }

    public void populateAuthors(){
        SystemController controller = new SystemController();
        HashMap<String, Author> authors = controller.getAllAuthors();
        int index = 0;
        DefaultComboBoxModel<Author> model = (DefaultComboBoxModel<Author>) authorDropdown.getModel();
        for(Author author: authors.values()){
            model.insertElementAt(author,index++);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here


    }

    void  validateInput(){
        validateEmptyFields();
        validateBorrowTime();
        validateAuthors();
    }

    void validateBorrowTime(){
        Object item = borrowTimeDropdown.getSelectedItem();
        if(item == null){
            validationMessage.append("Select a borrow time\n");
        }
    }

    void validateAuthors(){
        DefaultListModel<Author> model = (DefaultListModel<Author>) selectedAuthorList.getModel();
        if(model.isEmpty()){
            validationMessage.append("Select at least one authors\n");
        }
    }

    void validateEmptyFields(){
        ValidationUtils.validateField(titleTextField, titleLabel, validationMessage);
        ValidationUtils.validateField(isbnNumTextField, isbnNumLabel, validationMessage);
        ValidationUtils.validateISBN(isbnNumTextField, isbnNumLabel, validationMessage);

    }
}