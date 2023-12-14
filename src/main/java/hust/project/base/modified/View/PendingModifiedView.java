package hust.project.base.modified.View;
import hust.project.base.modified.Model.AttendanceRecordDTO;
import hust.project.base.modified.Model.AttendanceRecordRepository;
import hust.project.base.modified.Model.ModifiedDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.util.Callback;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static hust.project.base.constants.MetricsConstants.MAIN_WIDTH;
public class PendingModifiedView extends VBox{
    private  TableView<ModifiedDTO> requestTable;
    private AttendanceRecordRepository attendanceRecordRepository;

    public  PendingModifiedView(AttendanceRecordRepository repo){
        this.attendanceRecordRepository = repo;
        setSpacing(20);
        setPrefWidth(MAIN_WIDTH * 0.8);
        Label label = new Label("Danh sách các yêu cầu đang chờ");
        label.setStyle("-fx-font-size: 20px;");
        getChildren().addAll(label, createRequestTable());
    }
    private TableView<ModifiedDTO> createRequestTable() {
        TableView<ModifiedDTO> requestTable = new TableView<>();
        if (attendanceRecordRepository == null) {
            System.out.println("attendanceRecordRepository is null!");
            return null; // or handle the null scenario appropriately
        }
        List<ModifiedDTO> modifiedDTOList = attendanceRecordRepository.getAllModifiedDTOs();

        TableColumn<ModifiedDTO, Number> sttCol = new TableColumn<>("STT");
        sttCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(requestTable.getItems().indexOf(column.getValue()) + 1));

        TableColumn<ModifiedDTO, String> maYeuCauCol = new TableColumn<>("Mã yêu cầu");
        maYeuCauCol.setCellValueFactory(new PropertyValueFactory<>("recordId"));

        TableColumn<ModifiedDTO, String> loaiYeuCauCol = new TableColumn<>("Loại yêu cầu");
        loaiYeuCauCol.setCellValueFactory(new PropertyValueFactory<>("attendanceType"));
        TableColumn<ModifiedDTO, String> trangThaiCol = new TableColumn<>("Trạng thái");
        trangThaiCol.setCellValueFactory(new PropertyValueFactory<>("requestStatus"));
        TableColumn<ModifiedDTO, String> nhanVienCol = new TableColumn<>("Nhân viên");
        nhanVienCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        TableColumn<ModifiedDTO, Timestamp> ngayTaoCol = new TableColumn<>("Ngày tạo");
        ngayTaoCol.setCellValueFactory(new PropertyValueFactory<>("timestampbefore"));

        TableColumn<ModifiedDTO, Timestamp> ngayDuyetCol = new TableColumn<>("Ngày duyệt");
        ngayDuyetCol.setCellValueFactory(new PropertyValueFactory<>("timestampafter"));

        TableColumn<ModifiedDTO, String> tacVuCol = new TableColumn<>("Tác vụ");
        tacVuCol.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        TableColumn<ModifiedDTO, Void> actionCol = new TableColumn<>("Action");
//        requestTable.getColumns().add(actionCol);
        actionCol.setCellFactory(cellFactory);

        requestTable.getColumns().addAll(sttCol, maYeuCauCol, loaiYeuCauCol, nhanVienCol, trangThaiCol, ngayTaoCol, ngayDuyetCol, tacVuCol, actionCol);
        requestTable.setPrefWidth(MAIN_WIDTH * 0.8); // Ensure MAIN_WIDTH is defined
        requestTable.setPrefHeight(400);
        requestTable.setItems(FXCollections.observableArrayList(modifiedDTOList));
        return requestTable;
    }


        public List<ModifiedDTO> getModifiedDTOs(){
        return new ArrayList<>();
    }

    Callback<TableColumn<ModifiedDTO, Void>, TableCell<ModifiedDTO, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<ModifiedDTO, Void> call(final TableColumn<ModifiedDTO, Void> param) {
            final TableCell<ModifiedDTO, Void> cell = new TableCell<>() {

                private final Button btn = new Button("READ");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        ModifiedDTO data = getTableView().getItems().get(getIndex());
                        openModidiedView(data);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    };

    private void openModidiedView(ModifiedDTO data) {
        ModifiedView modifiedView = new ModifiedView();
        modifiedView.display(data);
    }
    public List<AttendanceRecordDTO> getAttendanceRecordDTOs(){
        return new ArrayList<>();
    }

}
