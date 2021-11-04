package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import javafx.scene.layout.Pane;

public class LogMedicationPurchaseController extends ViewController{

    public Pane rootPane;

    //Getter & Setters



    public ViewOperation getOperation() {
        return super.operation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
    }

    public LogMedicationPurchaseController(Pane rootPane) {
        this.rootPane= rootPane;
    }

    public Pane getRootPane() {
        return rootPane;
    }


}
