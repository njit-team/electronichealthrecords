package edu.njit.cs684.electronichealthrecords.domain.dbmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
public class Staff {

    @Id
    private String id;
    @Indexed(unique = true)
    @NotNull(message = "StaffId is not sent in request")
    private String staffId;
    @NotNull(message = "StaffType is not sent in request")
    private String staffType;

    public Staff(String staffId, String staffType) {
        this.staffId = staffId;
        this.staffType = staffType;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }
}
